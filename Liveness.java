import pascal.analysis.DepthFirstAdapter;
import pascal.node.*;

import java.util.LinkedList;

/**
 * Created by maximilian on 01.02.14.
 */
public class Liveness extends DepthFirstAdapter{

    private LinkedList graph;
    private int blockCounter;
    private int currentBlock;
    private FlowGraph blocks;



    class FlowGraph{
        private String name;
        private LinkedList<FlowGraph> edges;
        private LinkedList<String> def;
        private LinkedList<String> use;
        private LinkedList<String> in;
        private LinkedList<String> out;

        private boolean flag;

        public FlowGraph(String name){
            this.name = name;
            edges = new LinkedList<FlowGraph>();
            flag = false;
            def = new LinkedList<String>();
            use = new LinkedList<String>();
            in = new LinkedList<String>();
            out = new LinkedList<String>();



        }
        public String getName(){
            return this.name;
        }

        public FlowGraph getBlockName (String blockName){
            if(this.name.equalsIgnoreCase(blockName)) return this;

            for (int i = 0; i < edges.size(); i++){
                if(edges.get(i).getName().equals(blockName)){
                    return edges.get(i);
                }
                if (edges.get(i).getBlockName(blockName)!= null){
                    return edges.get(i).getBlockName(blockName);
                }


            }
            return null;

        }

        //add to the in set all variables that were used and defined
        public void setIn(){
            flag = false;
            for (int i = 0; i < this.use.size(); i++){
                if(!(this.in.contains(this.use.get(i)))){
                    this.in.add(this.use.get(i));
                    flag= false;
                }
            }
            LinkedList<String> outDef = this.getOutDef();

            for (int i = 0; i < outDef.size(); i++){
                if(!(this.in.contains(outDef.get(i)))){
                    this.in.add(outDef.get(i));
                    flag = true;
                }
            }
        }

        //add to the out set all variables which went out and remove all that were defined
        public LinkedList<String> getOutDef(){
            LinkedList<String> outDef= new LinkedList<String>();

            for(int i = 0; i < this.out.size(); i++){
                outDef.add(this.out.get(i));
            }

            for(int i=0; i < this.def.size(); i++){
                outDef.remove(this.def.get(i));
            }

            return outDef;

        }


        public void setOut(){
            for (int i = 0; i < edges.size(); i++){
                flag = false;
                for(int j = 0; j < edges.get(i).in.size(); j++){
                    if( !(this.out.contains(edges.get(i).in.get(j)))){
                        this.out.add(edges.get(i).in.get(j));
                        flag = true;

                    }
                }

            }

        }

        public boolean getFlag(){
            return this.flag;
        }

        public void add(FlowGraph g){
            this.edges.add(g);
        }



    }

    public Liveness(){
        blockCounter = 0;
        blocks = new FlowGraph("block" + blockCounter);

    }


    public void getLiveness(){
        boolean changed = true;
        FlowGraph current;

        while (changed){
            changed = false;
            for (int i = 0; i <= blockCounter; i++){
                current = blocks.getBlockName("block"+i);
                current.setIn();
                if(current.getFlag()) changed = true;

                current.setOut();
                if (current.getFlag())changed = true;
            }
        }

        int registerNumber = 0;
        for (int i = 0; i < blockCounter; i++){
            current = blocks.getBlockName("block"+i);
            System.out.println(current.getName()+ ":\nIn: " +current.in.toString()+"\nout: "+ current.out.toString());
            if (registerNumber < current.out.size()) registerNumber= current.out.size();
        }
        System.out.println(registerNumber+" registers are needed");
    }

    //why not use the sablecc classes? They work here too :D
     @Override
    public void caseAAssignmentAst (AAssignmentAst node){
        FlowGraph current = blocks.getBlockName("block"+blockCounter);
        String id = node.getIdentifier().toString().toLowerCase().replaceAll("\\s+", "");
        if (! current.def.contains(id)) current.def.add(id);
        node.getAst().apply(this);
    }

    @Override
    public void caseAIdentifierAst(AIdentifierAst node){
        FlowGraph current = blocks.getBlockName("block"+blockCounter);
        String id = node.getIdentifier().toString().toLowerCase().replace("\\s+","");
        if(! current.use.contains(id)) current.use.add(id);
    }

    @Override
    public void caseAClosedIfStatementAst(AClosedIfStatementAst node){
        FlowGraph current = blocks.getBlockName("block"+blockCounter);

        if(!(current.use.isEmpty() && current.def.isEmpty())) blockCounter++;

        FlowGraph leftBlock = new FlowGraph("block"+blockCounter);
        current.add(leftBlock);
        node.getLeft().apply(this);
        blockCounter++;

        FlowGraph rightBlock = new FlowGraph("block"+blockCounter);
        current.add(rightBlock);
        blockCounter++;

        FlowGraph endBlock = new FlowGraph("block"+blockCounter);
        current.add(endBlock);
        rightBlock.add(endBlock);
    }

    @Override
    public void caseAOpenIfStatementAst(AOpenIfStatementAst node){
        FlowGraph current = blocks.getBlockName("block"+blockCounter);

        if(!(current.use.isEmpty() && current.def.isEmpty())) blockCounter++;

        FlowGraph leftBlock = new FlowGraph("block"+blockCounter);
        current.add(leftBlock);
        node.getLeft().apply(this);
        blockCounter++;

        FlowGraph rightBlock = new FlowGraph("block"+blockCounter);
        current.add(rightBlock);
        blockCounter++;

        FlowGraph endBlock = new FlowGraph("block"+blockCounter);
        current.add(endBlock);
        rightBlock.add(endBlock);
    }

    @Override
    public void caseAOpenIfElseStatementAst(AOpenIfElseStatementAst node){
        FlowGraph current = blocks.getBlockName("block"+blockCounter);

        if (!(current.use.isEmpty() && current.def.isEmpty())) blockCounter++;

        FlowGraph leftBlock = new FlowGraph("block"+blockCounter);
        current.add(leftBlock);
        node.getLeft().apply(this);
        blockCounter++;

        FlowGraph midBlock = new FlowGraph("block"+blockCounter);
        current.add(midBlock);
        node.getMid().apply(this);
        blockCounter++;

        FlowGraph rightBlock = new FlowGraph("block"+blockCounter);
        current.add(rightBlock);
        node.getRight().apply(this);
        blockCounter++;

        FlowGraph endBlock = new FlowGraph("block"+blockCounter);
        midBlock.add(endBlock);
        rightBlock.add(endBlock);
    }

    @Override
    public void caseAOpenWhileStatementAst (AOpenWhileStatementAst node){
        FlowGraph current = blocks.getBlockName("block"+blockCounter);
        if (! (current.use.isEmpty() && current.def.isEmpty()))blockCounter++;

        FlowGraph leftBlock = new FlowGraph("block"+blockCounter);
        current.add(leftBlock);
        node.getLeft().apply(this);
        blockCounter++;

        FlowGraph rightBlock = new FlowGraph("block"+blockCounter);
        current.add(rightBlock);
        node.getRight().apply(this);
        blockCounter++;

        FlowGraph endBlock = new FlowGraph("block"+blockCounter);
        current.add(endBlock);

        rightBlock.add(endBlock);
    }

    @Override
    public void caseAClosedWhileStatementAst(AClosedWhileStatementAst node){
        FlowGraph current = blocks.getBlockName("block"+blockCounter);
        if (! (current.use.isEmpty() && current.def.isEmpty()))blockCounter++;

        FlowGraph leftBlock = new FlowGraph("block"+blockCounter);
        current.add(leftBlock);
        node.getLeft().apply(this);
        blockCounter++;

        FlowGraph rightBlock = new FlowGraph("block"+blockCounter);
        current.add(rightBlock);
        node.getRight().apply(this);
        blockCounter++;

        FlowGraph endBlock = new FlowGraph("block"+blockCounter);
        current.add(endBlock);

        rightBlock.add(endBlock);
    }
}

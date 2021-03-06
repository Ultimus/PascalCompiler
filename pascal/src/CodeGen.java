package pascal.src;

import pascal.analysis.DepthFirstAdapter;
import pascal.node.*;

import java.util.HashMap;

/**
 * Created by maximilian on 17.01.14.
 * Creates Code for Yasmin, Yasmin loves code, like all bitches
 */
public class CodeGen extends DepthFirstAdapter{

    private String code = "";
    private int labels;
    private int stackpointer = 1;
    private int comparePointer;

    private HashMap<String, Integer> symbols = new HashMap<String, Integer>(); // saves the number for the variable
    private HashMap<String,String> types = new HashMap<String, String>();
    private int labelCounter;


    public CodeGen(HashMap<String,String> symbols){
        types = symbols; // works, not sure why, but idea told me so, i ll figure out after a good sleep

    }

    public HashMap<String,Integer> getSymbols(){
        return symbols;
    }




    public int getStackpointer(){
        return stackpointer;
    }

    public String getCode(){
        return code;
    }

    //lets print some things
    public void caseAWritelnAst(AWritelnAst node){
        code += "\tgetstatic java/lang/System/out Ljava/io/PrintStream; \n";
        node.getAst().apply(this);
        code += "\tinvokevirtual java/io/PrintStream/println()V\n";
        stackpointer++;
    }

    //lets calculate some things
    @Override
    public void outAPlusAst(APlusAst node){
        code += "\tiadd\n";
        stackpointer--;
    }

    @Override
    public void outAMinusAst(AMinusAst node){
        code += "\tisub\n";
        stackpointer--;
    }

    @Override
    public void outAMultAst(AMultAst node){
        code += "\timul\n";
        stackpointer--;
    }

    @Override
    public void outADivAst(ADivAst node){
        code += "\tidiv\n";
        stackpointer--;
    }

    @Override
    public void outAModAst(AModAst node){
        code += "\tirem\n";
    }

    @Override
    public void outAUnMinusAst(AUnMinusAst node){
        code += "\tineg\n";
    }



    //lets compare some things
    @Override
    public void outAAndAst(AAndAst node){
        code += "\tiand\n";
        stackpointer--;
    }

    @Override
    public void outAOrAst(AOrAst node){
        code += "\tior\n";
        stackpointer--;
    }

    @Override
    public void outAXorAst(AXorAst node){
        code += "\tixor\n";
        stackpointer--;
    }

    @Override
    public void outANotAst(ANotAst node){
        //crap... i need to put up a hell of a work for just this easy thing
        int temp = labelCounter++;
        code += "\tifeq LabelNot"+temp+"\n\tbipush 0\n\tgoto LabelNotEnd"+temp+"\nLabelNot"+temp+":\nbipush 1\nLabelNotEnd"+temp+":\n";
    }


    //lets read a number
    @Override
    public void outANumberAst(ANumberAst node){
        int number = Integer.parseInt(node.getNumber().toString());
        code += "\tldc "+number+"\n";
        stackpointer++;
    }

    //lets read a boolean
    @Override
    public void outATrueAst(ATrueAst node){
        code += "\tbipush 1\n";
        stackpointer++;
    }

    @Override
    public void outAFalseAst(AFalseAst node){
        code += "\tbipush 0\n";
        stackpointer++;
    }


    //lets compare some thing

    @Override
    public void outAGreaterAst(AGreaterAst node){
        code += "\tifgt LabelTrue"+(comparePointer++)+"\n";
        stackpointer--;
    }

    @Override
    public void outAGreaterEqAst(AGreaterEqAst node){
        code += "\tifge LabelTrue"+(comparePointer++)+"\n";
        stackpointer--;
    }

    @Override
    public void outALessAst(ALessAst node){
        code += "\tiflt LabelTrue"+(comparePointer++)+"\n";
        stackpointer--;
    }

    @Override
    public void outALessEqAst(ALessEqAst node){
        code += "\tifle LabelTrue"+(comparePointer++)+"\n";
        stackpointer--;
    }

    @Override
    public void outAEqualAst(AEqualAst node){
        code += "\tifeq LabelTrue" +(comparePointer++)+"\n";
        stackpointer--;
    }

    @Override
    public void outAUnequalAst(AUnequalAst node){
        code += "\tifne LabelTrue"+(comparePointer++)+"\n";
        stackpointer--;
    }


}


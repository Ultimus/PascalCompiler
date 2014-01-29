
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
    private int comparePointer;
    private int breakPointer;

    private HashMap<String, Integer> symbols = new HashMap<String, Integer>(); // saves the number for the variable
    private HashMap<String,String> types = new HashMap<String, String>();
    private int labelCounter;


    public CodeGen(HashMap<String,String> symbols){
        types = symbols; // works, not sure why, but idea told me so, i ll figure out after a good sleep

    }

    public HashMap<String,Integer> getSymbols(){
        return symbols;
    }


    public String getCode(){
        return code;
    }

    //lets print some things
    public void caseAWritelnAst(AWritelnAst node){
        code += "\tgetstatic java/lang/System/out Ljava/io/PrintStream; \n";
        node.getAst().apply(this);
        code += "\tinvokevirtual java/io/PrintStream/println()V\n";
    }

    //lets calculate some things
    @Override
    public void outAPlusAst(APlusAst node){
        code += "\tiadd\n";
    }

    @Override
    public void outAMinusAst(AMinusAst node){
        code += "\tisub\n";
    }

    @Override
    public void outAMultAst(AMultAst node){
        code += "\timul\n";
    }

    @Override
    public void outADivAst(ADivAst node){
        code += "\tidiv\n";
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
    }

    @Override
    public void outAOrAst(AOrAst node){
        code += "\tior\n";
    }

    @Override
    public void outAXorAst(AXorAst node){
        code += "\tixor\n";
    }

    @Override
    public void outANotAst(ANotAst node){
        //crap... i need to put up a hell of a work for just this easy thing
        int temp = labelCounter++;
        code += "\tifeq LabelNot"+temp+"\n\tbipush 0\n\tgoto LabelNotEnd"+temp+"\nLabelNot"+temp+":\nbipush 1\nLabelNotEnd"+temp+":\n";
    }


    //lets read a number
    @Override
    public void caseANumberAst(ANumberAst node){
        int number = Integer.parseInt(node.getNumber().toString());
        code += "\tldc "+number+"\n";
    }

    //lets read a boolean
    @Override
    public void caseATrueAst(ATrueAst node){
        code += "\tbipush 1\n";
    }

    @Override
    public void caseAFalseAst(AFalseAst node){
        code += "\tbipush 0\n";
    }


    //lets compare some thing

    @Override
    public void outAGreaterAst(AGreaterAst node){
        code += "\tifgt LabelTrue"+(comparePointer++)+"\n";
    }

    @Override
    public void outAGreaterEqAst(AGreaterEqAst node){
        code += "\tifge LabelTrue"+(comparePointer++)+"\n";
    }

    @Override
    public void outALessAst(ALessAst node){
        code += "\tiflt LabelTrue"+(comparePointer++)+"\n";
    }

    @Override
    public void outALessEqAst(ALessEqAst node){
        code += "\tifle LabelTrue"+(comparePointer++)+"\n";
    }

    @Override
    public void outAEqualAst(AEqualAst node){
        code += "\tifeq LabelTrue" +(comparePointer++)+"\n";
    }

    @Override
    public void outAUnequalAst(AUnequalAst node){
        code += "\tifne LabelTrue"+(comparePointer++)+"\n";
    }


    //more difficult stuff now

    @Override
    public void caseAAssignmentAst(AAssignmentAst node){
        node.getAst().apply(this);
        code += "\tistore "+symbols.get(node.getIdentifier().toString().toLowerCase().replaceAll("\\s+",""))+"\n";
    }

    @Override
    public void outAIdentifierAst(AIdentifierAst node){
        String identifier = node.getIdentifier().toString().toLowerCase().replaceAll("\\s+","");
        Node parent = node.parent();
        String name = parent.getClass().getSimpleName().replaceAll("\\s+", "");
        boolean flag =true;
        while (!name.equals("aprogramstartast")){
            if(name.equals("adeclarationsast"))   //if variable is declared i will not load it
                flag = false;
            parent = parent.parent();
            name = parent.getClass().getSimpleName().replaceAll("\\s+","");

        }
        if(flag){
            code +="\tiload "+symbols.get(identifier)+"\n";
        }
    }

    @Override
    public void caseABreakAst(ABreakAst node){
        code += "\tgoto LabelBreakEnd"+breakPointer+"\n";
    }

    @Override
    public void caseAOpenWhileStatementAst(AOpenWhileStatementAst node){
        code += "LabelWhileOpen"+labelCounter+":\n";
        node.getLeft().apply(this);
        code += "\tifeq LabelWhileControl"+labelCounter+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelWhileOpen"+labelCounter+"\nLabelWhileControl"+labelCounter+":\nLabelBreakEnd"+breakPointer+"\n";
        breakPointer++;
        labelCounter++;
    }

    @Override
    public void caseAClosedWhileStatementAst(AClosedWhileStatementAst node){
        code += "LabelWhileOpen"+labelCounter+":\n";
        node.getLeft().apply(this);
        code += "\tifeq LabelWhileControl"+labelCounter+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelWhileOpen"+labelCounter+"\nLabelWhileControl"+labelCounter+":\nLabelBreakEnd"+breakPointer+"\n";
        breakPointer++;
        labelCounter++;
    }

    @Override
    public void caseAOpenIfStatementAst (AOpenIfStatementAst node){
        node.getLeft().apply(this);
        code += "\tifeq LabelIfControl"+labelCounter+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelIfControl"+labelCounter+"\n";
        labelCounter++;
    }

    @Override
    public void caseAClosedIfStatementAst(AClosedIfStatementAst node){
        node.getLeft().apply(this);
        code += "\tifeq LabelIfControl"+labelCounter+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelIfControl"+labelCounter+"\n";
        labelCounter++;
    }

    @Override
    public void caseAOpenIfElseStatementAst(AOpenIfElseStatementAst node){
        node.getLeft().apply(this);
        code += "\tifeq LabelIfElse"+labelCounter+"\n";
        node.getMid().apply(this);
        code += "\tgoto LabelIfElseEnd"+labelCounter+"\nLabelIfElse"+labelCounter+":\n";
        node.getRight().apply(this);
        code += "LabelIfElseEnd"+labelCounter+":\n";
        labelCounter++;
    }

    @Override
    public void caseACompareAst (ACompareAst node){
        node.getLeft().apply(this);
        node.getRight().apply(this);
        code += "\tisub\n";
        node.getMid().apply(this);
        code += "\tbipush 0\n";
        code += "\tgoto LabelCompare"+comparePointer+"\nLabelTrue"+comparePointer+":\n\tbipush 1\nLabelCompareEnd"+comparePointer+":\n";
    }



}



import pascal.analysis.DepthFirstAdapter;
import pascal.node.*;

import java.util.HashMap;

/**
 * Created by maximilian on 17.01.14.
 * Creates Code for Yasmin, Yasmin loves code, like all bitches
 */
public class CodeGen extends DepthFirstAdapter{

    private String code = "";
    private int labels = 0;
    private int comparePointer = 0;
    private int breakPointer = 0;
    private int stackpointer = 1;
    private String type;

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

    public int getStackpointer() {
        return stackpointer;
    }

    //lets print some things
    public void caseAWritelnAst(AWritelnAst node){
        code += "\tgetstatic java/lang/System/out Ljava/io/PrintStream; \n";
        node.getAst().apply(this);
        String flag = "";
        if(type.equals("I")) flag ="I";
        else if (type.equals("Z")) flag = "Z";

        code += "\tinvokevirtual java/io/PrintStream/println("+flag+")V\n";
        stackpointer++;
    }

    //lets calculate some things
    @Override
    public void outAPlusAst(APlusAst node){
        code += "\tiadd\n";
        stackpointer--;
        type = "I";
    }

    @Override
    public void outAMinusAst(AMinusAst node){
        code += "\tisub\n";
        stackpointer--;
        type = "I";
    }

    @Override
    public void outAMultAst(AMultAst node){
        code += "\timul\n";
        stackpointer--;
        type = "I";
    }

    @Override
    public void outADivAst(ADivAst node){
        code += "\tidiv\n";
        stackpointer--;
        type = "I";
    }

    @Override
    public void outAModAst(AModAst node){
        code += "\tirem\n";
        type = "I";
    }

    @Override
    public void outAUnMinusAst(AUnMinusAst node){
        code += "\tineg\n";
        type = "I";
    }



    //lets compare some things
    @Override
    public void outAAndAst(AAndAst node){
        code += "\tiand\n";
        stackpointer--;
        type = "Z";
    }

    @Override
    public void outAOrAst(AOrAst node){
        code += "\tior\n";
        stackpointer--;
        type = "Z";
    }

    @Override
    public void outAXorAst(AXorAst node){
        code += "\tixor\n";
        stackpointer--;
        type = "Z";
    }

    @Override
    public void outANotAst(ANotAst node){
        //crap... i need to put up a hell of a work for just this easy thing
        int temp = labelCounter++;
        code += "\tifeq LabelNot"+temp+"\n\tbipush 0\n\tgoto LabelNotEnd"+temp+"\nLabelNot"+temp+":\nbipush 1\nLabelNotEnd"+temp+":\n";
        type = "Z";
    }


    //lets read a number
    @Override
    public void caseANumberAst(ANumberAst node){
        int number = Integer.parseInt(node.getNumber().toString().trim());
        code += "\tldc "+number+"\n";
        stackpointer++;
        type = "I";
    }

    //lets read a boolean
    @Override
    public void caseATrueAst(ATrueAst node){
        code += "\tbipush 1\n";
        stackpointer++;
        type = "Z";
    }

    @Override
    public void caseAFalseAst(AFalseAst node){
        code += "\tbipush 0\n";
        stackpointer++;
        type = "Z";
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


    //more difficult stuff now

    @Override
    public void caseAAssignmentAst(AAssignmentAst node){
        node.getAst().apply(this);
        code += "\tistore "+symbols.get(node.getIdentifier().toString().toLowerCase().replaceAll("\\s+",""))+"\n";
        stackpointer--;
    }

    @Override
    public void outAIdentifierAst(AIdentifierAst node){
        String identifier = node.getIdentifier().toString().toLowerCase().replaceAll("\\s+","");
        Node parent = node.parent();
        String name = parent.getClass().getSimpleName().replaceAll("\\s+", "");
        boolean flag =true;
        while (!name.equals("AProgramStartAst")){
            if(name.equals("ADeclarationsAst"))   //if variable is declared i will not load it
                flag = false;
            parent = parent.parent();
            name = parent.getClass().getSimpleName().replaceAll("\\s+","");

        }
        if(flag){
            code +="\tiload "+symbols.get(identifier)+"\n";
            stackpointer++;
        }
    }

    @Override
    public void caseABreakAst(ABreakAst node){
        code += "\tgoto LabelBreakEnd"+breakPointer+"\n";
    }

    @Override
    public void caseAOpenWhileStatementAst(AOpenWhileStatementAst node){
        int temp = labelCounter++;
        code += "LabelWhile"+temp+":\n";
        node.getLeft().apply(this);
        code += "\tifeq LabelWhileControl"+temp+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelWhile"+temp+"\nLabelWhileControl"+temp+":\nLabelWhileEnd"+breakPointer+":\n";
        breakPointer++;
        stackpointer--;
    }

    @Override
    public void caseAClosedWhileStatementAst(AClosedWhileStatementAst node){
        int temp = labelCounter++;
        code += "LabelWhile"+temp+":\n";
        node.getLeft().apply(this);
        code += "\tifeq LabelWhileEnd"+temp+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelWhile"+temp+"\nLabelWhileEnd"+breakPointer+":\n";
        breakPointer++;
        stackpointer--;
    }

    @Override
    public void caseAOpenIfStatementAst (AOpenIfStatementAst node){
        node.getLeft().apply(this);
        code += "\tifeq LabelIfControl"+labelCounter+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelIfControl"+labelCounter+"\n";
        labelCounter++;
        stackpointer--;
    }

    @Override
    public void caseAClosedIfStatementAst(AClosedIfStatementAst node){
        node.getLeft().apply(this);
        code += "\tifeq LabelIfControl"+labelCounter+"\n";
        node.getRight().apply(this);
        code += "\tgoto LabelIfControl"+labelCounter+"\n";
        labelCounter++;
        stackpointer--;
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
        stackpointer--;
    }

    @Override
    public void caseAComparisonAst (AComparisonAst node){
        int temp = 0;
        node.getLeft().apply(this);
        node.getRight().apply(this);
        code += "\tisub\n";
        temp = comparePointer;
        node.getMid().apply(this);

        code += "\tbipush 0\n\tgoto LabelCompareEnd"+temp+"\nLabelTrue"+temp+":\n\tbipush 1\nLabelCompareEnd"+temp+":\n";
        stackpointer+=2;
    }



}


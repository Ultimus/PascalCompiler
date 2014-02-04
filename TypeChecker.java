
/**
 * Created by maximilian on 14.01.14.
 * Checks out the (body-) type of the (hoe/)code
 */

/*import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;*/

import pascal.analysis.DepthFirstAdapter;
import pascal.node.*;
import java.util.HashMap;


public class TypeChecker extends DepthFirstAdapter {

    private HashMap<String,String> symbols = new HashMap<String, String>();
    private String eoast;

    public HashMap<String , String> getSymbols(){
        return symbols;
    }


    //put all declarations into the HashMap

    @Override
    public void caseADeclarationsAst(ADeclarationsAst node){
        String []variable = node.getIdentifier().toString().toLowerCase().split(" ");
        String type = node.getType().toString().toLowerCase().replaceAll("\\s+", "");


        for(String tempVar : variable){
            if(!symbols.containsKey(variable)){
                symbols.put(tempVar, type);

            }
            else {
                System.out.println("ERROR: Already declared "+tempVar+ " as "+symbols.get(variable));
                System.exit(1);
            }
        }

    }


    //if number is in ast write it in eoast
    public void caseANumberAst(ANumberAst node){
        this.eoast = "integer";
    }

    //same for boolean
    public void caseATrueAst(ATrueAst node){
        this.eoast = "boolean";
    }

    public void caseAFalseAst(AFalseAst node){
        this.eoast = "boolean";
    }

    //check if variable has been declared and throw an error if not
    @Override
    public void caseAIdentifierAst(AIdentifierAst node)
    {
        String identifier = node.getIdentifier().toString().toLowerCase().replaceAll("\\s+","");
        if(!symbols.containsKey(identifier)){
            System.out.println("ERROR: Identifier '"+ identifier+ "' has been declared, but not initialized");
            System.exit(1);

        }
        this.eoast=symbols.get(identifier);



    }

    @Override
    public void caseAAssignmentAst(AAssignmentAst node){
        String identifier = node.getIdentifier().toString().toLowerCase().replaceAll("\\s+","");
        String type = symbols.get(identifier);

        if (!symbols.containsKey(identifier)){
            System.out.println ("ERROR: Undeclared Variable " + identifier);
            System.exit(1);
        }
        String expr = node.getAst().getClass().getSimpleName();

        node.getAst().apply(this);  //working its way through the AST

        //check for true and false
        if (expr.equals("ATrueAst") || expr.equals("AFalseAst")){
            if(!type.equals("boolean")){
                System.out.println("ERROR: Wrong type for " + identifier );
                System.exit(1);
            }

        }

        //in case of Number expr, is type Integer?
        if (expr.equals("ANumberAst")){
            if(!type.equals("integer")){
                System.out.println("Expr: "+expr + "    Type: " + type);
                System.out.println("ERROR: Number was not assigned to Integer");
                System.exit(1);
            }
        }

        //check for arithmetic operations
        if(expr.equals("APlusAst") || expr.equals("AMinusAst") || expr.equals("AMultAst") || expr.equals("ADivAst") || expr.equals("AUnMinusAst") || expr.equals("UnPlusAst")){
            if (!type.equals("integer")){
                System.out.println("ERROR: Integer was expected for " + identifier);
                System.exit(1);
            }
        }

        //same for boolean
        if(expr.equals("AEqualAst") || expr.equals("AUnequalAst") || expr.equals("AXorAst") || expr.equals("AAndAst") || expr.equals("AOrAst") || expr.equals("ANotAst") || expr.equals("AComparisonAst")){
            if(!type.equals("boolean")){
                System.out.println("ERROR Boolean was expected for " + identifier);
                System.exit(1);
            }

        }

    }

    //check all arithemtic types    *all of them...*

    //lets start with a big plus
    @Override
    public void caseAPlusAst(APlusAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;
        if (!left.equals(right)){
            System.out.println("ERROR: '+' expects Integer type");
            System.exit(1);
        }
    }


    @Override
    public void caseAMinusAst(AMinusAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;
        if (!left.equals(right)){
            System.out.println("ERROR: '-' expects Integer type");
            System.exit(1);
        }
    }

    @Override
    public void caseAMultAst(AMultAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;
        if (!left.equals(right)){
            System.out.println("ERROR: '*' expects Integer type");
            System.exit(1);
        }
    }


    @Override
    public void caseADivAst(ADivAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;
        if (!left.equals(right)){
            System.out.println("ERROR: '/' expects Integer type");
            System.exit(1);
        }
    }

    @Override
    public void caseAModAst(AModAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;
        if (!left.equals(right)){
            System.out.println("ERROR: 'mod' expects Integer type");
            System.exit(1);
        }
    }

    @Override
    public void caseAUnPlusAst(AUnPlusAst node){
        node.getAst().apply(this);
        if(!eoast.equals("integer") && !node.getAst().getClass().getSimpleName().equals("AIdentifierAst") && !node.getAst().getClass().getSimpleName().equals("ANumberAst")){
           System.out.println("ERROR: Unary Plus expects Integer");
            System.exit(1);
        }
    }

    @Override
    public void caseAUnMinusAst(AUnMinusAst node){
        node.getAst().apply(this);
        if(!eoast.equals("integer") && !node.getAst().getClass().getSimpleName().equals("AIdentifierAst") && !node.getAst().getClass().getSimpleName().equals("ANumberAst")){
            System.out.println("ERROR: Unary Minus expects Integer");
            System.exit(1);
        }
    }

    //same "thing" for booleans

    //main thing first

    @Override
    public void caseAComparisonAst(AComparisonAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;
        eoast = "boolean";

        if(!left.equals(right)){
            System.out.println("ERROR: wrong type for comparison");
            System.exit(1);
        }
    }


     @Override
    public void caseAOrAst(AOrAst node){
         node.getLeft().apply(this);
         String left = this.eoast;
         node.getRight().apply(this);
         String right = this.eoast;

         if (!left.equals(right)){
             System.out.println("ERROR: Or expects Boolean");
             System.exit(1);
         }
     }

    @Override
    public void caseAAndAst(AAndAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;

        if (!left.equals(right)){
            System.out.println("ERROR: And expects Boolean");
            System.exit(1);
        }
    }

    @Override
    public void caseAXorAst(AXorAst node){
        node.getLeft().apply(this);
        String left = this.eoast;
        node.getRight().apply(this);
        String right = this.eoast;

        if (!left.equals(right)){
            System.out.println("ERROR: Xor expects Boolean");
            System.exit(1);
        }
    }

    @Override
    public void caseANotAst(ANotAst node){
        node.getAst().apply(this);

        if (!eoast.equals("boolean")){
            System.out.println("ERROR: Not expects Boolean");
            System.exit(1);
        }
    }


    //check if break is only used when a while was before. Maybe this is not the job of a type checker, but i think it is usefull

    @Override
    public void caseABreakAst(ABreakAst node){
        Node parent = node.parent();
        String parentName = parent.getClass().getSimpleName().replaceAll("\\s+","");

        while (!parentName.equals("AProgramStartAst")){
            if (parentName.equals("AOpenWhileStatementAst") || parentName.equals("AClosedWhileStatementAst"))return ;
            parent = parent.parent();
            parentName = parent.getClass().getSimpleName().replaceAll("\\s+","");
        }
        System.out.println("ERROR: 'break' was used without 'while' statement");
        System.exit(1);
    }




}

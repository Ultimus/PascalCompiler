package pascal.src;
/**
 * Created by maximilian on 14.01.14.
 * Checks out the (body-) type of the (hoe/)code
 */

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import pascal.analysis.DepthFirstAdapter;
import pascal.node.*;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;


public class TypeChecker extends DepthFirstAdapter {

    private HashMap<String,String> symbols = new HashMap<String, String>();

    public HashMap<String , String> getSymbols(){
        return symbols;
    }


    //put all declarations into the HashMap
    @Override
    public void caseADeclarationAst(ADeclarationsAst node){
        String type = node.getRight().toString.toLowerCase();

    }




}

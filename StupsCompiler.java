/**
 * Created by maximilian on 11.12.13.
 */




import pascal.parser.*;
import pascal.lexer.*;
import pascal.node.*;
import java.io.*;

public class StupsCompiler {


    public static void main (String [] args)throws LexerException, IOException, ParserException{

        //check for arguments
        if(args.length < 2){
            System.out.println("Error: not enough arguments.\n Use: java StupsCompiler -argument <Filename>\n ");
            System.exit(1);
        }



        //lets go
        String filename = args[1];
        try
	   {
           if (args[0].equals("-compile")){


               filename = args[1];
               File file = new File(filename);
               PushbackReader reader = new PushbackReader(new FileReader(file));
               Lexer lexer = new Lexer(reader);
               Parser parser = new Parser(lexer);
               Start tree = parser.parse();
               ASTPrinter printer = new ASTPrinter();
               tree.apply(printer);
               TypeChecker typeChecker = new TypeChecker();
               tree.apply(typeChecker);
               CodeGen codeGen = new CodeGen(typeChecker.getSymbols());
               int var = typeChecker.getSymbols().size();
               for (String temp : typeChecker.getSymbols().keySet()){
                   codeGen.getSymbols().put(temp, var--); //I need the identifiers HERE!
               }
               tree.apply(codeGen);
               String output = typicalOutput(codeGen, filename.replace(".pas",""));
               Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename.replace(".pas","")+".j"),"UTF8"));
               writer.append(output);
               writer.close();

           }
           else if (args[0].equals("-liveness")){
               filename = args[1];
               File file = new File(filename);
               PushbackReader reader = new PushbackReader(new FileReader(file));
               Lexer lexer = new Lexer(reader);
               Parser parser = new Parser(lexer);
               Start tree = parser.parse();
               TypeChecker typeChecker = new TypeChecker();
               tree.apply(typeChecker);
               Liveness liveness = new Liveness();
               tree.apply(liveness);
               liveness.getLiveness();

           }
           else System.out.println(" Error: Wrong usage of StupsCompiler.\n Usage:  java StupsCompiler -compile <Filename>");
		  
		  
	  }
	catch(Exception e){
		System.out.println(e.getMessage());
	} 



    }

    private static String typicalOutput(CodeGen codeGen, String filename){
        int size = codeGen.getSymbols().size()+1;
        String output = ".bytecode 50.0\n.class public "+filename+"\n.super java/lang/Object\n.method public <init>()V\n\t.limit stack 1\n\t.limit locals 1\n\taload_0\n\tinvokespecial java/lang/Object/<init>()V\n\treturn\n.end method\n\t.method public static main([Ljava/lang/String;)V\n\t.limit stack "+codeGen.getStackpointer()+"\n\t.limit locals "+size+"\n"+codeGen.getCode()+"\treturn\n.end method\n";
        return output; //its similar to the String we had in our example but with a real stack height and without the useless prints
    }



}

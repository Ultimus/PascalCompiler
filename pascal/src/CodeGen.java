package pascal.src;

import pascal.analysis.DepthFirstAdapter;
import java.util.HashMap;

/**
 * Created by maximilian on 17.01.14.
 * Creates Code for Yasmin, Yasmin loves code, like all bitches
 */
public class CodeGen extends DepthFirstAdapter{

    private String code = "";
    private int labels;
    private int stackpointer;

    private HashMap<String, Integer> symbols = new HashMap<String, Integer>();



}

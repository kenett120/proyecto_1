
import script.ScriptInterpreter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> script = Arrays.asList(
                "VALID_SIGNATURE",
                "PUBKEY123",
                "OP_DUP",
                "OP_HASH160",
                "HASH160_PUBKEY123",
                "OP_EQUALVERIFY",
                "OP_CHECKSIG"
        );

        ScriptInterpreter interpreter = new ScriptInterpreter(true);
        boolean result = interpreter.execute(script);

        System.out.println("Resultado final: " + result);
    }
}

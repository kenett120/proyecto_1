package script;

import java.util.*;

public class ScriptInterpreter {

    private Deque<byte[]> stack;
    private boolean trace;

    public ScriptInterpreter(boolean trace) {
        this.stack = new ArrayDeque<>();
        this.trace = trace;
    }

    public boolean execute(List<String> script) {
        for (String token : script) {
            if (!processToken(token)) {
                return false;
            }

            if (trace) {
                printStack();
            }
        }

        return !stack.isEmpty() && isTrue(stack.peek());
    }

    private boolean processToken(String token) {
        switch (token) {

            case "OP_0":
                stack.push(new byte[]{0});
                break;

            case "OP_1":
                stack.push(new byte[]{1});
                break;

            case "OP_2":
                stack.push(new byte[]{2});
                break;

            case "OP_3":
                stack.push(new byte[]{3});
                break;

            case "OP_4":
                stack.push(new byte[]{4});
                break;


            case "OP_5":
                stack.push(new byte[]{5});
                break;

            case "OP_6":
                stack.push(new byte[]{6});
                break;


            case "OP_7":
                stack.push(new byte[]{7});
                break;

            case "OP_8":
                stack.push(new byte[]{8});
                break;

            case "OP_9":
                stack.push(new byte[]{9});
                break;

            case "OP_10":
                stack.push(new byte[]{10});
                break;

            case "OP_11":
                stack.push(new byte[]{11});
                break;

            case "OP_12":
                stack.push(new byte[]{12});
                break;

            case "OP_13":
                stack.push(new byte[]{13});
                break;

            case "OP_14":
                stack.push(new byte[]{14});
                break;

            case "OP_15":
                stack.push(new byte[]{15});
                break;

            case "OP_16":
                stack.push(new byte[]{16});
                break;

            case "OP_DUP":
                return opDup();

            case "OP_DROP":
                return opDrop();

            case "OP_EQUAL":
                return opEqual();

            case "OP_EQUALVERIFY":
                if (!opEqual()) return false;
                return opVerify();

            case "OP_HASH160":
                return opHash160();

            case "OP_CHECKSIG":
                return opCheckSig();

            default:
                stack.push(token.getBytes());
        }

        return true;
    }

    private boolean opDup() {
        if (stack.isEmpty()) return false;
        stack.push(Arrays.copyOf(stack.peek(), stack.peek().length));
        return true;
    }

    private boolean opDrop() {
        if (stack.isEmpty()) return false;
        stack.pop();
        return true;
    }

    private boolean opEqual() {
        if (stack.size() < 2) return false;

        byte[] a = stack.pop();
        byte[] b = stack.pop();

        boolean result = Arrays.equals(a, b);
        stack.push(result ? new byte[]{1} : new byte[]{0});

        return true;
    }

    private boolean opVerify() {
        if (stack.isEmpty()) return false;
        return isTrue(stack.pop());
    }

    private boolean opHash160() {
        if (stack.isEmpty()) return false;

        byte[] data = stack.pop();
        byte[] hash = ("HASH160_" + new String(data)).getBytes();

        stack.push(hash);
        return true;
    }

    private boolean opCheckSig() {
        if (stack.size() < 2) return false;

        byte[] pubKey = stack.pop();
        byte[] signature = stack.pop();

        boolean valid = new String(signature).contains("VALID");

        stack.push(valid ? new byte[]{1} : new byte[]{0});
        return true;
    }

    private boolean isTrue(byte[] value) {
        return value.length > 0 && value[0] != 0;
    }
    
    private void printStack() {
        System.out.println("Stack:");
        for (byte[] item : stack) {
            System.out.println(new String(item));
        }
        System.out.println("--------------");
    }

}

package indi.hiro.ss;

public class SsTest {

    public static void main(String[] args) {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("alpha");
        stack.push("beta");
        System.out.println(stack.pop()); // beta
        stack.push("gamma");
        stack.push("delta");
        System.out.println(stack.pop()); // delta
        System.out.println(stack.pop()); // gamma
        System.out.println(stack.pop()); // alpha
        stack.push("epsilon");
        stack.push("zeta");
        System.out.println(stack.pop()); // zeta
        System.out.println(stack.pop()); // epsilon
    }
}

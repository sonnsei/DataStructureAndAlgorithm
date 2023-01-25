package structure;

public class BITTest {
    public static void main(String[] args) {
        int[] input = new int[30];
        BIT bit = new BIT(input);
        bit.update(20, 9);
    }
}

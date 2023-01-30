package structure;

/**
 * @Description:
 * @author: sonnsei
 * @date: 2023/1/30
 */
public class HuffmanTest {
    public static void main(String[] args) {
        char[] chars = new char[]{'a', 'b', 'c', 'd','e', 'f'};
        int[] cnt = new int[]{5, 32, 18, 7, 25, 13};
        HuffmanTree huffmanTree = new HuffmanTree(chars, cnt);
        System.out.println(huffmanTree.getMap());

        String encode = "";
        System.out.println(encode = huffmanTree.encode(chars));
        System.out.println(huffmanTree.decode(encode));
    }
}

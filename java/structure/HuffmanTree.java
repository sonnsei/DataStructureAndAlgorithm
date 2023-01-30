package structure;

import java.util.*;

/**
 * @Description:
 * @author: sonnsei
 * @date: 2023/1/30
 */
public class HuffmanTree {
    class Node{
        int weight;
        Character v;
        Node left;
        Node right;

        public Node(int weight, Character v, Node left, Node right) {
            this.weight = weight;
            this.v = v;
            this.left = left;
            this.right = right;
        }
    }

    Node root;

    Map<Character,String> encodeMap;
    Map<String,Character> decodeMap;

    public HuffmanTree(char[] chars, int[] cnt) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        for(int i = 0; i < chars.length; i ++){
            char ch = chars[i];
            Node node = new Node(cnt[i],ch,null,null);
            pq.add(node);
        }

        while(pq.size()>1){
            Node node1 = pq.poll();
            Node node2 = pq.poll();
            Node p = new Node(node1.weight+node2.weight,null, node1,node2);
            pq.add(p);
        }
        this.root = pq.poll();
        this.encodeMap = new HashMap<>();
        this.decodeMap = new HashMap<>();
    }

    public Map<Character,String> getMap(){
        Map<Character, String> ans = new HashMap<>();
        preOrder(root, new StringBuilder(),ans);
        return ans;
    }

    private void preOrder(Node root, StringBuilder builder,Map<Character, String> ans) {
        if(root == null){
            return;
        }
        if(root.v != null){
            ans.put(root.v, builder.toString());
        }

        builder.append(0);
        preOrder(root.left, builder, ans);
        builder.setCharAt(builder.length()-1,'1');
        preOrder(root.right, builder, ans);
        builder.deleteCharAt(builder.length() - 1);
    }

    public String encode(char[] chars){
        if (encodeMap.isEmpty()) {
            encodeMap = getMap();
            for (Character key : encodeMap.keySet()) {
                decodeMap.put(encodeMap.get(key), key);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char ch : chars) {
            if (!encodeMap.containsKey(ch)) {
                return ch + " not found!";
            }
            builder.append(encodeMap.get(ch));
        }
        return builder.toString();
    }

    public List<Character> decode(String s){
        List<Character> ans = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (char ch : s.toCharArray()) {
            builder.append(ch);
            if (decodeMap.containsKey(builder.toString())) {
                ans.add(decodeMap.get(builder.toString()));
                builder = new StringBuilder();
            }
        }
        return ans;
    }
}

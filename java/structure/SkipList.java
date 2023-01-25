package structure;

import java.util.*;

/**
 * @Description: 跳表
 * @author: sonnsei
 * @date: 2023/1/24
 */
public class SkipList{
    private int level;
    private final int MAX_LEVEL  = 32;
    private Node head;
    private final double factor = 0.25;
    private  Random random;

    public SkipList() {
        this.level = 0;
        this.head = new Node(null, MAX_LEVEL);
        this.random = new Random();
    }

    public boolean search(Integer target) {
        if(target == null){
            return false;
        }
        Node cur = head;
        for(int i = level-1; i>=0; i--){
            while(cur.next[i]!= null && cur.next[i].e < target){
                cur = cur.next[i];
            }
        }
        cur = cur.next[0];
        if(cur == null || !target.equals(cur.e)){
            return false;
        }
        return true;
    }

    public void add(Integer target) {
        Node[] pre = new Node[MAX_LEVEL];
        Arrays.fill(pre,head);

        Node cur = head;
        for(int i = level-1; i>=0; i--){
            while(cur.next[i]!=null && cur.next[i].e<target){
                cur = cur.next[i];
            }
            pre[i] = cur;
        }

        int lv = randomLevel();
        level = Math.max(lv, level);

        Node newNode = new Node(target,lv);
        for(int i = 0; i < lv; i++){
            newNode.next[i] = pre[i].next[i];
            pre[i].next[i] = newNode;
        }
    }

    public boolean erase(Integer target) {
        Node[] pre = new Node[MAX_LEVEL];

        Node cur = head;
        for(int i = level-1; i>=0; i--){
            while(cur.next[i]!=null && cur.next[i].e<target){
                cur = cur.next[i];
            }
            pre[i] = cur;
        }

        Node delete = cur.next[0];
        if(delete == null || !delete.e.equals(target)){
            return false;
        }

        for(int i = 0; i < level; i++){
            if(!delete.equals(pre[i].next[i])){
                break;
            }
            pre[i].next[i]=delete.next[i];
        }

        while(level > 1 && head.next[level-1] == null){
            level--;
        }

        return true;
    }

    private int randomLevel() {
        int lv = 1;
        /* 随机生成 lv */
        while (random.nextDouble() < factor && lv < MAX_LEVEL) {
            lv++;
        }
        return lv;
    }

    class Node{
        Node[] next;
        Integer e;
        public Node(Integer e, int level) {
            this.e = e;
            this.next = new Node[level];
        }
    }
}

package structure;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for(int i = 0; i < 10; i++){
            skipList.add(i);
        }
        System.out.println(skipList);
    }
}

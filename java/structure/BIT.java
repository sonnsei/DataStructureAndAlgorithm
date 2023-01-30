package structure;

/**
 * @Description: 树状数组
 * @author: sonnsei
 * @date: 2023/1/25
 */
public class BIT {
    int[] parent;
    public BIT(int[] input) {
        int n = input.length;
        parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(i, input[i]);
        }
    }

    public int sum(int i) {
        int sum = 0;
        for (i++; i > 0; i -= (i & (-i))) {
            sum += parent[i];
        }
        return sum;
    }

    public void update(int i, int val) {
        for (i++; i < parent.length; i += (i & (-i))) {
            parent[i] += val;
        }
    }
}

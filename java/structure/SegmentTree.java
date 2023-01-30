package structure;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @Description:
 * @author: sonnsei
 * @date: 2023/1/26
 */
public class SegmentTree<E> {
    E[] tree;
    E[] data;
    Merger<E> merger;

    public interface Merger<E> {
        E merge(E a, E b);
    }

    public SegmentTree(E[] src,Merger<E> merger) {
        int n = src.length;
        data = Arrays.copyOf(src, n);
        tree = (E[])new Object[4*n];
        this.merger = merger;
        buildTree(0, 0, n - 1);
    }

    private void buildTree(int rootIndex, int left, int right) {
        if (left == right) {
            tree[rootIndex] = data[left];
            return;
        }

        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        int mid = (left + right) >>> 1;

        buildTree(leftChildIndex, left, mid);
        buildTree(rightChildIndex, mid + 1, right);

        //这里是区间合并操作，这里展示的是求和
        tree[rootIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    public void set(int index, E val) {
        data[index] = val;
        set(0, 0, data.length - 1, index, val);
    }

    private void set(int rootIndex, int left, int right, int opIndex, E val) {
        if (left == right) {
            tree[rootIndex] = val;
            return;
        }
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        int mid = (left + right) >>> 1;
        if (opIndex > mid) {
            set(rightChildIndex, mid + 1, right, opIndex, val);
        } else {
            set(leftChildIndex, left, mid, opIndex, val);
        }
        tree[rootIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    public E query(int ql, int qr) {
        return query(0, 0, data.length - 1, ql, qr);
    }

    private E query(int rootIndex, int left, int right, int ql, int qr) {
        if (left == ql && right == qr) {
            return tree[rootIndex];
        }
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        int mid = (left + right) >>> 1;

        if (ql > mid) {
            return query(rightChildIndex, mid + 1, right, ql, qr);
        }
        if (qr <= mid) {
            return query(leftChildIndex, left, mid, ql, qr);
        }
        return merger.merge(query(leftChildIndex, left, mid, ql, mid),query(rightChildIndex, mid + 1, right, mid + 1, qr));
    }
}

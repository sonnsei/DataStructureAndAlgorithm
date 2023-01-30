package structure;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description:
 * @author: sonnsei
 * @date: 2023/1/26
 */
public class SegmentTreeTest {
    public static void main(String[] args) {
//        Integer[] nums = new Integer[]{-2, 0, 3, -5, 2, -1};
//        Arrays.sort(nums, (Comparator<Integer>) (i1, i2) -> i2-i1);
//        Integer[] data = new Integer[nums.length];
//        for(int i = 0 ; i < nums.length ; i ++)
//            data[i] = nums[i];
//        SegmentTree<Integer> segmentTree = new SegmentTree<>(data,(a,b)->a+b);
//        System.out.println(segmentTree);

        System.out.println(monkeyMove2(500000003));
        System.out.println(monkeyMove(500000003));
        System.out.println(monkeyMove(3));

    }

    public static int monkeyMove(int n) {
        int mod = (int)1e9+7;
        long cnt = 1;
        for(int i = 0; i < n; i ++ ){
            cnt *=2;
            if(i != n-1){
                cnt  = cnt % mod;
            }
        }
        return (int)cnt-2;
//        int mod = (int)1e9+7;
//        return ((int)Math.pow(2,n)-2)%mod;
    }
    public static int monkeyMove2(int n) {
        int MOD = (int)1e9 + 7;
        long ans = 1;
        long x = 2;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = ans * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return (int)((ans + MOD - 2) % MOD);
    }

    public static int myPow(int m, int n, final int mod) {
        long ans = 1;
        long tmp = m;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = ans * tmp % mod;
            }
            tmp = tmp * tmp % mod;
            n >>= 1;
        }
        return (int)ans;
    }
}

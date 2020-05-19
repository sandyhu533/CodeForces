import java.util.*;
import java.math.*;

// 1.LinkedList本质上是链表，查找耗时O(N)，插入、删除在预先查找的情况下也是O(N)
// 2.树状数组，存储每个元素的数量 (1)查找到相应的位置 通过前缀和找到元素坐标 + 执行删除操作 （logn * logn + logn) (2)执行插入操作

public class Solution {
    static int n = (int)1e6;
    static int[] bit;

    static int sum(int i) {
        int s = 0;
        while(i > 0) {
            s += bit[i];
            i -= i & (-i);
        }
        return s;
    }

    static void add(int i, int val) {
        while(i <= n) {
            bit[i] += val;
            i += i & (-i);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int Q = scan.nextInt();

        bit = new int[n + 1];
        for(int i = 0; i < N; i++) {
            int val = scan.nextInt();
            add(val, 1);
        }

        for(int q = 0; q < Q; q++) {
            int val = scan.nextInt();
            if(val < 0) {
                int l = 0, r = n;
                while(l < r) {
                    int mid = (l + r) >> 1;
                    if(sum(mid) < -val) l = mid + 1;
                    else r = mid;
                }
                add(l, -1);
            } else {
                add(val, 1);
            }
        }

        if(sum(n) == 0) System.out.println(0);
        else {
            int l = 0, r = n;
            while(l < r) {
                int mid = (l + r) >> 1;
                if(sum(mid) == 0) l = mid + 1;
                else r = mid;
            }
            System.out.println(l);
        }
    }
}

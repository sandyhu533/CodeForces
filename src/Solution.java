import java.util.*;
import java.math.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = Integer.valueOf(scan.nextLine());
        for(int t = 0; t < T; t++) {
            char[] str = scan.nextLine().toCharArray();
            int n = str.length;
            int ans = n + 1;
            int l = 0, r = 0, status = 0;
            int[] ct = new int[3];
            while(r < n) {
                ct[str[r] - '1']++;
                while(ct[0] != 0 && ct[1] != 0 && ct[2] != 0) {
                    if(l == n) break;
                    ans = Math.min(ans, r - l + 1);
                    ct[str[l] - '1']--;
                    l++;
                }
                r++;
            }

            System.out.println(ans == n + 1 ? 0 : ans);

        }
    }
}

import java.util.*;
import java.math.*;

// 注意数据范围 10^9 ^2 应该用BigInteger了
public class AlarmClock900 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = Integer.valueOf(scan.nextLine());
        for(int t = 0; t < T; t++) {
            String[] config_str = scan.nextLine().split(" ");
            int a = Integer.valueOf(config_str[0]);
            int b = Integer.valueOf(config_str[1]);
            int c = Integer.valueOf(config_str[2]);
            int d = Integer.valueOf(config_str[3]);

            BigInteger ans = new BigInteger(String.valueOf(b));
            a -= b;
            if(a > 0) {
                if(c <= d) ans = new BigInteger("-1");
                else {
                    ans = ans.add(new BigInteger(String.valueOf((long)c * (long)(Math.ceil((double)a / (c - d))))));
                }
            }

            System.out.println(ans);
        }
    }
}

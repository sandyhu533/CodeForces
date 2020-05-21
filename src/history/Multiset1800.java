import java.util.*;
import java.math.*;
import java.io.*;

// 1.LinkedList本质上是链表，查找耗时O(N)，插入、删除在预先查找的情况下也是O(N)
// 2.树状数组，存储每个元素的数量 (1)查找到相应的位置 通过前缀和找到元素坐标 + 执行删除操作 n *（logn * logn + logn) (2)执行插入操作
// 3.使用快速IO

public class Multiset1800 {
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

    public static void main(String[] args) throws IOException {
        Reader rd = new Reader();
        int N = rd.nextInt();
        int Q = rd.nextInt();

        bit = new int[n + 1];
        for(int i = 0; i < N; i++) {
            int val = rd.nextInt();
            add(val, 1);
        }

        for(int q = 0; q < Q; q++) {
            int val = rd.nextInt();
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

    //Fast IO class
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}

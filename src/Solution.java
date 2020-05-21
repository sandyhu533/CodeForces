import java.io.*;
import java.util.*;

// 刚开始想到构成点和边，在此基础上做dijistra，但实际上边的权重是随着方案动态变化的，所以不可行
// 用优先队列BFS，枚举每个点的不同高度，以cost少为优先进行bfs，最后问题规模将是指数级，不可行
// 参考 https://dreamfarer.github.io/post/n4_Nmfn6u/ 所有点中必然有一个点是不动的，用O(n^2)去枚举这个不动的点
// 再做优先队列BFS，

public class Solution {
    static class Node implements Comparable<Node>{
        int x, y;
        long height, cost;

        public Node(int x, int y, long height, long cost) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost < node.cost ? -1 : 1;
        }
    }

    public static int M;
    public static int[][] dirs = {{-1, 0}, {0, -1}};
    public static int encode(int x, int y) {
        return x * M + y;
    }

    public static void main(String[] args) throws IOException {
         Reader rd = new Reader();
         int T = rd.nextInt();
         for(int t = 0; t < T; t++) {
             int n = rd.nextInt(), m = rd.nextInt();
             long[][] height = new long[n][m];
             for(int i = 0; i < n; i++) {
                 for(int j = 0; j < m; j++) {
                     height[i][j] = rd.nextLong();
                 }
             }

             M = m;

             PriorityQueue<Node> que = new PriorityQueue<>();
             que.add(new Node(n - 1, m - 1, height[n - 1][m - 1], 0));

             while(!que.isEmpty()) {
                 Node node = que.poll();
                 if(node.x == 0 && node.y == 0) {
                     System.out.println(node.cost);
                     break;
                 }
                 for(int[] dir : dirs) {
                     int nx = dir[0] + node.x;
                     int ny = dir[1] + node.y;
                     if(nx < 0 || ny < 0)   continue;
                     long h = height[nx][ny];
                     long c = node.cost;
                     if(h > node.height - 1) {
                         c += h - node.height + 1;
                     } else {
                        c += node.height - h - 1;
                     }
                     h = node.height - 1;
                     que.add(new Node(nx, ny, h, c));
                 }
             }

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

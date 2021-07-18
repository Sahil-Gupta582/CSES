import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    static long mod=(long)1e9+7;
    static long mod1=998244353;
    static ArrayList<Integer>[] adj;
    static int[] height;
    static int[] dist;
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);


        int t= 1;
        while(t-->0) {

            int n = in.nextInt();
            adj = new ArrayList[n+1];
            height = new int[n+1];
            dist = new int[n+1];
            for(int i = 0;i<=n;i++) adj[i] = new ArrayList<>();

            for(int i = 0;i<n-1;i++){
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a].add(b);
                adj[b].add(a);
            }

            dfs1(1,0);
            dfs2(1,0);
            for(int i = 1;i<=n;i++)
                out.print(Math.max(dist[i],height[i])-1+" ");


        }
        out.close();
    }

    static void dfs1(int curr,int prev){
        for(int i:adj[curr]){
            if(i!=prev){
                dfs1(i,curr);
                height[curr] = Math.max(height[i],height[curr]);
            }
        }
        height[curr]+=1;
    }
    static void dfs2(int src,int prev){

        int max1 = 0;
        int max2 = 0;

        for(int i :adj[src]){
            if(i!=prev) {
                if (height[i] >= max1) {
                    max2 = max1;
                    max1 = height[i];
                } else if (height[i] > max2) {
                    max2 = height[i];
                }
            }
        }

        for(int i:adj[src]){
            if(i!=prev){
                if(max1 == height[i]){
                    dist[i] = 1+Math.max(max2+1,dist[src]);
                }else
                    dist[i] = 1+Math.max(max1+1,dist[src]);

                dfs2(i,src);
            }
        }
    }


    static final Random random=new Random();

    static void ruffleSort(int[] a) {
        int n=a.length;//shuffle, then sort
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n), temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }
    static long gcd(long x, long y){
        if(x==0)
            return y;
        if(y==0)
            return x;
        long r=0, a, b;
        a = Math.max(x, y);
        b = Math.min(x, y);
        r = b;
        while(a % b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }
    static long modulo(long a,long b,long c){
        long x=1,y=a%c;
        while(b > 0){
            if(b%2 == 1)
                x=(x*y)%c;
            y = (y*y)%c;
            b = b>>1;
        }
        return  x%c;
    }
    public static void debug(Object... o){
        System.err.println(Arrays.deepToString(o));
    }

    static int upper_bound(int[] arr,int n,int x){
        int mid;
        int low=0;
        int high=n;
        while(low<high){
            mid=low+(high-low)/2;
            if(x>=arr[mid])
                low=mid+1;
            else
                high=mid;
        }
        return low;
    }

    static int lower_bound(int[] arr,int n,int x){
        int mid;
        int low=0;
        int high=n;
        while(low<high){
            mid=low+(high-low)/2;
            if(x<=arr[mid])
                high=mid;
            else
                low=mid+1;
        }
        return low;
    }
    static String printPrecision(double d){
        DecimalFormat ft = new DecimalFormat("0.00000000000");
        return String.valueOf(ft.format(d));
    }
    static int countBit(long mask){
        int ans=0;
        while(mask!=0){
            mask&=(mask-1);
            ans++;
        }
        return ans;
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        public int[] readArray(int n)
        {
            int[] arr=new int[n];
            for(int i=0;i<n;i++) arr[i]=nextInt();
            return arr;
        }
    }
}
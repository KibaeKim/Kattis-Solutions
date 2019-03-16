import java.util.*;

public class Shorty{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while(true){
            int n = input.nextInt();
            int m = input.nextInt();

            if(n == 0) break;

            Edge[] edges = new Edge[m];
            double[] weight = new double[n];

            for(int i = 1; i < n; i++){
                weight[i] = 0;
            }

            weight[0] = 1;

            for(int i = 0; i < m; i++){
                edges[i] = new Edge(input.nextInt(), input.nextInt(), input.nextDouble());

            }

            boolean changed = true;

            for(int i = 0; i < n-1 && changed; i++){
                changed = false;
                for (Edge e:edges){
                    // System.out.println("weight e.v: " + weight[e.v]);
                    // System.out.println("weight e.u: " + weight[e.u]);
                    // System.out.println("weight: " + e.weight);
                    if(weight[e.v] < weight[e.u] * e.weight){
                        weight[e.v] = weight[e.u] * e.weight;
                        changed = true;
                    }else if(weight[e.u] < weight[e.v] * e.weight){
                        weight[e.u] = weight[e.v] * e.weight;
                        changed = true;
                    }
                }
            }

            if(weight[n-1] == 0) System.out.println("1.0000");
            else {
                System.out.printf( "%.4f", weight[n-1]);
                System.out.println();
            }
        }
    }
}

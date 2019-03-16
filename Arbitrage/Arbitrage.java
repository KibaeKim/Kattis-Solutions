import java.io.*;
import java.util.*;
import java.math.*;

public class Arbitrage
{
    ArrayList<Edge> edges;
    String[] forex;
    HashMap<String, Double> hm;

    public Arbitrage(String forex_string){
        forex = forex_string.split(" ");
        edges = new ArrayList<Edge>();
        hm = new HashMap<String, Double>();
    }

    public static void main(String[] args)
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try{
            line = input.readLine();
        }catch(Exception e){
            System.out.println("Exception 1");
            return;
        }
        
        int number_of_forex, number_of_edges;
        number_of_forex = Integer.parseInt(line);
        Arbitrage arb;

        while(number_of_forex != 0)
        {
            try{
                arb = new Arbitrage(input.readLine());
            }catch(Exception e){
                System.out.println("Exception 2");
                return;
            }
            
            try{
                number_of_edges = Integer.parseInt(input.readLine());
            }catch(Exception e){
                System.out.println("Exception 3");
                return;
            }
            
            for(int i = 0; i < number_of_edges; i++){
                try{
                    add_edge(arb, input.readLine());
                }catch(Exception e){
                    System.out.println("Exception 4");
                    return;
                }
            }
            
            boolean has_negative_cycle = false;

            for(int s = 0; s < number_of_forex; s++){
                //Initializing arb.hm
                for(int i = 0; i < number_of_forex; i++)
                    arb.hm.put(arb.forex[i], Double.POSITIVE_INFINITY);
                arb.hm.put(arb.forex[s], (double)0);

                for(int i = 0; i < number_of_forex - 1; i++){
                    for(Edge e: arb.edges){
                        if(arb.hm.get(e.u) + e.weight < arb.hm.get(e.v))
                            arb.hm.put(e.v, arb.hm.get(e.u) + e.weight);
                    }
                }

                for(int i = 0; i < number_of_forex - 1; i++){
                    for(Edge e: arb.edges){
                        if(arb.hm.get(e.u) + e.weight < arb.hm.get(e.v)){
                                has_negative_cycle = true;
                                break;
                            
                        }
                    }
                    if(has_negative_cycle) break;
                }

                if (has_negative_cycle) break;
            }
            //arb.test();
            //System.out.println();
            if(has_negative_cycle) 
                System.out.println("Arbitrage");
            else
                System.out.println("Ok");

            try{
                number_of_forex = Integer.parseInt(input.readLine());
            }catch(Exception e){
                return;
            }
        }
    }

    public static void add_edge(Arbitrage arb, String line){
        String[] temp = line.split(" ");
        String[] weight_tool = temp[2].split(":");
        arb.edges.add(new Edge(temp[0], temp[1], Math.log
            (Double.parseDouble(weight_tool[0])/Double.parseDouble(weight_tool[1]))));

        // if(weight > 1)
        //  arb.edges.add(new Edge(temp[0], temp[1], weight));
        // else if(weight < 1)
        //  arb.edges.add(new Edge(temp[0], temp[1], -1.0/weight));
        // else{
        //  arb.edges.add(new Edge(temp[0], temp[1], 1.0));
        //  arb.edges.add(new Edge(temp[1], temp[0],-1.0));                 
        // }    
    }

    public void test(){
        System.out.println();
        for(Edge i: edges){
            System.out.println(i.u + " -> " + i.v + ": " + i.weight);
        }
        
    }
}

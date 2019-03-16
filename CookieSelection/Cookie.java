import java.util.*;

public class Cookie{

    /*
        Two PriorityQueues are used: bottomHalf and upperHalf
        bottomHalf returns the max value whereas upperHalf returns a min value
        topHalf.size() >= bottomHalf.size()
    */
    public static void main (String[] args){

        //The following 4 lines of code was retrieved from the HackerRank YouTube channel
        //The name of the video is: "Data Structures: Solve 'Find the Running Median' Using Heaps"
        PriorityQueue<Integer> bottomHalf = new PriorityQueue<Integer>(new Comparator<Integer>(){       //Max heap;
            public int compare(Integer a, Integer b){
                return -1 * a.compareTo(b);
            }
        });

        PriorityQueue<Integer> upperHalf = new PriorityQueue<Integer>();        //Min Heap
        Scanner input = new Scanner(System.in);
        int insert;
        int temp;

        while(input.hasNext()){

            if (input.hasNextInt()){
                insert = input.nextInt();
                
                if(bottomHalf.size() != 0){
                    if(insert <= bottomHalf.peek()){
                        bottomHalf.add(insert);
                    }else{
                        upperHalf.add(insert);
                        bottomHalf.add(upperHalf.poll());
                    }
                }else{
                    bottomHalf.add(insert);
                }

                if(bottomHalf.size() > upperHalf.size()){
                    upperHalf.add(bottomHalf.poll());
                }

            }else{
                temp = upperHalf.poll();

                if(bottomHalf.size() > upperHalf.size()){
                    upperHalf.add(bottomHalf.poll());
                }

                input.next();

                System.out.println(temp);
            }
        }

    }
}

import java.util.*;
import java.awt.*;

public class Kastenlauf{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        WeightedQuickUnionUF uf;
        int testCases = input.nextInt();
        int stores;
        Point start, end;
        Point[] pArray;


        for(int i=0; i<testCases; i++){

            stores = input.nextInt();
            start = new Point(input.nextInt(), input.nextInt());
            uf = new WeightedQuickUnionUF(stores + 2);  //# of stores plus start and destination
            pArray = new Point[stores + 2];
            pArray[0] = start;

            //Adding stores to pArray
            for(int j=1; j<stores+1; j++){
                pArray[j] = new Point(input.nextInt(), input.nextInt());
            }

            //Adding the destination to pArray
            end = new Point(input.nextInt(), input.nextInt());
            pArray[stores + 1] = end;

            //Unioning locations that are within 1000m
            for(int j=0; j<stores+1; j++){
                for(int k= j+1; k<stores + 2; k++){
                    if (!isGreaterThan1000(pArray[j], pArray[k])){
                        uf.union(j, k);
                    }
                }
            }

            //index 0 is start and stores+1 is the destination
            if(uf.connected(0, stores + 1)){
                System.out.println("happy");
            }else{
                System.out.println("sad");
            }

        }
    }

    public static boolean isGreaterThan1000(Point start, Point end){
        if(Math.abs(end.getX() - start.getX()) + Math.abs(end.getY() - start.getY()) > 1000){
            return true;
        }else return false;
    }
}

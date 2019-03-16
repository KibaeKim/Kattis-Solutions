import java.util.*;

public class Kayak{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int S = input.nextInt();
        int R = input.nextInt();

        boolean[] damaged = new boolean[N+2];
        boolean[] reserved = new boolean[N+2];

        for (int i = 0; i < S; i++)
            damaged[input.nextInt()] = true;

        for(int i = 0; i < R; i++){
            int a = input.nextInt();
            if(damaged[a-1]) 
                damaged[a-1] = false;
            else if(damaged[a+1]) 
                damaged[a+1] = false;
        }
        
        int count = 0;
        for (int i = 0; i < N+2; i++){
            if(damaged[i]) 
                count++;
        }

        System.out.println(count);

    }
}

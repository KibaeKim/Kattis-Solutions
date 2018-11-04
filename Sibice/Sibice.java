import java.util.*;

public class Sibice{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt(); //Number of matches
		double max_size = Math.sqrt(Math.pow(input.nextInt(), 2) + Math.pow(input.nextInt(), 2));
		for(int i = 0 ; i < n; i++){
			if (input.nextInt() <= max_size)
				System.out.println("DA");
			else
				System.out.println("NE");
		}
	}
}
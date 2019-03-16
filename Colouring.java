import java.util.*;

public class Colouring
{
	int N;
	boolean[][] is_adjacent;
	Node[] colourful;
	int[] colour;

	public Colouring(Scanner input)
	{
		Scanner line;

		N = input.nextInt();
		colourful = new Node[N+1];
		is_adjacent = new boolean[N][N];
		colour = new int[N];
		input.nextLine();

		//Creating adjacency matrix
		for(int i = 0; i < N; i++){
			colourful[i+1] = new Node();
			line = new Scanner(input.nextLine());
			while(line.hasNextInt()){
				int j = line.nextInt();
				is_adjacent[i][j] = is_adjacent[j][i] = true;
			}
		}
	}

	//Checking if vertex can be coloured by c
	public boolean can_colour(int vertex, int c)
	{
		for(Integer i: colourful[c].ll)
			if(is_adjacent[vertex][i])
				return false;
		return true;
	}

	//Checking if graph can be coloured up to vertex using k colours
	public boolean graph_colour(int vertex, int k)
	{
		for(int i = 1; i <= k; i++){
			if(can_colour(vertex, i)){
				colour[vertex] = i;
				colourful[i].ll.add((Integer) vertex);
				if(vertex + 1 < N){
					if (graph_colour(vertex + 1, k))
						return true;
					else{
						colour[vertex] = 0;
						colourful[i].ll.remove((Integer) vertex);
					}
				}
				else
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args){
		Colouring graph = new Colouring(new Scanner(System.in));
		int i;
		//Finding the minimum value, i, where graph can be coloured using i colours
		for(i = 1; true; i++){
			if (graph.graph_colour(0, i)){
				System.out.println(i);
				return;
			}
		}
	}
}

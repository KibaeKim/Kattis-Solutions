import java.util.*;

public class Boggle
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Creating dictionary in lexicographic order
        HashMap dictionary = make_dictionary(input);

        int n = input.nextInt();
        char[][] board;
        boolean[][] visited;
        HashMap<String, Boolean> hm;

        for(int i = 0; i < n; i++){
            visited = new boolean[4][4];
            board = get_board(input);
            hm = new HashMap<String, Boolean>();

            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    find_words(board, visited, j, k, "", dictionary, hm);
                    
                }
            }
            String[] keyset = hm.keySet().toArray(new String[hm.keySet().size()]);
            Arrays.sort(keyset);
            String max = "";
            int score = 0;
            for(String key: keyset){
                if(key.length() == 3 || key.length() == 4)
                    score += 1;
                else if(key.length() == 5)
                    score+= 2;
                else if(key.length() == 6)
                    score += 3;
                else if(key.length() == 7)
                    score += 5;
                else if(key.length() >= 8)
                    score += 11;
                if (key.length() > max.length())
                    max = key;
            }
            System.out.println(score + " " + max + " " + keyset.length);
        }
    }

    public static void find_words(char[][] board, boolean[][] visited, int i, int j, 
        String str, HashMap dictionary, HashMap hm){
        visited[i][j] = true;
        str = str + board[i][j];

        if(dictionary.containsKey(str))
            hm.put(str, true);

        for(int row = i-1; row <= i+1 && row < 4 && str.length() < 8; row++)
            for(int col = j-1; col <= j+1 && col < 4; col++)
                if(row >= 0 && col >= 0 && !visited[row][col])
                    find_words(board, visited, row, col, str, dictionary, hm);

        str = str.substring(0,str.length());
        visited[i][j] = false;
    }

    public static HashMap make_dictionary(Scanner input){
        int n = input.nextInt();
        HashMap dictionary = new HashMap<String, Boolean>();

        for(int i = 0; i < n; i++)
            dictionary.put(input.next(), true);
        return dictionary;
    }

    public static char[][] get_board(Scanner input){
        String line;
        char[][] board = new char[4][4];

        for(int i = 0; i < 4; i++){
            line = input.next();
            for(int j = 0; j < 4; j++)
                board[i][j] = line.charAt(j);
        }

        return board;
    }
}

import java.util.*;
import java.io.*;
import java.lang.*;
import java.awt.Point;


public class day_17 {
	private static char[][] board;
	private static int max_y;
	private static int smallest_y;
	
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    try {
      Scanner input = new Scanner(new File("text.txt"));
      while (input.hasNextLine()) {
        list.add(input.nextLine());
      } 
      //Question 1
      System.out.print(waterCount(list));
      //Question 2
      
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    
  }
  public static int waterCount(List<String> list) {
    board = new char[2000][2000];
    max_y = -1;
		smallest_y = 2000;
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        board[i][j] = '.';
      }
    }
    for (String line : list) {
      Scanner sc = new Scanner(line);
      String in1 = sc.next();
      String in2 = sc.next();
      int coord = Integer.parseInt(in1.substring(2, in1.length() - 1));
      for (int i = Integer.parseInt(in2.substring(2, in2.indexOf("."))); i <= Integer.parseInt(in2.substring(in2.indexOf(".") + 2, in2.length())); i++) {
        if (in1.charAt(0) == 'x') {
          board[coord][i] = '#';
					if (i > max_y) {
						max_y = i;
					} if (i < smallest_y) {
						smallest_y = i;
					}
        } else {
          board[i][coord] = '#';
					if (coord > max_y) {
						max_y = coord;
					} if (coord < smallest_y) {
						smallest_y = coord;
					}
        }
      }
    }
		/*
    
		*/
		flow_down(500, smallest_y);
		int count = 0;
		for (int i = 150; i < 400; i++) {
      for (int j = 400; j < 700; j++) {
        if (board[j][i] == '~') {
					count++;
				}
				System.out.print(board[j][i]);
      }
			System.out.println();
    }
    return count;
  }
	
	public static void flow_down(int x, int y) {
		if (board[x][y] == '#' || (board[x][y] == '~' && board[x + 1][y - 1] == '#')) {
			System.out.println("fill " + x + " " + (y - 1));
			fill(x, y - 1);
		} else if (y > max_y || board[x][y] == '~') {
			return;
		} else {
			board[x][y] = '~';
			System.out.println("flow down " + x + " " + y);
			flow_down(x, y + 1);
		}
	}
	
	public static void fill(int x, int y) {
		board[x][y] = '~';
		boolean blocked = true;
		int temp_x = x - 1;
		int temp_y = y;
		
		while (board[temp_x][temp_y] != '#') { //left checking
			board[temp_x][temp_y] = '~';
			if (board[temp_x][temp_y + 1] == '.') {
				flow_down(temp_x, temp_y + 1);
				blocked = false;
				break;
			}
			temp_x--;
		}
		temp_x = x + 1;
		while (board[temp_x][temp_y] != '#') { //right checking
			board[temp_x][temp_y] = '~';
			if (board[temp_x][temp_y + 1] == '.') {
				flow_down(temp_x, temp_y + 1);
				blocked = false;
				break;
			}
			temp_x++;
		}
		if (blocked) {
			System.out.println("fill " + (temp_x - 1) + " " + (y - 1));
			fill(temp_x - 1, y - 1);
		}
	}
	
	
	
}

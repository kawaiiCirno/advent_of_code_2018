import java.util.*;
import java.io.*;


public class day_3 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    try {
      Scanner input = new Scanner(new File("text.txt"));
      while (input.hasNextLine()) {
        list.add(input.nextLine());
      } 
      //Question 1
      //System.out.print(overlap(list));
      //Question 2
      System.out.print(non_overlap_id(list));
      
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    
  }
  public static int overlap(List<String> list) {
    int[][] board = new int[1000][1000];
    for (String line : list) {
      Scanner sc = new Scanner(line);
      sc.next();
      sc.next();
      String coord = sc.next();
      String dimension = sc.next();
      int x_coord = Integer.parseInt(coord.substring(0, coord.indexOf(",")));
      int y_coord = Integer.parseInt(coord.substring(coord.indexOf(",") + 1, coord.length() - 1));
      int x_dim = Integer.parseInt(dimension.substring(0, dimension.indexOf("x")));
      int y_dim = Integer.parseInt(dimension.substring(dimension.indexOf("x") + 1, dimension.length()));
      
      for (int i = x_coord; i < x_coord + x_dim; i++) {
        for (int j = y_coord; j < y_coord + y_dim; j++) {
          board[i][j]++;
        }
      }
    }
    int count = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        if (board[i][j] > 1) {
          count++;
        }
      }
    }
    return count;
    
  }
  
  public static int non_overlap_id(List<String> list) {
    int[][] ids = new int[1000][1000];
    List<Integer> possible_no_overlap = new ArrayList<Integer>();
    for (String line : list) {
      Scanner sc = new Scanner(line);
      String full_id = sc.next();
      int id = Integer.parseInt(full_id.substring(1, full_id.length()));
      sc.next();
      String coord = sc.next();
      String dimension = sc.next();
      int x_coord = Integer.parseInt(coord.substring(0, coord.indexOf(",")));
      int y_coord = Integer.parseInt(coord.substring(coord.indexOf(",") + 1, coord.length() - 1));
      int x_dim = Integer.parseInt(dimension.substring(0, dimension.indexOf("x")));
      int y_dim = Integer.parseInt(dimension.substring(dimension.indexOf("x") + 1, dimension.length()));
      boolean not_overlapped = true;
      for (int i = x_coord; i < x_coord + x_dim; i++) {
        for (int j = y_coord; j < y_coord + y_dim; j++) {
          if (ids[i][j] == 0) {
            ids[i][j] = id;
            if (!possible_no_overlap.contains(id) && not_overlapped) {
              possible_no_overlap.add(id);
            }
          } else {
            possible_no_overlap.removeAll(Arrays.asList(ids[i][j]));
            possible_no_overlap.removeAll(Arrays.asList(id));
            not_overlapped = false;
          }
        }
      }
    }
    System.out.println(possible_no_overlap);
    return possible_no_overlap.get(0);    
  }
}

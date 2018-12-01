import java.util.*;
import java.io.*;


public class day_1 {
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<Integer>();
    try {
      Scanner input = new Scanner(new File("numbers.txt"));
      while (input.hasNextLine()) {
        list.add(Integer.parseInt(input.nextLine()));
      } 
      
      //Question 1
      //System.out.print(list_sum(list));
      //Question 2
      System.out.print(find_repeat(list));
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    
  }
  public static int list_sum(List<Integer> list) {
    int count = 0; 
    for (Integer i : list) {
      count += i;
    }
    return count;
  }
  
  public static int find_repeat(List<Integer> list) {
    int count = 0;
    List<Integer> repeat = new ArrayList<Integer>();
    repeat.add(0);
    while (true) {
      for (Integer i : list) {
        count += i;
        if (repeat.contains(count)) {
          return count;
        } else {
          repeat.add(count);
        }
      }
    }
  }
}

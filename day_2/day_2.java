import java.util.*;
import java.io.*;


public class day_2 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    try {
      Scanner input = new Scanner(new File("text.txt"));
      while (input.hasNextLine()) {
        list.add(input.nextLine());
      } 
      //Question 1
      //System.out.print(checksum(list));
      //Question 2
      System.out.print(checkId(list));
      
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    
  }
  public static int checksum(List<String> list) {
    int two_repeat = 0;
    int three_repeat = 0;
    for (String line : list) {
      int[] repeats = new int[26];
      for (int i = 0; i < line.length(); i++) {
        repeats[(int) line.charAt(i) - 97] += 1;
      }
      for (int i = 0; i < 26; i++) { 
        if (repeats[i] == 2) {
           two_repeat++;
           break;
        }
      }
      for (int i = 0; i < 26; i++) { 
        if (repeats[i] == 3) {
           three_repeat++;
           break;
        }
      }
    }
    return two_repeat * three_repeat;
  }
  
  public static String checkId(List<String> list) {
    int different_position = 0;
    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = i + 1; j < list.size(); j++) {
        if (compare_ids(list.get(i), list.get(j)) != -1) {
          different_position = compare_ids(list.get(i), list.get(j));
          return list.get(i).substring(0, different_position) + list.get(i).substring(different_position + 1, list.get(i).length());
        }
      }
    }
    return "java > python";
  }
  public static int compare_ids(String a, String b) {
    int different_position = -1;
    int different_counter = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        different_counter++;
        different_position = i;
        if (different_counter == 2) {
          return -1;
        }
      }
    }
    return different_position;
  }
}

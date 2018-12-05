import java.util.*;
import java.io.*;


public class day_4 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    try {
      Scanner input = new Scanner(new File("text.txt"));
      while (input.hasNextLine()) {
        list.add(input.nextLine());
      } 
      //Question 1
      //System.out.println(id_times_minute(list));
      //Question 2
      System.out.println(id_times_minute_2(list));
      
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    
  }
  public static int id_times_minute(List<String> list) {
    
    String[] sorted_list = list.toArray(new String[list.size()]);
    int[][] guard_times = new int[5000][61]; 
    int longest_guard_id = 0;
    int curr_guard = -1;
    int sleep_time_start = 0;
    
    Arrays.sort(sorted_list, new Comparator<String>() {
      public int compare(String str1, String str2) {
        if (Integer.parseInt(str1.substring(6, 8)) < Integer.parseInt(str2.substring(6, 8))) {
          return -1;
        } else if (Integer.parseInt(str1.substring(6, 8)) > Integer.parseInt(str2.substring(6, 8))) {
          return 1;
        } else {
          if (Integer.parseInt(str1.substring(9, 11)) < Integer.parseInt(str2.substring(9, 11))) {
            return -1;
          } else if (Integer.parseInt(str1.substring(9, 11)) > Integer.parseInt(str2.substring(9, 11))) {
            return 1;
          } else {
            if (Integer.parseInt(str1.substring(12, 14)) < Integer.parseInt(str2.substring(12, 14))) {
              return -1;
            } else if (Integer.parseInt(str1.substring(12, 14)) > Integer.parseInt(str2.substring(12, 14))) {
              return 1;
            } else {
              if (Integer.parseInt(str1.substring(15, 17)) < Integer.parseInt(str2.substring(15, 17))) {
                return -1;
              } else if (Integer.parseInt(str1.substring(15, 17)) > Integer.parseInt(str2.substring(15, 17))) {
                return 1;
              } else {
                return 0;
              }
            }
          }
        }
      }
    });
    
    for (String line : sorted_list) {
      Scanner sc = new Scanner(line);
      System.out.println(line);
      sc.next();
      String time = sc.next();
      String action = sc.next();
      if (action.equals("Guard")) {
        curr_guard = Integer.parseInt(sc.next().substring(1));
      } else if (action.equals("falls")) {
        sleep_time_start = Integer.parseInt(time.substring(3, 5));
      } else {
        System.out.println("start: " + sleep_time_start);
        System.out.println("end: " + time.substring(3, 5));
        for (int i = sleep_time_start; i < Integer.parseInt(time.substring(3, 5)); i++) {
          guard_times[curr_guard][i]++;
          guard_times[curr_guard][60]++;
        }
        System.out.println(guard_times[curr_guard][60] + ">=" + guard_times[longest_guard_id][60]);
        if (guard_times[curr_guard][60] >= guard_times[longest_guard_id][60]) {
            longest_guard_id = curr_guard;
        }
        //DEBUG
        for (int i = 0; i < 61; i++) {
          System.out.print(guard_times[curr_guard][i] + " ");
        }
        System.out.println();
      }
    }
    System.out.println(longest_guard_id);
    int largest_index = 0;
    for (int i = 0; i < 60; i++) {
      System.out.print(guard_times[longest_guard_id][i] + " ");
      if (guard_times[longest_guard_id][i] >= guard_times[longest_guard_id][largest_index]) {
        largest_index = i;
      }
    }
    return longest_guard_id * largest_index;
  }
  public static int id_times_minute_2(List<String> list) {
    
    String[] sorted_list = list.toArray(new String[list.size()]);
    int[][] guard_times = new int[5000][61]; 
    int curr_guard = -1;
    int sleep_time_start = 0;
    TreeSet<Integer> guards = new TreeSet<Integer>();
    
    Arrays.sort(sorted_list, new Comparator<String>() {
      public int compare(String str1, String str2) {
        if (Integer.parseInt(str1.substring(6, 8)) < Integer.parseInt(str2.substring(6, 8))) {
          return -1;
        } else if (Integer.parseInt(str1.substring(6, 8)) > Integer.parseInt(str2.substring(6, 8))) {
          return 1;
        } else {
          if (Integer.parseInt(str1.substring(9, 11)) < Integer.parseInt(str2.substring(9, 11))) {
            return -1;
          } else if (Integer.parseInt(str1.substring(9, 11)) > Integer.parseInt(str2.substring(9, 11))) {
            return 1;
          } else {
            if (Integer.parseInt(str1.substring(12, 14)) < Integer.parseInt(str2.substring(12, 14))) {
              return -1;
            } else if (Integer.parseInt(str1.substring(12, 14)) > Integer.parseInt(str2.substring(12, 14))) {
              return 1;
            } else {
              if (Integer.parseInt(str1.substring(15, 17)) < Integer.parseInt(str2.substring(15, 17))) {
                return -1;
              } else if (Integer.parseInt(str1.substring(15, 17)) > Integer.parseInt(str2.substring(15, 17))) {
                return 1;
              } else {
                return 0;
              }
            }
          }
        }
      }
    });
    
    for (String line : sorted_list) {
      Scanner sc = new Scanner(line);
      System.out.println(line);
      sc.next();
      String time = sc.next();
      String action = sc.next();
      if (action.equals("Guard")) {
        curr_guard = Integer.parseInt(sc.next().substring(1));
        guards.add(curr_guard);
      } else if (action.equals("falls")) {
        sleep_time_start = Integer.parseInt(time.substring(3, 5));
      } else {
        System.out.println("start: " + sleep_time_start);
        System.out.println("end: " + time.substring(3, 5));
        for (int i = sleep_time_start; i < Integer.parseInt(time.substring(3, 5)); i++) {
          guard_times[curr_guard][i]++;
          guard_times[curr_guard][60]++;
        }
      }
    }
    int largest_index = 0;
    int largest_minute = 0;
    int largest_guard_id = 0;
    for (int current_guard : guards) {
      for (int i = 0; i < 60; i++) {
        if (guard_times[current_guard][i] > guard_times[largest_guard_id][largest_index]) {
          largest_index = i;
          largest_minute = guard_times[current_guard][i];
          largest_guard_id = current_guard;
        }
      }
    }
    return largest_guard_id * largest_index;
  }
  
}

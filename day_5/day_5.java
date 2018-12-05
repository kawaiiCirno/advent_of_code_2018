import java.util.*;
import java.io.*;
import java.lang.*;


public class day_5 {
  public static void main(String[] args) {

    try {
      String in = "";
      Scanner input = new Scanner(new File("text.txt"));
      while (input.hasNext()) {
        in = input.next();
      } 
      //Question 1
      //System.out.print(react(in));
      //Question 2
      System.out.print(shortestReact(in));
      
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    
  }
  public static int react(String polymers) {
    boolean wasNotChanged = true;
    while (wasNotChanged) {
      wasNotChanged = false;
      for (int i = 0; i < polymers.length() - 1; i++) {
        if (Math.abs(polymers.charAt(i) - polymers.charAt(i + 1)) == 32) {
          polymers = polymers.substring(0, i) + polymers.substring(i + 2);
          wasNotChanged = true;
        }
      }
    }
    return polymers.length();
  }
  
  public static int shortestReact(String polymers) {
    int min = Integer.MAX_VALUE;
    for (int chars = 65; chars <= 90; chars++) {
      String modifiedPolymer = polymers.replace(Character.toString((char) chars), "");
      modifiedPolymer = modifiedPolymer.replace(Character.toString((char) (chars + 32)), "");
      int reaction = react(modifiedPolymer);
      if (reaction < min) {
        min = reaction;
      }
      
      /*
      for (int i = 0; i < modifiedPolymer.length(); i++) {
        if (polymers.charAt(i) == (char)chars || polymers.charAt(i) == (char)(chars + 32)) {
          polymers = polymers.substring(0, i) + polymers.substring(i + 1);
        }
      }
      */
    }
    return min;
  }
}

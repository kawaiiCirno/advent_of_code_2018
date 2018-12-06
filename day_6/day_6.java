import java.util.*;
import java.io.*;
import java.lang.*;
import java.awt.Point;


public class day_6 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    try {
      Scanner input = new Scanner(new File("text.txt"));
      while (input.hasNextLine()) {
        list.add(input.nextLine());
      } 
      //Question 1
      System.out.print(largest_area(list));
      //Question 2
      
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
    
  }
  public static int largest_area(List<String> list) {
    HashSet<Point> coords = new HashSet<Point>();
    int largest_x = -1;
    int largest_y = -1;
    int smallest_x = 9999;
    int smallest_y = 9999;
    for (String line : list) {
      Scanner sc = new Scanner(line);
      String x = sc.next();
      String y = sc.next();
      int x_coord = Integer.parseInt(x.substring(0, x.indexOf(",")));
      int y_coord = Integer.parseInt(y);
      if (x_coord > largest_x) {
        largest_x = x_coord;
      }
      if (x_coord < smallest_x) {
        smallest_x = x_coord;
      }
      if (y_coord > largest_y) {
        largest_y = y_coord;
      }
      if (y_coord < smallest_y) {
        smallest_y = y_coord;
      }
      coords.add(new Point(x_coord, y_coord));
    }
    /*
    for (Point point : enclosed_coords) {
      System.out.println(point);
    }
    */
    HashSet<Point> enclosed_coords = find_enclosed(coords);
    PriorityQueue<Integer> enclosed_areas = new PriorityQueue<>(10, Collections.reverseOrder());
    for (Point point : enclosed_coords) {
      int area = 0;
      for (int x = smallest_x; x <= largest_x; x++) {
        for (int y = smallest_y; y <= largest_y; y++) {
          Point comparing_point = new Point(x, y);
          int dist = manhattan_distance(comparing_point, point); //distance from comparing point to box coord 
          /*
          if (manhattan_distance(comparing_point, closest_ne) > dist && manhattan_distance(comparing_point, closest_nw) > dist
          && manhattan_distance(comparing_point, closest_sw) > dist && manhattan_distance(comparing_point, closest_se) > dist) {
            area++;
          }
          */
          boolean already_closer = true;
          for (Point point2 : coords) {
            if (manhattan_distance(comparing_point, point2) < dist) {
              already_closer = false;
            }
          }
          if (already_closer) {
              area++;
          }
        }
      }
      enclosed_areas.add(area);
    }
    return enclosed_areas.peek();
    
  }
  public static int manhattan_distance(Point p1, Point p2) {
    return (int) (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
  }
  
  public static HashSet<Point> find_enclosed(HashSet<Point> coords) {
    HashSet<Point> enclosed_coords = new HashSet<Point>();
    for (Point point : coords) {
      boolean enclosed_ne = false;
      boolean enclosed_nw = false;
      boolean enclosed_sw = false;
      boolean enclosed_se = false;
      for (Point checking_point : coords) {
        if (checking_point.getX() > point.getX() && checking_point.getY() > point.getY()) {
          //NE
          enclosed_ne = true;
        } else if (checking_point.getX() < point.getX() && checking_point.getY() > point.getY()) {
          //NW
          enclosed_nw = true;
        } else if (checking_point.getX() < point.getX() && checking_point.getY() < point.getY()) {
          //SW
          enclosed_sw = true;
        } else if (checking_point.getX() > point.getX() && checking_point.getY() < point.getY()) {
          //SE
          enclosed_se = true;
        } 
      }
      if (enclosed_ne && enclosed_nw && enclosed_sw && enclosed_se) {
        enclosed_coords.add(point);
      }
    }
    return enclosed_coords;
  }
  

/*
Oops i could of used a manhattan distance method to make this shorter and simpler :P
  public static Point find_closest_point_ne(Point point, HashSet<Point> coords) {
    Point closest_point = null;
    for (Point checking_point : coords) {
      if (checking_point.getX() > point.getX() && checking_point.getY() > point.getY()) {
        if (closest_point == null 
        || checking_point.getX() - point.getX() + checking_point.getY() - point.getY() < closest_point.getX() - point.getX() + closest_point.getY() - point.getY()) {
          closest_point = checking_point;
        }
      }
    }
    return closest_point;
  }
  public static Point find_closest_point_nw(Point point, HashSet<Point> coords) {
    Point closest_point = null;
    for (Point checking_point : coords) {
      if (checking_point.getX() < point.getX() && checking_point.getY() > point.getY()) {
        if (closest_point == null 
        || point.getX() - checking_point.getX() + checking_point.getY() - point.getY() < point.getX() - closest_point.getX() + closest_point.getY() - point.getY()) {
          closest_point = checking_point;
        }
      }
    }
    return closest_point;
  }
  public static Point find_closest_point_sw(Point point, HashSet<Point> coords) {
    Point closest_point = null;
    for (Point checking_point : coords) {
      if (checking_point.getX() < point.getX() && checking_point.getY() < point.getY()) {
        if (closest_point == null 
        || point.getX() - checking_point.getX() + point.getY() - checking_point.getY() < point.getX() - closest_point.getX() + point.getY() - closest_point.getY()) {
          closest_point = checking_point;
        }
      }
    }
    return closest_point;
  }
  public static Point find_closest_point_se(Point point, HashSet<Point> coords) {
    Point closest_point = null;
    for (Point checking_point : coords) {
      if (checking_point.getX() > point.getX() && checking_point.getY() < point.getY()) {
        if (closest_point == null 
        || checking_point.getX() - point.getX() + point.getY() - checking_point.getY() < closest_point.getX() - point.getX() + point.getY() - closest_point.getY()) {
          closest_point = checking_point;
        }
      }
    } 
    return closest_point;
  }
  */
}

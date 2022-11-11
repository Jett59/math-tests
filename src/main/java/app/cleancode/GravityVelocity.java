package app.cleancode;

public class GravityVelocity {
public static void main(String[] args) {
  double velocity = 1;
  double y = 0;
  while (velocity > 0) {
    y += velocity / 1048576;
    velocity -= 1d / 1048576;
    System.out.println(y);
  }
  System.out.println();
  System.out.println(velocity);
  System.out.println(y);
}
}

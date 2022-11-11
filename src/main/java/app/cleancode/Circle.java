package app.cleancode;

public class Circle {
  private static double estimatePiSlow(double yIncrement) {
    double circumference = 0;
    for (double y = yIncrement; y <= 1; y += yIncrement) {
      double currentX = Math.sqrt(1 - y * y);
      double lastX = Math.sqrt(1 - y * y + 2 * yIncrement * y - yIncrement * yIncrement);
      double distance = Math.sqrt(Math.pow(currentX - lastX, 2) + yIncrement * yIncrement);
      circumference += distance;
    }
    circumference *= 4;
    double calculatedPi = circumference / 2;
    return calculatedPi;
  }

  private static double estimatePiFast(double yIncrement) {
    double y = 0;
    double currentX = Math.sqrt(1 - y * y);
    double lastX = Math.sqrt(1 - y * y + 2 * yIncrement * y - yIncrement * yIncrement);
    double distance = Math.sqrt(Math.pow(currentX - lastX, 2) + yIncrement * yIncrement);
    double circumference = 4 * (1 / yIncrement) * distance;
    return circumference / 2;
  }

  private static final double Y_INCREMENT = 1.0 / Math.pow(2, 28);

  public static void main(String[] args) {
    long start = System.nanoTime();
    double pi = estimatePiSlow(Y_INCREMENT);
    long timeTaken = System.nanoTime() - start;
    System.out.println(pi);
    System.out.printf("Calculated in %.3fs\n", timeTaken / 1000000000d);
    pi = estimatePiFast(Y_INCREMENT);
    System.out.println(pi);
  }
}

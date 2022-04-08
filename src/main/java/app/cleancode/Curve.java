package app.cleancode;

public class Curve {
    private static double increment = 0.0315;
    private static final double decrement = 0.0005;

    public static void main(String[] args) {
        double triangularIncrement = increment / decrement;
        System.out.println("Probably will be: "
                + (triangularIncrement * triangularIncrement + triangularIncrement) / 2
                        * decrement);
        double increment = Curve.increment;
        double result = 0;
        int i;
        for (i = 0; i < 65536 && increment > 0; i++) {
            result += increment;
            increment -= decrement;
        }
        System.out.println(result);
        System.out.println("in " + i + " iterations");
    }
}

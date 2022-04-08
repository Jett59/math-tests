package app.cleancode;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("ax2+bx+c = 0");
            System.out.print("a = ");
            BigDecimal a = new BigDecimal(scanner.nextLine());
            System.out.print("b = ");
            BigDecimal b = new BigDecimal(scanner.nextLine());
            System.out.print("c = ");
            BigDecimal c = new BigDecimal(scanner.nextLine());
            solve(a, b, c);
        }
    }

    private static void solve(BigDecimal a, BigDecimal b, BigDecimal c) {
        System.out.printf("x = (-%s\u00B1sqrt(%s\u00B2-4*%s*%s))/(2*%s)\n", b, b, a, c, a);
        BigDecimal discriminant = b.pow(2).subtract(new BigDecimal(4).multiply(a).multiply(c));
        if (discriminant.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("No real solution");
        } else if (discriminant.compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal solution =
                    b.negate().divide(a.multiply(new BigDecimal(2)), MathContext.DECIMAL128);
            System.out.printf("x = %s\n", solution);
        } else {
            BigDecimal solution1 = b.negate().subtract(discriminant.sqrt(MathContext.DECIMAL128))
                    .divide(new BigDecimal(2).multiply(a), MathContext.DECIMAL128);
            BigDecimal solution2 = b.negate().add(discriminant.sqrt(MathContext.DECIMAL128))
                    .divide(new BigDecimal(2).multiply(a), MathContext.DECIMAL128);
            System.out.printf("x = %s or %s\n", solution1, solution2);
        }
    }
}

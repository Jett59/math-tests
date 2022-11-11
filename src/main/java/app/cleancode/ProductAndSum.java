package app.cleancode;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class ProductAndSum {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("s = sum");
            System.out.println("p = product");
            System.out.print("s = ");
            BigDecimal sum = new BigDecimal(scanner.nextLine());
            System.out.print("p = ");
            BigDecimal product = new BigDecimal(scanner.nextLine());
            System.out.printf("x+y = %s\n", sum);
            System.out.printf("xy = %s\n", product);
            System.out.printf("y = %s-x\n", sum);
            System.out.printf("x(%s-x) = %s\n", sum, product);
            System.out.printf("%sx-x\u00b2 = %s\n", sum, product);
            System.out.printf("-x\u00B2+%sx-%s = 0\n", sum, product);
            solve(BigDecimal.ONE.negate(), sum, product.negate());
        }
    }

    private static void solve(BigDecimal a, BigDecimal b, BigDecimal c) {
        System.out.printf("x = (-%s\u00B1sqrt(%s\u00B2-4*%s*%s))/(2*%s)\n", b, b, a, c, a);
        BigDecimal discriminant = b.pow(2).subtract(new BigDecimal(4).multiply(a).multiply(c));
        if (discriminant.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("No such thing");
        } else if (discriminant.compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal solution =
                    b.negate().divide(a.multiply(new BigDecimal(2)), MathContext.DECIMAL128);
            System.out.printf("x = y = %s\n", solution);
        } else {
            BigDecimal solution1 = b.negate().subtract(discriminant.sqrt(MathContext.DECIMAL128))
                    .divide(new BigDecimal(2).multiply(a), MathContext.DECIMAL128);
            BigDecimal solution2 = b.negate().add(discriminant.sqrt(MathContext.DECIMAL128))
                    .divide(new BigDecimal(2).multiply(a), MathContext.DECIMAL128);
            System.out.printf("x = %s\n", solution1);
            System.out.printf("y = %s\n", solution2);
        }
    }
}

package app.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Ratio {
    public static class Fraction {
        long numerator, denominator;

        public String toString() {
            return numerator + "/" + denominator;
        }

        public static Fraction from(String str) {
            String[] parts = str.split("/");
            Fraction result = new Fraction();
            result.numerator = Long.parseLong(parts[0]);
            result.denominator = Long.parseLong(parts[1]);
            return result;
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Fraction a = Fraction.from(input.nextLine());
        Fraction b = Fraction.from(input.nextLine());
        List<Long> aprimes = new ArrayList<>();
        addPrimes(a.denominator, aprimes);
        List<Long> bprimes = new ArrayList<>();
        addPrimes(b.denominator, bprimes);
        List<Long> aprimes2 = getUnique(aprimes, bprimes);
        List<Long> bprimes2 = getUnique(bprimes, aprimes);
        for (long l : bprimes2) {
            a.numerator *= l;
            a.denominator *= l;
        }
        for (long l : aprimes2) {
            b.denominator *= l;
            b.numerator *= l;
        }
        System.out.print(a);
        System.out.print(":");
        System.out.println(b);
        long bn = b.numerator;
        long an = a.numerator;
        System.out.print(an);
        System.out.print(":");
        System.out.println(bn);
        aprimes.clear();
        addPrimes(an, aprimes);
        bprimes.clear();
        addPrimes(bn, bprimes);
        List<Long> cprimes = getInCommon(aprimes, bprimes);
        if (cprimes.size() > 0) {
            long divisor = 1;
            for (long l : cprimes) {
                divisor *= l;
            }
            System.out.println("/" + divisor);
            an /= divisor;
            bn /= divisor;
            System.out.println(an + ":" + bn);
        }
    }

    public static void addPrimes(long number, List<Long> result) {
        if (number == 0) {
            return;
        }
        while (number != 1) {
            final long numberCopy = number;
            OptionalLong prime = LongStream.rangeClosed(2, (long) Math.sqrt(number))
                    .filter(l -> numberCopy % l == 0).findFirst();
            long primeNumber = prime.orElse(number);
            number /= primeNumber;
            result.add(primeNumber);
        }
    }

    public static List<Long> getInCommon(List<Long> a, List<Long> b) {
        List<Long> bCopy = new ArrayList<>(b);
        return a.stream().filter(l -> {
            if (bCopy.contains(l)) {
                bCopy.remove(l);
                return true;
            } else {
                return false;
            }
        }).toList();
    }

    public static List<Long> getUnique(List<Long> a, List<Long> b) {
        List<Long> bCopy = new ArrayList<>(b);
        return a.stream().filter(l -> {
            if (bCopy.contains(l)) {
                bCopy.remove(l);
                return false;
            } else {
                return true;
            }
        }).toList();
    }

}

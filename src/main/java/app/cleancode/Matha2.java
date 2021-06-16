package app.cleancode;

import java.util.Arrays;

public class Matha2 {
public static void main(String[] args) {
	findFactors (-2147483648);
}
public static final int [] primes = new int [] {
		2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31
};
public static void findFactors (int number) {
	int tmp = number;
	while (Math.abs(tmp) != 1) {
		for (int prime : primes) {
			if (tmp % prime == 0) {
				System.out.print(prime);
				System.out.print(" * ");
				tmp = tmp / prime;
			}
		}
	}
}
}

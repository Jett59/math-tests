package app.cleancode;

import java.util.HashSet;
import java.util.Set;

public class Matha1 {
public static void main(String[] args) {
	Set<Triad> triads = new HashSet<>();
	for (double a = 0; a < 100; a ++) {
		for (double b = 0; b < 100; b ++) {
			for (double c = 0; c < 100; c ++) {
				if (triangle_angle (a, b, c) == 90d) {
					triads.add(new Triad(a, b, c));
				}
			}
		}
	}
	triads.forEach(triad -> {
		System.out.printf("%.0f, %.0f, %.0f\n", triad.a, triad.b, triad.c);
	});
}
public static double triangle_angle(double a, double b, double c) {
	return Math.toDegrees(Math.acos((a*a + b*b - c*c) / (2*a*b)));
}
public static class Triad {
	private final double a, b, c;
	public Triad(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public int hashCode() {
		return (int) (a + b + c);
	}
	@Override
	public boolean equals(Object obj) {
		return obj.hashCode() == hashCode ();
	}
}
}

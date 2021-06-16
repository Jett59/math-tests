package app.cleancode;

import java.io.BufferedInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matha3 {
public static void main(String[] args) {
	BufferedInputStream input = new BufferedInputStream(System.in);
	Scanner scanner = new Scanner(input);
	String line;
	double last = 0;
	while (!((line = scanner.nextLine()).contains("done"))) {
		try {
			last = interpret(line.replace(" ", ""), last);
		}catch (IllegalArgumentException e) {
			System.err.printf("Error: %s\n", e.getMessage());
		}
	}
	System.out.println("finished at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
}
public static final Pattern brackets = Pattern.compile("\\((.*?)\\)");
public static String resolvBrackets (String phraze, double last) {
	while (phraze.contains("(")) {
	int start = -1;
	for (int i = 0; i < phraze.length(); i ++) {
		if (phraze.charAt(i) == '(')  {
			start = i;
		}else if (phraze.charAt(i) == ')') {
			String bracketContents = phraze.substring(start + 1, i);
			phraze = phraze.replace(String.format("(%s)", bracketContents), String.format("%f", evaluate(bracketContents, last)));
			break;
		}
	}
	}
	return phraze;
}
public static double interpret (String line, double last) {
	line = resolvBrackets (line, last);
	double result = evaluate(line, last);
	System.out.println(result);
	return result;
}
public static final Pattern numbers = Pattern.compile("^.?([\\d.]+)");
public static String findNumericalString (String phraze) {
	Matcher matcher = numbers.matcher(phraze);
	if (matcher.find()) {
		return phraze.substring(0, matcher.end());
	}else {
		throw new IllegalArgumentException ("phraze " + phraze + " does not contain numbers");
	}
}
public static double evaluate (String phraze, double last) {
	double result = last;
	for (int i = 0; i < phraze.length(); i ++) {
		char c = phraze.charAt(i);
		if (Character.isDigit(c)) {
			String number = findNumericalString(phraze.substring(i));
			i += number.length() - 1;
			result = Double.parseDouble(number);
			continue;
		}
		switch (c) {
		case '+': {
			String numericalString = findNumericalString(phraze.substring(i + 1));
			i += numericalString.length();
			result += Double.parseDouble(numericalString);
			break;
		}
		case '-': {
			String numericalString = findNumericalString(phraze.substring(i + 1));
			i += numericalString.length();
			result -= Double.parseDouble(numericalString);
			break;
		}
		case '*': {
			String numericalString = findNumericalString(phraze.substring(i + 1));
			i += numericalString.length();
			result *= Double.parseDouble(numericalString);
			break;
		}
		case '/': {
			String numericalString = findNumericalString(phraze.substring(i + 1));
			i += numericalString.length();
			result /= Double.parseDouble(numericalString);
			break;
		}
		case '^': {
			String numericalString = findNumericalString(phraze.substring(i + 1));
			i += numericalString.length();
			result = Math.pow(result, Double.parseDouble(numericalString));
			break;
		}
		default:
			throw new IllegalArgumentException("character "+c+" not defined\n"
					+ "@Char "+i);
		}
	}
	return result;
}
}

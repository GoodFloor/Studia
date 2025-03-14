package exercises.homework_07;
import java.util.Scanner;

import exercises.homework_07.model.Person;
import exercises.homework_07.model.Student;

public class Problem {

	public static void main(String[] args) {
		problem01Incrementer("5");

		problem02Ping(new Scanner(System.in));

		problem03PrintGrade(new Student("John", "Bambo", 5));

		problem04PrintArray(args);

		problem05Print();

	}

	/**
	 * Converts a string to integer, increments a value and prints a message on
	 * System.out.
	 * 
	 * @param numberAsString
	 */
	private static void problem01Incrementer(String numberAsString) {
		System.out.println("problem01Incrementer()");

		int result = Integer.parseInt(numberAsString);
		result++;
		System.out.println("When we increment the number " + numberAsString + " we get: " + result);
	}

	/**
	 * Outputs the lines from the scanner. The method will terminate once the lines
	 * is equal to "q".
	 * 
	 * @param s
	 */
	private static void problem02Ping(Scanner s) {
		System.out.println("problem02Ping()");

		while (s.hasNext()) {
			String line = s.nextLine();
			if (line.equals("q")) {
				break;
			} else
				System.out.println(line);
		}
	}

	/**
	 * Downcasts from person to student and prints the grade.
	 * 
	 * @param person
	 */
	private static void problem03PrintGrade(Person person) {
		System.out.println("problem03PrintGrade()");

		Student student = (Student) person;
		System.out.println("Grade: " + student.getGrade());

	}

	/**
	 * Prints all the elements from an input array.
	 * 
	 * @param array
	 */
	private static void problem04PrintArray(String[] array) {
		System.out.println("problem04PrintArray()");

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	/**
	 * Prints the "problem05Print()"
	 * 
	 * @return
	 */
	private static String problem05Print() {
		System.out.println("problem05Print()");

		return "problem05Print()";
	}

}
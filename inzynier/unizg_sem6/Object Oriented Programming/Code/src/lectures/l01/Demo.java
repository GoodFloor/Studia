package lectures.l01;

import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("Hello world!");
		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter your age: ");
//		while (sc.hasNextLine()) {
//			int age = Integer.parseInt(sc.nextLine());
//			if (age < 18) {
//				System.out.println("You are a child");
//			} else {
//				System.out.println("You are an adult");
//			}
//			System.out.print("Enter your age or finish: ");
//		}
		sc.close();
		String text = "Quick brown fox jumps over lazy dogs.";
		String[] arr = text.split("[ou]");
//		String[] arr = text.split(" ");
//		String[] arr = text.split("[ .]");
		for (String s : arr) {
			System.out.println(s);
		}
		String s = String.join("/", arr);
		System.out.println(s);
		
	}

}

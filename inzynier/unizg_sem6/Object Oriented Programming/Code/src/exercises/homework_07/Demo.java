package exercises.homework_07;
public class Demo {

	public static void main(String[] args) {
		Resource r1 = new Resource(1);
		try {
			try (r1; Resource r2 = new Resource(2)) {
				System.out.println("try");
				Integer.parseInt("zero");
			} catch (NumberFormatException e) {
				throw new RuntimeException("wrapped exception", e);
			} finally {
				System.out.println("finally");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause().getMessage());
		} finally {
			System.out.println("finally 2");
		}
	}
}
package exercises.homework_07;

public class Resource implements AutoCloseable {
	private int id;

	public Resource(int id) {
		this.id = id;
	}

	@Override
	public void close() {
		System.out.println("R" + id + " is now closed!");
	}
}

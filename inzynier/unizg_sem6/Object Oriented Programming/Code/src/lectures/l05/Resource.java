package lectures.l05;
public class Resource implements AutoCloseable {

    public void test() throws Exception {
        throw new Exception("Exception");
    }

    public void test2() {

    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing");
    }
    
}

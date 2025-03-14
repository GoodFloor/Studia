package lectures.l05;
public class MyCustomException extends RuntimeException {
    Throwable cause;

    public MyCustomException(Throwable e) {
        this.cause = e;
    }
    @Override
    public String getMessage() {
        return "\nCaused by: " + cause;
    }

}

package laboratories.lab03;
public class Demo2 {
    public static void main(String[] args) {
        try {
            DateUtils.checkDateFormatValidity("01.04.");
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            DateUtils.checkDateFormatValidity("aa.12.2020");
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            DateUtils.checkDateFormatValidity("31.04.1994");
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            DateUtils.checkDateFormatValidity("31.24.2002");
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            DateUtils.checkDateFormatValidity("30.04.2022");
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            DateUtils.checkDateFormatValidity("01.04.2002");
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

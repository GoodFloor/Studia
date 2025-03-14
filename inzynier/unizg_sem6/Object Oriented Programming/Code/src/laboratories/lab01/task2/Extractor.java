package laboratories.lab01.task2;

public class Extractor {
    public static String withoutNumbers(String str) {
        StringBuilder current = new StringBuilder();
        StringBuilder output = new StringBuilder();
        boolean hadNumber = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                if (current.length() > 0 && hadNumber) {
                    output.append(current);
                    output.append(" ");
                }
                hadNumber = false;
                current = new StringBuilder();
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                hadNumber = true;
            } else {
                current.append(str.charAt(i));
            }
        }
        if (current.length() > 0 && hadNumber) {
            output.append(current);
            output.append(" ");
        }
        return output.toString();
    }
}

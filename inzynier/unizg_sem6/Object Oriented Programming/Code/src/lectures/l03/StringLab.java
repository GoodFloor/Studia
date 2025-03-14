package lectures.l03;
public class StringLab {
    public static String stringFloss(String[] elements) {
        StringBuilder sb = new StringBuilder();
        int longestString = 0;
        for (String string : elements) {
            if (string.length() > longestString) {
                longestString = string.length();
            }
        }
        for (int i = 0; i < longestString; i++) {
            for (String string : elements) {
                if (string.length() > i) {
                    sb.append(string.charAt(i));
                }
            }
        }
        return sb.toString();
    }
    public static String upsideDown(String input) {
        String upper = input.toUpperCase();
        String lower = input.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (upper.charAt(i) == lower.charAt(i)) {
                sb.append(input.charAt(i));
            } else if (upper.charAt(i) == input.charAt(i)) {
                sb.append(lower.charAt(i));
            } else {
                sb.append(upper.charAt(i));
            }
        }
        return sb.toString();
    }
}

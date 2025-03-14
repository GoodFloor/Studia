package lectures.l13;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class CompareDemo {
    public static void main(String[] args) {
        // We can compare letters but it doesn't work well for non-english letters
        System.out.println("Normal comparing");
        System.out.println("B".compareTo("C")); // -1
        System.out.println("B".compareTo("Ć")); // -196
        System.out.println("Ć".compareTo("D")); // 194

        // Solution is using Local comparator for specific language (e.g. polish - pl, croatian - hr, etc.)
        System.out.println("\nLocal comparator");
        Comparator<Object> plComp = Collator.getInstance(Locale.forLanguageTag("pl"));
        System.out.println(plComp.compare("B", "C"));   // -1
        System.out.println(plComp.compare("C", "Ć"));   // -1
        System.out.println(plComp.compare("Ć", "D"));   // -1

    }
}

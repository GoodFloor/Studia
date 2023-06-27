import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Run {
    public static void main(String[] args) {
        if(args.length == 0)
            return;
        String PATH_TO_FX = "D:\\Apps\\Programowanie\\Java\\javafx-sdk-18.0.1\\lib";
        String compileCommand = "javac --module-path " + PATH_TO_FX + " --add-modules javafx.controls ";
        String runCommand = "java --module-path " + PATH_TO_FX + " --add-modules javafx.controls ";
        compileCommand += args[0] + ".java";
        runCommand += args[0];
        try {
            Process execC;
            Process execR;
            if(args.length == 1) {
                execC = Runtime.getRuntime().exec(compileCommand);
                execC.waitFor();
                execR = Runtime.getRuntime().exec(runCommand);

                BufferedReader inC = new BufferedReader(new InputStreamReader(execC.getInputStream()));
                BufferedReader errC = new BufferedReader(new InputStreamReader(execC.getErrorStream()));
                String out = inC.readLine();
                while(out != null) {
                    out = inC.readLine();
                }
                inC.close();

                out = errC.readLine();
                while(out != null) {
                    out = errC.readLine();
                }
                errC.close();
            }
            else if(args[1] == "c") {
                execC = Runtime.getRuntime().exec(compileCommand);

                BufferedReader inC = new BufferedReader(new InputStreamReader(execC.getInputStream()));
                BufferedReader errC = new BufferedReader(new InputStreamReader(execC.getErrorStream()));
                String out = inC.readLine();
                while(out != null) {
                    out = inC.readLine();
                }
                inC.close();

                out = errC.readLine();
                while(out != null) {
                    out = errC.readLine();
                }
                errC.close();
            }
            else if(args[1] == "r")
                execR = Runtime.getRuntime().exec(runCommand);
            else {
                System.out.println("Nieprawidłowy argument: \"" + args[1] + "\"");
                return;
            }
            /*BufferedReader inC = new BufferedReader(new InputStreamReader(execC.getInputStream()));
            BufferedReader errC = new BufferedReader(new InputStreamReader(execC.getErrorStream()));
            BufferedReader inR = new BufferedReader(new InputStreamReader(execR.getInputStream()));
            BufferedReader errR = new BufferedReader(new InputStreamReader(execR.getErrorStream()));

            dane.setText("");
            wynik.setText("");

            String out = in.readLine();
            while(out != null) {
                wynik.append(out + "\n");
                out = in.readLine();
            }
            in.close();

            out = err.readLine();
            while(out != null) {
                wynik.append(out + "\n");
                out = err.readLine();
            }
            err.close();*/

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
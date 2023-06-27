public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Poprawna składnia:\n\tjava App <rodzaj grafu>");
            return;
        }
        GraphFactory graphGenerator = null;
        if ("clique".equals(args[0])) {
            graphGenerator = new Clique();
        }
        else if ("pathEnd".equals(args[0])) {
            graphGenerator = new PathEnd();
        }
        else if ("pathMiddle".equals(args[0])) {
            graphGenerator = new PathMiddle();
        }
        else if ("binaryTree".equals(args[0])) {
            graphGenerator = new BinaryTree();
        }
        else if ("lollipop".equals(args[0])) {
            graphGenerator = new Lollipop();
        }
        else {
            System.out.println("Dostępne opcje grafów: \n\t[clique] / [pathEnd] / [pathMiddle] / [binaryTree] / [lollipop]");
            return;
        }
        try {
            ThreadsController threadsController = new ThreadsController(args[0]);
            for (int n = 100; n < 2001; n+= 50) {
                GraphNode startingNode = graphGenerator.generateGraph(n);
                int k = 100;
                while (k > 0) {
                    threadsController.newThread(n, startingNode);
                    k--;
                }
            }
            threadsController.waitForAll();
            System.out.println("Zakończono wykonywanie programu");
            threadsController.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

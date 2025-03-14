package laboratories.lab03;
/**
 * Demo
 */
public class Demo1 {

    public static void main(String[] args) {
        GeometricStatistics<Float> gs1 = new GeometricStatistics<>();
        try {
            System.out.println(gs1.getProduct());
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            GeometricStatistics<Integer> gs2 = new GeometricStatistics<>();
            gs2.addElement((int)1);
            gs2.addElement((int)-5);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            GeometricStatistics<Float> gs3 = new GeometricStatistics<>();
            gs3.addElement((float) 1.5);
            gs3.addElement((float) 5.5);
            gs3.addElement((float) 6.2);
            gs3.addElement((float) 12.4);

            System.out.println(gs3.getProduct());	 // 634.2599609756476
            System.out.println(gs3.getMean());		// 5.018417906034013
            System.out.println(gs3.getMin());		 // 1.5
            System.out.println(gs3.getMax());		 // 12.4
            GeometricStatistics<Byte> gsT = new GeometricStatistics<>();
            gsT.addElement((byte) 0);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
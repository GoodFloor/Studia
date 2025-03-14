package hr.fer.oop;

public class Demo {
    public static void main(String[] args) {
        String correctInput = ",Brand-Model,Year\r\n"
				+ ",Nimble-Phantom,2019\r\n"
				+ ",Aether-Titan,2017\r\n"
				+ ",Zenith-Omega,2016\r\n"
				+ ",Nimble-Icarus,2015\r\n"
				+ ",Aether-Artemis,2019\r\n"
				+ ",ZENITH-Eros,2018\r\n"
				+ ",Nimble-Lyra,2015\r\n"
				+ ",Aether-Helios,2017\r\n"
				+ ",Zenith-Sirius,2016\r\n"
				+ ",Nimble-Orion,2014\r\n"
				+ ",Aether-Selene,2016\r\n"
				+ ",Zenith-Atlas,2019\r\n"
				+ ",NIMBLE-Nova,2017\r\n"
				+ ",Aether-Athena,2016\r\n"
				+ ",Zenith-Hyperion,2018";
		
		CarManager manager = new Solution();
		
		manager = new Solution();
		System.out.printf("Number of cars: %d\n", manager.processDataset(correctInput));
		System.out.printf("Average year of ZENITH cars: %.1f\n", manager.averageYear(Brand.ZENITH));
		System.out.printf("Average year of NIMBLE cars: %.1f\n", manager.averageYear(Brand.NIMBLE));
		System.out.printf("Average year of AETHER cars: %.1f\n", manager.averageYear(Brand.AETHER));
		
		String badInput = ",Brand-Model,Year\r\n"
				+ ",Aether-Nimbus,-2017\r\n" // bad year
				+ ",QNimble-Andromeda,2016\r\n" // bad brand
				+ ",Zenith-Vega,,2015\r\n" // extra comma
				+ ",Nimble-Altair,-2019\r\n" // bad year
				+ ",ZenithUranus,2a016\r\n" // bad brand, bad year
				+ ",Aether-Nova\r\n" // missing a lot of things
				+ ",w1w11ww1w11w1\r\n" // wrong
				+ "Nimble-Centaurus,2018\r\n" // missing delimiter
				+ "Zenith-Nebula,2019\r\n" // missing delimiter
				+ ",Aether-Orbit,2016\r\n" 
				+ ",Nimble-Callisto,2017\r\n" 
				+ ",Zenith-Cassiopeia,2018\r\n"
				+ ",Zenith-Galaxy,2017";
		

		
		System.out.println();
		System.out.printf("Number of cars: %d\n", manager.processDataset(badInput));
		System.out.printf("Average year of ZENITH cars: %.1f\n", manager.averageYear(Brand.ZENITH));
		System.out.printf("Average year of NIMBLE cars: %.1f\n", manager.averageYear(Brand.NIMBLE));
		System.out.printf("Average year of AETHER cars: %.1f\n", manager.averageYear(Brand.AETHER));
    }
}

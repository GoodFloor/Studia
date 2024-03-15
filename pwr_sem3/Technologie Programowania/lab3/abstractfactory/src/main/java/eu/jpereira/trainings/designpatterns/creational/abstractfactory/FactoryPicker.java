package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

// Moja klasa pomocnicza do wyboru typu fabryki
public class FactoryPicker {
    public static AbstractFactory pickFactory(String element) {
        if (element.equalsIgnoreCase("HEADER")) {
            return new HeaderFactory();
        } else if (element.equalsIgnoreCase("FOOTER")) {
            return new FooterFactory();
        } else if (element.equalsIgnoreCase("BODY")) {
            return new BodyFactory();
        }
        return null;
    }
}

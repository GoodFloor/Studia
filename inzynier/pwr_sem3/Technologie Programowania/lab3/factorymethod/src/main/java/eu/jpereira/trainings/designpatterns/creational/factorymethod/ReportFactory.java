package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class ReportFactory implements ReportFactoryInterface{
    public Report getReport(String type) {
        if (type.equals("JSON")) {
			return new JSONReport();
		} else if (type.equals("XML")) {
			return new XMLReport();
		} else if (type.equals("HTML")) {
			return new HTMLReport();
		} else if (type.equals("PDF")) {
			return new PDFReport();
		} else {
            return null;
        }
    }
}

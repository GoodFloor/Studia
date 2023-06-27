package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

// Moja klasa sbstrakcyjnej fabryki
public abstract class AbstractFactory {
    abstract ReportBody generateBody(String type);
    abstract ReportHeader generateHeader(String type);
    abstract ReportFooter generateFooter(String type);
}

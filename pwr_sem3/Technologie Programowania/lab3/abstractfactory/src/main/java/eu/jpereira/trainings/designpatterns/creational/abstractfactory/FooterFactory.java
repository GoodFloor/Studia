package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportFooter;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportFooter;

//Moja implementacja abstrakcyjnej fabryki do budowania klasy ReportFooter
public class FooterFactory extends AbstractFactory{

    @Override
    public ReportFooter generateFooter(String type) {
        if (type.equals("JSON")) {
            return new JSONReportFooter();
        } else if (type.equals("XML")) {
            return new XMLReportFooter();
        }
        return null;
    }

    @Override
    ReportHeader generateHeader(String type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    ReportBody generateBody(String type) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportBody;

// Moja implementacja abstrakcyjnej fabryki do budowania klasy ReportBody
public class BodyFactory extends AbstractFactory{

    @Override
    public ReportBody generateBody(String type) {
        if (type.equals("JSON")) {
            return new JSONReportBody();
        } else if (type.equals("XML")) {
            return new XMLReportBody();
        }
        return null;
    }

    @Override
    ReportHeader generateHeader(String type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    ReportFooter generateFooter(String type) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

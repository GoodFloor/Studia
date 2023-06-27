package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportHeader;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportHeader;

//Moja implementacja abstrakcyjnej fabryki do budowania klasy ReportHeader
public class HeaderFactory extends AbstractFactory {

    @Override
    ReportBody generateBody(String type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReportHeader generateHeader(String type) {
        if (type.equals("JSON")) {
            return new JSONReportHeader();
        } else if (type.equals("XML")) {
            return new XMLReportHeader();
        }
        return null;
    }

    @Override
    ReportFooter generateFooter(String type) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

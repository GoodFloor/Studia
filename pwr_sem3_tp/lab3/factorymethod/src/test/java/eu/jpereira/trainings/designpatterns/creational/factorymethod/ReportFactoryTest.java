package eu.jpereira.trainings.designpatterns.creational.factorymethod;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReportFactoryTest extends AbstractReportingTest{
    @Test
	public void testReturnHTMLReport() {
        ReportFactory rf = new ReportFactory();
        assertEquals(rf.getReport("HTML").getClass(), HTMLReport.class);
    }
    @Test
	public void testReturnJSONReport() {
        ReportFactory rf = new ReportFactory();
        assertEquals(rf.getReport("JSON").getClass(), JSONReport.class);
    }
    @Test
	public void testReturnPDFReport() {
        ReportFactory rf = new ReportFactory();
        assertEquals(rf.getReport("PDF").getClass(), PDFReport.class);
    }
    @Test
	public void testReturnXMLReport() {
        ReportFactory rf = new ReportFactory();
        assertEquals(rf.getReport("XML").getClass(), XMLReport.class);
    }
    @Test
	public void testReturnNullReport() {
        ReportFactory rf = new ReportFactory();
        assertEquals(rf.getReport("notExisting"), null);
    }
}

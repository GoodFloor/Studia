package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;

// Mój abstrakcyjny builder
public abstract class ReportBuilder {

	protected Report report = new Report();
	public abstract void buildReport(SaleEntry saleEntry);
	public abstract Report getReport();
}

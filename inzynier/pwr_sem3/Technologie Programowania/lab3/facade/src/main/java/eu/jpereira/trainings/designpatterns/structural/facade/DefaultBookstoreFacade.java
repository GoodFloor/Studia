package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.BookDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerNotificationService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.OrderingService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.WharehouseService;

public class DefaultBookstoreFacade implements BookstoreFacade {
    private BookDBService bookDBService;
    private CustomerDBService customerService;
    private CustomerNotificationService customerNotificationService;
    private OrderingService orderingService;
    private WharehouseService warehouseService;

    @Override
    public void placeOrder(String customerId, String isbn) {
        Customer customer = customerService.findCustomerById(customerId);
        Book book = bookDBService.findBookByISBN(isbn);
        Order order = orderingService.createOrder(customer, book);
        DispatchReceipt dispatchReceipt = warehouseService.dispatch(order);
        customerNotificationService.notifyClient(dispatchReceipt);
    }

    public void setBookDBService(BookDBService bookDBService) {
        this.bookDBService = bookDBService;
    }

    public void setCustomerService(CustomerDBService customerService) {
        this.customerService = customerService;
    }

    public void setCustomerNotificationService(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }

    public void setOrderingService(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    public void setWarehouseService(WharehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }
}

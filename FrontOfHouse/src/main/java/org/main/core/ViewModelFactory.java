package org.main.core;

import org.main.view.ViewOrder.ViewOrderViewModel;
import org.main.view.booking.createBookings.CreateBookingViewModel;
import org.main.view.booking.editBookings.EditBookingViewController;
import org.main.view.booking.viewBookings.ViewBookingViewModel;
import org.main.view.login.LoginViewModel;
import org.main.view.order.OrderingViewModel;
import org.main.view.table.TableViewModel;

/**
 * @author Mihail Constantin
 * @author Kian Pflitsch
 * @author Muhhamed Siddique
 * @author  Jahir Amir
 * @version 1.0
 * Factory Class that initialises and stores all the ViewModel.
 */
public class ViewModelFactory {

    private final LoginViewModel loginViewModel;

    private final TableViewModel tableViewModel;
    private static ViewModelFactory viewModelFactory;

    private final OrderingViewModel orderViewModel;

    private final ViewOrderViewModel viewOrderViewModel;


    private final CreateBookingViewModel createBookingViewModel;
    private final EditBookingViewController editBookingViewController;
    private final ViewBookingViewModel viewBookingViewModel;

    /**
     * Custom Constructor that initialises all the ViewModels
     */
    private ViewModelFactory(){

        loginViewModel = new LoginViewModel();
        tableViewModel = new TableViewModel();
        createBookingViewModel = new CreateBookingViewModel();
        editBookingViewController = new EditBookingViewController();
        viewBookingViewModel = new ViewBookingViewModel();
        orderViewModel = new OrderingViewModel();
        viewOrderViewModel = new ViewOrderViewModel();

    }

    /**
     * Singleton implementation of the ViewModelFactory
     * @return This instance of ViewModelFactory
     */
    public static ViewModelFactory getInstance()
    {
        if(viewModelFactory == null)
            viewModelFactory = new ViewModelFactory();
        return viewModelFactory;
    }

    public TableViewModel getTableViewModel(){return tableViewModel;}

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public ViewBookingViewModel getBookingViewModel() {
        return viewBookingViewModel;
    }

    public OrderingViewModel getOrderViewModel() {
        return orderViewModel;
    }

    public ViewOrderViewModel getViewOrderViewModel() {
        return viewOrderViewModel;
    }
}

package org.main.core;

import org.main.view.ViewOrder.ViewOrderViewModel;
import org.main.view.booking.createBookings.CreateBookingViewModel;
import org.main.view.booking.editBookings.EditBookingViewController;
import org.main.view.booking.viewBookings.ViewBookingViewModel;
import org.main.view.login.LoginViewModel;
import org.main.view.order.OrderingViewModel;
import org.main.view.table.TableViewModel;

public class ViewModelFactory {

    private final LoginViewModel loginViewModel;

    private final TableViewModel tableViewModel;
    private static ViewModelFactory viewModelFactory;

    private final OrderingViewModel orderViewModel;

    private final ViewOrderViewModel viewOrderViewModel;


    private final CreateBookingViewModel createBookingViewModel;
    private final EditBookingViewController editBookingViewController;
    private final ViewBookingViewModel viewBookingViewModel;


    private ViewModelFactory(){

        loginViewModel = new LoginViewModel();
        tableViewModel = new TableViewModel();
        createBookingViewModel = new CreateBookingViewModel();
        editBookingViewController = new EditBookingViewController();
        viewBookingViewModel = new ViewBookingViewModel();
        orderViewModel = new OrderingViewModel();
        viewOrderViewModel = new ViewOrderViewModel();

    }

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

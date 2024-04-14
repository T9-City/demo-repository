package org.main.core;

import org.main.view.booking.createBookings.CreateBookingViewModel;
import org.main.view.booking.editBookings.EditBookingViewController;
import org.main.view.booking.viewBookings.ViewBookingViewModel;
import org.main.view.login.LoginViewModel;
import org.main.view.table.TableViewModel;

public class ViewModelFactory {

    private final LoginViewModel loginViewModel;

    private final TableViewModel tableViewModel;
    private static ViewModelFactory viewModelFactory;

    private final CreateBookingViewModel createBookingViewModel;
    private final EditBookingViewController editBookingViewController;
    private final ViewBookingViewModel viewBookingViewModel;


    private ViewModelFactory(){

        loginViewModel = new LoginViewModel();
        tableViewModel = new TableViewModel();
        createBookingViewModel = new CreateBookingViewModel();
        editBookingViewController = new EditBookingViewController();
        viewBookingViewModel = new ViewBookingViewModel();

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
}

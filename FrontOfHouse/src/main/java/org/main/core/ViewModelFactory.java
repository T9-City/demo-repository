package org.main.core;

import org.main.view.booking.createBookings.CreateBookingViewModel;
import org.main.view.login.LoginViewModel;

public class ViewModelFactory {

    private final LoginViewModel loginViewModel;
    private final CreateBookingViewModel bookingViewModel;
    private static ViewModelFactory viewModelFactory;

    private ViewModelFactory(){

        loginViewModel = new LoginViewModel();
        bookingViewModel = new CreateBookingViewModel();
    }

    public static ViewModelFactory getInstance()
    {
        if(viewModelFactory == null)
            viewModelFactory = new ViewModelFactory();
        return viewModelFactory;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public CreateBookingViewModel getBookingViewModel() {
        return bookingViewModel;
    }
}

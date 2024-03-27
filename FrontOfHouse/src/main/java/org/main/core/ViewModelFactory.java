package org.main.core;


import org.main.booking.BookingViewModel;
//import org.main.view.login.LoginViewModel;

public class ViewModelFactory {

    //private final LoginViewModel loginViewModel;
    private final BookingViewModel bookingViewModel;
    private static ViewModelFactory viewModelFactory;

    private ViewModelFactory(){
        //loginViewModel = new LoginViewModel();
        bookingViewModel = new BookingViewModel();

    }

    public static ViewModelFactory getInstance()
    {
        if(viewModelFactory == null)
            viewModelFactory = new ViewModelFactory();
        return viewModelFactory;
    }

//    public LoginViewModel getLoginViewModel() {
//        return loginViewModel;
//    }


    public BookingViewModel getBookingViewModel() {
        return bookingViewModel;
    }
}

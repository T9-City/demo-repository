package org.main.core;

import org.main.view.login.LoginViewModel;

public class ViewModelFactory {

    private final LoginViewModel loginViewModel;
    private static ViewModelFactory viewModelFactory;

    private ViewModelFactory(){

        loginViewModel = new LoginViewModel();
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
}

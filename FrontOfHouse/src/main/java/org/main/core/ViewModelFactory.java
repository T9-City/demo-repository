package org.main.core;

import org.main.view.login.LoginViewModel;
import org.main.view.table.TableViewModel;

public class ViewModelFactory {

    private final LoginViewModel loginViewModel;

    private final TableViewModel tableViewModel;
    private static ViewModelFactory viewModelFactory;

    private ViewModelFactory(){

        loginViewModel = new LoginViewModel();
        tableViewModel = new TableViewModel();
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

}

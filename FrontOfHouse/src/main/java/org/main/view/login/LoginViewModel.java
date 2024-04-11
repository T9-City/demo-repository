package org.main.view.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.main.gateway.Implementation.ManagementImpl;
import org.main.gateway.Management;

public class LoginViewModel {

    private final StringProperty staffName;
    private final StringProperty role;
    private final Management management;


    public LoginViewModel()
    {
        staffName = new SimpleStringProperty();
        role = new SimpleStringProperty();
        management = new ManagementImpl();
    }

    public void login()
    {
        if(management.loginStaff(staffName.getValue(), role.getValue()));{
        System.out.println(true);
        //fire event success
    }
        System.out.println(false);
        //fire event failed
    }


    public StringProperty staffNameProperty() {
        return staffName;
    }


    public StringProperty roleProperty() {
        return role;
    }


}

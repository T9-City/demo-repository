package org.main.view.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.main.gateway.Implementation.ManagementImpl;
import org.main.gateway.Management;

/**
 * @author Mihail Constantin
 * @version 1.0
 * LoginViewModel is the login implementation of ViewModel.
 * Its private variables are bound to the data in the GUI and vice-versa.
 * It handles calls upon the other parts of the system and handles logic.
 */
public class LoginViewModel {

    private final StringProperty staffName;
    private final StringProperty role;
    private final Management management;

    /**
     * Custom constructor 5
     */
    public LoginViewModel()
    {
        staffName = new SimpleStringProperty();
        role = new SimpleStringProperty();
        management = new ManagementImpl();
    }

    /**
     *
     * @return
     */
    public boolean login()
    {
        System.out.println(management.loginStaff(staffName.getValue(), role.getValue()));
        return management.loginStaff(staffName.getValue(), role.getValue());
    }


    public StringProperty staffNameProperty() {
        return staffName;
    }


    public StringProperty roleProperty() {
        return role;
    }


}

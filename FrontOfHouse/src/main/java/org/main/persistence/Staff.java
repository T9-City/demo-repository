package org.main.persistence;

import org.main.utils.StaffRole;

/**
 * @author Mihail Constantin
 * @version 1.0
 * Staff object that would contain staff from the company
 */
public class Staff {

    private String name;
    private StaffRole role;

    /**
     * Custom constructor that takes the name and the role of the class.
     * The local variables are assigned the ones taken as a parameter.
     * @param name Name of the staff
     * @param role Role of the staff as an Enum of StaffRole
     */
    public Staff(String name, StaffRole role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StaffRole getRole() {
        return role;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }
}

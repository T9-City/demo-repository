package org.main.persistence;

import org.main.utils.StaffRole;

public class Staff {

    private String name;
    private StaffRole role;

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

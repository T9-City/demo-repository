package org.main.gateway.Implementation;

import org.main.gateway.Management;
import org.main.persistence.Staff;
import org.main.utils.StaffRole;

import java.util.List;

public class ManagementImpl implements Management {
    @Override
    public List<Staff> getAllStaff() {
        return null;
    }

    @Override
    public List<Staff> getStaffByRole(StaffRole role) {
        return null;
    }

    public boolean loginStaff(String staffName, String staffRole) {
        return false;
    }
}

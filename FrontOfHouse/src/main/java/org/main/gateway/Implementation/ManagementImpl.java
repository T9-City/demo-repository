package org.main.gateway.Implementation;

import org.main.gateway.Management;
import org.main.persistence.Staff;
import org.main.utils.StaffRole;

import java.util.ArrayList;
import java.util.List;

public class ManagementImpl implements Management {

    ArrayList<Staff> staffList = new ArrayList<>();

    @Override
    public List<Staff> getAllStaff() {
        Staff staff = new Staff("Honer", StaffRole.MAITRE);
        Staff staff1 = new Staff("Laure", StaffRole.SOMMELIER);
        Staff staff2 = new Staff("Bark",StaffRole.WAITER);
        return staffList;
    }

    @Override
    public List<Staff> getStaffByRole(StaffRole role) {
        return null;
    }

    public boolean loginStaff(String staffName, String staffRole) {
        return false;
    }
}

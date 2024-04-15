package org.main.gateway.Implementation;

import org.main.gateway.Management;
import org.main.persistence.Staff;
import org.main.utils.StaffRole;

import java.util.ArrayList;
import java.util.List;

public class ManagementImpl implements Management {

    ArrayList<Staff> staffList = new ArrayList<>();

    public ManagementImpl()
    {
        Staff staff = new Staff("Homer", StaffRole.MAITRE);
        Staff staff1 = new Staff("Laure", StaffRole.SOMMELIER);
        Staff staff2 = new Staff("Bark",StaffRole.WAITER);
        staffList.add(staff);
        staffList.add(staff1);
        staffList.add(staff2);
    }

    @Override
    public List<Staff> getAllStaff() {
        return null;
    }

    @Override
    public List<Staff> getStaffByRole(StaffRole role) {
        return null;
    }

    public boolean loginStaff(String staffName, String staffRole) {
        for (Staff staff: staffList)
                if(staff.getName().equals(staffName) && (staff.getRole().toString().equals(staffRole)))
                        return true;{
        }
        return false;
    }
}

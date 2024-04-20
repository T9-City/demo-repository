package org.main.gateway.Implementation;

import org.main.gateway.Management;
import org.main.persistence.Staff;
import org.main.utils.StaffRole;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mihail Constantin
 * @version 1.0
 * Hard coded implementation of the management API
 */
public class ManagementImpl implements Management {

    ArrayList<Staff> staffList = new ArrayList<>();

    /**
     * Custom constructor, creates staff Objects that are introduced in an ArrayList for use
     */
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

    /**
     * Checks in the ArrayList stored locally if the StaffName and the StaffRole exist
     * @param staffName the staffName that is sent from the Login screen
     * @param staffRole the staffRole that is sent from the Login screen
     * @return true of false if the staff is present in the ArrayList
     */
    public boolean loginStaff(String staffName, String staffRole) {
        for (Staff staff: staffList)
                if(staff.getName().equals(staffName) && (staff.getRole().toString().equals(staffRole)))
                        return true;{
        }
        return false;
    }
}

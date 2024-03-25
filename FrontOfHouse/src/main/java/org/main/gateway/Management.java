package org.main.gateway;

import org.main.persistence.Staff;
import org.main.utils.StaffRole;

import java.util.List;

public interface Management {

    /**
     * Gets all the staff members from the database.
     * @return A list of all the {@link Staff} members.
     */
    List<Staff> getAllStaff();

    /**
     * Gets all the staff members with a role filter.
     * @param role The {@link StaffRole} that is filtered for.
     * @return A list of {@link Staff} that are the filtered {@link StaffRole}.
     */
    List<Staff> getStaffByRole(StaffRole role);

    boolean loginStaff(String staffName, String staffRole);


}

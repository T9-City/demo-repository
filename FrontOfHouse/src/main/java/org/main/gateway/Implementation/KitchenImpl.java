package org.main.gateway.Implementation;

import org.main.gateway.Kitchen;
import org.main.persistence.Dish;
import org.main.persistence.Menu;

public class KitchenImpl implements Kitchen {


    @Override
    public Menu getMenu() {
        return null;
    }

    @Override
    public boolean isDishAvailable(int dishID) {
        return false;
    }

    @Override
    public boolean isDishAvailable(Dish dish) {
        return false;
    }
}

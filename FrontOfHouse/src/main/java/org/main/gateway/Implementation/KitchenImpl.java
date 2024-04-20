package org.main.gateway.Implementation;

import org.main.gateway.Kitchen;
import org.main.persistence.Dish;
import org.main.persistence.Menu;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class KitchenImpl implements Kitchen {


    public KitchenImpl()
    {
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("meat");
        ArrayList<String> allergens = new ArrayList<String>();
        ingredients.add("eggs");

        Dish dish1 = new Dish(1, 10, "burger", "tasty", ingredients, allergens, true);
        Dish dish2 = new Dish(2, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish3 = new Dish(3, 15, "chips", "very tasty", ingredients, allergens, false);
        Dish dish4 = new Dish(4, 15, "Pepsi", "very tasty", ingredients, allergens, true);
        Dish dish5 = new Dish(5, 10, "Wings", "tasty", ingredients, allergens, true);
        Dish dish6 = new Dish(6, 15, "Rice", "very tasty", ingredients, allergens, true);
        Dish dish7 = new Dish(7, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish8 = new Dish(8, 15, "pizza", "very tasty", ingredients, allergens, true);

        Dish dish9 = new Dish(9, 10, "burger", "tasty", ingredients, allergens, true);
        Dish dish10 = new Dish(10, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish11 = new Dish(11, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish12 = new Dish(12, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish13 = new Dish(13, 10, "burger", "tasty", ingredients, allergens, true);
        Dish dish14 = new Dish(14, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish15 = new Dish(15, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish16 = new Dish(16, 15, "pizza", "very tasty", ingredients, allergens, true);
        ArrayList<Dish> dishArray = new ArrayList<>();
        dishArray.add(dish1);
        dishArray.add(dish2);
        dishArray.add(dish3);
        dishArray.add(dish4);
        dishArray.add(dish5);
        dishArray.add(dish6);
        dishArray.add(dish7);
        dishArray.add(dish8);
        dishArray.add(dish9);
        dishArray.add(dish10);
        dishArray.add(dish11);
        dishArray.add(dish12);
        dishArray.add(dish13);
        dishArray.add(dish14);
        dishArray.add(dish15);
        dishArray.add(dish16);
        Menu menuList = new Menu(dishArray, ZonedDateTime.now());

    }

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

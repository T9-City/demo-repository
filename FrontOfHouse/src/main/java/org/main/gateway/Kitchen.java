package org.main.gateway;

import org.main.persistence.Dish;
import org.main.persistence.Menu;

public interface Kitchen {

    /**
     * Returns the current menu in rotation.
     * It is recommended to run this method once and store the result, rather than calling it multiple times.
     * This method can be run during startup, and every time the menu is updated.
     *
     * @return An ArrayList of Dish objects representing the menu.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     ArrayList&lt;Dish&gt; menu = dishService.getMenu();
     *     boolean isPizzaAvailable = menu.stream().map(Dish::isAvailable).filter(name -> name.contains("Pizza")).findFirst().orElse(false);
     *     System.out.println("Is a delicious pizza available? " + isPizzaAvailable);
     * </pre>
     */
    Menu getMenu();

    /**
     * Returns if the dish with the specified dishID is available for ordering.
     * This method can be run when an order is placed or on every login, to check if the dish is still available.
     *
     * @param dishID The ID of the dish to retrieve.
     * @return True if the dish is available, false if it is not.
     * <hr/>
     * {@code @example}
     * <pre>
     *    // Assume an implementation of dishService
     *    int pizzaDishID = 123;
     *    if (dishService.isDishAvailable(pizzaDishID)) {
     *        System.out.println("Pizza is available! Proceed with ordering.");
     *    } else {
     *        System.out.println("Pizza is unfortunately out of stock. Mark it as unavailable.");
     *    }
     * </pre>
     */
    boolean isDishAvailable(int dishID);

    /**
     * Returns if the dish is available for ordering.
     * This method can be run when an order is placed or on every login, to check if the dish is still available.
     *
     * @param dish The dish to retrieve.
     * @return True if the dish is available, false if it is not.
     * <hr/>
     * {@code @example}
     * <pre>
     *    // Assume an implementation of dishService
     *    Dish pizzaDish = new Dish(123, 1000, "Pizza", "A delicious pizza", new ArrayList&lt;String&gt;(), new ArrayList&lt;String&gt;(), true);
     *    if (dishService.isDishAvailable(pizzaDish)) {
     *        System.out.println("Pizza is available! Proceed with ordering.");
     *    } else {
     *        System.out.println("Pizza is unfortunately out of stock. Mark it as unavailable.");
     *    }
     * </pre>
     */
    boolean isDishAvailable(Dish dish);

    /**
     * Represents a menu, containing a list of dishes and the next time the menu will be updated.
     * The nextMenuUpdate field can be used to determine if the menu has been updated since the last getMenu call, or to make an event to update the menu after the specified date &amp; time.
     */
}

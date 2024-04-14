package org.main.persistence;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Menu {

    /**
     * The list of dishes in the menu.
     */
    private final ArrayList<Dish> dishes;
    /**
     * The next time the menu will be updated.
     * @see ZonedDateTime
     */
    private final ZonedDateTime nextMenuUpdate;

    /**
     * Creates a new Menu object with the specified parameters.
     *
     * @param dishes The list of dishes in the menu.
     * @param nextMenuUpdate The next time the menu will be updated.
     * @see ZonedDateTime
     * <hr/>
     * {@code @example}
     * <pre>
     *    // Assume an implementation of dishService
     *    Menu menu = dishService.getMenu();
     * </pre>
     * {@code @menu creation (internal)}
     * <pre>
     *    // Assume an implementation of dishService
     *    ArrayList&lt;Dish&gt; dishes = new ArrayList&lt;Dish&gt;();
     *    dishes.add(new Dish(123, 1000, "Pizza", "A delicious pizza", new ArrayList&lt;String&gt;(), new ArrayList&lt;String&gt;(), true));
     *    dishes.add(new Dish(456, 1500, "Pasta", "A delicious pasta", new ArrayList&lt;String&gt;(), new ArrayList&lt;String&gt;(), true));
     *    dishes.add(new Dish(789, 2000, "Salad", "A delicious salad", new ArrayList&lt;String&gt;(), new ArrayList&lt;String&gt;(), true));
     *    Menu menu = new Menu(dishes, ZonedDateTime.now().plusDays(7));
     * </pre>
     */
    protected Menu(ArrayList<Dish> dishes, ZonedDateTime nextMenuUpdate) {
        this.dishes = dishes;
        this.nextMenuUpdate = nextMenuUpdate;
    }

    /**
     * Returns the list of dishes in the menu.
     * @see ArrayList
     * @return The list of dishes in the menu.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     ArrayList&lt;Dish&gt; dishes = dishService.getMenu().getDishes();
     *     System.out.println("The menu contains " + dishes.size() + " dishes.");
     *     // Output: The menu contains 3 dishes.
     *
     *     System.out.println("The first dish is " + dishes.get(0).getName());
     *     // Output: The first dish is Pizza
     * </pre>
     */
    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    /**
     * Returns the next time the menu will be updated.
     * @see ZonedDateTime
     * @return The next time the menu will be updated.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     ZonedDateTime nextMenuUpdate = dishService.getMenu().getNextMenuUpdate();
     *     System.out.println("The menu will be updated on " + nextMenuUpdate);
     *     // Output: The menu will be updated on 2024-02-07T00:00:00Z
     * </pre>
     */
    public ZonedDateTime getNextMenuUpdate() {
        return nextMenuUpdate;
    }

}

package org.main.persistence;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Dish {
    /**
     * The ID of the dish.
     * This can be used to identify the dish when placing an order.
     */
    private final int dishID;
    /**
     * The price of the dish in pence.
     */
    private final int price;
    /**
     * The name of the dish.
     */
    private String name;
    /**
     * The description of the dish.
     */
    private String description;
    /**
     * The list of ingredients in the dish.
     */
    private final ArrayList<String> ingredients;
    /**
     * The list of allergens in the dish.
     */
    private final ArrayList<String> allergens;
    /**
     * The image of the dish.
     * This is stored as a byte array to allow for easy storage and network transmission.
     */
    private byte[] image;
    /**
     * The availability of the dish.
     */
    private boolean isAvailable;

    /**
     * Creates a new Dish object with the specified parameters.
     *
     * @param dishID The ID of the dish.
     * @param price The price of the dish in pence.
     * @param name The name of the dish.
     * @param description The description of the dish.
     * @param ingredients The list of ingredients in the dish.
     * @param allergens The list of allergens in the dish.
     * @param isAvailable The availability of the dish.
     * <hr/>
     * {@code @example}
     * <pre>
     *  // Assume an implementation of dishService
     *  Dish pizzaDish = new Dish(123, 1000, "Pizza", "A delicious pizza", new ArrayList&lt;String&gt;(), new ArrayList&lt;String&gt;(), true);
     * </pre>
     */
    protected Dish(int dishID, int price, String name, String description, ArrayList<String> ingredients, ArrayList<String> allergens, boolean isAvailable) {
        this.dishID = dishID;
        this.price = price;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.isAvailable = isAvailable;
    }

    /**
     * Returns the ID of the dish.
     *
     * @return The ID of the dish.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     int dishID = dish.getDishID();
     *     System.out.println("The ID of the dish is " + dishID);
     *     // Output: The ID of the dish is 123
     * </pre>
     */
    public int getDishID() {
        return dishID;
    }

    /**
     * Returns the price of the dish in pence.
     *
     * @return The price of the dish in pence.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     int dishPrice = dish.getPrice();
     *     System.out.println("The cost of the dish is " + dishPrice + " pence or £" + (dishPrice / 100));
     *     // Output: The cost of the dish is 1000 pence or £10
     * </pre>
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the name of the dish.
     *
     * @param name The name of the dish.
     * <hr/>
     * {@code @example (internal)}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     System.out.println(dish.getName()); // Output: Pizza
     *     dish.setName("Delicious Pizza");
     * </pre>
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the dish.
     *
     * @return The name of the dish.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     String dishName = dish.getName();
     *     System.out.println("The name of the dish is " + dishName);
     *     // Output: The name of the dish is Pizza
     * </pre>
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the description of the dish.
     * @param description The description of the dish.
     * <hr/>
     * {@code @example (internal)}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     System.out.println(dish.getDescription()); // Output: A delicious pizza
     *     dish.setDescription("A very delicious pizza");
     * </pre>
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the dish.
     *
     * @return The description of the dish.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     String dishDescription = dish.getDescription();
     *     System.out.println("The description of the dish is " + dishDescription);
     *     // Output: The description of the dish is A delicious pizza
     * </pre>
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the list of ingredients in the dish.
     *
     * @return The list of ingredients in the dish.
     * <hr/>
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     ArrayList&lt;String&gt; dishIngredients = dish.getIngredients();
     *     dishIngredients.stream().forEach(ingredient -> System.out.println("The dish contains " + ingredient));
     * </pre>
     */
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    /**
     * Returns the list of allergens in the dish.
     *
     * @return The list of allergens in the dish.
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     ArrayList&lt;String&gt; dishAllergens = dish.getAllergens();
     *     dishAllergens.stream().forEach(allergen -> System.out.println("People allergic to " + allergen + " cannot eat this dish!"));
     * </pre>
     */
    public ArrayList<String> getAllergens() {
        return allergens;
    }

    /**
     * Returns the image of the dish.
     *
     * @return The image of the dish.
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     byte[] dishImage = dish.getImage();
     *
     *     ByteArrayInputStream byteStream = new ByteArrayInputStream(imageBytes);
     *     BufferedImage image = ImageIO.read(byteStream);
     * </pre>
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the image of the dish.
     *
     * @param image The image of the dish.
     * {@code @example (internal)}
     * <pre>
     *     File imageFile = new File("your_image_path.jpg");
     *     BufferedImage image = ImageIO.read(imageFile);
     *
     *     ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
     *     ImageIO.write(image, "jpg", byteStream);
     *
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     dish.setImage(byteStream.toByteArray());
     * </pre>
     */
    protected void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Returns the availability of the dish.
     *
     * @return The availability of the dish.
     * {@code @example}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     boolean isDishAvailable = dish.isAvailable();
     *     System.out.println("Is the dish available? " + isDishAvailable);
     * </pre>
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the dish.
     *
     * @param isAvailable The availability of the dish.
     * {@code @example (internal)}
     * <pre>
     *     // Assume an implementation of dishService
     *     Dish dish = dishService.getMenu().getDishes().get(0);
     *     dish.setAvailable(false);
     * </pre>
     */
    protected void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String pizzaID;

        // Create an ArrayList to store all pizza ids
        ArrayList<String> order = new ArrayList<>();


        do {
            // prompts user to add pizza or exit program
            System.out.println("do you want to order a pizza" +
                    " \nEnter 0 to end order, Enter 1 to add A pizza to the list of orders" );

            // prompts the user for input.
            int input = getUserInput();

            // checks if the user wants to add a pizza to the order
            if (input == 1){

                // creates a pizza.
                pizzaID = createPizzaID();

                // adds created pizza to order list.
                // checks if the pizza id is at least 2 chars long
                if (pizzaID.length() >= 2) {
                    order.add(pizzaID);
                }

            }
            // checks if the user wants to go to receipt.
            else if (input == 0){
                // break if user has finished ordering.
                break;
            }

            // checks if the user has inputted an unrecognised value
            else {
                System.out.printf("%s You have entered a value witch is not recognised, pleas try again %s\n", ANSI_RED, ANSI_RESET);
            }


        }while (true);

        // creates receipt
        createOrderFacture(order);

    }

    /***
     * takes in an array(order list) of total orders and calls method calculatePizzaPrice on every item in order list
     * and stores its value to display a total price.
     * @param orderList consisting of all pizzaIDs
     */
    private static void createOrderFacture(ArrayList<String> orderList) {

        // total price for the order is store in totalPrice.
        float totalPrice = 0;

        System.out.println("----------------- Receipt -----------------\n");


        // loops through all pizza ids.
        for (int i = 0; i < orderList.size(); i++) {

            // retrieves the pizza id from order list, and stores it in pizzaID
            String pizzaID = orderList.get(i);
            // calculates the pizza price and adds it to total order price
            totalPrice += calculatePizzaPrice(pizzaID);

        }
        // prints total order price
        System.out.print("\n-------------------------------------------\n");
        System.out.printf(" The total price for the order is %-5sDDK%.2f %s", ANSI_GREEN, totalPrice, ANSI_RESET);
        System.out.print("\n-------------------------------------------\n");

    }

    /***
     * Takes in a pizza id and calculates the total price for the pizza.
     * @param pizzaID ref README.md:37
     * @return pizza price
     */
    private static float calculatePizzaPrice(String pizzaID){

        // creates a list of the pizzas attributes.
        String[] pizzaAttributes = pizzaID.split(",");
        float pizzaPrice = 0;
        int toppingPrice = 0;
        float multiple;

        // get the pizza size from the last index of pizzaID
        int pizzaSize = Integer.parseInt(pizzaAttributes[pizzaAttributes.length-1]);

        // multiples the pizzas standard price with the corresponding price change in procent in relation to the pizza size.
        multiple = switch (pizzaSize) {
            case 1 -> 0.75f;
            case 2 -> 1f;
            case 3 -> 1.5f;
            default -> throw new IllegalStateException(ANSI_RED + "Unexpected value: " + pizzaSize);
        };

        // gets pizza size and stores it in a string for use in receipt presentation.
        String pizzaSizeFormatted = switch (pizzaSize){
            case 1 -> "Small";
            case 2 -> "Standard";
            case 3 -> "Family";
            default -> throw new IllegalStateException(ANSI_RED + "Unexpected value: " + pizzaSize);
        };

        // get pizzas price
        int pizzaIndex = Integer.parseInt(pizzaAttributes[0]);

        // pizzaIndex - 1 to account for index witch starts count at 0
        pizzaIndex -= 1;

        String[] item = PIZZA_ITEMS[pizzaIndex].split(",");
        pizzaPrice += Integer.parseInt(item[1]);
        // calculating pizza price in relation to pizza size.
        pizzaPrice = pizzaPrice * multiple;

        // calculating spacing of pizza price.
        int spacingValue = pizzaSizeFormatted.length() + item[0].length() +(-34);
        String spacing = String.valueOf(spacingValue);

        // prints the pizza size + pizza name with the corresponding price
        System.out.printf("\n(%s) %s %" + spacing + "s DDK %.2f %s\n",pizzaSizeFormatted, item[0], ANSI_GREEN, pizzaPrice, ANSI_RESET);



        // checks if user has picked extra toppings.
        if (pizzaAttributes.length > 2 ){

            System.out.println("Toppings added:");
            // get the price of the toppings, starting from index 1 to index -2.
            for (int i = 1; i <  pizzaAttributes.length -1; i++) {

                int toppingsIndex = Integer.parseInt(pizzaAttributes[i]);
                // subtract 1 from topping index to account for index start at 0
                toppingsIndex -= 1;

                // splits selected topping string to get the assigned price.
                item = TOPPING_ITEMS[toppingsIndex].split(",");
                toppingPrice += Integer.parseInt(item[1]);

                // prints the topping name with the corresponding price
                System.out.printf("%-32s %s DDK %s %s\n", item[0], ANSI_GREEN, item[1], ANSI_RESET);


            }
        }

        // calculates the total price for the selected pizza.
        float totalPrice = pizzaPrice + toppingPrice;
        System.out.printf("Total pizza price: %-20sDDK %.2f%s\n\n", ANSI_GREEN, totalPrice, ANSI_RESET );

        return totalPrice;
    }

    /***
     * this method returns a pizza id, and is the method where all
     * pizza ids are build.
     * String pizzaID consists of CSV where
     *             [0] = the pizza number
     *             [1] -> [-2] = the pizza toppings
     *             [-1] = pizza size.
     *             all attributes are seperated with a ",".
     * @return pizzaID
     */
    private static String createPizzaID() {


        // initializes the return var.
        String pizzaID = "";

        do {
            // write out the pizzas in PIZZA_ITEM
            presentPizza();

            // prompts user to pick a pizza or end order.
            System.out.print("\nPlease enter the number corresponding to the pizza you want to buy\nEnter 0 to finish ");
            int userInput = getUserInput();

            // input validation.
            if (userInput == 0){
                break;
            }

            // checks if the number entered corresponds to a pizza in the pizzaItems list.
            else if (userInput <= PIZZA_ITEMS.length) {

                // adds the pizza picked by the user to pizza id.
                pizzaID += userInput + ",";
                // adds the toppings picked by the user to pizza id.
                pizzaID += getToppings();
                // adds the pizza size to pizza id.
                pizzaID += pickPizzaSize();
                // breakes when the pizza order is complete.
                break;

            }

            // prompts user if input is not valid.
            else {
                System.out.printf("\n%sYou entered the value %d, witch does not correspond to any of the pizzas on the menu%s\n",ANSI_RED, userInput, ANSI_RESET);
            }

        } while (true);

        // returns the pizza attributes.
        return pizzaID;

    }

    /***
     * prompts user for an input of type int, and validates the input.
     * if input is not of type int it prompts again.
     * @return int user input
     */
    private static int getUserInput() {
        // creates scanner object.
        Scanner scanner = new Scanner(System.in);
        int returnValue;

        do {
            // input validating, checks if input is a numerical number;
            if (scanner.hasNextInt()){
                returnValue = scanner.nextInt();

                // checks if input is negativ.
                if ( returnValue >= 0)
                {
                    // breaks if valid input is received.
                    break;
                }
                else{
                    System.out.printf("%s You have entered a numerical value witch is negative. please only enter positive numbers in the terminal.  %s\n", ANSI_RED, ANSI_RESET);
                    scanner.nextInt();
                }

            }

            // if input is not a numerical number, prompt for a new input.
            else{
                System.out.printf("%s You have entered a value witch is not recognised, pleas only use digits. %s\n", ANSI_RED, ANSI_RESET);
                scanner.nextLine();
            }

        }while (true);

        return returnValue;

    }

    /***
     * creates a string consisting of the toppings picked by the user, and stores
     * it with every topping seperated by a ",".
     * it validates the user input to the list of existing toppings.
     *
     *  @return String consisting of toppings picked.
     */
    private static String getToppings() {

        // created a string builder to avoid string concatenation.
        StringBuilder toppingsPicked = new StringBuilder();

        do {
            // presents all toppings in a nicely formatted way.
            presentToppings();

            // prompts user for input.
            System.out.print("\nPlease enter the number corresponding to the topping you " +
                    "want to add to your pizza\nEnter 0 to exit toppings menu ");

            // stores user input.
            int userInput = getUserInput();

            // checks if user are finished selecting extra toppings.
            if (userInput == 0 ){
                break;
            }

            // checks if the number entered corresponds to a topping in the toppingsItems list.
            else if (userInput <= TOPPING_ITEMS.length) {
                toppingsPicked.append(userInput).append(",");
            }

            // prompts the user to retry or exit the menu after wrong input.
            else {
                System.out.printf("\n%sYou entered the value %d, witch does not correspond to any of the " +
                        "toppings on the menu\nEnter 0 to exit toppings menu%s \n",ANSI_RED, userInput, ANSI_RESET);
            }


        } while (true);
        return toppingsPicked.toString();

    }

    /***
     * prints list of pizzas in a nicely formatted way.
     */
    private static void presentPizza() {

        // prints the pizza with the corresponding price.
        for (int i = 0; i < PIZZA_ITEMS.length; i++) {

            // converts string form CSV to its values
            String[] item = PIZZA_ITEMS[i].split(",");
            System.out.printf("nr. (%d) %15s %s%10s DDK%s\n",i+1, item[0],ANSI_GREEN, item[1], ANSI_RESET);



        }

    }

    /***
     * prints list of toppings in a nicely formatted way.
     */
    private static void presentToppings() {

        // prints the toppings with the corresponding price.
        for (int i = 0; i < TOPPING_ITEMS.length; i++) {

            // converts string form CSV to its values split into separate strings
            String[] item = TOPPING_ITEMS[i].split(",");
            System.out.printf("nr. (%d) %15s %s%10s DDK%s\n",i+1, item[0],ANSI_GREEN, item[1], ANSI_RESET);
        }

    }

    /***
     * prompts the user to pick a pizza size and returns the size picked.
     * @return int
     */
    private static int pickPizzaSize(){

        int size;

        //prompt user to pick pizza size, until pizza size pick is satisfactory
        do {

            // present pizza sizes.
            System.out.print("(1) small: \n(2) standard: \n(3) family: \n");

            // prompts user to pick the pizza size.
            System.out.print("Enter the number corresponding to the pizza size you want. \n");
            int userInput = getUserInput();

            // the pizza price is calculated as standard price * multiplier, where multiplayer == pizza size in procent.
            size = switch (userInput){
                // small size
                case 1 -> 1;
                // standard size
                case 2 -> 2;
                // family size
                case 3 -> 3;
                default -> 0;

            };

            if (size != 0){
                break;
            }
            // prompt user for new input.
            System.out.printf("%sYou have entered a value witch does not correspond to a pizza size, please try again\n%s", ANSI_RED, ANSI_RESET);


        }while(true);
        return size;
    }

    /*
     * Class variable for pizza items
     */
    private static final String[] PIZZA_ITEMS ={

            // data for pizza items in the menu is stored in CSV format
            // first value must be the pizza name
            // second value must be price of the pizza.

            "Margherita,60",
            "Hawaii,75",
            "Italiano,70",
            "MeatLover,85",
            "Pepperoni,75",
            "Capricciosa,65",
            "Vegetariana,55",
            "Napoli,75",
            "Marinara,65",
            "Tuna,70",
            "Ortolana,80"

    };

    /*
     * Class variable for toppings items
     */
    private static final String[] TOPPING_ITEMS ={

            // data for toppings items in the menu is stored in CSV format
            // first value must be the topping name
            // second value must be  price of the topping.

            "Pepperoni,10",
            "Mozzarella,8",
            "Mushrooms,5",
            "Pineapple,7",
            "Olives,6",
            "Peppers,5",
            "Onion,4",
            "Tomatoes,6",
            "Bacon,10",
            "Cheese,9",
            "Prosciutto,11",
            "Salami,10",
            "Basil,4",
            "Garlic,3",
            "Jalapeños,6",
            "Pesto,9"

    };


    // ANSI escape code colors.
    // from -> https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

}


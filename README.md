# Pizza Ordering Program Report
This Java program represents a simple system for capturing pizza orders from the user. The system allows the user to select a pizza from the menu, add extra toppings, and choose a size. After the selections, the program generates a receipt that displays the details and the total cost of the order.

##Main Components
Main method: The central loop where the user is prompted whether they want to order a pizza or exit the program.
ArrayList order: Utilized to store all pizza IDs that represent individual pizzas the user has ordered.
User input functions: Such as getUserInput(), which handles and validates user input.
Order creation functions: createPizzaID() is responsible for creating a unique ID for each pizza based on the choice of pizza, toppings, and size.
Price calculation: calculatePizzaPrice() computes the total cost for a given pizza based on the pizza's ID.
Receipt generator: createOrderFacture() generates a receipt that displays each pizza, its toppings, size, and cost, along with the total cost of the entire order.

## Data Structures
PIZZA_ITEMS & TOPPING_ITEMS: These two arrays contain the details for each pizza and topping on the menu. They are used when presenting options to the user and when calculating prices.

pizzaID consist of one sting, where all the pizza attributes is stored.
the structure of pizza id is as follows.

the attrubutes in pizza ids end is marked with the char ','
eks.

pizzaID = "1,2,2,1"
->    "Margheritha, Mozzarella, small"


    where the first numerical value is the number coresponding to the picked pizza.
    
    the last numerical value reprecents the pizza size. 

    all other values in between reprecnts ekstra toppings picked. 


## Distinct Features
User Input Validation: The program ensures the user's input is valid and provides feedback if not.
Flexibility in pizza choices: The user can add as many toppings as desired to a pizza, and choose between different pizza sizes.
Color code in the terminal: Using ANSI codes, certain parts of the text are highlighted to enhance the user experience and clarify essential information.

## Conclusion
This program demonstrates fundamental programming principles like loops, arrays, user input handling, and functions. It offers a simple yet efficient user experience for taking pizza orders. However, future versions could include features like discounts, delivery options, or integration with a database to store past orders.

## Requirements:
- Only one pizza can be ordered at the time.
- The menu is to be presented as text for the user.
- After the number of the pizza is chosen, it must also be possible to choose extra toppings like: Onions, Cheese, Mushrooms.
- Furthermore. the user must be able to choose the size of the pizza, either child, standard or family

- The prices in the menu above is the price for a standard pizza and:
  size child cost 75% of the standard price.
  size family costs 150% of the standard price.


- Finally, the total order including the price has to be calculated and printed out on the screen in a nicely formatted way.

1) Hand-in the source code individually on Moodle Friday 27th by the latest at 15h00.


2) The source code must have some general qualities for readability:
- good variable names
- comments
- constants etc.

3) Each student application must be “live-tested” by another student in the class D23.
   The test students name must be stated in the hand-in.
   He or she is then responsible for the correctness of the program –
   computations, all input, messages in short: that everything works!
   /Frank and Tommy

### TODO list.

    [X] Main program loop 

    [X] Present a list of ordered items 

    [X] add pizza size to list of ordere items. 

    [X] add more pizza choises.  

    [X] add more toppings choises,  
 
    [X] Calculate pizza price. 

    [X] refactor 

    [X] comments 

    [X] method descritions
        -> return values
        -> descriptions

    [X] input validation  
        -> implement hasNextInt. 

    [X] User test — by alexander. 
        -> [X] index out of bounce if input is negative.  
        -> [X give all errors the same color 

    [X] pizza size presentation worng order. 
    
    [X] make toppings added clear in recept. 

    [ ] Add break condistion descriptions for do-whiles -> comment from Casper. 

    [X] Fix github mess. 
    
    fix malformated string when number of item is more then 1 char long. 
![Screenshot 2023-10-15 at 20.31.54.png](..%2F..%2F..%2F..%2F..%2F..%2FScreenshot%202023-10-15%20at%2020.31.54.png)

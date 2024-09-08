import java.util.Arrays;
import java.util.Scanner;

public class Main
{


    static Scanner sc = new Scanner(System.in);
    static Store store = new Store();

    static Cart currentCart = null;

    public static void main(String[] args)
    {

        store.stockStore();
        store.stockAisles();

        String selection ="If you are store employee enter 1 else press 2 ";

        System.out.println(selection);
        int select = sc.nextInt();
        sc.nextLine();




            String menu = """
                    Enter the number from below to make selection.
                    (1) to List all products.
                    (2) to start Shopping
                    (3) to buy a product
                    (4)  print cart
                    (5) to checkout
                    (5) Exit
                    """;

            String  menu2 = """
                    (1) to add Products
                    (2) to refill inventory
                    (3) List Inventory
                    (4) Stock Aisle's
                    (5) Exit
                    """;

            int result = 0;



            if (select==1){


                while (true){
                    System.out.println(menu2);

                    int switchSelection = sc.nextInt();
                    sc.nextLine();

                    switch (switchSelection){
                        case 1-> addProducts();
                        case 2-> {
                            System.out.println("Store refilled");
                            store.stockStore();
                            store.ListInventory();
                        }
                        case 3-> store.ListInventory();
                        case 4 -> store.stockAisles();
                        case 5-> {
                            return;
                        }
                        default -> System.out.println("Invalid Selection");
                    }
                }






            }else
            {

                while (true)

                {

                    System.out.println(menu);

                    try
                    {
                        result = sc.nextInt();
                        sc.nextLine();

                    } catch (Exception e)
                    {
                        System.out.println("Invalid Input: Please enter a valid number.");
                        sc.next();
                        continue;
                    }

                    switch (result)
                    {
                        case 1 -> store.ListInventory();


                        case 2 -> getCart();

                        case 3 -> buyProduct();
                        case 4 -> listCart();
                        case 5 ->
                        {
                            checkOut();
                            currentCart = null;
                        }
                        case 6 ->
                        {
                            return;
                        }


                        default -> System.out.println("Invalid selection ");


                    }


                }
            }




    }

    public static Cart getCart()
    {
        if (currentCart == null)
        {
            System.out.println("If you are shopping online please enter 1: otherwise enter any number ");
            int j = sc.nextInt();
            if (j == 1)
            {
                currentCart = store.newUser("V");
            } else
            {
                currentCart = store.newUser("Q");
            }

        } else
        {
            System.out.println("You already have an active cart");
        }

        return currentCart;
    }

    public static void buyProduct()
    {


        store.ListInventory();

        InventoryItem item = store.InventoryItems();

        if (item != null)
        {
            store.buy(currentCart, 1, item);
            System.out.println("Added 1 unit of =" + item);
        } else
        {
            System.out.println("No valid product selected");
        }


    }

    public static void listCart()
    {
        currentCart.printSlip();
    }

    public static void checkOut()
    {
        store.checkOutCart(currentCart);
    }

    public static void addProducts(){

        System.out.println("Enter SKU");
        String sku = sc.nextLine();
        System.out.println("Enter Name");
        String Name = sc.nextLine();
        System.out.println("Enter Manufacturer");
        String manufacturer = sc.nextLine();
        Category category = getCategory();

        System.out.println("Enter quantity ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter sale price");
        int salePrice = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter low stock limit ");
        int lowStock = sc.nextInt();
        sc.nextLine();



        InventoryItem item = new InventoryItem(new Product(sku,Name,manufacturer,category),quantity,salePrice,lowStock);
        store.addProducts(sku,item);


    }

    public static Category getCategory() {

        Category[] categories = Category.values(); // Get all the categories

        while (true) { // Loop until a valid category is selected
            System.out.println("Select a category:");

            // Display the available categories
            for (int i = 0; i < categories.length; i++) {
                System.out.println((i + 1) + ". " + categories[i]);
            }

            // Get the user input
            int selectedOption = sc.nextInt();
            sc.nextLine();  // Clear the newline character

            // Validate the input
            if (selectedOption >= 1 && selectedOption <= categories.length) {
                return categories[selectedOption - 1]; // Return the selected category
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }

    }


}

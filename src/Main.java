import java.util.Random;
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

        while (true)
        {
            String menu = """
                    Enter the number from below to make selection.
                    (1) to List all products.
                    (2) to start Shopping
                    (3) to buy a product
                    (4)  print cart
                    (5) to checkout
                    (5) Exit
                    """;

            int result = 0;


            while (true){
                System.out.println(menu);

                try
                {
                  result=  sc.nextInt();

                }catch (Exception e){
                    System.out.println("Invalid Input: Please enter a valid number.");
                    sc.next();
                    continue;
                }

                switch (result)
                {
                    case 1 -> store.ListInventory();


                    case 2 -> getCart();

                    case 3 -> buyProduct();
                    case 4-> listCart();
                    case 5-> {
                        checkOut();
                        currentCart=null;
                    }
                    case 6-> {
                    return;
                }



                    default -> System.out.println("Invalid selection ");



                }


            }

        }



    }

    public static Cart getCart(){
       if (currentCart==null){
           System.out.println("If you are shopping online please enter 1: otherwise enter any number ");
           int j = sc.nextInt();
           if (j==1){
               currentCart=store.newUser("V");
           }else {
               currentCart=store.newUser("Q");
           }

       }else {
           System.out.println("You already have an active cart");
       }

       return currentCart;
    }

    public static void buyProduct(){



        store.ListInventory();

        InventoryItem item = store.InventoryItems();

        if (item!=null){
            store.buy(currentCart,1,item);
            System.out.println("Added 1 unit of ="+ item);
        }else {
            System.out.println("No valid product selected");
        }




    }

    public static void listCart(){
        currentCart.printSlip();
    }
    public static void checkOut(){
        store.checkOutCart(currentCart);
    }







}

import java.util.*;

public class Store
{


    // collection of alphabets for sku
    private static final char[] collectionOfAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // random generator for the sku

    private final Random random = new Random();


    private Map<String, InventoryItem> inventory;

    private final NavigableSet<Cart> cartSet = new TreeSet<>(Comparator.comparing(Cart::getId));

    private Map<Category, Map<String, InventoryItem>> aisleInventory;

    private Map<Integer, Double> sale;






    public Cart newUser(String cartType)
    {
        String type = cartType.trim().substring(0, 1).toUpperCase();

        Cart cart;
        if (type.equals("V"))
        {
            cart = new Cart(Cart.CartType.VIRTUAL);
            System.out.println("Virtual cart added");
        } else
        {
            cart = new Cart(Cart.CartType.PHYSICAL);
            System.out.println("Physical cart added");
        }


        cartSet.add(cart);


        return cart;


    }


    public boolean checkOutCart(Cart cart)
    {


        if (sale == null)
        {
            sale = new HashMap<>();
        }

        for (Cart cart1 : cartSet)
        {
            if (cart1.id == cart.id)
            {

                double total = 0.0;

                for (Map.Entry<String,Integer>entry:cart.products.entrySet())
                {

                    String sku = entry.getKey();
                    int quantity = entry.getValue();

                    InventoryItem item= inventory.get(sku);
                    if (item!=null){
                        total+= item.getSalePrice()*quantity;
                        item.sellItem();
                    }else {
                        System.out.println("Product with SKU "+sku + " not found in inventory ");
                    }






                }


                sale.put(cart.id, total);

                System.out.println("Total price for the cart id = "+cart.id +", Total = "+ total);


                cartSet.remove(cart1);
                System.out.println(" Cart id " + cart1.id + " removed ");


                return true;
            } else
            {
                System.out.println(" Cart does not exist ");

            }
        }
        return false;
    }

    public void abandonCarts()
    {

        cartSet.clear();


    }

    public void ListProductsByCategory()
    {


        aisleInventory.keySet().forEach(k ->
        {
            System.out.println("------\n" + k + "\n------");
            System.out.println();
            aisleInventory.get(k).keySet().forEach(System.out::println);
            System.out.println();
        });


    }


    public void buy(Cart cart, int qty,InventoryItem item)
    {




        if (item != null)
        {


            cart.addItem(item, qty);
            InventoryItem item1=inventory.get(item.getProduct().sku());
            item1.reserveItem(1);
        } else
        {
            System.out.println("No item was selected ");

        }
    }


    public InventoryItem InventoryItems()
    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the index of the product number you want to buy ");
        int input = -1;



        try{
            input = sc.nextInt();

        }catch (Exception e){
            System.out.println("Invalid input. Please enter a valid number ");
        }

        int index = 1;
        for (Map.Entry<String, InventoryItem> entry : inventory.entrySet())
        {
          //  System.out.println("Index " + index + " ,SKU= " + entry.getKey() + " , Value = " + entry.getValue());
            if (index == input)
            {
                return entry.getValue();

            }

            System.out.println("Invalid Item, No item found ");

            index++ ;

        }






        return null;
    }


    public void stockStore()
    {


        inventory = new HashMap<>();

        List<Product> products = new ArrayList<>(List.of(new Product("A100", "apple", "local", Category.PRODUCE),
                new Product(randomSKU(), "Banana", "Local", Category.PRODUCE),
                new Product(randomSKU(), "Pear", "Local", Category.PRODUCE),
                new Product(randomSKU(), "Lemon", "Local", Category.PRODUCE),
                new Product(randomSKU(), "Milk", "Farm", Category.DAIRY),
                new Product(randomSKU(), "Yogurt", "Farm", Category.DAIRY),
                new Product(randomSKU(), "Cheese", "Farm", Category.DAIRY),
                new Product(randomSKU(), "Rice Long ", "Nabisco", Category.CEREAL),
                new Product(randomSKU(), "Granola ", "Nat Valley", Category.CEREAL),
                new Product(randomSKU(), "Ground beef ", "Butcher", Category.MEAT_and_SEAFOOD),
                new Product(randomSKU(), "Chicken ", "Butcher", Category.MEAT_and_SEAFOOD),
                new Product(randomSKU(), "Bacon ", "Butcher", Category.MEAT_and_SEAFOOD),
                new Product(randomSKU(), "Cola ", "Coca Next", Category.BEVERAGE),
                new Product(randomSKU(), "Coffee ", "Value", Category.BEVERAGE),
                new Product(randomSKU(), "Tea ", " Herbal", Category.BEVERAGE),
                new Product(randomSKU(), "Panadol ", " GC Smith", Category.HEALTH),
                new Product(randomSKU(), "Lotion ", "Nivea ", Category.HEALTH),
                new Product(randomSKU(), "Crisp ", "Walkers ", Category.SNACKS),
                new Product(randomSKU(), "Almonds ", "Local", Category.SNACKS),
                new Product(randomSKU(), "Croissant ", "Local", Category.BAKERY),
                new Product(randomSKU(), "Apple ", "Local", Category.PRODUCE),
                new Product(randomSKU(), "Orange " , "Local", Category.PRODUCE),
                new Product(randomSKU(), "Strawberry", "Local", Category.PRODUCE),
                new Product(randomSKU(), "Butter", "Farm ", Category.DAIRY),
                new Product(randomSKU(), "Cottage Cheese", "Farm ", Category.DAIRY),
                new Product(randomSKU(), "Ice Cream", "Farm ", Category.DAIRY),
                new Product(randomSKU(), "Frosted Flakes", "Kellogg's", Category.CEREAL),
                new Product(randomSKU(), "Muesli", "Nat Valley", Category.CEREAL),
                new Product(randomSKU(), "Lamb Steak", "Butcher", Category.MEAT_and_SEAFOOD),
                new Product(randomSKU(), "Salmon", "Butcher", Category.MEAT_and_SEAFOOD),
                new Product(randomSKU(), "Turkey Slices", "Butcher", Category.MEAT_and_SEAFOOD),
                new Product(randomSKU(), "Juice", "Tropicana", Category.BEVERAGE),
                new Product(randomSKU(), "Smoothie", "Green Valley", Category.BEVERAGE),
                new Product(randomSKU(), "Mint Tea", "Herbal ", Category.BEVERAGE),
                new Product(randomSKU(), "Vitamins", "Nature's Way ", Category.HEALTH),
                new Product(randomSKU(), "Shampoo", "Hair and Shoulders ", Category.HEALTH),
                new Product(randomSKU(), "Potato Chips", "Lays ", Category.SNACKS),
                new Product(randomSKU(), "Cashews", "Local ", Category.SNACKS),
                new Product(randomSKU(), "Doughnut", "Local ", Category.BAKERY)


        ));

        products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p, random.nextInt(20, 120), random.nextDouble(1, 10.25), 5)));


    }

    public void stockAisles()
    {


        aisleInventory = new EnumMap<>(Category.class);

        for (InventoryItem item : inventory.values())
        {
            Category aisle = item.getProduct().category();
            Map<String, InventoryItem> productMap = aisleInventory.get(aisle);
            if (productMap == null)
            {
                productMap = new TreeMap<>();
            }

            productMap.put(item.getProduct().Name(), item);
            aisleInventory.putIfAbsent(aisle, productMap);


        }


    }

    public void ListInventory()
    {
        System.out.println("-".repeat(30));
        System.out.println("List of Available Items");

        int index = 1;

        for (Map.Entry<String,InventoryItem> entry:inventory.entrySet()){
            System.out.println("index = " + index + ", SKU = " + entry.getKey() + ", Product  = "+ entry.getValue());
            index++;

        }

    }

    public static String randomSKU()
    {
        Random random = new Random();

        int newInt = 0;

        int alphabetCount = 0;
        StringBuilder randomString = new StringBuilder();
        while (randomString.length() < 5)
        {
            if (alphabetCount < 2)
            {
                char letter = collectionOfAlphabets[random.nextInt(collectionOfAlphabets.length)];
                randomString.append(letter);
                alphabetCount++;
            } else
            {
                newInt = random.nextInt(10);
                randomString.append(newInt);
            }
        }
        return randomString.toString();
    }


    public void printCurrentCart(Cart cart){
        if (cartSet.contains(cart)){

        }
    }


}

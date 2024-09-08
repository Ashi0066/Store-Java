import java.time.LocalDate;
import java.util.*;


public class Cart
{
    enum CartType{PHYSICAL,VIRTUAL}
    static int  lastId =1;
    public int id;
    Map<String,Integer> products;
    LocalDate date;
    CartType cartType ;


    public Cart (CartType cartType){
        id=lastId++;
        this.date= LocalDate.now();
        products=new HashMap<>();

        this.cartType=cartType;
    }



    public int getId()
    {
        return id;
    }

    public void addItem(InventoryItem item, int qty){





            products.merge(item.getProduct().sku(),qty,Integer::sum);
            System.out.println("prodcut adddes");


    }

    public void removeItem(InventoryItem item,int qty)
    {

            int currentAmount = products.get(item.getProduct().sku());
            if (currentAmount<qty){
                products.computeIfPresent(item.getProduct().sku(),(k,v)->v-qty);
            } else if (currentAmount==qty)
            {
                products.remove(item.getProduct().sku());

            }

    }

    public void removeItem(InventoryItem item){
       if(products.containsKey(item.getProduct().sku())){
           products.remove(item.getProduct().sku());
       }else{
           System.out.println("Item not in the cart");
       }
    }

    public void printSlip(){

        System.out.println("Cart id: %d".formatted(id));
        System.out.println("Cart Date  %s".formatted(date));
        String product = "Product";
        String amount = "Amount";
        System.out.println("%s %30s".formatted(product,amount));
        System.out.println("-".repeat(40));
        for(Map.Entry<String,Integer> entry:products.entrySet()){
            String name = entry.getKey();
            Integer pAmount= entry.getValue();
            System.out.println("%s %30d".formatted(name,pAmount));
        }




    }

}

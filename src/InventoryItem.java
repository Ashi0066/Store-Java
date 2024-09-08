public class InventoryItem
{
    private Product product;
    private int qtyTotal;
    private  int qtyReserved;
    private  int qtyReorder;
    private int qtyLow;
    private double salePrice ;


     public InventoryItem(Product product,int qtyTotal,double salePrice,int qtyLow){
         this.product= product;
         this.qtyTotal= qtyTotal;
         this.salePrice=salePrice;
         this.qtyLow=qtyLow;


     }

    public double getSalePrice()
    {
        return salePrice;
    }

    public Product getProduct()
    {
        return product;
    }

    public boolean reserveItem(int qtyToReserved){

       if (qtyTotal-qtyReserved>=qtyToReserved){
           this.qtyReserved+=qtyToReserved;

           return true;
       }else {
           System.out.println("The total number of items available exceeds reserved amount ");
           return false;
       }



     }



    public void releaseItem(int amount ){

        if (amount<=qtyReserved){
            this.qtyReserved-=amount;

        }else {
            System.out.println("Can not release more than reserved ");
        }

    }

     public void sellItem(){
         qtyTotal-=qtyReserved;

         qtyReserved=0;
     }


     public void placeInventoryOrder(int qtyReorder){

         qtyTotal=qtyTotal+qtyReorder;

         if (qtyTotal<qtyLow){
             int reorderAmount = 10 ;

             qtyTotal+=reorderAmount;
             System.out.println("Additional reorder of "+ qtyReorder + "units of "+ product.Name() + " has been placed as product reached below minimum qty ");
         }



     }

    @Override
    public String toString()
    {
        return product.toString()+", Sale price = %s , Total quantity = %d".formatted(salePrice,qtyTotal);
    }
}




public record Product(String sku,String Name, String manufacturer,Category category)
{

    @Override
    public String toString()
    {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", Name='" + Name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", category=" + category +
                '}';
    }

}

import model.MyDate;
import model.Product;
import model.Sale;
import model.UpcomingSale;

public class Test
{
   public static void main(String[] args)
   {
      Product product = new Product(1, "Paine", 200, "Neagra", " ", " ");
      Sale sale = new Sale(1, new MyDate(20, 8, 2019), new MyDate(20, 8, 2019),
            product, 50);
      
      System.out.println(((UpcomingSale) sale.getState()).getPrice(sale));
      System.out.println(((UpcomingSale) sale.getState()).getPriceAfterSaleApplies(sale));
      
      sale.getState().setNextState(sale);
      
      System.out.println(sale.getState().getPrice(sale));
      
      sale.getState().setNextState(sale);
      System.out.println(sale.getState().getPrice(sale));
   
   }

}

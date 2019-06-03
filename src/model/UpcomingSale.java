package model;


public class UpcomingSale implements SaleState
{

   @Override
   public void setNextState(Sale sale)
   {
      sale.getProduct().setPrice(getPriceAfterSaleApplies(sale));
      sale.setState(new AvailableSale());
   }

   public double getPrice(Sale sale)
   {
      return sale.getPrice();
   }

   public double getPriceAfterSaleApplies(Sale sale)
   {
      return sale.getPrice() - (sale.getAmount() * sale.getPrice()) / 100;
   }

}

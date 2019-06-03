package model;

public class AvailableSale implements SaleState
{

   @Override
   public void setNextState(Sale sale)
   {
      sale.getProduct().setPrice(getInitialPrice(sale));
      sale.setState(new FinishedSale());
   }

   public double getPrice(Sale sale)
   {
      return sale.getPrice();
   }

   public double getInitialPrice(Sale sale)
   {
      return sale.getPrice() / (100 - sale.getAmount()) * 100;
   }

}

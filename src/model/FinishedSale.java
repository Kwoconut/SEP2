package model;

public class FinishedSale implements SaleState
{

   @Override
   public void setNextState(Sale sale)
   {
      // TODO Auto-generated method stub
   }

   @Override
   public double getPrice(Sale sale)
   {
      return sale.getPrice();
   }

}

package server;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import model.MyDate;
import model.Sale;
import model.Store;
import model.StoreModel;

public class TaskScheduler implements Runnable
{
   private int hour;
   ServerModel model;

   public TaskScheduler(ServerModel model)
   {

      Calendar calendar = Calendar.getInstance();
      this.hour = calendar.get(Calendar.HOUR_OF_DAY);
      this.model = model;
   }

   private int getHoursUntilTarget()
   {
      return 24 - hour;
   }

   private void goThroughSales()
   {
      MyDate now = new MyDate();
      ArrayList<Sale> sales = model.getSales();
      for (int i = 0; i < sales.size(); i++)
      {
         if (now.isAfter(sales.get(i).getEndDate()))
         {
            model.updateRemoveSale(sales.get(i)); // in server update product
                                                  // (including dtb and client)
            model.removeSale(sales.get(i)); // remove sale from
                                            // server/db/clients cuz sale ended
         }
      }
      for (int i = 0; i < sales.size(); i++)
      {
         if (sales.get(i).getIsChangedValue() == false
               && now.isAfter(sales.get(i).getStartDate()))
         {
            model.updateAddSale(sales.get(i)); // in server update
                                               // product(including dtb and
                                               // client)
            model.changedValue(sales.get(i)); // set the boolean to true
         }
      }
   }

   @Override
   public void run()
   {
      while (true)
      {
         goThroughSales();
         int time = getHoursUntilTarget();
         try
         {
            Thread.sleep(time * 1000 * 60 * 60);
         }
         catch (InterruptedException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }

   }
}

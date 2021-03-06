package server;

import java.util.ArrayList;
import java.util.Calendar;
import model.MyDate;
import model.Sale;
import model.UpcomingSale;

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
            model.removeSale(sales.get(i));
         }
      }

      for (int i = 0; i < sales.size(); i++)
      {
         if (sales.get(i).getState() instanceof UpcomingSale
               && now.equals(((sales.get(i).getStartDate()))))
         {
            model.setSaleAvailable(sales.get(i));
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
            e.printStackTrace();
         }

      }

   }
}

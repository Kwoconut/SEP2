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
		   this.hour= calendar.get(Calendar.HOUR_OF_DAY);
		   this.model = model;
	   }
	   
	   private int getHoursUntilTarget() 
	   {
	       return hour < 24 ? 24 - hour : 24 - hour + 24;
	   }
	   private void goThroughSales()
	   {	   
		   MyDate now = new MyDate();
		   ArrayList<Sale> sales = model.getSales();
		   for(int i=0;i<sales.size();i++)
		   {
			  if(sales.get(i).getEndDate().isAfter(now));
					  {
						  model.updateSale(sales.get(i));
						  model.removeSale(sales.get(i));
					  }
			  if(sales.get(i).getStartDate())
		   }
	   }
	@Override
	public void run() 
	{
		while (true)
	      {
	    	  TaskScheduler tsk = new TaskScheduler();
	    	  tsk.goThroughSales();
	    	  int time = getHoursUntilTarget();
	         try
	         {
	            Thread.sleep(time*1000);
	         }
	         catch (InterruptedException e)
	         {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
		
	}

}
}

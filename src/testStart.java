import javafx.application.Application;

public class testStart
{
   public static void main(String[] args)
   {
      new Thread(() -> Application.launch(test.class)).start();
   }

}

package model;

import java.io.Serializable;

public class Offer implements Serializable
{
   private int ID;
   private String name;
   private String phoneNo;
   private String email;
   private String message;

   public Offer(int ID, String name, String phoneNo, String email,
         String message)
   {
      this.ID = ID;
      this.name = name;
      this.phoneNo = phoneNo;
      this.email = email;
      this.message = message;
   }

   public int getID()
   {
      return ID;
   }

   public String getName()
   {
      return name;
   }

   public String getPhoneNo()
   {
      return phoneNo;
   }

   public String getEmail()
   {
      return email;
   }

   public String getMessage()
   {
      return message;
   }

   public int checkFormat()
   {
      if (name.equals("") || name.length() > 50)
      {
         return 0;
      }
      else if (phoneNo.equals("") || phoneNo.length() < 8
            || phoneNo.length() > 20)
      {
         return 1;
      }
      else if (email.equals("")
            || !(email
                  .matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
                        + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"))
            || email.length() > 100)
      {

         return 2;
      }
      else if (message.equals(""))
      {
         return 3;
      }
      else
      {
         return 4;
      }
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Offer))
      {
         return false;
      }
      Offer other = (Offer) obj;
      return ID == other.getID() && name.equals(other.getName()) && phoneNo.equals(other.getPhoneNo())
            && email.equals(other.getEmail())
            && message.equals(other.getMessage());
   }

   public String toString()
   {
      return ID + "\n" + name + "\n" + phoneNo + "\n" + email + "\n" + message;
   }
}

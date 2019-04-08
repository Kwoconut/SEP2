package model;

public class Offer
{
   private String name;
   private String phoneNo;
   private String email;
   private String message;

   public Offer(String name, String phoneNo, String email, String message)
   {
      this.name = name;
      this.phoneNo = phoneNo;
      this.email = email;
      this.message = message;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getName()
   {
      return name;
   }

   public void setPhoneNo(String phoneNo)
   {
      this.phoneNo = phoneNo;
   }

   public String getPhoneNo()
   {
      return phoneNo;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getEmail()
   {
      return email;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public String getMessage()
   {
      return message;
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Offer))
      {
         return false;
      }
      Offer other = (Offer) obj;
      return name.equals(other.getName()) && phoneNo.equals(other.getPhoneNo())
            && email.equals(other.getEmail())
            && message.equals(other.getMessage());
   }
   
   public String toString()
   {
      return name + "\n" + phoneNo + "\n" + email + "\n" + message;
   }
}

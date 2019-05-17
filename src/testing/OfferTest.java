package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Offer;

public class OfferTest
{

   private Offer offer;
   private Offer emptyNameOffer;
   private Offer wrongNameOffer;
   private Offer wrongEmailOffer;
   private Offer wrongPhoneOffer;
   private Offer emptyMessageOffer;

   @Before
   public void setUP()
   {
      offer = new Offer(1, "Jack", "+45748563857", "jack.jackson@gmail.com",
            "I want to buy some roof.");
   }

   @Test
   public void testGetID()
   {
      assertEquals(1, offer.getID());
   }

   @Test
   public void testGetName()
   {
      assertEquals("Jack", offer.getName());
   }

   @Test
   public void testGetPhoneNo()
   {
      assertEquals("+45748563857", offer.getPhoneNo());
   }

   @Test
   public void testGetEmail()
   {
      assertEquals("jack.jackson@gmail.com", offer.getEmail());
   }

   @Test
   public void testGetMessage()
   {
      assertEquals("I want to buy some roof.", offer.getMessage());
   }

   @Test
   public void testCheckFormat()
   {
      emptyNameOffer = new Offer(1, "", "+45748563857",
            "jack.jackson@gmail.com", "I want to buy some roof.");
      wrongNameOffer = new Offer(1, "slkzghsda;ghslakdhgksadgflkasjkfhaslfdlasfksldjga;sldag;lsadjg;ldashg;lhsghk", "+45748563857",
            "jack.jackson@gmail.com", "I want to buy some roof.");
      wrongEmailOffer = new Offer(1, "Jack", "+45748563857",
            "jack.jacksongmail.com", "I want to buy some roof.");
      wrongPhoneOffer = new Offer(1, "Jack", "+45", "jack.jackson@gmail.com",
            "I want to buy some roof.");
      emptyMessageOffer = new Offer(1,"Jack","+45748563857", "jack.jackson@gmail.com",
            "");
      
      
      assertEquals(0,emptyNameOffer.checkFormat());
      assertEquals(0,wrongNameOffer.checkFormat());
      assertEquals(1,wrongPhoneOffer.checkFormat());
      assertEquals(2,wrongEmailOffer.checkFormat());
      assertEquals(3,emptyMessageOffer.checkFormat());
   }

   @Test
   public void testEqualsObject()
   {
      Offer offer2 = new Offer(1, "Jack", "+45748563857", "jack.jackson@gmail.com",
            "I want to buy some roof.");
      Offer offer3 = new Offer(1, "Ja", "+45748563857", "jack.jackson@gmail.com",
            "I want to buy some roof.");
      assertEquals(true,offer.equals(offer));
      assertEquals(false,offer.equals(offer3));
   }
   
   @Test
   public void TestEqualsNullObject()
   {
      assertEquals(false,offer.equals(null));
   }

}

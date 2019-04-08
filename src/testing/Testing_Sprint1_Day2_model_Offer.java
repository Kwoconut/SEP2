package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Offer;

public class Testing_Sprint1_Day2_model_Offer 
{
	private Offer offer1;
	private Offer offer2;
	private Offer offer3;
	
	@Before
	public void init()
	{
		offer1 = new Offer("Angel","88003553555" , "Angel.ingeras@gmail.com", "Eu vreu sifer calitativ, da ieftin , totusi is roman huli");
		offer2 = new Offer("Andrei","6666666" , "Andrei.hellMaster@gmail.com", "Dieeee!!! ,, You all will dieeeee !!!!");
		offer3 = new Offer("Andrei","6666666" , "Andrei.hellMaster@gmail.com", "Dieeee!!! ,, You all will dieeeee !!!!");
	}
	
	@Test
	
	public void setName() 
	{
		offer1.setName("Amin");
		assertEquals("Amin",offer1.getName());
	}
	
	@Test
	public void getName()
	{
		assertEquals("Angel",offer1.getName());
	}
	
	@Test
	public void setPhoneNo()
	{
		offer1.setPhoneNo("7777777");
		assertEquals("7777777",offer1.getPhoneNo());
	}
	
	@Test
	public void getPhoneNo()
	{
		assertEquals("88003553555",offer1.getPhoneNo());
	}
	
	@Test
	public void setEmail()
	{
		offer1.setEmail("ValeraGay@mail.ru");
		assertEquals("ValeraGay@mail.ru",offer1.getEmail());
	}
	
	@Test
	public void getEmail()
	{
		assertEquals("Angel.ingeras@gmail.com",offer1.getEmail());
	}
	@Test
	
	public void setMessage()
	{
		offer1.setMessage("I am very good boy");
		assertEquals("I am very good boy",offer1.getMessage());
	}
	
	@Test
	public void getMessage()
	{
		assertEquals("Eu vreu sifer calitativ, da ieftin , totusi is roman huli",offer1.getMessage());
	}
	
	@Test
	public void equals()
	{
		assertEquals(true,offer2.equals(offer3));
	}
	
	@Test
	public void toStringTest()
	{
		String test;
		test = "Andrei"+"\n" + "6666666" + "\n" + "Andrei.hellMaster@gmail.com" + "\n" + "Dieeee!!! ,, You all will dieeeee !!!!";
		assertEquals(test,offer3.toString());
	}

}

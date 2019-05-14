package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.Offer;

public class Testing_Sprint2_Day3_model_Offer {
	private Offer offer1;
	private Offer offer2;
	private Offer offer3;
	
	@Before
	public void init()
	{
		offer1 = new Offer(1,"Angel","88003553555" , "Angel.ingeras@gmail.com", "Eu vreu sifer calitativ, da ieftin , totusi is roman huli");
		offer2 = new Offer(2,"","6666666" , "Andrei.hellMaster@gmail.com", "Dieeee!!! ,, You all will dieeeee !!!!");
		offer3 = new Offer(3,"Andrei","666642666" , "andrei", "Dieeee!!! ,, You all will dieeeee !!!!");
	}
	@Test
public void getID()
{
   assertEquals(1,offer1.getID());
}
@Test
public void setID()
{
	offer1.setID(6);
   assertEquals(6,offer1.getID());
}
@Test
public void checkFormat()
{
	assertEquals(4,offer1.checkFormat());
}
@Test
public void checkFormat1()
{
	assertEquals(0,offer2.checkFormat());
}
@Test
public void checkFormat2()
{
	assertEquals(2,offer3.checkFormat());
}
}
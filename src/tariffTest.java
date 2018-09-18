import static org.junit.Assert.*;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class tariffTest {
	parking parking1;
	entrance entrance1;
	tariff tariff1;
	FrameFixture tar1;
	
	@Before
	public void setUp() throws Exception {
		parking1=new parking();
		entrance1=new entrance(parking1);
		tariff1=new tariff("B002",parking1,entrance1);
		tar1=new FrameFixture(tariff1);
		tar1.show();
	}

	@After
	public void tearDown() throws Exception {
		tar1.cleanUp();
	}

	

	@Test
	public void testActionPerformed() {
	}

	@Test
	public void testGetTariff() {
	}

	@Test
	public void testGetUserID() {
	}

	@Test
	public void testGetPakingID() {
	}

}

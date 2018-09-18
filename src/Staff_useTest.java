import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Staff_useTest {
	JFrame frame;
	entrance entrance1;
	parking parking1;
	Staff_use Staff_use1;
	SimpleDateFormat df;
	FrameFixture sta1;
	
	@Before
	public void setUp() throws Exception {
		parking1=new parking();
		entrance1=new entrance(parking1);
		df=new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Staff_use1=new Staff_use(entrance1,parking1,"A100",df.format(new Date()));
		sta1=new FrameFixture(Staff_use1.frame);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testStaff_use() {
		sta1.show();
		sta1.close();
	}

}

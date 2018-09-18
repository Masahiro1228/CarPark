import static org.junit.Assert.*;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class exitTest {
	exit exit1;
	parking parking1;
	entrance entrance1;
	FrameFixture exi1;
	
	@Before
	public void setUp() throws Exception {
		parking1=new parking();
		entrance1=new entrance(parking1);
		exit1=new exit(parking1,entrance1);
		exi1=new FrameFixture(exit1);
		exi1.show();
	}

	@After
	public void tearDown() throws Exception {
		exi1.cleanUp();
	}

	@Test
	public void testExit() {
		exi1.textBox("textField1").enterText("A100");
		exi1.button("button1").click();
	}

	@Test
	public void testActionPerformed() {
	}

}



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class TestCoordinates {
	Collection<Integer> collection;

	   @Test
	    public void testCollectionAdd() {
	        collection = new ArrayList<Integer>();
	        assertEquals(0, collection.size());
	        collection.add(10);
	        assertEquals(1, collection.size());
	        collection.add(20);
	        assertEquals(2, collection.size());
	        collection.add(30);
	        assertEquals(3, collection.size());
	        collection.add(40);
	        assertEquals(4, collection.size());
	        collection.add(50);
	        assertEquals(5, collection.size());
	    }
	   
	@Test
    public void ReturnRightxyList() {
   
		// assert statements
        assertEquals("Return empty y coordinates: ", collection, PanelCam.getRyList());
        assertEquals("Return empty x coordinates: ", collection, PanelCam.getRxList());
		            
	}
	
	@Test
    public void ReturnLeftxyList() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.getLyList());
            assertEquals("Return empty x coordinates: ", collection, PanelCam.getLxList());
    }
	
	@Test
    public void ReturnMouthxyList() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.getMyList());
            assertEquals("Return empty x coordinates: ", collection, PanelCam.getMxList());
    }
	
	@Test
    public void ReturnLeftEyeYCoordinates() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.leyey);
    }
	
	
	@Test
    public void ReturnLeftEyeXCoordinates() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.leyex);
    }

	@Test
    public void ReturnRightEyeYCoordinates() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.reyey);
    }
	
	@Test
    public void ReturnRightEyeXCoordinates() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.reyex);
    }
	
	@Test
    public void ReturnMouthyCoordinates() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.mouthy);
    }
	
	@Test
    public void ReturnMouthXCoordinates() {
   
			// assert statements
            assertEquals("Return empty y coordinates: ", collection, PanelCam.mouthx);
    }

}

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main {

	   public static void main(String s[]) {       
		   JFrame f = new JFrame("Facial Recognition"); 
		   f.addWindowListener(new WindowAdapter() {  
			   
			   public void windowClosing(WindowEvent e) {
				   System.exit(0);
			   }  
		   });  
		   
	   f.pack();  
	   f.setSize(200,200);  
	   f.setVisible(true);  
	   }  
}

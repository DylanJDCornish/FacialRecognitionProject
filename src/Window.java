import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;

public class Window extends PanelCam {
	private static final long serialVersionUID = 1L;
	private static final String TITLE_TEXT = "Facial Recognition to find Clinical Diseases Detector";
	private static final int TITLE_POINTS = 12;
	private static final int HEADING_POINTS = 10;
	private static final int COORD = 10;
	static JFrame frame;
	static JLabel cameraLabel;
	static ImageIcon Webcam;
	static JPanel grid, gridWebcam;
	static ArrayList<Integer> leyex, leyey, reyex, reyey, mouthx, mouthy;
	static String leyexy, reyexy, mouthxy;
	static JScrollPane scrollPane;
	static JTextArea textContent;
	static PanelCam pc = new PanelCam();
	
	public Window() {
		
	}
	
	//Create a frame
	public static void GUI() {
		textContent = new JTextArea();
		textContent.setLayout(null);
		textContent.setEditable(false);
		textContent.setBounds(52, 47, 300, 135);
		JScrollPane loadScroll = new JScrollPane(textContent);

		JButton captureBtn = new JButton("Stop Webcam");
		JButton saveAllBtn = new JButton("Save All Coordinates");
		JButton saveBtn = new JButton("Save Results");
		JButton loadBtn = new JButton("Load Results");
		JButton exitBtn = new JButton("Exit");

		JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
	    titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, TITLE_POINTS));
	    titleLabel.setForeground(Color.black);
		JLabel space = new JLabel("");
		JLabel space4 = new JLabel("");
		JLabel lCoord = new JLabel("Left Eye: ", SwingConstants.CENTER);
		lCoord.setFont(lCoord.getFont().deriveFont(Font.BOLD, HEADING_POINTS));
		JLabel rCoord = new JLabel("Right Eye: ",  SwingConstants.CENTER);
		rCoord.setFont(rCoord.getFont().deriveFont(Font.BOLD, HEADING_POINTS));
		JLabel mCoord = new JLabel("Mouth: ", SwingConstants.CENTER);
		mCoord.setFont(mCoord.getFont().deriveFont(Font.BOLD, HEADING_POINTS));
		JLabel loadDataTitle = new JLabel("Loaded Data Results: ");
		loadDataTitle.setFont(loadDataTitle.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel leftEyeCoordLowest = new JLabel("Lowest Left Eye Coordinates: ");
	    leftEyeCoordLowest.setFont(leftEyeCoordLowest.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel leftEyeCoordHighest = new JLabel("Highest Left Eye Coordinates: ");
	    leftEyeCoordHighest.setFont(leftEyeCoordHighest.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel leftEyeCoordAverage = new JLabel("Average Left Eye Coordinates: ");
	    leftEyeCoordAverage.setFont(leftEyeCoordAverage.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel rightEyeCoordLowest = new JLabel("Lowest Right Eye Coordinates: ");
	    rightEyeCoordLowest.setFont(rightEyeCoordLowest.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel rightEyeCoordHighest = new JLabel("Highest Right Eye Coordinates: ");
	    rightEyeCoordHighest.setFont(rightEyeCoordHighest.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel rightEyeCoordAverage = new JLabel("Average Right Eye Coordinates: ");
	    rightEyeCoordAverage.setFont(rightEyeCoordAverage.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel mouthCoordLowest = new JLabel("Lowest Mouth Coordinates: ");
	    mouthCoordLowest.setFont(mouthCoordLowest.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel mouthCoordHighest = new JLabel("Highest Mouth Coordinates: ");
	    mouthCoordHighest.setFont(mouthCoordHighest.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel mouthCoordAverage = new JLabel("Average Mouth Coordinates: ");
	    mouthCoordAverage.setFont(mouthCoordAverage.getFont().deriveFont(Font.PLAIN, COORD));
	    JLabel loadResultsTitle = new JLabel("Loaded Results: ", SwingConstants.CENTER);
	    loadResultsTitle.setFont(loadResultsTitle.getFont().deriveFont(Font.BOLD, HEADING_POINTS));
	    
	    
        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new java.awt.BorderLayout());
        graphPanel.setBorder(BorderFactory.createMatteBorder(1,0,1,1, Color.BLACK));
        ChartPanel CP = new ChartPanel(Graph.chart);
        graphPanel.add(CP,BorderLayout.CENTER);
        graphPanel.validate();

		XYSeries leftEye = new XYSeries("Left Eye"); 
		Graph.dataset.addSeries(leftEye); 
		XYSeries rightEye = new XYSeries("Right Eye"); 
		Graph.dataset.addSeries(rightEye);
		XYSeries mouth = new XYSeries("Mouth"); 
		Graph.dataset.addSeries(mouth);
	    
	    grid = new JPanel();
		GridLayout layout = new GridLayout(25,450);
		grid.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
		grid.setLayout(layout);
		grid.add(space);
		grid.add(titleLabel);
		grid.add(captureBtn);
		grid.add(saveAllBtn);
		grid.add(saveBtn);
		grid.add(loadBtn);
		grid.add(exitBtn);
		grid.add(lCoord);
		grid.add(leftEyeCoordLowest);
		grid.add(leftEyeCoordHighest);
		grid.add(leftEyeCoordAverage);
		grid.add(rCoord);
		grid.add(rightEyeCoordLowest);
		grid.add(rightEyeCoordHighest);
		grid.add(rightEyeCoordAverage);
		grid.add(mCoord);
		grid.add(mouthCoordLowest);
		grid.add(mouthCoordHighest);
		grid.add(mouthCoordAverage);
		grid.add(space4);
		grid.add(loadResultsTitle);
		grid.add(loadScroll);
	
		frame = new JFrame();
		frame.getContentPane().setLayout(new GridLayout());
		frame.setSize(1400, 450);
		frame.getContentPane().add(grid);
	    frame.getContentPane().add(graphPanel);
		frame.setVisible(true);
		frame.setTitle("Facial Recognition");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		captureBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (PanelCam.videoDevice.isOpened()) { 
					stopCamera();
				  
				  String [] getAllCoordinates = PanelCam.allCoordinates();
				  String leftEyeAverage = getAllCoordinates[0];
				  String leftEyeMin = getAllCoordinates[1];
				  String leftEyeMax = getAllCoordinates[2];
				  String mouthAverage = getAllCoordinates[3];
				  String mouthMin = getAllCoordinates[4];
				  String mouthMax = getAllCoordinates[5];
				  String rightEyeAverage = getAllCoordinates[6];
				  String rightEyeMin =  getAllCoordinates[7];
				  String rightEyeMax =  getAllCoordinates[8];
				  
				  leftEyeCoordAverage.setText("Average Left Eye Coordinates: " + leftEyeAverage);
				  rightEyeCoordAverage.setText("Average Right Eye Coordinates: " + rightEyeAverage);
				  mouthCoordAverage.setText("Average Mouth Coordinates: " + mouthAverage);
				  leftEyeCoordLowest.setText("Lowest Left Eye Coordinates: " + leftEyeMin);
				  rightEyeCoordLowest.setText("Lowest Right Eye Coordinates: " + rightEyeMin);
				  mouthCoordLowest.setText("Lowest Mouth Coordinates: " + mouthMin);
				  leftEyeCoordHighest.setText("Highest Left Eye Coordinates: " + leftEyeMax);
				  mouthCoordHighest.setText("Highest Mouth Coordinates: " + mouthMax);
				  rightEyeCoordHighest.setText("Highest Right Eye Coordinates: " + rightEyeMax);
		
				  leyex = PanelCam.getLxList();
				  leyey = PanelCam.getLyList();
				  reyex = PanelCam.getRxList();
				  reyey = PanelCam.getRyList();
				  mouthx = PanelCam.getMxList();
				  mouthy = PanelCam.getMyList();
				  leyexy = ("Left Eye Coordinates: " + "\r\n" + "X: " + leyex + "\r\n" + "Y: " + leyey);
				  reyexy = ("Right Eye Coordinates: " + "\r\n" + "X: " + reyex + "\r\n" + "Y: " + reyey);
				  mouthxy = ("Mouth Coordinates: " + "\r\n" + "X: " + mouthx + "\r\n" + "Y: " + mouthy);
					
					
				  	for(int i = 0; i < leyex.size(); i++) {
				  		Integer lXcoord = leyex.get(i);
						Integer lYcoord = leyey.get(i);
						leftEye.add(lXcoord, lYcoord);
					}

					for(int i = 0; i < reyex.size(); i++) {
						Integer rXcoord = reyex.get(i);
						Integer rYcoord = reyey.get(i);
						rightEye.add(rXcoord, rYcoord);
					}
					
					for(int i = 0; i < mouthx.size(); i++){
						Integer mXcoord = mouthx.get(i);
						Integer mYcoord = mouthy.get(i);
						mouth.add(mXcoord, mYcoord);
					}
					
				}
			
					else if (!PanelCam.videoDevice.isOpened()) {
				  
					}
		}});
		
		saveAllBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File(""));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileTypeFilter(".txt", "TextFile"));
				
				int result = fs.showSaveDialog(null);
					if(result == JFileChooser.APPROVE_OPTION) {
						File fi = fs.getSelectedFile();
							try{
								String s2 = System.lineSeparator();
								FileWriter fw = new FileWriter(fi.getPath());
								fw.write(String.valueOf(leyexy));
								fw.write(s2);
								fw.write(String.valueOf(reyexy));
								fw.write(s2);
								fw.write(String.valueOf(mouthxy));
								fw.flush();
								fw.close();
							}
							catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
				  
					}
		  }
		
		});
		
		saveBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				JFileChooser fs = new JFileChooser(new File(""));
				fs.setDialogTitle("Save a File");
				fs.setFileFilter(new FileTypeFilter(".txt", "TextFile"));
				
				int result = fs.showSaveDialog(null);
					if(result == JFileChooser.APPROVE_OPTION) {
						File fi = fs.getSelectedFile();
							try{
								String s1 = System.lineSeparator();
								FileWriter fw = new FileWriter(fi.getPath());
								fw.write(String.valueOf("Lowest Left Eye Coordinates: " + leftEyeMin));
								fw.write(s1);
								fw.write(String.valueOf("Highest Left Eye Coordinates: " + leftEyeMax));
								fw.write(s1);
								fw.write(String.valueOf("Average Left Eye Coordinates: " + leftEyeAverage));
								fw.write(s1);
								fw.write(String.valueOf("Lowest Right Eye Coordinates: " + rightEyeMin));
								fw.write(s1);
								fw.write(String.valueOf("Highest Right Eye Coordinates: " + rightEyeMax));
						  		fw.write(s1);
						  		fw.write(String.valueOf("Average Right Eye Coordinates: " + rightEyeAverage));
						  		fw.write(s1);
						  		fw.write(String.valueOf("Lowest Mouth Coordinates: " + mouthMin));
						  		fw.write(s1);
						  		fw.write(String.valueOf("Highest Mouth Coordinates: " + mouthMax));
						  		fw.write(s1);
						  		fw.write(String.valueOf("Average Mouth Coordinates: " + mouthAverage));
						  		fw.flush();
						  		fw.close();
							}
							catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
				  }
			}
		});
		
		loadBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				JFileChooser fs = new JFileChooser(new File(""));
					fs.setDialogTitle("Open a File");
					fs.setFileFilter(new FileTypeFilter(".txt", "Text File"));
					int result = fs.showOpenDialog(null);
						if(result == JFileChooser.APPROVE_OPTION) {
								try{
									File fi = fs.getSelectedFile();
									String filename = fi.getAbsolutePath();
									FileReader reader = new FileReader(filename);									
									BufferedReader br = new BufferedReader(reader);
									textContent.read(br, null);
									br.close();
									textContent.requestFocus();
											} 
								catch(Exception e2){
									JOptionPane.showMessageDialog(null, e2.getMessage());
								}
						}
			}
		});
		
		exitBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			System.exit(0);
			}
	});

	}
	
	//Create label to show image
	public static void PushImage(Image img2) {
		if (frame == null) {
			Window.GUI();
		}
		
		if (cameraLabel != null) {
			frame.remove(cameraLabel);
		}
		
		Webcam = new ImageIcon(img2);
		cameraLabel = new JLabel(Webcam, JLabel.CENTER);
		gridWebcam = new JPanel();
		gridWebcam.setBorder(BorderFactory.createMatteBorder(1,0,1,1, Color.BLACK));
		gridWebcam.add(cameraLabel);
		frame.getContentPane().add(cameraLabel);
	
		//New Frame object
		frame.revalidate();
	}
	
	public static void stopCamera() {
		videoDevice.release();
	}
}
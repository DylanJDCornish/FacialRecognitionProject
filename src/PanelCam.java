import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class PanelCam extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// Initiate variables
	static Mat frameCapture;
	static MatOfRect faces, mouths, Leyes, Reyes;
	static Point facey;
	static VideoCapture videoDevice;
	static String l, r, c, d1, leftEyeAverage, leftEyeMin, leftEyeMax, mouthMin, mouthAverage, mouthMax, rightEyeMin, rightEyeMax, rightEyeAverage;
	static Integer x, y;
	static MatOfByte byteMatData;
	static ArrayList<Integer> leyex, leyey, reyex, reyey, mouthx, mouthy;

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Add method to main
		Detection();
	}
	
	public static void Detection() {

		 // Add xml cascade files to classifier
		 CascadeClassifier cascadeFaceClassifier = new CascadeClassifier(
				"C:\\FacialRecognitionApplication\\resources\\lbpcascades\\lbpcascade_frontalface.xml");
		 CascadeClassifier cascadeLeftEyeClassifier = new CascadeClassifier(
				"C:\\FacialRecognitionApplication\\data\\haarcascades\\haarcascade_lefteye_2splits.xml");
		 CascadeClassifier cascadeRightEyeClassifier = new CascadeClassifier(
				"C:\\FacialRecognitionApplication\\data\\haarcascades\\haarcascade_righteye_2splits.xml");
		 CascadeClassifier cascadeMouthClassifier = new CascadeClassifier(
	    		"C:\\FacialRecognitionApplication\\resources\\Mouth.xml");
		
		//Default camera device
	    videoDevice = new VideoCapture();
		videoDevice.open(0);
		
		// Assign variables
		leyex = new ArrayList<Integer>();
		leyey = new ArrayList<Integer>();


		reyex = new ArrayList<Integer>();
		reyey = new ArrayList<Integer>();
		mouthx = new ArrayList<Integer>();
		mouthy = new ArrayList<Integer>();
		
		// Format coordinate outcome to two decimal places
		DecimalFormat df = new DecimalFormat("#.00");
		
			if (videoDevice.isOpened()) {
				// Continuous image flow with an infinite loop	
				while (true) {		
					Mat frameCapture = new Mat();
					Size sz = new Size(350,350);
					videoDevice.read(frameCapture);
					Imgproc.resize(frameCapture, frameCapture, sz);
					// The captured image is first returned and loaded into the frame
					faces = new MatOfRect();
					// Apply cascade
					cascadeFaceClassifier.detectMultiScale(frameCapture, faces);								
					// If there is a frame to be captured, draw a square
						for (Rect face : faces.toArray()) {
							Imgproc.rectangle(frameCapture, new Point(face.x, face.y-10),
							new Point(face.x + face.width, face.y + face.height+10), new Scalar(0, 200, 0),2);
							facey = new Point(face.x, face.y);
						
				int limitleye = 0;
				// Find the left eye
				Leyes = new MatOfRect();
				cascadeLeftEyeClassifier.detectMultiScale(frameCapture, Leyes);
					for (Rect Leye : Leyes.toArray()) {
						if (limitleye == 1){
						// For getting x cordinates
						x = Leye.x;
						// For getting y cordinates
						y = Leye.y;
						
						// Add coordinates to array
						leyex.add(x);
						leyey.add(y);

						// Assign variables
						Double totalx = 0.0;
						Double averagex = 0.0;
						Double totaly = 0.0;
						Double averagey = 0.0;
						Integer xmin = leyex.get(0);
						Integer ymin = leyey.get(0);
						Integer xmax = 0;
						Integer ymax = 0;
						
							for (Integer i = 0; i < leyex.size(); i++) {
								Integer numberx = leyex.get(i);
								totalx = totalx + numberx;
									// min
									if(xmin > numberx) {
										xmin = numberx;
									}
										// max
										if (xmax < numberx) {
											xmax = numberx;
										}
							}
							
							for (Integer i = 0; i < leyey.size(); i++) {
								Integer numbery = leyey.get(i);
								totaly = totaly + numbery;
									if(ymin > numbery) {
										ymin = numbery;
									}
										if (ymax < numbery) {
										ymax = numbery;
										}
							}
				
				// Print outcomes onto console
				String c1 = ("Left Eye Coordinates: " + " x: " + x + ", y: " + y);
				System.out.println(String.format(c1));
				System.out.println("THis is the left eye x HIGHEST: " + xmax.toString());
				System.out.println("THis is the left eye y HIGHEST: " + ymax.toString());
				System.out.println("THis is the left eye x LOWEST: " + xmin.toString());
				System.out.println("THis is the left eye y LOWEST: " + ymin.toString());
					
					for (Integer i = 0; i < leyey.size(); i++) {
						totaly = totaly + leyey.get(i);		
					}
		
				averagex = totalx / leyex.size();
				averagey = totaly / leyey.size();
				
				System.out.println("THis is the left eye X AVERAGE: " + averagex.toString());
				System.out.println("THis is the left eye Y AVERAGE: " + averagey.toString());
				
				// Format outcomes
				leftEyeAverage =  df.format(averagex) + "x" + ", " + df.format(averagey) + "y";
				leftEyeMin = df.format(xmin)+ "x" + ", " + df.format(ymin) + "y";
				leftEyeMax = df.format(xmax) + "x" + ", " + df.format(ymax) + "y";
				c = ("Left eye Coordinates: " + "x: " + x + ", y: " + y);
				l = ("X: " + x + "   " + "Y: " + y);

				Point Leyepoint = new Point(Leye.x, Leye.y);
						
					//Draw a square
					if (face.contains(Leyepoint)) {
						Imgproc.putText(frameCapture, l, new Point(Leye.x,Leye.y-5), 1, 1, new Scalar(0,0,0));
						Imgproc.rectangle(frameCapture, new Point(Leye.x, Leye.y), new Point(Leye.x + Leye.width, Leye.y + Leye.height),
						new Scalar(200, 200, 100),2);
					}
						}
					else {
						
					}
						
				limitleye++;
					}

				int limitmouth = 0;
						
				// Find the mouth
				mouths = new MatOfRect();
				// Apply cascade
				cascadeMouthClassifier.detectMultiScale(frameCapture, mouths, 1.1, 2, 0, new Size(30, 30), new Size());
					for (Rect mouth : mouths.toArray()) {
						if (limitmouth == 1) {
							int x1 = mouth.x; //for getting x cordinates
							int y1 = mouth.y; //for getting y cordinates
							
							// Add coordinates to array
							mouthx.add(x1);
							mouthy.add(y1);
							
							// Assign variables
							Double totalx = 0.0;
							Double averagex = 0.0;
							Double totaly = 0.0;
							Double averagey = 0.0;
							Integer xmin = mouthx.get(0);
							Integer ymin = mouthy.get(0);
							Integer xmax = 0;
							Integer ymax = 0;
							
								for (Integer i = 0; i < mouthx.size(); i++) {
									Integer numberx = mouthx.get(i);
									totalx = totalx + numberx;
										if(xmin > numberx) {
											xmin = numberx;
										}
								
										if (xmax < numberx) {
											xmax = numberx;
										}
								}
									
								for (Integer i = 0; i < mouthy.size(); i++) {
									Integer numbery = mouthy.get(i);
									totaly = totaly + numbery;
										if(ymin > numbery) {
											ymin = numbery;
										}
										
										if (ymax < numbery) {
											ymax = numbery;
										}
								}
							
								for (Integer i = 0; i < mouthx.size(); i++) {
									totalx = totalx + mouthx.get(i);
								}
							
								for (Integer i = 0; i < mouthy.size(); i++) {
									totaly = totaly + mouthy.get(i);			
								}
				
				// Calculate average			
				averagex = totalx / mouthx.size();
				averagey = totaly / mouthy.size();
							
				System.out.println("THis is the mouth X AVERAGE: " + averagex.toString());
				System.out.println("THis is the mouth Y AVERAGE: " + averagey.toString());
				
				// Format outcomes
				mouthAverage = df.format(averagex) + "x" + ", " + df.format(averagey) + "y";
				mouthMin = df.format(xmin) + "x" + ", " + df.format(ymin) + "y";
				mouthMax = df.format(xmax) + "x" + ", " + df.format(ymax) + "y";
				String c1 = ("Mouth Coordinates: " + " x: " + x1 + ", y: " + y1);
				d1 = ("X: " + x1 + "   " + "Y: " + y1);
				
				// Print outcomes onto console
				System.out.println(String.format(c1));
				System.out.println("THis is the Mouth x HIGHEST: " + xmax.toString());
				System.out.println("THis is the Mouth y HIGHEST: " + ymax.toString());
				System.out.println("THis is the mouth x LOWEST: " + xmin.toString());
				System.out.println("THis is the mouth y LOWEST: " + ymin.toString());
							
				Point mouthpoint = new Point(mouth.x, mouth.y);
							
					// Draw a rectangle
					if (face.contains(mouthpoint)) {
						Imgproc.putText(frameCapture, d1, new Point(mouth.x,mouth.y-5), 1, 1, new Scalar(0,0,255));
						Imgproc.rectangle(frameCapture, new Point(mouth.x, mouth.y), new Point(mouth.x + mouth.width, mouth.y + mouth.height),
						new Scalar(129, 90, 50),2);
					}
						}
					else {
						
					}
				limitmouth++;
					}

				int limit = 0;
					
				// Find the right eye
				Reyes = new MatOfRect();
				// Apply cascade
				cascadeRightEyeClassifier.detectMultiScale(frameCapture, Reyes);
					for (Rect Reye : Reyes.toArray()) {
						if (limit == 1){
							// For getting x cordinates
							int Rx = Reye.x; 
							// For getting y cordinates
							int Ry = Reye.y;
						
							// Add coordinates to array
							reyex.add(Rx);
							reyey.add(Ry);
						
							// Assign variables
							Double totalx = 0.0;
							Double averagex = 0.0;
							Double totaly = 0.0;
							Double averagey = 0.0;
							Integer xmax = 0;
							Integer ymax = 0;
							Integer xmin = reyex.get(0);
							Integer ymin = reyey.get(0);
						
								for (Integer i = 0; i < reyex.size(); i++) {
									Integer numberx = reyex.get(i);
									totalx = totalx + numberx;
										if(xmin > numberx) {
											xmin = numberx;
										}
										
										if (xmax < numberx) {
											xmax = numberx;
										}
								}	
								
								for (Integer i = 0; i < reyey.size(); i++) {
									Integer numbery = reyey.get(i);
									totaly = totaly + numbery;
										if(ymin > numbery) {
											ymin = numbery;
										}
										
										if (ymax < numbery)	{
											ymax = numbery;
										}
								}
						
								for (Integer i = 0; i < reyex.size(); i++) {
									totalx = totalx + reyex.get(i);
								}
						
								for (Integer i = 0; i < reyey.size(); i++) {
									totaly = totaly + reyey.get(i);			
								}
									
				// Calculate average
				averagex = totalx / reyex.size();
				averagey = totaly / reyey.size();
				
				// Print outcomes onto console		
				String c1 = ("Right Eye Coordinates: " + " x: " + Rx + ", y: " + Ry);
				System.out.println(String.format(c1));
				System.out.println("THis is the right eye x HIGHEST: " + xmax.toString());
				System.out.println("THis is the right eye y HIGHEST: " + ymax.toString());
				System.out.println("THis is the mouth X AVERAGE: " + averagex.toString());
				System.out.println("THis is the mouth Y AVERAGE: " + averagey.toString());
				System.out.println("THis is the right eye x LOWEST: " + xmin.toString());
				System.out.println("THis is the right eye y LOWEST: " + ymin.toString());
				
				// Formate outcomes
				rightEyeAverage = df.format(averagex) + "x" + ", " + df.format(averagey) + "y";
				rightEyeMin = df.format(xmin) + "x" + ", " + df.format(ymin) + "y";
				rightEyeMax = df.format(xmax) + "x" + ", " + df.format(ymax) + "y";	
				r = ("X: " + Rx + "   " + "Y: " + Ry);
				
				Point Reyepoint = new Point(Reye.x, Reye.y);
		
					//Draw a square
					if (face.contains(Reyepoint)){
						Imgproc.putText(frameCapture, r, new Point(Reye.x,Reye.y-5), 1, 1, new Scalar(0,0,0));
						Imgproc.rectangle(frameCapture, new Point(Reye.x, Reye.y), new Point(Reye.x + Reye.width, Reye.y + Reye.height),
						new Scalar(200, 200, 100),2);
					}
						}
					else {
						
					}
						
				limit++;
					
					}
				}
				Window.PushImage(ConvertMat2Image(frameCapture));
				}
				} 
				else {
					System.out.println("Error");;
				}	
	}
	
	// Turning the mat object into an image type
	public static BufferedImage ConvertMat2Image(Mat camera) {
		byteMatData = new MatOfByte();
		Imgcodecs.imencode(".jpg", camera, byteMatData);
		
		// The toArray () method of the mat object converts the elements to byte arrays
		byte[] byteArray = byteMatData.toArray();
		BufferedImage image = null;
			try {
				InputStream in = new ByteArrayInputStream(byteArray);
				image = ImageIO.read(in);
			} 
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		return image;
	}
	
	public static ArrayList<Integer> getLxList() {
	       return leyex;
	   }
	
	public static ArrayList<Integer> getLyList() {
	       return leyey;
	   }
	
	public static ArrayList<Integer> getRxList() {
	       return reyex;
	   }
	
	public static ArrayList<Integer> getRyList() {
	       return reyey;
	   }
	
	public static ArrayList<Integer> getMxList() {
	       return mouthx;
	   }
	
	public static ArrayList<Integer> getMyList() {
	       return mouthy;
	   }
	
	public static String []  allCoordinates(){
		String allvalues [] = {leftEyeAverage, leftEyeMin, leftEyeMax, mouthAverage, mouthMin, mouthMax, rightEyeAverage, rightEyeMin, rightEyeMax};
		return allvalues;

	}
}
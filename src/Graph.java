import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Graph extends ApplicationFrame {
	private static final long serialVersionUID = 1L;
	
	// Implement xyseriescollection date for plot graph
	static XYSeriesCollection dataset = new XYSeriesCollection(); 
	
	// Preate panel for plot graph
	ChartPanel chartPanel = new ChartPanel(chart); 

	// Create plot graph
	static JFreeChart chart = ChartFactory.createScatterPlot ( 
			"Scatter Plot", // title 
			"X", "Y", // axis labels 
			dataset, // dataset 
			PlotOrientation.VERTICAL, 
			true, // legend? yes 
			true, // tooltips? yes 
			false // URLs? no 
	); 
	
	// Add title to graph
	public Graph(String title) {
		super(title); 
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel); 
	} 

	public static void main(String[] args) { 
		Graph demo = new Graph("Scatter Plot Demo"); 
		demo.pack(); 
		RefineryUtilities.centerFrameOnScreen(demo); 
		demo.setVisible(true); 

	} 
}
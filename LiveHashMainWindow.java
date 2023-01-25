import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class LiveHashMainWindow extends JFrame {

	private LiveHashCoord coord;
	
	private Container contentPane;
	private JComboBox<String> hashAlgorithms;
	private JTextArea textIn;
	private JTextArea textOut;
	private Timer timer;

	public LiveHashMainWindow() throws Exception {
		this.setTitle("LiveHash");
		this.setSize(720,600);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.coord = new LiveHashCoord();

		this.contentPane = this.getContentPane();
		this.hashAlgorithms = new JComboBox<String>(this.coord.getAvailableAlgorithms());
		this.textIn = new JTextArea();
		this.textIn.setCaretPosition(0);
			
		this.textOut = new JTextArea();
		this.contentPane.setLayout(new BorderLayout());
		this.contentPane.add(this.hashAlgorithms, BorderLayout.NORTH);
		this.contentPane.add(this.textIn, BorderLayout.CENTER);
		this.contentPane.add(this.textOut, BorderLayout.SOUTH);

		this.coord.setHashAlgorithm("MD5");

		this.timer = new Timer(true);
		this.timer.schedule(new TimerTask(){
			public void run(){
				try {
					coord.setHashAlgorithm(hashAlgorithms.getSelectedItem().toString());
					String currentPlainText = textIn.getText();
					String currentHashText = coord.getHashFromText(currentPlainText);
					textOut.setText(currentHashText);
				}catch(Exception e){
					textOut.setText("Whoops! An error on my behalf.  The error was " + e.getMessage());
			}
		}}, 100, 100);	
							
	}


	public static void main(String[] args) throws Exception {
		new LiveHashMainWindow();
	}
}

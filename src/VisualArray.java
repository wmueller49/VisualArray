import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 * 
 */

/**
 * @author Owner
 *
 */
public class VisualArray{

	private ArraySquare[] visArray;
	
	private JFrame window;
	private JPanel panel;
	private final static int FONTSIZE = 40;
	private Color backgroundColor = Color.white;
	
	private int rows;
	private int cols;
	private int height;
	private int width;
	
	
	/**
	 * 
	 */
	public VisualArray() {
		visArray = new ArraySquare[10];
		fill(10);
		rows = 10;
		cols = 10;
		height = rows;
		width = cols;
		generateBoard();
	}
	
	public VisualArray(int n) {
		visArray = new ArraySquare[n];
		fill(100);
		rows = n;
		cols = 100;
		height = rows;
		width = cols;
		generateBoard();
	}
	
	public VisualArray(int n, int s) {
		visArray = new ArraySquare[n];
		fill(s);
		rows = n;
		cols = s;
		height = rows;
		width = cols;
		generateBoard();
	}
	
	private void fill(int s) {
		int max = length();
		for(int i = 0; i < max; i++) {
			visArray[i] = new ArraySquare((int) Math.floor(Math.random() * s) + 1);
		}
	}
	
	public int length() {
		return visArray.length;
	}
	
	public void generateBoard() {
		window = new JFrame("VisualArray");
		window.setSize(new Dimension(width, height));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		//panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//panel.setLayout(new GridLayout(rows, cols));
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));		
		
		for(ArraySquare as : visArray) {
			panel.add(as);
		}
		
		panel.setPreferredSize(new Dimension(10 * width, 1080));
		window.add(panel);
		window.pack();
		window.setResizable(true);
		window.setVisible(true);
	}
	
	public void merge(ArraySquare arr[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		
		ArraySquare left[] = new ArraySquare[] {new ArraySquare(n1)};
		ArraySquare right[] = new ArraySquare[] {new ArraySquare(n2)};
		
		for(int i = 0; i < n1; i++) {
			System.out.println(left[i]);
			left[i] = arr[l+i];
		}
		
		for(int j = 0; j < n2; j++) {
			right[j] = arr[m + 1 + j];
		}
	
		int i = 0;
		int j = 0;
		
		int k = l;
		while(i < n1 && j < n2) {
			if(left[i].getHeight() <= right[j].getHeight()) {
				arr[k] = left[i];
				i++;
			}
			else {
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		
		while(i < n1) {
			arr[k] = left[i];
			i++;
			k++;
		}
		
		while(j < n2) {
			arr[k] = right[j];
			j++;
			k++;
		}	
	}
	
	public void sort(ArraySquare arr[], int l, int r) {
		if(l < r) {
			int m = (l+r)/2;
			sort(arr, l, m);
			sort(arr, m+1, r);
			merge(arr, l, m, r);
		}
	}
	
	public void insertionSort(ArraySquare arr[]) {
		int n = arr.length;
		
		for(int i = 1; i < n; i++) {
			int key = arr[i].getValue();
			int j = i-1;
			
			while(j >= 0 && arr[j].getValue() > key) {
				arr[j+1].setValue(arr[j].getValue());
				arr[j+1].repaint();
				j = j-1;
				
				try {
					Thread.sleep(250);
				}
				catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			arr[j+1].setValue(key);
			arr[j+1].repaint();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VisualArray a = new VisualArray(20);
		
		for(ArraySquare x : a.visArray) {
			System.out.println(x.getValue());
		}
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
			public Void doInBackground() {
				a.insertionSort(a.visArray);
				return null;
			}
			protected void done() {
				System.out.println("Done");
				for(ArraySquare x : a.visArray) {
					System.out.println(x.getValue());
				}
			}
		};
		
		System.out.println("Solving...");
		worker.execute();
		
		
	}

}

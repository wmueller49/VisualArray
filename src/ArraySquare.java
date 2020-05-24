import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 */

/**
 * @author Owner
 *
 */
public class ArraySquare extends JPanel{

	private int height;
	private final static int FONTSIZE = 40;
	private Color backgroundColor = Color.white;
	
	public ArraySquare(int h) {
		height = h;
	}
	
	public int getValue() {
		return height;
	}
	
	public void setValue(int h) {
		height = h;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(new Dimension(100, 10*height));
		this.setBackground(backgroundColor);
		
		g.setFont(new Font("Helvetica", Font.BOLD, FONTSIZE));
		int x = (this.getWidth() / 2) - FONTSIZE/4;
        int y = (this.getHeight() / 2) + FONTSIZE/4;
        String value = height + "";
        g.drawString(value, x, y);
        
        Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

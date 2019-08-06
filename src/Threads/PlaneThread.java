package Threads;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.mainFrame;

public class PlaneThread extends Thread {
	private JPanel panel;
	private JFrame frame;

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void run() {

		JLabel label = new JLabel();

		label.setBounds(10, 31, 128, 56);

		// label.setForeground(Color.BLACK);
		// label.setBackground(Color.BLACK);
		// label.setText("Danas");
		label.setIcon(new ImageIcon(mainFrame.class.getResource("/img/airplane_4.png")));
		// label.setIcon(new ImageIcon("../img/airplane_4.jpg"));
		// label.setOpaque(true);
		// label.setVisible(true);
		int i = 5;
		int speed = 50;
		// panel.add(label);
		JLabel label1 = new JLabel();
		JLabel angry = new JLabel();
		
		angry.setBounds(846, 57, 23, 27);
		angry.setIcon(new ImageIcon(mainFrame.class.getResource("/img/angryBirds_1.png")));
		label1.setBounds(0, 0, 879, 139);
		label1.setIcon(new ImageIcon(mainFrame.class.getResource("/img/background.jpg")));
		panel.add(label);
		//panel.add(angry);
		panel.add(label1);

		int y = 846;
		
		while (true) {

//			angry.setBounds(y, 57, 23, 27);
//			int xOffset = 10 + (i * 10);
			label.setBounds(i, 40, 128, 56);
			
			// System.out.println(x);
			i += 5;
			y -= 5;
			try {
				sleep(speed);
			} catch (InterruptedException e) {
				System.out.println("Greska Tread");
			}

			if (i == 860) {
				// System.out.println(i);
				i = -150;
			}
			//System.out.println(i);
			
			
			
			if (y==-144) {
				
				y =846;
			}

			panel.repaint();
		}

	}

}

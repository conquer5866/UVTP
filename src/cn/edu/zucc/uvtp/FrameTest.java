package cn.edu.zucc.uvtp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameTest implements ActionListener {
	private JPanel northPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JFrame frame = new JFrame("Test");

	public FrameTest() {
		init();
	}

	private void init() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		JTextField tf = new JTextField();

		JButton btn = new JButton("Detail...");
		btn.addActionListener(this);

		northPanel.add(new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		centerPanel.add(new JLabel("bbbbbbbbbbbbbbbbbbbbbbbbbbbb"));
		centerPanel.setVisible(false);
		frame.add(BorderLayout.NORTH, northPanel);
		frame.add(BorderLayout.CENTER, centerPanel);
		frame.add(BorderLayout.SOUTH, btn);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		centerPanel.setVisible(!centerPanel.isVisible());
		//frame.pack();
	}

	public static void main(String[] args) {
		new FrameTest();
	}
}
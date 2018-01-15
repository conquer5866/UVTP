package cn.edu.zucc.uvtp.ui.diyclass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrmText extends JFrame implements ActionListener{
	JPanel a = new JPanel();
	JPanel b = new JPanel(); 
	JPanel c = new JPanel(); 
	JPanel d = new JPanel(); 
	JButton b1 = new JButton("c");
	JButton b2 = new JButton("d");
	public FrmText(){
		this.setSize(1600,900);
		a.setBackground(Color.BLACK);
		FlowLayout f = new FlowLayout(FlowLayout.LEFT);
		f.setHgap(0);
		f.setVgap(0);
		a.setLayout(f);
		a.setPreferredSize(new Dimension(1600,900));
		b.setBackground(Color.YELLOW);
		b.setPreferredSize(new Dimension(200,900));
		b.add(b1);
		b.add(b2);
		c.setBackground(Color.BLUE);
		c.setPreferredSize(new Dimension(1380,900));
		c.add(b1);
		c.add(b2);
		d.setBackground(Color.GREEN);
		d.setPreferredSize(new Dimension(1380,900));
		d.setVisible(false);
		a.add(b);
		a.add(c);
		a.add(d);
		this.add(a,new BorderLayout().CENTER);
		b1.addActionListener(this);
		b2.addActionListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b1){
			this.c.setVisible(true);
			this.d.setVisible(false);
		}
		if(e.getSource() == this.b2){
			this.c.setVisible(false);
			this.d.setVisible(true);
		}
	}
	public static void main(String[] args) {
		new FrmText();
	}
}

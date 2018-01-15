package cn.edu.zucc.uvtp.ui.users;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import cn.edu.zucc.uvtp.control.MessageManager;
import cn.edu.zucc.uvtp.control.OrdersManager;
import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.model.Messages;
import cn.edu.zucc.uvtp.ui.diyclass.ImageButton;
import cn.edu.zucc.uvtp.util.BaseException;

public class FrmMessageB extends JDialog implements ActionListener {
	Messages m =new Messages();
	private JPanel pd_main = new JPanel();
	private JPanel pd_w = new JPanel();
	private JPanel pd_t = new JPanel();
	
	private JTextArea t_n = new JTextArea(2,15);
	
	private ImageButton b_OK = new ImageButton(new ImageIcon("Image/O1.png"));
	//private ImageButton b_Cancel = new ImageButton(new ImageIcon("Image/X1.png"));
	
	public FrmMessageB(JFrame x, String s, boolean b,Messages m) {
		super(x,s,b);
		this.m=m;
		this.setResizable(false);																	//禁止修改窗口大小
		this.setUndecorated(true);																	//取消顶部栏
		this.setSize(400, 200);
		//------------------------屏幕居中显示-------------------------------------------------
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
	  	//------------------------主界面------------------------------------------------------
	  	FlowLayout f = new FlowLayout(FlowLayout.CENTER);
	  	f.setHgap(0);
	  	f.setVgap(20);
	  	pd_main.setLayout(f);
	  	pd_main.setBackground(new Color(54,54,54));
	  	//------------------------输入栏------------------------------------------------------

	  	JPanel t_1 = new JPanel();
	  	Font font = new Font("微软雅黑",Font.BOLD,48);
	  	
	  	f = new FlowLayout(FlowLayout.CENTER);
	  	f.setHgap(0);
	  	f.setVgap(20);
	  	pd_w.setLayout(f);
	  	pd_w.setOpaque(false);
	  	pd_w.setPreferredSize(new Dimension(this.getWidth(),90));
	  	
	  	f = new FlowLayout(FlowLayout.CENTER);
	  	f.setHgap(0);
	  	f.setVgap(20);
	  	t_1.setLayout(f);
	  	t_1.setOpaque(false);
	  	t_1.setPreferredSize(new Dimension(400,90));
	  	//t_1.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	
	  	font = new Font("等线",Font.PLAIN,24);
	  	t_n.setPreferredSize(new Dimension(300,40));
	  	t_n.setOpaque(false);
	  	t_n.setForeground(new Color(105,105,105));
	  	t_n.setFont(font);
	  	t_n.setEditable(false);
	  	t_n.setLineWrap(true);
	  	t_n.setWrapStyleWord(true);
	  	
	  	String x1 = "";
	  	String x3 = null;
		try {
			x3 = (new UsersManager()).getUser(m.getMSender()).getUName();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	if(m.getMessage() == 10){
			x1 = x3+"拒绝您对编号为"+m.getMONum()+"的订单的预约请求";	
	  	}
	  	else if(m.getMessage() == 11){
	  		x1 = x3+"接受您对编号为"+m.getMONum()+"的订单的预约请求";
	  	}
	  	else if(m.getMessage() == 20){
	  		x1 = x3+"取消了编号为"+m.getMONum()+"的订单的交易";
	  	}
	  	else if(m.getMessage() == 21){
	  		x1 = x3+"接受了编号为"+m.getMONum()+"的订单的交易，交易成功并已过户";
	  	}
	  	else if(m.getMessage() == 30){
	  		x1 = x3+"拒绝了您对编号为"+m.getMONum()+"的订单的退车请求";
	  	}
	  	else if(m.getMessage() == 31){
	  		x1 = x3+"接受了您对编号为"+m.getMONum()+"的订单的退车请求，已重新过户";
	  	}
	  	t_n.setText(x1);
	  	t_1.add(t_n);
	  	pd_w.add(t_1);
	  	//-------------------------------------------------------------------------------------
	  	f = new FlowLayout();
	  	f.setHgap(80);
	  	f.setVgap(0);
	  	pd_t.setLayout(f);
	  	pd_t.setOpaque(false);
	  	pd_t.setPreferredSize(new Dimension(400,40));
	  	pd_t.add(b_OK);
	  	//pd_t.add(b_Cancel);
	  	b_OK.setRolloverIcon(new ImageIcon("Image/O2.png"));
	  	b_OK.setPressedIcon(new ImageIcon("Image/O2.png"));
	  	//b_Cancel.setRolloverIcon(new ImageIcon("Image/X2.png"));
	  	//b_Cancel.setPressedIcon(new ImageIcon("Image/X2.png"));
	  	b_OK.addActionListener(this);
	  	//b_Cancel.addActionListener(this);
	  	//-------------------------------------------------------------------------------------
	  	pd_main.add(pd_w);
	  	pd_main.add(pd_t);
	  	this.getContentPane().add(pd_main,new BorderLayout().CENTER);
	  	this.validate();
	}
	public void actionPerformed(ActionEvent e) {
		/*if(e.getSource() == this.b_Cancel){
			this.setVisible(false);
			return;
		}*/
		if(e.getSource() == this.b_OK){
			try {
				(new MessageManager()).changeState(m.getMNum(), 1);
				this.setVisible(false);
				(new MessageManager()).del();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}

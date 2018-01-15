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
import cn.edu.zucc.uvtp.control.TransferManager;
import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.model.Messages;
import cn.edu.zucc.uvtp.model.Orders;
import cn.edu.zucc.uvtp.model.Transfer;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.ui.diyclass.ImageButton;
import cn.edu.zucc.uvtp.util.BaseException;

public class FrmMessageA extends JDialog implements ActionListener {
	private Messages m = new Messages();
	private JPanel pd_main = new JPanel();
	private JPanel pd_w = new JPanel();
	private JPanel pd_t = new JPanel();
	
	private JTextArea t_n = new JTextArea(2,15);
	private JLabel t_n2 = new JLabel();
	
	private ImageButton b_OK = new ImageButton(new ImageIcon("Image/O1.png"));
	private ImageButton b_Cancel = new ImageButton(new ImageIcon("Image/X1.png"));
	
	public FrmMessageA(JFrame x, String s, boolean b,Messages m) {
		super(x,s,b);
		this.m = m;
		this.setResizable(false);																	//禁止修改窗口大小
		this.setUndecorated(true);																	//取消顶部栏
		this.setSize(400, 260);
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
	  	pd_w.setPreferredSize(new Dimension(this.getWidth(),150));
	  	
	  	f = new FlowLayout(FlowLayout.CENTER);
	  	f.setHgap(0);
	  	f.setVgap(20);
	  	t_1.setLayout(f);
	  	t_1.setOpaque(false);
	  	t_1.setPreferredSize(new Dimension(400,150));
	  	//t_1.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	
	  	font = new Font("等线",Font.PLAIN,24);
	  	t_n.setPreferredSize(new Dimension(300,40));
	  	t_n.setOpaque(false);
	  	t_n.setForeground(new Color(105,105,105));
	  	t_n.setFont(font);
	  	t_n.setEditable(false);
	  	t_n.setLineWrap(true);
	  	t_n.setWrapStyleWord(true);
	  	
	  	t_n2.setPreferredSize(new Dimension(300,40));
	  	t_n2.setOpaque(false);
	  	t_n2.setForeground(new Color(105,105,105));
	  	t_n2.setFont(font);
	  	String x1 = "";
	  	String x2 = "";
	  	String x3 = null;
		try {
			x3 = (new UsersManager()).getUser(m.getMSender()).getUName();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	if(m.getMessage() == 1){
			x1 = x3+"想预约您编号为"+m.getMONum()+"的订单";
	  		x2 = "是否接受？";
	  		
	  	}
	  	else if(m.getMessage() == 2){
	  		x1 = "查看过车辆后";
	  		x2 = "您是否想要买下这辆车？";
	  	}
	  	else if(m.getMessage() == 3){
	  		x1 = x3+"想要退掉编号为"+m.getMONum()+"的订单";
	  		x2 = "您是否接受?";
	  	}
	  	t_n.setText(x1);
  		t_n2.setText(x2);
	  	t_1.add(t_n);
	  	t_1.add(t_n2);
	  	pd_w.add(t_1);
	  	//-------------------------------------------------------------------------------------
	  	f = new FlowLayout();
	  	f.setHgap(80);
	  	f.setVgap(0);
	  	pd_t.setLayout(f);
	  	pd_t.setOpaque(false);
	  	pd_t.setPreferredSize(new Dimension(400,40));
	  	pd_t.add(b_OK);
	  	pd_t.add(b_Cancel);
	  	b_OK.setRolloverIcon(new ImageIcon("Image/O2.png"));
	  	b_OK.setPressedIcon(new ImageIcon("Image/O2.png"));
	  	b_Cancel.setRolloverIcon(new ImageIcon("Image/X2.png"));
	  	b_Cancel.setPressedIcon(new ImageIcon("Image/X2.png"));
	  	b_OK.addActionListener(this);
	  	b_Cancel.addActionListener(this);
	  	//-------------------------------------------------------------------------------------
	  	pd_main.add(pd_w);
	  	pd_main.add(pd_t);
	  	this.getContentPane().add(pd_main,new BorderLayout().CENTER);
	  	this.validate();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b_Cancel){
			if(this.m.getMessage() == 1){
				try {
					(new MessageManager()).changeState(m.getMNum(),2);
					m.setMReceiver(m.getMSender());
					m.setMSender(Users.currentu.getUNum());
					m.setMessage(10);
					m.setMState(0);
					(new MessageManager()).add(m);
					this.setVisible(false);
					(new MessageManager()).del();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else if(this.m.getMessage() == 2){
				try {
					(new MessageManager()).changeState(m.getMNum(),2);
					m.setMReceiver(m.getMSender());
					m.setMSender(Users.currentu.getUNum());
					m.setMessage(20);
					m.setMState(0);
					(new MessageManager()).add(m);
					this.setVisible(false);
					(new MessageManager()).del();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else if(this.m.getMessage() == 3){
				try {
					(new MessageManager()).changeState(m.getMNum(),2);
					m.setMReceiver(m.getMSender());
					m.setMSender(Users.currentu.getUNum());
					m.setMessage(30);
					m.setMState(0);
					(new MessageManager()).add(m);
					this.setVisible(false);
					(new MessageManager()).del();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		else if(e.getSource() == this.b_OK){
			if(this.m.getMessage() == 1){
				try {
					(new MessageManager()).changeState(m.getMNum(),1);
					m.setMReceiver(m.getMSender());
					m.setMSender(Users.currentu.getUNum());
					m.setMessage(11);
					m.setMState(0);
					(new MessageManager()).add(m);
					m.setMessage(2);
					(new MessageManager()).add(m);
					this.setVisible(false);
					(new MessageManager()).del();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else if(this.m.getMessage() == 2){
				try {
					(new MessageManager()).changeState(m.getMNum(),1);
					m.setMReceiver(m.getMSender());
					m.setMSender(Users.currentu.getUNum());
					m.setMessage(21);
					m.setMState(0);
					(new MessageManager()).add(m);
					Transfer t = new Transfer();
					t.setTBuNum(Users.currentu.getUNum());
					t.setTSuNum(m.getMReceiver());
					t.setTPrice((new OrdersManager()).getOrders(m.getMONum()).getOPrice());
					t.setTType(0);
					t.setTVNum((new OrdersManager()).getOrders(m.getMONum()).getOVNum());
					(new TransferManager()).add(t);
					this.setVisible(false);
					(new MessageManager()).del();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else if(this.m.getMessage() == 3){
				try {
					(new MessageManager()).changeState(m.getMNum(),1);
					m.setMReceiver(this.m.getMSender());
					m.setMSender(Users.currentu.getUNum());
					m.setMONum(this.m.getMONum());
					m.setMessage(31);
					m.setMState(0);
					(new MessageManager()).add(m);
					Transfer t = new Transfer();
					t.setTBuNum(this.m.getMSender());
					t.setTSuNum(this.m.getMReceiver());
					t.setTPrice((new OrdersManager()).getOrders(this.m.getMONum()).getOPrice());
					t.setTType(1);
					t.setTVNum((new OrdersManager()).getOrders(this.m.getMONum()).getOVNum());
					(new TransferManager()).add(t);
					this.setVisible(false);
					(new MessageManager()).del();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
	}
}

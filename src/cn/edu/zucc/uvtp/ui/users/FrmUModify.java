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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import cn.edu.zucc.uvtp.control.SysUsersManager;
import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.control.VehicleTypeManager;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.model.VehicleType;
import cn.edu.zucc.uvtp.ui.diyclass.ImageButton;
import cn.edu.zucc.uvtp.ui.users.FrmLogin;
import cn.edu.zucc.uvtp.util.BaseException;


public class FrmUModify  extends JDialog implements ActionListener {
	private JPanel pd_main = new JPanel();
  	private JPanel pd_T = new JPanel();
	private JPanel pd_w = new JPanel();
	private JPanel pd_t = new JPanel();
	
	private JTextField t_n1 = new JTextField(11);
	private JTextField t_n2 = new JTextField(11);	private JLabel t_n2x = new JLabel();
	private JTextArea t_a2 = new JTextArea(2,11);
	private JTextField t_n3 = new JTextField(11);
	private JTextField t_n4 = new JTextField(11);


	private ImageButton b_OK = new ImageButton(new ImageIcon("Image/O1.png"));
	private ImageButton b_Cancel = new ImageButton(new ImageIcon("Image/X1.png"));
	
	public FrmUModify(JFrame x, String s, boolean b) {
		super(x,s,b);
		this.setResizable(false);																	//禁止修改窗口大小
		this.setUndecorated(true);																	//取消顶部栏
		this.setSize(400, 540);
		//------------------------屏幕居中显示-------------------------------------------------
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
		//------------------------鼠标事件:拖拽标题栏------------------------------------------
		/*MouseEventListener mouseListener = new MouseEventListener(this);
	    this.pd_main.addMouseListener(mouseListener);
	    this.pd_main.addMouseMotionListener(mouseListener);*/
	    //------------------------标题栏-------------------------------------------------------	
	  	/*pu_main.setLayout(new FlowLayout(FlowLayout.RIGHT));										//伪标题栏
	  	pu_main.setBackground(new Color(0 ,191 ,255));												//DeepSkyBlue
	  	pu_main.setPreferredSize(new Dimension(this.getWidth(),40));
	  	pu_main.add(b_close);
	  	
	  	b_close.addActionListener(this);
	  	b_close.setRolloverIcon(new  ImageIcon("Image/Button_Close_2.png"));
	  	b_close.setPressedIcon(new  ImageIcon("Image/Button_Close_2.png"));*/
	  	//------------------------主界面------------------------------------------------------
	  	FlowLayout f = new FlowLayout(FlowLayout.CENTER);
	  	f.setHgap(0);
	  	f.setVgap(20);
	  	pd_main.setLayout(f);
	  	pd_main.setBackground(new Color(54,54,54));
	  	//------------------------输入栏------------------------------------------------------
	  	JLabel l_t = new JLabel ("用户信息修改");
	  	JLabel l_n1 = new JLabel ("用户名");
	  	JLabel l_n2 = new JLabel ("真实姓名");
	  	JLabel l_a = new JLabel("地址");
	  	JLabel l_n3 = new JLabel("邮箱");
	  	JLabel l_n4 = new JLabel("QQ");

	  	JPanel t_1 = new JPanel();
	  	JPanel t_2 = new JPanel();
	  	JPanel t_a = new JPanel();
	  	JPanel t_3 = new JPanel();
	  	JPanel t_4 = new JPanel();
	  	
	  	Font font = new Font("微软雅黑",Font.BOLD,32);
	  	l_t.setForeground(new Color(105,105,105));
	  	l_t.setFont(font);
	  	pd_T.setOpaque(false);
	  	pd_T.setPreferredSize(new Dimension(400,60));
	  	pd_T.add(l_t,new BorderLayout().CENTER);
	  	
	  	f = new FlowLayout(FlowLayout.CENTER);
	  	f.setHgap(0);
	  	f.setVgap(20);
	  	pd_w.setLayout(f);
	  	pd_w.setOpaque(false);
	  	pd_w.setPreferredSize(new Dimension(this.getWidth(),340));
	  	
	  	f = new FlowLayout(FlowLayout.CENTER);
	  	f.setHgap(0);
	  	f.setVgap(0);
	  	t_1.setLayout(f);
	  	t_1.setOpaque(false);
	  	t_1.setPreferredSize(new Dimension(110,40));
	  	t_1.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));

	  	t_2.setLayout(f);
	  	t_2.setOpaque(false);
	  	t_2.setPreferredSize(new Dimension(110,40));
	  	t_2.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));

	  	t_a.setLayout(f);
	  	t_a.setOpaque(false);
	  	t_a.setPreferredSize(new Dimension(110,80));
	  	t_a.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));

	  	t_3.setLayout(f);
	  	t_3.setOpaque(false);
	  	t_3.setPreferredSize(new Dimension(110,40));
	  	t_3.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));

	  	t_4.setLayout(f);
	  	t_4.setOpaque(false);
	  	t_4.setPreferredSize(new Dimension(110,40));
	  	t_4.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	
	  	font = new Font("微软雅黑",Font.PLAIN,20);
	  	l_n1.setForeground(new Color(105,105,105));
	  	l_n1.setFont(font);
	  	l_n2.setForeground(new Color(105,105,105));
	  	l_n2.setFont(font);
	  	l_a.setForeground(new Color(105,105,105));
	  	l_a.setFont(font);
	  	l_n3.setForeground(new Color(105,105,105));
	  	l_n3.setFont(font);
	  	l_n4.setForeground(new Color(105,105,105));
	  	l_n4.setFont(font);
	  	
	  	t_1.add(l_n1);
	  	t_2.add(l_n2);
	  	t_a.add(l_a);
	  	t_3.add(l_n3);
	  	t_4.add(l_n4);
	  	
	  	font = new Font("等线",Font.PLAIN,24);
	  	t_n1.setPreferredSize(new Dimension(300,40));
	  	t_n1.setOpaque(false);
	  	t_n1.setForeground(new Color(105,105,105));
	  	t_n1.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_n1.setCaretColor(new Color(105,105,105));
	  	t_n1.setFont(font);
	  	t_n1.setText(Users.currentu.getUName());
	  	
	  	t_n2.setPreferredSize(new Dimension(300,40));
	  	t_n2.setOpaque(false);
	  	t_n2.setForeground(new Color(105,105,105));
	  	t_n2.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_n2.setCaretColor(new Color(105,105,105));
	  	t_n2.setFont(font);

	  	t_a2.setPreferredSize(new Dimension(224,80));
	  	t_a2.setOpaque(false);
	  	t_a2.setForeground(new Color(105,105,105));
	  	t_a2.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_a2.setCaretColor(new Color(105,105,105));
	  	t_a2.setFont(font);
	  	t_a2.setLineWrap(true);
	  	t_a2.setText(Users.currentu.getUAdress());

	  	t_n3.setPreferredSize(new Dimension(300,40));
	  	t_n3.setOpaque(false);
	  	t_n3.setForeground(new Color(105,105,105));
	  	t_n3.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_n3.setCaretColor(new Color(105,105,105));
	  	t_n3.setFont(font);
	  	t_n3.setText(Users.currentu.getUEmail());

	  	t_n4.setPreferredSize(new Dimension(300,40));
	  	t_n4.setOpaque(false);
	  	t_n4.setForeground(new Color(105,105,105));
	  	t_n4.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_n4.setCaretColor(new Color(105,105,105));
	  	t_n4.setFont(font);
	  	t_n4.setText(Users.currentu.getUQq());
	  	
	  	
	  	t_n2x.setPreferredSize(new Dimension(224,40));
	  	t_n2x.setOpaque(false);
	  	t_n2x.setForeground(new Color(105,105,105));
	  	//t_n2x.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	//t_n2x.setCaretColor(new Color(105,105,105));
	  	t_n2x.setFont(font);
	  	t_n2x.setText(Users.currentu.getURName());
	  	
	  	if(Users.currentu.getURName()!=null){
	  		t_n2x.setVisible(true);
	  		t_n2.setVisible(false);
	  	}
	  	else {
	  		t_n2x.setVisible(false);
	  		t_n2.setVisible(true);
	  	}
	  	
	  	pd_w.add(t_1);	pd_w.add(t_n1);
	  	pd_w.add(t_2);	pd_w.add(t_n2);	pd_w.add(t_n2x);
	  	pd_w.add(t_a);	pd_w.add(t_a2);
	  	pd_w.add(t_3);	pd_w.add(t_n3);
	  	pd_w.add(t_4);	pd_w.add(t_n4);
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
	  	pd_main.add(pd_T);
	  	pd_main.add(pd_w);
	  	pd_main.add(pd_t);
	  	this.getContentPane().add(pd_main,new BorderLayout().CENTER);
	  	//this.validate();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b_Cancel){
			this.setVisible(false);
		}
		else if(e.getSource() == this.b_OK){
			Users u = Users.currentu;
			String a = t_n1.getText();
			String b = t_n2x.getText();
			if(t_n2.isVisible()){
				b = t_n2.getText();
			}
			String c = t_a2.getText();
			String d = t_n3.getText();
			String f = t_n4.getText();
			
			u.setUName(a);
			u.setURName(b);
			u.setUAdress(c);
			u.setUEmail(d);
			u.setUQq(f);
			try {
				(new UsersManager()).modify(u);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}

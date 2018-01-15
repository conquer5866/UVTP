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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import cn.edu.zucc.uvtp.control.VehicleBrandManager;
import cn.edu.zucc.uvtp.control.VehicleLineManager;
import cn.edu.zucc.uvtp.control.VehicleTypeManager;
import cn.edu.zucc.uvtp.control.VehiclesManager;
import cn.edu.zucc.uvtp.model.VehicleBrand;
import cn.edu.zucc.uvtp.model.VehicleLine;
import cn.edu.zucc.uvtp.model.Vehicles;
import cn.edu.zucc.uvtp.ui.diyclass.ImageButton;
import cn.edu.zucc.uvtp.util.BaseException;

public class FrmVcModify extends JDialog implements ActionListener {
	Vehicles vc = new Vehicles();
	
	private JPanel pd_main = new JPanel();
  	private JPanel pd_T = new JPanel();
	private JPanel pd_w = new JPanel();
	private JPanel pd_t = new JPanel();

	private JComboBox c_type = new JComboBox();
	private JComboBox c_line = new JComboBox();
	private Object[] item_GBox = {"手动","自动"};	
	private JComboBox c_GBox = new JComboBox(item_GBox);
	private Object[] item_Displacement = {"1.0L及以下","1.1~1.6L","1.7~2.0L","2.1~2.5L","2.6~3.0L","3.1~4.0L","4.1L及以上"};	
	private JComboBox c_Displacement = new JComboBox(item_Displacement);
	private JTextField t_date = new JTextField(11);
	private JTextField t_Time = new JTextField(11);
	private JTextField t_Mileage = new JTextField(11);
	private JTextField t_Color = new JTextField(11);
	private JTextField t_price = new JTextField(11);
	private Object[] item_Drive = {"前驱","后驱","四驱"};
	private JComboBox c_Drive = new JComboBox(item_Drive);
	private JTextField t_SeatNum = new JTextField(11);
	private Object[] item_Origin = {"一手购买","二手购买"};
	private JComboBox c_Origin = new JComboBox(item_Origin);

	private ImageButton b_OK = new ImageButton(new ImageIcon("Image/O1.png"));
	private ImageButton b_Cancel = new ImageButton(new ImageIcon("Image/X1.png"));
	
	public FrmVcModify(JFrame x, String s, boolean b,Vehicles vc) {
		super(x,s,b);
		this.vc = vc;
		this.setResizable(false);																	//禁止修改窗口大小
		this.setUndecorated(true);																	//取消顶部栏
		this.setSize(400, 920);
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
	  	JLabel l_t = new JLabel ("添加车辆");
	  	JLabel l_n1 = new JLabel ("车型");
	  	JLabel l_n2 = new JLabel ("车系");
	  	JLabel l_n3 = new JLabel("变速箱");
	  	JLabel l_n4 = new JLabel("排量");
	  	JLabel l_n5 = new JLabel("生产年份");
	  	JLabel l_n6 = new JLabel("上牌时间");
	  	JLabel l_n7 = new JLabel("行驶里程");
	  	JLabel l_n8 = new JLabel("颜色");
	  	JLabel l_n12 = new JLabel("市场指导价");
	  	JLabel l_n9 = new JLabel("驱动");
	  	JLabel l_n10 = new JLabel("座位数");
	  	JLabel l_n11 = new JLabel("来源");


	  	JPanel t_1 = new JPanel();
	  	JPanel t_2 = new JPanel();
	  	JPanel t_3 = new JPanel();
	  	JPanel t_4 = new JPanel();
	  	JPanel t_5 = new JPanel();
	  	JPanel t_6 = new JPanel();
	  	JPanel t_7 = new JPanel();
	  	JPanel t_8 = new JPanel();
	  	JPanel t_9 = new JPanel();
	  	JPanel t_10 = new JPanel();
	  	JPanel t_11 = new JPanel();
	  	JPanel t_12 = new JPanel();
	  	
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
	  	pd_w.setPreferredSize(new Dimension(this.getWidth(),720));
	  	
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
	  	t_3.setLayout(f);
	  	t_3.setOpaque(false);
	  	t_3.setPreferredSize(new Dimension(110,40));
	  	t_3.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_4.setLayout(f);
	  	t_4.setOpaque(false);
	  	t_4.setPreferredSize(new Dimension(110,40));
	  	t_4.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_5.setLayout(f);
	  	t_5.setOpaque(false);
	  	t_5.setPreferredSize(new Dimension(110,40));
	  	t_5.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_6.setLayout(f);
	  	t_6.setOpaque(false);
	  	t_6.setPreferredSize(new Dimension(110,40));
	  	t_6.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_7.setLayout(f);
	  	t_7.setOpaque(false);
	  	t_7.setPreferredSize(new Dimension(110,40));
	  	t_7.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_8.setLayout(f);
	  	t_8.setOpaque(false);
	  	t_8.setPreferredSize(new Dimension(110,40));
	  	t_8.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_9.setLayout(f);
	  	t_9.setOpaque(false);
	  	t_9.setPreferredSize(new Dimension(110,40));
	  	t_9.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_10.setLayout(f);
	  	t_10.setOpaque(false);
	  	t_10.setPreferredSize(new Dimension(110,40));
	  	t_10.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_11.setLayout(f);
	  	t_11.setOpaque(false);
	  	t_11.setPreferredSize(new Dimension(110,40));
	  	t_11.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_12.setLayout(f);
	  	t_12.setOpaque(false);
	  	t_12.setPreferredSize(new Dimension(110,40));
	  	t_12.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	
	  	font = new Font("微软雅黑",Font.PLAIN,20);
	  	l_n1.setForeground(new Color(105,105,105));
	  	l_n1.setFont(font);
	  	l_n2.setForeground(new Color(105,105,105));
	  	l_n2.setFont(font);
	  	l_n3.setForeground(new Color(105,105,105));
	  	l_n3.setFont(font);
	  	l_n4.setForeground(new Color(105,105,105));
	  	l_n4.setFont(font);
	  	l_n5.setForeground(new Color(105,105,105));
	  	l_n5.setFont(font);
	  	l_n6.setForeground(new Color(105,105,105));
	  	l_n6.setFont(font);
	  	l_n7.setForeground(new Color(105,105,105));
	  	l_n7.setFont(font);
	  	l_n8.setForeground(new Color(105,105,105));
	  	l_n8.setFont(font);
	  	l_n9.setForeground(new Color(105,105,105));
	  	l_n9.setFont(font);
	  	l_n10.setForeground(new Color(105,105,105));
	  	l_n10.setFont(font);
	  	l_n11.setForeground(new Color(105,105,105));
	  	l_n11.setFont(font);
	  	l_n12.setForeground(new Color(105,105,105));
	  	l_n12.setFont(font);

	  	t_1.add(l_n1);
		t_2.add(l_n2);
		t_3.add(l_n3);
		t_4.add(l_n4);
		t_5.add(l_n5);
		t_6.add(l_n6);
		t_7.add(l_n7);
		t_8.add(l_n8);
		t_9.add(l_n9);
		t_10.add(l_n10);
		t_11.add(l_n11);
		t_12.add(l_n12);

	  	
	  	font = new Font("等线",Font.PLAIN,24);
	  	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	  	t_date.setPreferredSize(new Dimension(224,40));
	  	t_date.setOpaque(false);
	  	t_date.setForeground(new Color(105,105,105));
	  	t_date.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_date.setCaretColor(new Color(105,105,105));
	  	t_date.setFont(font);
	  	t_date.setText(format1.format(vc.getVcDate()));

	  	t_Time.setPreferredSize(new Dimension(224,40));
	  	t_Time.setOpaque(false);
	  	t_Time.setForeground(new Color(105,105,105));
	  	t_Time.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_Time.setCaretColor(new Color(105,105,105));
	  	t_Time.setFont(font);
	  	t_Time.setText(format1.format(vc.getVcTime()));

	  	t_Mileage.setPreferredSize(new Dimension(224,40));
	  	t_Mileage.setOpaque(false);
	  	t_Mileage.setForeground(new Color(105,105,105));
	  	t_Mileage.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_Mileage.setCaretColor(new Color(105,105,105));
	  	t_Mileage.setFont(font);
	  	t_Mileage.setText(vc.getVcMileage()+"");

	  	t_Color.setPreferredSize(new Dimension(224,40));
	  	t_Color.setOpaque(false);
	  	t_Color.setForeground(new Color(105,105,105));
	  	t_Color.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_Color.setCaretColor(new Color(105,105,105));
	  	t_Color.setFont(font);
	  	t_Color.setText(vc.getVcColor());

	  	t_SeatNum.setPreferredSize(new Dimension(224,40));
	  	t_SeatNum.setOpaque(false);
	  	t_SeatNum.setForeground(new Color(105,105,105));
	  	t_SeatNum.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_SeatNum.setCaretColor(new Color(105,105,105));
	  	t_SeatNum.setFont(font);
	  	t_SeatNum.setText(vc.getVcSeatNum());
	  	
	  	t_price.setPreferredSize(new Dimension(224,40));
	  	t_price.setOpaque(false);
	  	t_price.setForeground(new Color(105,105,105));
	  	t_price.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	t_price.setCaretColor(new Color(105,105,105));
	  	t_price.setFont(font);
	  	t_price.setText(vc.getVcPrice()+"");

	  	/*t_p.setPreferredSize(new Dimension(224,40));
	  	t_p.setBackground(new Color(54,54,54));
	  	t_p.setForeground(new Color(105,105,105));
	  	t_p.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	//t_p.setCaretColor(new Color(105,105,105));
	  	t_p.setFont(font);
	  	//t_p.setLineWrap(true);
		try {
			this.loadVbname();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	  	c_type.setPreferredSize(new Dimension(224,40));
	  	c_type.setBackground(new Color(54,54,54));
	  	c_type.setForeground(new Color(105,105,105));
	  	c_type.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	c_type.setFont(font);
	  	try {
			this.loadVtname();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	c_type.setSelectedIndex((Integer.valueOf(this.vc.getVcTypeId()).intValue()-1));
	  	
	  	c_line.setPreferredSize(new Dimension(224,40));
	  	c_line.setBackground(new Color(54,54,54));
	  	c_line.setForeground(new Color(105,105,105));
	  	c_line.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	c_line.setFont(font);
	  	try {
			this.loadVlname();
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  	c_line.setSelectedIndex((Integer.valueOf(this.vc.getVcLNum()).intValue()-1));
	  	
	  	c_GBox.setPreferredSize(new Dimension(224,40));
	  	c_GBox.setBackground(new Color(54,54,54));
	  	c_GBox.setForeground(new Color(105,105,105));
	  	c_GBox.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	c_GBox.setFont(font);
	  	int i1 = 0;
	  	switch(vc.getVcGearBox()){
	  	case "手动": i1 = 0;break;
	  	case "自动": i1 = 1;break;
	  	}
	  	c_GBox.setSelectedIndex(i1);

	  	c_Displacement.setPreferredSize(new Dimension(224,40));
	  	c_Displacement.setBackground(new Color(54,54,54));
	  	c_Displacement.setForeground(new Color(105,105,105));
	  	c_Displacement.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	c_Displacement.setFont(font);
	  	int i2 = 0;
	  	switch(vc.getVcDisplacement()){
	  	case "1.0L及以下": i2 = 0; break;
	  	case "1.1~1.6L": i2 = 1;break;
	  	case "1.7~2.0L": i2 = 2;break;
	  	case "2.1~2.5L": i2 = 3;break;
	  	case "2.6~3.0L": i2 = 4;break;
	  	case "3.1~4.0L": i2 = 5;break;
	  	case "4.1L及以上": i2 = 6;break;
	  	}
	  	c_Displacement.setSelectedIndex(i2);

	  	c_Drive.setPreferredSize(new Dimension(224,40));
	  	c_Drive.setBackground(new Color(54,54,54));
	  	c_Drive.setForeground(new Color(105,105,105));
	  	c_Drive.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	c_Drive.setFont(font);
	  	int i3 = 0;
	  	switch(vc.getVcDrive()){
	  	case "前驱": i3 = 0; break;
	  	case "后驱": i3 = 1; break;
	  	case "四驱": i3 = 2; break;
	  	}
	  	c_Drive.setSelectedIndex(i3);

	  	c_Origin.setPreferredSize(new Dimension(224,40));
	  	c_Origin.setBackground(new Color(54,54,54));
	  	c_Origin.setForeground(new Color(105,105,105));
	  	c_Origin.setBorder(BorderFactory.createLineBorder(new Color(28,28,28),3));
	  	c_Origin.setFont(font);
	  	if(vc.getVcOrigin().equals("一手购买")){
	  		c_Origin.setSelectedIndex(0);
	  	}
	  	else if(vc.getVcOrigin().equals("二手购买")){
	  		c_Origin.setSelectedIndex(1);
	  	}
	  	
	  	
	  	pd_w.add(t_1);	pd_w.add(c_type);
	  	pd_w.add(t_2);	pd_w.add(c_line);
	  	pd_w.add(t_3);	pd_w.add(c_GBox);
	  	pd_w.add(t_4);	pd_w.add(c_Displacement);
	  	pd_w.add(t_5);	pd_w.add(t_date);
	  	pd_w.add(t_6);	pd_w.add(t_Time);
	  	pd_w.add(t_7);	pd_w.add(t_Mileage);
	  	pd_w.add(t_8);	pd_w.add(t_Color);
	  	pd_w.add(t_12); pd_w.add(t_price);
	  	pd_w.add(t_9);	pd_w.add(c_Drive);
	  	pd_w.add(t_10);	pd_w.add(t_SeatNum);
	  	pd_w.add(t_11);	pd_w.add(c_Origin);
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
	  	this.validate();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b_Cancel){
			this.setVisible(false);
			return;
		}
		else if(e.getSource() == this.b_OK){
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			try {
				vc.setVcTypeId((new VehicleTypeManager()).getvbnum((String)this.c_type.getSelectedItem()));
				vc.setVcLNum((new VehicleLineManager()).getvlnum((String)this.c_line.getSelectedItem()));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			vc.setVcGearBox((String)this.c_GBox.getSelectedItem());
			vc.setVcDisplacement((String)this.c_Displacement.getSelectedItem());
			try {
				vc.setVcDate(f.parse(this.t_date.getText()));
				vc.setVcTime(f.parse(this.t_Time.getText()));
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			vc.setVcMileage(Double.valueOf(this.t_Mileage.getText()));
			vc.setVcColor(this.t_Color.getText());
			vc.setVcPrice(Double.valueOf(this.t_price.getText()));
			vc.setVcDrive((String)this.c_Drive.getSelectedItem());
			vc.setVcSeatNum(this.t_SeatNum.getText());
			vc.setVcOrigin((String)this.c_Origin.getSelectedItem());
			
			try {
				(new VehiclesManager()).modify(vc);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	public void loadVtname() throws BaseException{
		String[] str = (new VehicleTypeManager()).loadVtname();
		for(int i = 0; i<str.length;i++){
			this.c_type.addItem(str[i]);
		}
	}
	
	public void loadVlname() throws BaseException{
		String[] str = (new VehicleLineManager()).loadVlname();
		for(int i = 0; i<str.length;i++){
			this.c_line.addItem(str[i]);
		}
	}
}

package cn.edu.zucc.uvtp.ui.users;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.Document;

import cn.edu.zucc.uvtp.control.MessageManager;
import cn.edu.zucc.uvtp.control.OrdersManager;
import cn.edu.zucc.uvtp.control.TransferManager;
import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.control.VehiclesManager;
import cn.edu.zucc.uvtp.control.vordersmanager;
import cn.edu.zucc.uvtp.model.Messages;
import cn.edu.zucc.uvtp.model.Orders;
import cn.edu.zucc.uvtp.model.Transfer;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.model.Vehicles;
import cn.edu.zucc.uvtp.model.vorders;
import cn.edu.zucc.uvtp.ui.diyclass.FileUseDemo;
import cn.edu.zucc.uvtp.ui.diyclass.ImageButton;
import cn.edu.zucc.uvtp.util.BaseException;

public class FrmUMain extends JFrame implements ActionListener {
	public int a;
	
	private ImageIcon Button_Close_1 = new ImageIcon("Image/Button_Close_1.png");
	private ImageIcon Button_Minimize_1 = new ImageIcon("Image/Button_Minimize_1.png");
	private ImageIcon Button_Search = new ImageIcon("Image/Button_search.png");

	private JPanel pu_main = new JPanel(); // 顶部容器：欢迎，搜索，缩小，关闭
	private JPanel pu_left = new JPanel();
	private JPanel pu_center = new JPanel();
	private JPanel pu_right = new JPanel();
	private JPanel pd_main = new JPanel(); // 底部容器：功能按钮1，显示部分，功能按钮2
	private JPanel pd_left = new JPanel();
	private JPanel pd_center = new JPanel();
	private JPanel pd_right = new JPanel();

	private JPanel pdc_home = new JPanel();
	private JPanel pdc_me = new JPanel(); // private JPanel pdc2_work =new
											// JPanel();
	private JPanel pdc_mycar = new JPanel();
	private JPanel pdc_myorders = new JPanel();
	private JPanel pdc_transfer = new JPanel();
	private JPanel pdc_message = new JPanel();

	private JLabel l_welcome = new JLabel("  欢迎登录，" + Users.currentu.getUName()); // 欢迎登录语句

	private JTextField t_search = new JTextField(20);
	private ImageButton b_minimize = new ImageButton(Button_Minimize_1); // 最小化按钮
	private ImageButton b_close = new ImageButton(Button_Close_1); // 最大化按钮
	private ImageButton b_search = new ImageButton(Button_Search);

	private ImageButton b_home = new ImageButton(new ImageIcon("Image/Button_Home_s1.png"));
	private ImageButton b_me = new ImageButton(new ImageIcon("Image/Button_U_s1.png"));
	private ImageButton b_mycar = new ImageButton(new ImageIcon("Image/Button_VC_s1.png"));
	private ImageButton b_myorders = new ImageButton(new ImageIcon("Image/Button_O_s1.png"));
	private ImageButton b_transfer = new ImageButton(new ImageIcon("Image/Button_T_s1.png"));
	private ImageButton b_message = new ImageButton(new ImageIcon("Image/Button_M_s1.png"));
	private ImageButton b_menu = new ImageButton(new ImageIcon("Image/Button_Menu_1.png"));
	private JLabel l_kong1 = new JLabel();
	private JLabel l_kong2 = new JLabel();

	private String userimagestr = "Image/User/" + Users.currentu.getUNum() + ".png";

	/*
	 * private JPanel pdc_home = new JPanel(); private JPanel pdc_me = new
	 * JPanel(); private JPanel pdc_mycar = new JPanel(); private JPanel
	 * pdc_myorders = new JPanel(); private JPanel pdc_transter = new JPanel();
	 */

	public ImageButton b_yuyue = new ImageButton(new ImageIcon("Image/Button_yuyue.png"));
	public ImageButton b_sctx = new ImageButton(new ImageIcon("Image/Button_sctx.png"));
	public ImageButton b_modifycall = new ImageButton(new ImageIcon("Image/Button_modifycall.png"));
	public ImageButton b_modifypwd = new ImageButton(new ImageIcon("Image/Button_modifypwd1.png"));
	public ImageButton b_add = new ImageButton(new ImageIcon("Image/Button_add.png"));
	public ImageButton b_modify = new ImageButton(new ImageIcon("Image/Button_modify.png"));
	public ImageButton b_del = new ImageButton(new ImageIcon("Image/Button_del.png"));
	public ImageButton b_gp = new ImageButton(new ImageIcon("Image/Button_guapai.png"));
	public ImageButton b_quxiao = new ImageButton(new ImageIcon("Image/Button_quxiao.png"));
	public ImageButton b_up = new ImageButton(new ImageIcon("Image/Button_up.png"));
	public ImageButton b_down = new ImageButton(new ImageIcon("Image/Button_down.png"));
	public ImageButton b_ok = new ImageButton(new ImageIcon("Image/O1.png"));
	public ImageButton b_cancel = new ImageButton(new ImageIcon("Image/X1.png"));
	public ImageButton b_xx = new ImageButton(new ImageIcon("Image/Button_xx1.png"));
	public ImageButton b_tc = new ImageButton(new ImageIcon("Image/Button_tuiche.png"));
	
	private ImageIcon image = new ImageIcon();
	private JLabel l_tu = new JLabel();
	private String uimagestr = "";
	private JLabel l_uname = new JLabel("用户名");
	private JTextField t_uname = new JTextField(11);
	private JLabel l_rname = new JLabel("真实姓名");
	private JTextField t_rname1 = new JTextField(11);
	private JLabel l_rname2 = new JLabel();
	private JLabel l_adress = new JLabel("地址");
	private JTextArea t_adress = new JTextArea(2, 11);
	private JLabel l_call = new JLabel("手机号");
	private JLabel l_callnum = new JLabel(Users.currentu.getUMobileNum());
	private JLabel l_email = new JLabel("邮箱");
	private JTextField t_email = new JTextField(11);
	private JLabel l_qq = new JLabel("QQ");
	private JTextField t_qq = new JTextField(11);
	private JPanel pdc2_work = new JPanel();
	private JPanel pdc2_picture = new JPanel();

	JTable datavotable = new JTable();
	JTable datavctable = new JTable();
	JTable dataotable = new JTable();
	JTable datattable = new JTable();
	JTable datamtable = new JTable();
	private List<vorders> allvo = new ArrayList<vorders>();
	private List<Vehicles> allvc = new ArrayList<Vehicles>();
	private List<Orders> allo = new ArrayList<Orders>();
	private List<Transfer> allt = new ArrayList<Transfer>();
	private List<Messages> allm = new ArrayList<Messages>();

	public void reloadvotable() {
		Object votableTitle[] = vorders.tableTitles;
		Object votableData[][];
		DefaultTableModel votablemodel = new DefaultTableModel();
		datavotable.setModel(votablemodel);
		try {
			allvo = (new vordersmanager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		votableData = new Object[allvo.size()][vorders.tableTitles.length];

		for (int i = 0; i < allvo.size(); i++) {
			for (int j = 0; j < vorders.tableTitles.length; j++)
				votableData[i][j] = allvo.get(i).getCell(j);
		}
		votablemodel.setDataVector(votableData, votableTitle);
		this.datavotable.validate();
		this.datavotable.repaint();
		this.tablebs(datavotable);
	}
	public void Searchvotable(String str) {
		Object votableTitle[] = vorders.tableTitles;
		Object votableData[][];
		DefaultTableModel votablemodel = new DefaultTableModel();
		datavotable.setModel(votablemodel);
		try {
			allvo = (new vordersmanager()).seearch(str);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		votableData = new Object[allvo.size()][vorders.tableTitles.length];

		for (int i = 0; i < allvo.size(); i++) {
			for (int j = 0; j < vorders.tableTitles.length; j++)
				votableData[i][j] = allvo.get(i).getCell(j);
		}
		votablemodel.setDataVector(votableData, votableTitle);
		this.datavotable.validate();
		this.datavotable.repaint();
		this.tablebs(datavotable);
	}
	public void reloadvctable() {
		Object vctableTitle[] = Vehicles.tableTitles2;
		Object vctableData[][];
		DefaultTableModel vctablemodel = new DefaultTableModel();
		datavctable.setModel(vctablemodel);
		try {
			allvc = (new VehiclesManager()).loadAll2();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		vctableData = new Object[allvc.size()][Vehicles.tableTitles2.length];

		for (int i = 0; i < allvc.size(); i++) {
			for (int j = 0; j < Vehicles.tableTitles2.length; j++)
				vctableData[i][j] = allvc.get(i).getCell2(j);
		}
		vctablemodel.setDataVector(vctableData, vctableTitle);
		this.datavctable.validate();
		this.datavctable.repaint();
		this.tablebs(datavctable);
	}

	public void reloadotable() {
		Object otableTitle[] = Orders.tableTitles2;
		Object otableData[][];
		DefaultTableModel otablemodel = new DefaultTableModel();
		dataotable.setModel(otablemodel);
		Orders o = new Orders();
		try {
			allo = (new OrdersManager()).loadAll2();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		otableData = new Object[allo.size()][Orders.tableTitles2.length];

		for (int i = 0; i < allo.size(); i++) {
			for (int j = 0; j < Orders.tableTitles2.length; j++)
				otableData[i][j] = allo.get(i).getCell2(j);
		}
		otablemodel.setDataVector(otableData, otableTitle);
		this.dataotable.validate();
		this.dataotable.repaint();
		this.tablebs(dataotable);
	}
	public void reloadttable() {
		Object ttableTitle[] = Transfer.tableTitles;
		Object ttableData[][];
		DefaultTableModel ttablemodel = new DefaultTableModel();
		datattable.setModel(ttablemodel);
		try {
			allt = (new TransferManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		ttableData = new Object[allt.size()][Transfer.tableTitles.length];

		for (int i = 0; i < allt.size(); i++) {
			for (int j = 0; j < Transfer.tableTitles.length; j++)
				ttableData[i][j] = allt.get(i).getCell(j);
		}
		ttablemodel.setDataVector(ttableData, ttableTitle);
		this.datattable.validate();
		this.datattable.repaint();
		this.tablebs(datattable);
	}
	public void reloadmtable() {
		Object mtableTitle[] = Messages.tableTitles;
		Object mtableData[][];
		DefaultTableModel mtablemodel = new DefaultTableModel();
		datamtable.setModel(mtablemodel);
		Messages m = new Messages();
		try {
			allm = (new MessageManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

		mtableData = new Object[allm.size()][Messages.tableTitles.length];

		for (int i = 0; i < allm.size(); i++) {
			for (int j = 0; j < Messages.tableTitles.length; j++)
				mtableData[i][j] = allm.get(i).getCell(j);
		}
		mtablemodel.setDataVector(mtableData, mtableTitle);
		this.datamtable.validate();
		this.datamtable.repaint();
		this.tablebs(datamtable);
	}
	public void tablebs(JTable table) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					setHorizontalAlignment(SwingConstants.CENTER);
					if (row % 2 == 0)
						setBackground(new Color(255, 255, 255));
					else if (row % 2 == 1)
						setBackground(new Color(240, 240, 240)); // 设置偶数行底色
					return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}
			};
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public FrmUMain() {
		
		// -------------------------------------------------------------------------
		this.setResizable(false); // 禁止修改窗口大小
		this.setUndecorated(true); // 取消顶部栏
		this.setSize(1600, 900);

		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		// 鼠标事件:拖拽标题栏
		MouseEventListene mouseListener = new MouseEventListene(this, this.pu_main);
		this.pu_main.addMouseListener(mouseListener);
		this.pu_main.addMouseMotionListener(mouseListener);

		FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
		//-------------------------首页-----------------------------------------
		datavotable.setBorder(null);
		datavotable.setRowHeight(30);
		datavotable.setBackground(new Color(255, 255, 255));
		datavotable.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		JTableHeader th = datavotable.getTableHeader();
		th.setBackground(new Color(0, 0, 0));
		th.setForeground(new Color(255, 255, 255));
		th.setFont(new Font("微软雅黑", Font.BOLD, 24));
		datavotable.setTableHeader(th);
		datavotable.setShowGrid(false);
		pdc_home.setLayout(new BorderLayout());
		JScrollPane jsp = new JScrollPane(this.datavotable);
		jsp.getViewport().setBackground(new Color(255, 255, 255));
		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		pdc_home.add(jsp, BorderLayout.CENTER);
		this.reloadvotable();
		// ------------------------用户管理-------------------------------------
		Color lightgrey = new Color(200, 200, 200);
		Font font = new Font("微软雅黑", Font.BOLD, 22);
		Font ff = new Font("微软雅黑", Font.PLAIN, 22);

		l_rname2.setOpaque(false);
		l_rname2.setFont(ff);
		l_rname2.setForeground(new Color(54, 54, 54));
		l_rname2.setPreferredSize(new Dimension(240, 40));

		t_rname1.setOpaque(false);
		t_rname1.setFont(ff);
		t_rname1.setForeground(new Color(54, 54, 54));
		t_rname1.setBorder(BorderFactory.createLineBorder(lightgrey, 3));
		t_rname1.setPreferredSize(new Dimension(240, 40));

		if (Users.currentu.getURName() != null) {
			t_rname1.setVisible(false);
			l_rname2.setVisible(true);
			l_rname2.setText(Users.currentu.getURName());
		} else {
			t_rname1.setVisible(true);
			l_rname2.setVisible(false);
		}
		FlowLayout f = new FlowLayout(FlowLayout.CENTER);
		f.setHgap(0);
		f.setVgap(40);

		pdc2_work.setLayout(f);
		pdc2_work.setOpaque(false);
		pdc2_work.setPreferredSize(new Dimension(500, 800));

		pdc2_picture.setLayout(f);
		pdc2_picture.setOpaque(false);
		pdc2_picture.setPreferredSize(new Dimension(600, 800));
		uimagestr = this.userimagestr;
		if (new ImageIcon(this.userimagestr).getImageLoadStatus() != MediaTracker.COMPLETE) {
			uimagestr = "Image/User/users.png";
		}
		image = new ImageIcon(uimagestr);
		image.setImage(image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
		l_tu.setPreferredSize(new Dimension(400, 400));
		l_tu.setOpaque(false);
		l_tu.setIcon(image);
		pdc2_picture.add(l_tu, new BorderLayout().CENTER);
		
		l_uname.setOpaque(false);
		l_uname.setFont(font);
		l_uname.setForeground(new Color(54, 54, 54));
		l_uname.setPreferredSize(new Dimension(160, 40));
		// l_mb.setBorder(BorderFactory.createLineBorder(new
		// Color(28,28,28),3));

		l_rname.setOpaque(false);
		l_rname.setFont(font);
		l_rname.setForeground(new Color(54, 54, 54));
		l_rname.setPreferredSize(new Dimension(160, 40));

		l_adress.setOpaque(false);
		l_adress.setFont(font);
		l_adress.setForeground(new Color(54, 54, 54));
		l_adress.setPreferredSize(new Dimension(160, 80));

		l_call.setOpaque(false);
		l_call.setFont(font);
		l_call.setForeground(new Color(54, 54, 54));
		l_call.setPreferredSize(new Dimension(160, 40));

		l_email.setOpaque(false);
		l_email.setFont(font);
		l_email.setForeground(new Color(54, 54, 54));
		l_email.setPreferredSize(new Dimension(160, 40));

		l_qq.setOpaque(false);
		l_qq.setFont(font);
		l_qq.setForeground(new Color(54, 54, 54));
		l_qq.setPreferredSize(new Dimension(160, 40));

		t_uname.setOpaque(false);
		t_uname.setFont(ff);
		t_uname.setForeground(new Color(54, 54, 54));
		t_uname.setBorder(BorderFactory.createLineBorder(lightgrey, 3));
		t_uname.setPreferredSize(new Dimension(240, 40));
		t_uname.setText(Users.currentu.getUName());

		t_adress.setOpaque(false);
		t_adress.setFont(ff);
		t_adress.setForeground(new Color(54, 54, 54));
		t_adress.setBorder(BorderFactory.createLineBorder(lightgrey, 3));
		t_adress.setPreferredSize(new Dimension(240, 80));
		t_adress.setLineWrap(true);
		t_adress.setText(Users.currentu.getUAdress());

		t_email.setOpaque(false);
		t_email.setFont(ff);
		t_email.setForeground(new Color(54, 54, 54));
		t_email.setBorder(BorderFactory.createLineBorder(lightgrey, 3));
		t_email.setPreferredSize(new Dimension(240, 40));
		t_email.setText(Users.currentu.getUEmail());

		t_qq.setOpaque(false);
		t_qq.setFont(ff);
		t_qq.setForeground(new Color(54, 54, 54));
		t_qq.setBorder(BorderFactory.createLineBorder(lightgrey, 3));
		t_qq.setPreferredSize(new Dimension(240, 40));
		t_qq.setText(Users.currentu.getUQq());

		l_callnum.setOpaque(false);
		l_callnum.setBorder(null);
		l_callnum.setFont(ff);
		l_callnum.setForeground(new Color(54, 54, 54));
		// l_callnum.setBorder(BorderFactory.createLineBorder(new
		// Color(28,28,28),3));
		l_callnum.setPreferredSize(new Dimension(240, 40));

		pdc2_work.add(l_uname);
		pdc2_work.add(t_uname);
		pdc2_work.add(l_rname);
		pdc2_work.add(t_rname1);
		pdc2_work.add(l_rname2);
		pdc2_work.add(l_adress);
		pdc2_work.add(t_adress);
		pdc2_work.add(l_call);
		pdc2_work.add(l_callnum);
		pdc2_work.add(l_email);
		pdc2_work.add(t_email);
		pdc2_work.add(l_qq);
		pdc2_work.add(t_qq);

		this.pdc_me.add(pdc2_picture, new BorderLayout().WEST);
		this.pdc_me.add(pdc2_work, new BorderLayout().CENTER);
		// ------------------------车辆管理-----------------------------------
		datavctable.setBorder(null);
		datavctable.setRowHeight(30);
		datavctable.setBackground(new Color(255, 255, 255));
		datavctable.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		th = datavctable.getTableHeader();
		th.setBackground(new Color(0, 0, 0));
		th.setForeground(new Color(255, 255, 255));
		th.setFont(new Font("微软雅黑", Font.BOLD, 24));
		datavctable.setTableHeader(th);
		datavctable.setShowGrid(false);

		pdc_mycar.setLayout(new BorderLayout());
		jsp = new JScrollPane(this.datavctable);
		jsp.getViewport().setBackground(new Color(255, 255, 255));
		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		pdc_mycar.add(jsp, BorderLayout.CENTER);
		// -------------------------挂牌管理-----------------------------------
		dataotable.setBorder(null);
		dataotable.setRowHeight(30);
		dataotable.setBackground(new Color(255, 255, 255));
		dataotable.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		th = dataotable.getTableHeader();
		th.setBackground(new Color(0, 0, 0));
		th.setForeground(new Color(255, 255, 255));
		th.setFont(new Font("微软雅黑", Font.BOLD, 24));
		dataotable.setTableHeader(th);
		dataotable.setShowGrid(false);

		pdc_myorders.setLayout(new BorderLayout());
		jsp = new JScrollPane(this.dataotable);
		jsp.getViewport().setBackground(new Color(255, 255, 255));
		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		pdc_myorders.add(jsp, BorderLayout.CENTER);
		//-------------------------过户---------------------------------------
		datattable.setBorder(null);
		datattable.setRowHeight(30);
		datattable.setBackground(new Color(255, 255, 255));
		datattable.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		th = datattable.getTableHeader();
		th.setBackground(new Color(0, 0, 0));
		th.setForeground(new Color(255, 255, 255));
		th.setFont(new Font("微软雅黑", Font.BOLD, 24));
		datattable.setTableHeader(th);
		datattable.setShowGrid(false);

		pdc_transfer.setLayout(new BorderLayout());
		jsp = new JScrollPane(this.datattable);
		jsp.getViewport().setBackground(new Color(255, 255, 255));
		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		pdc_transfer.add(jsp, BorderLayout.CENTER);
		//-------------------------消息---------------------------------------
		datamtable.setBorder(null);
		datamtable.setRowHeight(30);
		datamtable.setBackground(new Color(255, 255, 255));
		datamtable.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		th = datamtable.getTableHeader();
		th.setBackground(new Color(0, 0, 0));
		th.setForeground(new Color(255, 255, 255));
		th.setFont(new Font("微软雅黑", Font.BOLD, 24));
		datamtable.setTableHeader(th);
		datamtable.setShowGrid(false);

		pdc_message.setLayout(new BorderLayout());
		jsp = new JScrollPane(this.datamtable);
		jsp.getViewport().setBackground(new Color(255, 255, 255));
		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		pdc_message.add(jsp, BorderLayout.CENTER);
		// ------------------------标题栏-------------------------------------
		pu_main.setLayout(new FlowLayout(FlowLayout.LEFT)); // 伪标题栏
		pu_main.setBackground(new Color(0, 191, 255)); // DeepSkyBlue
		pu_main.setPreferredSize(new Dimension(1600, 100));

		pu_left.setLayout(new FlowLayout(FlowLayout.LEFT)); // 欢迎面板
		pu_left.setOpaque(false); // 背景透明
		pu_left.setPreferredSize(new Dimension(290, 90));
		pu_left.add(l_welcome);

		flowlayout = new FlowLayout(FlowLayout.CENTER); // 搜索栏
		flowlayout.setHgap(0); // 控件间距
		pu_center.setLayout(flowlayout);
		pu_center.setPreferredSize(new Dimension(1190, 60));
		pu_center.setOpaque(false); // 背景透明
		pu_center.add(new JLabel(new ImageIcon("Image/Label_search.png")));
		pu_center.add(t_search);
		pu_center.add(b_search);

		pu_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pu_right.setPreferredSize(new Dimension(100, 90));
		pu_right.setOpaque(false); // 背景透明
		pu_right.add(b_minimize);
		pu_right.add(b_close);

		pu_main.add(pu_left);
		pu_main.add(pu_center);
		pu_main.add(pu_right);
		// ----------------------------功能栏-----------------------------------
		flowlayout = new FlowLayout(FlowLayout.LEFT);
		flowlayout.setHgap(0);
		flowlayout.setVgap(0);

		pd_main.setLayout(flowlayout);
		pd_main.setBackground(new Color(255, 255, 255));
		pd_main.setPreferredSize(new Dimension(1600, 800));

		pd_left.setLayout(flowlayout);
		pd_left.setBackground(new Color(105, 105, 105));
		pd_left.setPreferredSize(new Dimension(80, 800));
		pd_left.setBorder(null);
		l_kong1.setPreferredSize(new Dimension(80, 80));

		pd_left.add(l_kong1);
		pd_left.add(b_home);
		pd_left.add(b_me);
		pd_left.add(b_mycar);
		pd_left.add(b_myorders);
		pd_left.add(b_transfer);
		pd_left.add(b_message);
		l_kong2.setPreferredSize(new Dimension(80, 160));
		pd_left.add(l_kong2);
		pd_left.add(b_menu);

		flowlayout = new FlowLayout(FlowLayout.LEFT);
		flowlayout.setHgap(0);
		flowlayout.setVgap(0);
		this.pd_center.setLayout(flowlayout);
		this.pd_center.setOpaque(false);
		this.pd_center.setPreferredSize(new Dimension(1320, 800));
		this.pd_center.add(this.pdc_home);
		this.pd_center.add(this.pdc_me);
		this.pd_center.add(this.pdc_mycar);
		this.pd_center.add(this.pdc_myorders);
		this.pd_center.add(this.pdc_transfer);
		this.pd_center.add(this.pdc_message);

		this.pdc_home.setVisible(false);
		this.pdc_me.setVisible(false);
		this.pdc_mycar.setVisible(false);
		this.pdc_myorders.setVisible(false);
		this.pdc_transfer.setVisible(false);
		this.pdc_message.setVisible(false);
		
		flowlayout = new FlowLayout(FlowLayout.CENTER);
		flowlayout.setHgap(0);
		flowlayout.setVgap(20);
		this.pd_right.setLayout(flowlayout);
		this.pd_right.setPreferredSize(new Dimension(200, 800));
		this.pd_right.add(b_yuyue);
		this.pd_right.add(b_sctx);
		this.pd_right.add(b_modifycall);
		this.pd_right.add(b_modifypwd);
		this.pd_right.add(b_add);
		this.pd_right.add(b_modify);
		this.pd_right.add(b_del);
		this.pd_right.add(b_gp);
		this.pd_right.add(b_quxiao);
		this.pd_right.add(b_ok);
		this.pd_right.add(b_cancel);
		this.pd_right.add(b_xx);
		this.pd_right.add(b_tc);

		this.pdc_home.setPreferredSize(new Dimension(1320, 800));
		this.pdc_me.setPreferredSize(new Dimension(1320, 800));
		this.pdc_mycar.setPreferredSize(new Dimension(1320, 800));
		this.pdc_myorders.setPreferredSize(new Dimension(1320, 800));
		this.pdc_transfer.setPreferredSize(new Dimension(1320, 800));
		this.pdc_message.setPreferredSize(new Dimension(1320, 800));

		this.pdc_what(1);

		pd_main.add(pd_left);
		pd_main.add(pd_center);
		pd_main.add(pd_right);
		// --------------------------布局--------------------------------------
		this.getContentPane().add(pu_main, new BorderLayout().NORTH);
		this.getContentPane().add(pd_main, new BorderLayout().SOUTH);
		this.setVisible(true);
		// --------------------------关闭、最小化、搜索按钮------------------------------
		// b_minimize.setSelectedIcon(new
		// ImageIcon("Image/Button_Minimize_2.png"));
		b_minimize.setPressedIcon(new ImageIcon("Image/Button_Minimize_2.png"));
		b_minimize.setRolloverIcon(new ImageIcon("Image/Button_Minimize_2.png"));
		// b_close.setSelectedIcon(new ImageIcon("Image/Button_Close_2.png"));
		b_close.setPressedIcon(new ImageIcon("Image/Button_Close_2.png"));
		b_close.setRolloverIcon(new ImageIcon("Image/Button_Close_2.png"));
		b_search.setRolloverIcon(new ImageIcon("Image/Button_Search2.png"));
		b_search.setPressedIcon(new ImageIcon("Image/Button_Search2.png"));
		b_search.addActionListener(this);
		b_minimize.addActionListener(this);
		b_close.addActionListener(this);
		// --------------------------欢迎面板------------------------------------
		font = new Font("等线", Font.BOLD, 24);
		l_welcome.setFont(font);
		l_welcome.setForeground(new Color(255, 255, 255));
		// --------------------------搜索栏-------------------------------------
		font = new Font("等线", Font.PLAIN, 28);
		t_search.setFont(font);
		t_search.setPreferredSize(new Dimension(t_search.getWidth(), 41));
		t_search.setForeground(new Color(255, 255, 255));
		t_search.setBackground(new Color(67, 110, 238));
		t_search.setCaretColor(new Color(255, 255, 255));
		t_search.setBorder(null);
		// --------------------------Menu------------------------------------
		b_menu.addActionListener(this);
		b_home.addActionListener(this);
		b_me.addActionListener(this);
		b_mycar.addActionListener(this);
		b_myorders.addActionListener(this);
		b_transfer.addActionListener(this);
		b_message.addActionListener(this);

		b_menu.setPressedIcon(new ImageIcon("Image/Button_Menu_2.png"));
		b_menu.setRolloverIcon(new ImageIcon("Image/Button_Menu_2.png"));

		b_home.setRolloverIcon(new ImageIcon("Image/Button_Home_l2.png"));
		b_me.setRolloverIcon(new ImageIcon("Image/Button_U_l2.png"));
		b_mycar.setRolloverIcon(new ImageIcon("Image/Button_VC_l2.png"));
		b_myorders.setRolloverIcon(new ImageIcon("Image/Button_O_l2.png"));
		b_transfer.setRolloverIcon(new ImageIcon("Image/Button_T_l2.png"));
		b_message.setRolloverIcon(new ImageIcon("Image/Button_M_l2.png"));

		b_home.setPressedIcon(new ImageIcon("Image/Button_Home_l2.png"));
		b_me.setPressedIcon(new ImageIcon("Image/Button_U_l2.png"));
		b_mycar.setPressedIcon(new ImageIcon("Image/Button_VC_l2.png"));
		b_myorders.setPressedIcon(new ImageIcon("Image/Button_O_l2.png"));
		b_transfer.setPressedIcon(new ImageIcon("Image/Button_T_l2.png"));
		b_message.setPressedIcon(new ImageIcon("Image/Button_M_l2.png"));
		// -------------------------------------------------------------------
		this.b_yuyue.addActionListener(this);
		this.b_sctx.addActionListener(this);
		this.b_modifycall.addActionListener(this);
		this.b_modifypwd.addActionListener(this);
		this.b_add.addActionListener(this);
		this.b_modify.addActionListener(this);
		this.b_del.addActionListener(this);
		this.b_gp.addActionListener(this);
		this.b_quxiao.addActionListener(this);
		this.b_ok.addActionListener(this);
		this.b_cancel.addActionListener(this);
		this.b_xx.addActionListener(this);
		this.b_tc.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.b_minimize) {
			this.setExtendedState(JFrame.ICONIFIED);
		} else if (e.getSource() == this.b_close) {
			System.exit(0);
		} else if (e.getSource() == this.b_menu) {
			if (this.pd_left.getWidth() == 250) {
				this.menushort();
			} else {
				this.menulong();
			}
		} else if (e.getSource() == this.b_home) {
			this.a = 1;
			this.pdc_what(1);
		} else if (e.getSource() == this.b_me) {
			this.a = 2;
			this.pdc_what(2);
		} else if (e.getSource() == this.b_mycar) {
			this.a = 3;
			this.pdc_what(3);
		} else if (e.getSource() == this.b_myorders) {
			this.a = 4;
			this.pdc_what(4);
		} else if (e.getSource() == this.b_transfer) {
			this.a = 5;
			this.pdc_what(5);
		} else if (e.getSource() == this.b_message) {
			this.a = 6;
			this.pdc_what(6);
		}

		else if (e.getSource() == this.b_yuyue) {
			Messages m = new Messages();
			m.setMSender(Users.currentu.getUNum());
			m.setMReceiver(this.allvo.get(this.datavotable.getSelectedRow()).getUnum());
			m.setMONum(this.allvo.get(this.datavotable.getSelectedRow()).getOnum());
			m.setMessage(1);
			try {
				(new MessageManager()).add(m);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.b_sctx) {
			(new FileUseDemo()).CopyFile(this);
		} else if (e.getSource() == this.b_modifycall) {
			FrmUModifycall dlg = new FrmUModifycall(this,"",true);
			dlg.setVisible(true);
			this.pdc_what(2);
		} else if (e.getSource() == this.b_modifypwd) {
			FrmUModifypwd dlg = new FrmUModifypwd(this,"",true);
			this.setVisible(false);
			dlg.setVisible(true);
		} else if (e.getSource() == this.b_modify) {
			if (a == 2) {
				FrmUModify dlg = new FrmUModify(this,"",true);
				dlg.setVisible(true);
				this.pdc_what(2);
			}
			else if(a == 3){
				FrmVcModify dlg = new FrmVcModify(this,"",true,this.allvc.get(this.datavctable.getSelectedRow()));
				dlg.setVisible(true);
				this.pdc_what(3);
			}
			else if (a == 4){
				FrmOModify dlg = new FrmOModify(this,"挂牌修改",true,this.allo.get(this.dataotable.getSelectedRow()));
				dlg.setVisible(true);
				this.reloadotable();
			}
		} else if (e.getSource() == this.b_add) {
			if(this.a == 3){
				FrmVcAdd dlg = new FrmVcAdd(this,"",true);
				dlg.setVisible(true);
				this.pdc_what(3);
			}

		} else if (e.getSource() == this.b_del) {
			if(this.a == 3){
				try {
					(new VehiclesManager()).del(this.allvc.get(this.datavctable.getSelectedRow()).getVcNum());
					this.pdc_what(3);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.b_gp) {
			if(this.a == 3){
				Orders o = new Orders();
				o.setOVNum(this.allvc.get(this.datavctable.getSelectedRow()).getVcNum());
				o.setOSuNum(Users.currentu.getUNum());
				FrmOAdd dlg = new FrmOAdd(this,"",true,o);
				dlg.setVisible(true);

			}
		} else if (e.getSource() == this.b_quxiao) {
			try {
				new OrdersManager().del(this.allo.get(this.dataotable.getSelectedRow()).getONum());
				this.pdc_what(4);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.b_ok) {

		} else if (e.getSource() == this.b_cancel) {

		} else if (e.getSource() == this.b_up) {

		} else if (e.getSource() == this.b_down) {

		} else if (e.getSource() == this.b_xx) {
			if(this.a == 6){
				int x = this.allm.get(this.datamtable.getSelectedRow()).getMessage();
				Messages m = null;
				try {
					m = (new MessageManager()).getMessage(this.allm.get(this.datamtable.getSelectedRow()).getMNum());
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				if((x==1)||(x==2)||(x==3)){
					FrmMessageA dlg = new FrmMessageA(this,"",true,m);
					dlg.setVisible(true);
					this.pdc_what(6);
				}
				else{
					FrmMessageB dlg = new FrmMessageB(this,"",true,m);
					dlg.setVisible(true);
					this.pdc_what(6);
					try {
						(new MessageManager()).del();
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} else if(e.getSource() == this.b_search){
			String str = this.t_search.getText();
			if(this.a == 1){
				this.Searchvotable(str);
			}
		} else if(e.getSource() == this.b_tc) {
			Messages m = new Messages();
			m.setMSender(Users.currentu.getUNum());
			m.setMReceiver(this.allt.get(this.datattable.getSelectedRow()).getTSuNum());
			
			String x = "";
			try {
				x=(new vordersmanager()).find(this.allt.get(this.datattable.getSelectedRow()).getTSuNum(), 2).getOnum();
			} catch (BaseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			m.setMONum(x);
			m.setMessage(3);
			try {
				(new MessageManager()).add(m);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}

	}

	public void pdc_what(int x) {

		b_up.setRolloverIcon(new ImageIcon("Image/Button_up2.png"));
		b_up.setPressedIcon(new ImageIcon("Image/Button_up2.png"));
		b_down.setRolloverIcon(new ImageIcon("Image/Button_down2.png"));
		b_down.setPressedIcon(new ImageIcon("Image/Button_down2.png"));

		this.pdc_home.setVisible(false);
		this.pdc_me.setVisible(false);
		this.pdc_mycar.setVisible(false);
		this.pdc_myorders.setVisible(false);
		this.pdc_transfer.setVisible(false);
		this.pdc_message.setVisible(false);

		this.pdc_home.setBackground(Color.RED);
		this.pdc_me.setBackground(Color.WHITE);
		this.pdc_mycar.setBackground(Color.YELLOW);
		this.pdc_myorders.setBackground(Color.GREEN);
		this.pdc_transfer.setBackground(Color.CYAN);
		this.pdc_message.setBackground(Color.BLUE);
		// ------------------------首页-----------------------------------------

		if (x == 1) { // 首页
			this.reloadvotable();
			this.pdc_home.setVisible(true);
			this.pdc_me.setVisible(false);
			this.pdc_mycar.setVisible(false);
			this.pdc_myorders.setVisible(false);
			this.pdc_transfer.setVisible(false);
			this.pdc_message.setVisible(false);
			this.pdr_what(1);
		} else if (x == 2) { // 用户管理
			this.uimagestr = this.userimagestr;
			/*if (new ImageIcon(this.userimagestr).getImageLoadStatus() != MediaTracker.COMPLETE) {
				this.uimagestr = "Image/User/users.png";
			}*/
			if(!(new File(this.uimagestr).exists())){
				this.uimagestr = "Image/User/users.png";
			}
			this.image = new ImageIcon(this.uimagestr);
			this.image.setImage(image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
			this.l_tu.setIcon(image);
			
			t_uname.setText(Users.currentu.getUName());
			l_rname2.setText(Users.currentu.getURName());
			t_adress.setText(Users.currentu.getUAdress());
			t_email.setText(Users.currentu.getUEmail());
			t_qq.setText(Users.currentu.getUQq());
			
			this.pdc_home.setVisible(false);
			this.pdc_me.setVisible(true);
			this.pdc_mycar.setVisible(false);
			this.pdc_myorders.setVisible(false);
			this.pdc_transfer.setVisible(false);
			this.pdc_message.setVisible(false);
			this.pdr_what(2);
		} else if (x == 3) { // 车辆管理
			this.reloadvctable();
			this.pdc_home.setVisible(false);
			this.pdc_me.setVisible(false);
			this.pdc_mycar.setVisible(true);
			this.pdc_myorders.setVisible(false);
			this.pdc_transfer.setVisible(false);
			this.pdc_message.setVisible(false);
			this.pdr_what(3);
		} else if (x == 4) { // 我的订单
			this.reloadotable();
			this.pdc_home.setVisible(false);
			this.pdc_me.setVisible(false);
			this.pdc_mycar.setVisible(false);
			this.pdc_myorders.setVisible(true);
			this.pdc_transfer.setVisible(false);
			this.pdc_message.setVisible(false);
			this.pdr_what(4);
		} else if (x == 5) { // 我的过户记录
			this.pdc_home.setVisible(false);
			this.pdc_me.setVisible(false);
			this.pdc_mycar.setVisible(false);
			this.pdc_myorders.setVisible(false);
			this.pdc_transfer.setVisible(true);
			this.pdc_message.setVisible(false);
			this.pdr_what(5);
		} else if (x == 6) {
			this.pdc_home.setVisible(false);
			this.pdc_me.setVisible(false);
			this.pdc_mycar.setVisible(false);
			this.pdc_myorders.setVisible(false);
			this.pdc_transfer.setVisible(false);
			this.pdc_message.setVisible(true);
			this.pdr_what(6);
		}
	}

	public void pdr_what(int x) {
		this.b_yuyue.setRolloverIcon(new ImageIcon("Image/Button_yuyue2.png"));
		this.b_yuyue.setPressedIcon(new ImageIcon("Image/Button_yuyue2.png"));
		this.b_sctx.setRolloverIcon(new ImageIcon("Image/Button_sctx2.png"));
		this.b_sctx.setPressedIcon(new ImageIcon("Image/Button_sctx2.png"));
		this.b_modifycall.setRolloverIcon(new ImageIcon("Image/Button_modifycall2.png"));
		this.b_modifycall.setPressedIcon(new ImageIcon("Image/Button_modifycall2.png"));
		this.b_modifypwd.setRolloverIcon(new ImageIcon("Image/Button_modifypwd2.png"));
		this.b_modifypwd.setPressedIcon(new ImageIcon("Image/Button_modifypwd2.png"));
		this.b_add.setRolloverIcon(new ImageIcon("Image/Button_add2.png"));
		this.b_add.setPressedIcon(new ImageIcon("Image/Button_add2.png"));
		this.b_modify.setRolloverIcon(new ImageIcon("Image/Button_modify2.png"));
		this.b_modify.setPressedIcon(new ImageIcon("Image/Button_modify2.png"));
		this.b_del.setRolloverIcon(new ImageIcon("Image/Button_del2.png"));
		this.b_del.setPressedIcon(new ImageIcon("Image/Button_del2.png"));
		this.b_gp.setRolloverIcon(new ImageIcon("Image/Button_guapai2.png"));
		this.b_gp.setPressedIcon(new ImageIcon("Image/Button_guapai2.png"));
		this.b_quxiao.setRolloverIcon(new ImageIcon("Image/Button_quxiao2.png"));
		this.b_quxiao.setPressedIcon(new ImageIcon("Image/Button_quxiao2.png"));
		this.b_ok.setRolloverIcon(new ImageIcon("Image/O2.png"));
		this.b_ok.setPressedIcon(new ImageIcon("Image/O2.png"));
		this.b_cancel.setRolloverIcon(new ImageIcon("Image/X2.png"));
		this.b_cancel.setPressedIcon(new ImageIcon("Image/X2.png"));
		this.b_xx.setRolloverIcon(new ImageIcon("Image/Button_xx2.png"));
		this.b_xx.setPressedIcon(new ImageIcon("Image/Button_xx2.png"));
		this.b_tc.setRolloverIcon(new ImageIcon("Image/Button_tuiche2.png"));
		this.b_tc.setPressedIcon(new ImageIcon("Image/Button_tuiche2.png"));
		
		if (x == 1) {
			this.a = 1;
			this.b_yuyue.setVisible(true);
			this.b_sctx.setVisible(false);
			this.b_modifycall.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_del.setVisible(false);
			this.b_gp.setVisible(false);
			this.b_quxiao.setVisible(false);
			this.b_ok.setVisible(false);
			this.b_cancel.setVisible(false);
			this.b_xx.setVisible(false);
			this.b_tc.setVisible(false);
		} else if (x == 2) {
			this.a = 2;
			this.b_yuyue.setVisible(false);
			this.b_sctx.setVisible(true);
			this.b_modifycall.setVisible(true);
			this.b_modifypwd.setVisible(true);
			this.b_add.setVisible(false);
			this.b_modify.setVisible(true);
			this.b_del.setVisible(false);
			this.b_gp.setVisible(false);
			this.b_quxiao.setVisible(false);
			this.b_ok.setVisible(false);
			this.b_cancel.setVisible(false);
			this.b_xx.setVisible(false);
			this.b_tc.setVisible(false);
		} else if (x == 3) {
			this.a = 3;
			this.b_yuyue.setVisible(false);
			this.b_sctx.setVisible(false);
			this.b_modifycall.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_add.setVisible(true);
			this.b_modify.setVisible(true);
			this.b_del.setVisible(true);
			this.b_gp.setVisible(true);
			this.b_quxiao.setVisible(false);
			this.b_ok.setVisible(false);
			this.b_cancel.setVisible(false);
			this.b_xx.setVisible(false);
			this.b_tc.setVisible(false);
		} else if (x == 4) {
			this.a = 4;
			this.b_yuyue.setVisible(false);
			this.b_sctx.setVisible(false);
			this.b_modifycall.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_add.setVisible(false);
			this.b_modify.setVisible(true);
			this.b_del.setVisible(false);
			this.b_gp.setVisible(false);
			this.b_quxiao.setVisible(true);
			this.b_ok.setVisible(false);
			this.b_cancel.setVisible(false);
			this.b_xx.setVisible(false);
			this.b_tc.setVisible(false);
		} else if (x == 5) {
			this.reloadttable();
			this.a = 5;
			this.b_yuyue.setVisible(false);
			this.b_sctx.setVisible(false);
			this.b_modifycall.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_del.setVisible(false);
			this.b_gp.setVisible(false);
			this.b_quxiao.setVisible(false);
			this.b_ok.setVisible(false);
			this.b_cancel.setVisible(false);
			this.b_xx.setVisible(false);
			this.b_tc.setVisible(true);
		} else if (x == 6) {
			this.reloadmtable();
			this.a = 6;
			this.b_yuyue.setVisible(false);
			this.b_sctx.setVisible(false);
			this.b_modifycall.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_del.setVisible(false);
			this.b_gp.setVisible(false);
			this.b_quxiao.setVisible(false);
			this.b_ok.setVisible(false);
			this.b_cancel.setVisible(false);
			this.b_xx.setVisible(true);
			this.b_tc.setVisible(false);
		}
	}

	public void menulong() {
		this.b_home.setIcon(new ImageIcon("Image/Button_Home_l1.png"));
		this.b_me.setIcon(new ImageIcon("Image/Button_U_l1.png"));
		this.b_mycar.setIcon(new ImageIcon("Image/Button_VC_l1.png"));
		this.b_myorders.setIcon(new ImageIcon("Image/Button_O_l1.png"));
		this.b_transfer.setIcon(new ImageIcon("Image/Button_T_l1.png"));
		this.b_message.setIcon(new ImageIcon("Image/Button_M_l1.png"));

		this.pd_left.setPreferredSize(new Dimension(250, 800));
		this.l_kong1.setPreferredSize(new Dimension(250, 80));
		this.l_kong2.setPreferredSize(new Dimension(250, 160));
		this.pd_center.setPreferredSize(new Dimension(1150, 800));
		this.pdc_home.setPreferredSize(new Dimension(1150, 800));
		this.pdc_me.setPreferredSize(new Dimension(1150, 800));
		this.pdc_mycar.setPreferredSize(new Dimension(1150, 800));
		this.pdc_myorders.setPreferredSize(new Dimension(1150, 800));
		this.pdc_transfer.setPreferredSize(new Dimension(1150, 800));
		this.pdc_message.setPreferredSize(new Dimension(1150, 800));

		this.b_home.setRolloverIcon(new ImageIcon("Image/Button_Home_l2.png"));
		this.b_me.setRolloverIcon(new ImageIcon("Image/Button_U_l2.png"));
		this.b_mycar.setRolloverIcon(new ImageIcon("Image/Button_VC_l2.png"));
		this.b_myorders.setRolloverIcon(new ImageIcon("Image/Button_O_l2.png"));
		this.b_transfer.setRolloverIcon(new ImageIcon("Image/Button_T_l2.png"));
		this.b_message.setRolloverIcon(new ImageIcon("Image/Button_M_l2.png"));

		this.b_home.setPressedIcon(new ImageIcon("Image/Button_Home_l2.png"));
		this.b_me.setPressedIcon(new ImageIcon("Image/Button_U_l2.png"));
		this.b_mycar.setPressedIcon(new ImageIcon("Image/Button_VC_l2.png"));
		this.b_myorders.setPressedIcon(new ImageIcon("Image/Button_O_l2.png"));
		this.b_transfer.setPressedIcon(new ImageIcon("Image/Button_T_l2.png"));
		this.b_message.setPressedIcon(new ImageIcon("Image/Button_M_l2.png"));
	}

	public void menushort() {
		this.b_home.setIcon(new ImageIcon("Image/Button_Home_s1.png"));
		this.b_me.setIcon(new ImageIcon("Image/Button_U_s1.png"));
		this.b_mycar.setIcon(new ImageIcon("Image/Button_VC_s1.png"));
		this.b_myorders.setIcon(new ImageIcon("Image/Button_O_s1.png"));
		this.b_transfer.setIcon(new ImageIcon("Image/Button_T_s1.png"));
		this.b_message.setIcon(new ImageIcon("Image/Button_M_s1.png"));

		this.pd_left.setPreferredSize(new Dimension(80, 800));
		this.l_kong1.setPreferredSize(new Dimension(80, 80));
		this.l_kong2.setPreferredSize(new Dimension(80, 160));
		this.pd_center.setPreferredSize(new Dimension(1320, 800));
		this.pdc_home.setPreferredSize(new Dimension(1320, 800));
		this.pdc_me.setPreferredSize(new Dimension(1320, 800));
		this.pdc_mycar.setPreferredSize(new Dimension(1320, 800));
		this.pdc_myorders.setPreferredSize(new Dimension(1320, 800));
		this.pdc_transfer.setPreferredSize(new Dimension(1320, 800));
		this.pdc_message.setPreferredSize(new Dimension(1320, 800));

		this.b_home.setRolloverIcon(new ImageIcon("Image/Button_Home_s2.png"));
		this.b_me.setRolloverIcon(new ImageIcon("Image/Button_U_s2.png"));
		this.b_mycar.setRolloverIcon(new ImageIcon("Image/Button_VC_s2.png"));
		this.b_myorders.setRolloverIcon(new ImageIcon("Image/Button_O_s2.png"));
		this.b_transfer.setRolloverIcon(new ImageIcon("Image/Button_T_s2.png"));
		this.b_message.setRolloverIcon(new ImageIcon("Image/Button_M_s2.png"));

		this.b_home.setPressedIcon(new ImageIcon("Image/Button_Home_s2.png"));
		this.b_me.setPressedIcon(new ImageIcon("Image/Button_U_s2.png"));
		this.b_mycar.setPressedIcon(new ImageIcon("Image/Button_VC_s2.png"));
		this.b_myorders.setPressedIcon(new ImageIcon("Image/Button_O_s2.png"));
		this.b_transfer.setPressedIcon(new ImageIcon("Image/Button_T_s2.png"));
		this.b_message.setPressedIcon(new ImageIcon("Image/Button_M_s2.png"));
	}
}

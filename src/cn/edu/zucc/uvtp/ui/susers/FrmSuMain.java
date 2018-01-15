package cn.edu.zucc.uvtp.ui.susers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cn.edu.zucc.uvtp.control.OrdersManager;
import cn.edu.zucc.uvtp.control.SysUsersManager;
import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.control.VehicleBrandManager;
import cn.edu.zucc.uvtp.control.VehicleLineManager;
import cn.edu.zucc.uvtp.control.VehicleTypeManager;
import cn.edu.zucc.uvtp.control.VehiclesManager;
import cn.edu.zucc.uvtp.model.Orders;
import cn.edu.zucc.uvtp.model.SystemUsers;
import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.model.VehicleBrand;
import cn.edu.zucc.uvtp.model.VehicleLine;
import cn.edu.zucc.uvtp.model.VehicleType;
import cn.edu.zucc.uvtp.model.Vehicles;
import cn.edu.zucc.uvtp.ui.diyclass.ImageButton;
import cn.edu.zucc.uvtp.util.BaseException;



public class FrmSuMain extends JFrame implements ActionListener {
	private int a ;
	
	private ImageIcon Button_Close_1 = new  ImageIcon("Image/Button_Close_1.png");
	private ImageIcon Button_Minimize_1 = new  ImageIcon("Image/Button_Minimize_1.png");
	private ImageIcon Button_Search = new ImageIcon("Image/Button_search.png");
	
	private JPanel pu_main = new JPanel();														//顶部容器：欢迎，搜索，缩小，关闭
	private JPanel pu_left = new JPanel();
	private JPanel pu_center = new JPanel();
	private JPanel pu_right = new JPanel();
	private JPanel pd_main = new JPanel();														//底部容器：功能按钮1，显示部分，功能按钮2 
	private JPanel pd_left = new JPanel();
	private JPanel pd_center = new JPanel();
	private JPanel pd_right = new JPanel();
	
	private JPanel pdc_mb = new JPanel();
	private JPanel pdc_su = new JPanel();
	private JPanel pdc_u = new JPanel();
	private JPanel pdc_vc = new JPanel();
	private JPanel pdc_vt = new JPanel();
	private JPanel pdc_vb = new JPanel();
	private JPanel pdc_vl = new JPanel();
	private JPanel pdc_o = new JPanel();
	
	private JLabel l_welcome = new JLabel("  欢迎登录，"+SystemUsers.currentsu.getSuName());										//欢迎登录语句
	private JLabel l_uname = new JLabel();
	
	private JTextField t_search = new JTextField(20); 
	private ImageButton b_minimize = new ImageButton(Button_Minimize_1);							//最小化按钮
	private ImageButton b_close = new ImageButton(Button_Close_1);								//最大化按钮
	private ImageButton b_search = new ImageButton(Button_Search);								//搜索按钮
	
	private ImageButton b_su1 = new ImageButton(new ImageIcon("Image/Button_SU_s1.png"));
	private ImageButton b_u1 = new ImageButton(new ImageIcon("Image/Button_U_s1.png"));
	private ImageButton b_vc1 = new ImageButton(new ImageIcon("Image/Button_VC_s1.png"));
	private ImageButton b_vt1 = new ImageButton(new ImageIcon("Image/Button_VT_s1.png"));
	private ImageButton b_vb1 = new ImageButton(new ImageIcon("Image/Button_VB_s1.png"));
	private ImageButton b_vl1 = new ImageButton(new ImageIcon("Image/Button_VL_s1.png"));
	private ImageButton b_o1 = new ImageButton(new ImageIcon("Image/Button_O_s1.png"));
	private ImageButton b_menu = new ImageButton(new ImageIcon("Image/Button_Menu_1.png")); 
	private JLabel l_kong1 = new JLabel();
	private JLabel l_kong2 = new JLabel(); 
	
	private ImageButton b_add = new ImageButton(new ImageIcon("Image/Button_add.png"));
	private ImageButton b_del = new ImageButton(new ImageIcon("Image/Button_del.png"));
	private ImageButton b_modify = new ImageButton(new ImageIcon("Image/Button_modify.png"));
	private ImageButton b_modifypwd = new  ImageButton(new ImageIcon("Image/Button_modifypwd1.png"));
	private ImageButton b_lock = new ImageButton(new ImageIcon("Image/Button_lock.png"));
	
	JTable datamb = new JTable();
	JTable datasutable = new JTable();
	JTable datautable = new JTable();
	JTable datavctable = new JTable();
	JTable datavttable = new JTable();
	JTable datavbtable = new JTable();
	JTable datavltable = new JTable();
	JTable dataotable = new JTable();
	
	private List<SystemUsers> allsu = new ArrayList<SystemUsers>();
	private List<Users> allu = new ArrayList<Users>();
	private List<Vehicles> allvc = new ArrayList<Vehicles>();
	private List<VehicleType> allvt = new ArrayList<VehicleType>();
	private List<VehicleBrand> allvb = new ArrayList<VehicleBrand>();
	private List<VehicleLine> allvl = new ArrayList<VehicleLine>();
	private List<Orders> allo = new ArrayList<Orders>();
	
	public void reloadsutable(){
		Object sutableTitle[] = SystemUsers.tableTitles;
		Object sutableData[][];
		DefaultTableModel sutablemodel = new DefaultTableModel();
		datasutable.setModel(sutablemodel);
		SystemUsers su = new SystemUsers();
		try {
			allsu=(new SysUsersManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		sutableData = new Object[allsu.size()][SystemUsers.tableTitles.length];
		
		for(int i=0;i<allsu.size();i++){
			for(int j=0;j<SystemUsers.tableTitles.length;j++)
				sutableData[i][j]=allsu.get(i).getCell(j);
		}
		sutablemodel.setDataVector(sutableData,sutableTitle);
		this.datasutable.validate();
		this.datasutable.repaint();
		this.tablebs(datasutable);
		this.a = 1;
	}
	public void reloadutable(){
		Object utableTitle[] = Users.tableTitles;
		Object utableData[][];
		DefaultTableModel utablemodel = new DefaultTableModel();
		datautable.setModel(utablemodel);
		//Users u = new Users();
		try {
			allu=(new UsersManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		utableData = new Object[allu.size()][Users.tableTitles.length];
		
		for(int i=0;i<allu.size();i++){
			for(int j=0;j<Users.tableTitles.length;j++)
				utableData[i][j] = allu.get(i).getCell(j);
		}
		utablemodel.setDataVector(utableData,utableTitle);
		this.datautable.validate();
		this.datautable.repaint();
		this.tablebs(datautable);
		this.a = 2;
	}
	
	public void reloadvctable(){
		Object vctableTitle[] = Vehicles.tableTitles1;
		Object vctableData[][];
		DefaultTableModel vctablemodel = new DefaultTableModel();
		datavctable.setModel(vctablemodel);
		try {
			allvc=(new VehiclesManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		vctableData = new Object[allvc.size()][Vehicles.tableTitles1.length];
		
		for(int i=0;i<allvc.size();i++){
			for(int j=0;j<Vehicles.tableTitles1.length;j++)
				vctableData[i][j] = allvc.get(i).getCell1(j);
		}
		vctablemodel.setDataVector(vctableData,vctableTitle);
		this.datavctable.validate();
		this.datavctable.repaint();
		this.tablebs(datavctable);
		this.a = 3;
	}
	
	public void reloadvttable(){
		Object vttableTitle[] = VehicleType.tableTitles;
		Object vttableData[][];
		DefaultTableModel vttablemodel = new DefaultTableModel();
		datasutable.setModel(vttablemodel);
		VehicleType vt = new VehicleType();
		try {
			allvt=(new VehicleTypeManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		vttableData = new Object[allvt.size()][VehicleType.tableTitles.length];
		
		for(int i=0;i<allvt.size();i++){
			for(int j=0;j<VehicleType.tableTitles.length;j++)
				vttableData[i][j]=allvt.get(i).getCell(j);
		}
		vttablemodel.setDataVector(vttableData,vttableTitle);
		this.datavttable.validate();
		this.datavttable.repaint();
		this.tablebs(datavttable);
		this.a = 4;
	}
	public void reloadvbtable(){
		Object vbtableTitle[] = VehicleBrand.tableTitles;
		Object vbtableData[][];
		DefaultTableModel vbtablemodel = new DefaultTableModel();
		datavbtable.setModel(vbtablemodel);
		VehicleBrand vb = new VehicleBrand();
		try {
			allvb=(new VehicleBrandManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		vbtableData = new Object[allvb.size()][VehicleBrand.tableTitles.length];
		
		for(int i=0;i<allvb.size();i++){
			for(int j=0;j<VehicleBrand.tableTitles.length;j++)
				vbtableData[i][j]=allvb.get(i).getCell(j);
		}
		vbtablemodel.setDataVector(vbtableData,vbtableTitle);
		this.datavbtable.validate();
		this.datavbtable.repaint();
		this.tablebs(datavbtable);
		this.a = 5;
	}
	public void searchvbtable(String str){
		Object vbtableTitle[] = VehicleBrand.tableTitles;
		Object vbtableData[][];
		DefaultTableModel vbtablemodel = new DefaultTableModel();
		datavbtable.setModel(vbtablemodel);
		VehicleBrand vb = new VehicleBrand();
		try {
			allvb=(new VehicleBrandManager()).search(str);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		vbtableData = new Object[allvb.size()][VehicleBrand.tableTitles.length];
		
		for(int i=0;i<allvb.size();i++){
			for(int j=0;j<VehicleBrand.tableTitles.length;j++)
				vbtableData[i][j]=allvb.get(i).getCell(j);
		}
		vbtablemodel.setDataVector(vbtableData,vbtableTitle);
		this.datavbtable.validate();
		this.datavbtable.repaint();
		this.tablebs(datavbtable);
		this.a = 5;
	}
	public void reloadvltable(){
		Object vltableTitle[] = VehicleLine.tableTitles;
		Object vltableData[][];
		DefaultTableModel vltablemodel = new DefaultTableModel();
		datavltable.setModel(vltablemodel);
		VehicleLine vl = new VehicleLine();
		try {
			allvl=(new VehicleLineManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		vltableData = new Object[allvl.size()][VehicleLine.tableTitles.length];
		
		for(int i=0;i<allvl.size();i++){
			for(int j=0;j<VehicleLine.tableTitles.length;j++)
				vltableData[i][j]=allvl.get(i).getCell(j);
		}
		vltablemodel.setDataVector(vltableData,vltableTitle);
		this.datavltable.validate();
		this.datavltable.repaint();
		this.tablebs(datavltable);
		this.a = 6;
	}
	public void reloadotable(){
		Object otableTitle[] = Orders.tableTitles1;
		Object otableData[][];
		DefaultTableModel otablemodel = new DefaultTableModel();
		dataotable.setModel(otablemodel);
		Orders o = new Orders();
		try {
			allo=(new OrdersManager()).loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		otableData = new Object[allo.size()][Orders.tableTitles1.length];
		
		for(int i=0;i<allo.size();i++){
			for(int j=0;j<Orders.tableTitles1.length;j++)
				otableData[i][j]=allo.get(i).getCell1(j);
		}
		otablemodel.setDataVector(otableData,otableTitle);
		this.dataotable.validate();
		this.dataotable.repaint();
		this.tablebs(dataotable);
		this.a = 7;
	}
	
	public void tablebs(JTable table){
		try { 
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){ 
				public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
					setHorizontalAlignment(SwingConstants.CENTER);
					if (row % 2 == 0) 
						setBackground(new Color(255,255,255));
					else if (row % 2 == 1) 
						setBackground(new Color(240,240,240)); 		// 设置偶数行底色 
					return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column); 
		        } 
		   }; 
		   for(int i = 0; i < table.getColumnCount(); i++){ 
			   table.getColumn(table.getColumnName(i)).setCellRenderer(tcr); 
		   } 
		}catch (Exception ex){
			ex.printStackTrace();
		}   
	}
	public FrmSuMain(){
//-------------------------------------------------------------------------
		this.setResizable(false);																	//禁止修改窗口大小
		this.setUndecorated(true);																	//取消顶部栏
		this.setSize(1600, 900);
		
		
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		//鼠标事件:拖拽标题栏
		MouseEventListener mouseListener = new MouseEventListener(this,this.pu_main);
	    this.pu_main.addMouseListener(mouseListener);
	    this.pu_main.addMouseMotionListener(mouseListener);
	    
	    FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
//------------------------pdc模板------------------------------------
	    pdc_mb.setLayout(new BorderLayout());
	    pdc_mb.setPreferredSize(new Dimension(1320,800));
	    pdc_mb.setOpaque(false);
//------------------------表格模板-----------------------------------
	    datamb.setBorder(null);
	    datamb.setRowHeight(30);
	    datamb.setBackground(new Color(255,255,255));
	    datamb.setFont(new Font("微软雅黑",Font.PLAIN,20));
	    
	    JTableHeader th = datamb.getTableHeader();
	    th.setBackground(new Color(0,0,0));
	    th.setForeground(new Color(255,255,255));
	    th.setFont(new Font("微软雅黑",Font.BOLD,24));
	    datamb.setTableHeader(th);
	    datamb.setShowGrid(false);
//------------------------管理员-------------------------------------
	    datasutable = datamb;
	    
	    pdc_su = pdc_mb;
	    JScrollPane jsp = new JScrollPane(this.datasutable);
	    jsp.getViewport().setBackground(new Color(255,255,255));
	    jsp.setBorder(new EmptyBorder(0,0,0,0));
	    pdc_su.add(jsp,BorderLayout.CENTER); 
	    this.reloadsutable();
//------------------------用户---------------------------------------
	    datautable = datamb;
	    pdc_u = pdc_mb;
	    jsp = new JScrollPane(this.datautable);
	    jsp.getViewport().setBackground(new Color(255,255,255));
	    jsp.setBorder(new EmptyBorder(0,0,0,0));
	    pdc_u.add(jsp,BorderLayout.CENTER); 
//------------------------车辆---------------------------------------
	    datavctable = datamb;
	    pdc_vc = pdc_mb;
	    jsp = new JScrollPane(this.datavctable);
	    jsp.getViewport().setBackground(new Color(255,255,255));
	    jsp.setBorder(new EmptyBorder(0,0,0,0));
	    pdc_vc.add(jsp,BorderLayout.CENTER); 
//------------------------车型---------------------------------------
	    datavttable = datamb;
	    pdc_vt = pdc_mb;
	    jsp = new JScrollPane(this.datavttable);
	    jsp.getViewport().setBackground(new Color(255,255,255));
	    jsp.setBorder(new EmptyBorder(0,0,0,0));
	    pdc_vt.add(jsp,BorderLayout.CENTER);
//------------------------品牌---------------------------------------
	    datavbtable = datamb;
	    pdc_vb = pdc_mb;
	    jsp = new JScrollPane(this.datavbtable);
	    jsp.getViewport().setBackground(new Color(255,255,255));
	    jsp.setBorder(new EmptyBorder(0,0,0,0));
	    pdc_vb.add(jsp,BorderLayout.CENTER);
//------------------------车系---------------------------------------
	    datavltable = datamb;
	    pdc_vl = pdc_mb;
	    jsp = new JScrollPane(this.datavltable);
	    jsp.getViewport().setBackground(new Color(255,255,255));
	    jsp.setBorder(new EmptyBorder(0,0,0,0));
	    pdc_vl.add(jsp,BorderLayout.CENTER);
//------------------------挂牌---------------------------------------
	    dataotable = datamb;
	    pdc_o = pdc_mb;
	    jsp = new JScrollPane(this.dataotable);
	    jsp.getViewport().setBackground(new Color(255,255,255));
	    jsp.setBorder(new EmptyBorder(0,0,0,0));
	    pdc_o.add(jsp,BorderLayout.CENTER);
//------------------------标题栏-------------------------------------		
		pu_main.setLayout(new FlowLayout(FlowLayout.LEFT));										//伪标题栏
		pu_main.setBackground(new Color(0 ,191 ,255));												//DeepSkyBlue
		pu_main.setPreferredSize(new Dimension(1600,100));
		
		pu_left.setLayout(new FlowLayout(FlowLayout.LEFT));										//欢迎面板
		pu_left.setOpaque(false);																	//背景透明
		pu_left.setPreferredSize(new Dimension(290,90));				
		pu_left.add(l_welcome);
		
		flowlayout = new FlowLayout(FlowLayout.CENTER);											//搜索栏
		flowlayout.setHgap(0);																		//控件间距
		pu_center.setLayout(flowlayout);		
		pu_center.setPreferredSize(new Dimension(1190,60));
		pu_center.setOpaque(false);																	//背景透明
		pu_center.add(new JLabel(new ImageIcon("Image/Label_search.png")));
		pu_center.add(t_search);
		pu_center.add(b_search);
		
		pu_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pu_right.setPreferredSize(new Dimension(100,90));
		pu_right.setOpaque(false);																	//背景透明
		pu_right.add(b_minimize);
		pu_right.add(b_close);
		
		pu_main.add(pu_left);
		pu_main.add(pu_center);
		pu_main.add(pu_right);
		
//----------------------------功能栏-----------------------------------
		flowlayout = new FlowLayout(FlowLayout.LEFT);
		flowlayout.setHgap(0);
		flowlayout.setVgap(0);
		
		pd_main.setLayout(flowlayout);
		pd_main.setBackground(new Color(255,255,255));
		pd_main.setPreferredSize(new Dimension(1600,800));
		
		pd_left.setLayout(flowlayout);
		pd_left.setBackground(new Color(105,105,105));
		pd_left.setPreferredSize(new Dimension(80,800));
		pd_left.setBorder(null);
		l_kong1.setPreferredSize(new Dimension(80,80));
		pd_left.add(l_kong1);
		pd_left.add(b_su1);	
		pd_left.add(b_u1);	
		pd_left.add(b_vc1);	
		pd_left.add(b_vt1);	
		pd_left.add(b_vb1);	
		pd_left.add(b_vl1);	
		pd_left.add(b_o1);
		l_kong2.setPreferredSize(new Dimension(250,80));
		pd_left.add(l_kong2);
		pd_left.add(b_menu);
		
		pd_center = pdc_su;
		this.reloadsutable();
		
		flowlayout = new FlowLayout(FlowLayout.CENTER);
		flowlayout.setHgap(0);
		flowlayout.setVgap(20);
		pd_right.setLayout(flowlayout);
		pd_right.setPreferredSize(new Dimension(200,800));
		pd_right.add(b_add);
		pd_right.add(b_modify);
		pd_right.add(b_modifypwd);
		pd_right.add(b_del);
		pd_right.add(b_lock);
		
		pd_main.add(pd_left);
		pd_main.add(pd_center);
		pd_main.add(pd_right);
//--------------------------功能按钮----------------------------------
		b_add.setRolloverIcon(new ImageIcon("Image/Button_add2.png"));
		b_add.setPressedIcon(new ImageIcon("Image/Button_add2.png"));
		b_del.setRolloverIcon(new ImageIcon("Image/Button_del2.png"));
		b_del.setPressedIcon(new ImageIcon("Image/Button_del2.png"));
		b_modify.setRolloverIcon(new ImageIcon("Image/Button_modify2.png"));
		b_modify.setPressedIcon(new ImageIcon("Image/Button_modify2.png"));
		b_modifypwd.setRolloverIcon(new ImageIcon("Image/Button_modifypwd2.png"));
		b_modifypwd.setPressedIcon(new ImageIcon("Image/Button_modifypwd2.png"));
		b_lock.setRolloverIcon(new ImageIcon("Image/Button_lock2.png"));
		b_lock.setPressedIcon(new ImageIcon("Image/Button_lock2.png"));
		
		if(this.a == 1){
			this.b_add.setVisible(true);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(true);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
		}
		else if(this.a == 2){
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(true);
		}
		else if(this.a == 3){
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
		}
		else if(this.a == 4){
			this.b_add.setVisible(true);
			this.b_modify.setVisible(true);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
		}
		else if(this.a == 5){
			this.b_add.setVisible(true);
			this.b_modify.setVisible(true);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
		}
		else if(this.a == 6){
			this.b_add.setVisible(true);
			this.b_modify.setVisible(true);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
		}
		else if(this.a == 7){
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
		}
		
		b_add.addActionListener(this);
		b_del.addActionListener(this);
		b_modify.addActionListener(this);
		b_modifypwd.addActionListener(this);
		b_lock.addActionListener(this);
//--------------------------布局--------------------------------------
		this.getContentPane().add(pu_main,new BorderLayout().NORTH);
		this.getContentPane().add(pd_main,new BorderLayout().SOUTH);
		this.setVisible(true);
//--------------------------关闭、最小化、搜索按钮------------------------------		
		//b_minimize.setSelectedIcon(new ImageIcon("Image/Button_Minimize_2.png"));
		b_minimize.setPressedIcon(new ImageIcon("Image/Button_Minimize_2.png"));
		b_minimize.setRolloverIcon(new ImageIcon("Image/Button_Minimize_2.png"));
		//b_close.setSelectedIcon(new ImageIcon("Image/Button_Close_2.png"));
		b_close.setPressedIcon(new ImageIcon("Image/Button_Close_2.png"));
		b_close.setRolloverIcon(new ImageIcon("Image/Button_Close_2.png"));
		b_search.setRolloverIcon(new ImageIcon("Image/Button_Search2.png"));
		b_search.setPressedIcon(new ImageIcon("Image/Button_Search2.png"));
		b_search.addActionListener(this);
		b_minimize.addActionListener(this);
		b_close.addActionListener(this);
//--------------------------欢迎面板------------------------------------
		Font font = new Font("等线",Font.BOLD,24);
		l_welcome.setFont(font);
		l_welcome.setForeground(new Color(255,255,255));
//--------------------------搜索栏-------------------------------------
		font = new Font("等线",Font.PLAIN,28); 
		t_search.setFont(font);
		t_search.setPreferredSize(new Dimension(t_search.getWidth(),41));
		t_search.setForeground(new Color(255,255,255));
		t_search.setBackground(new Color(67,110,238));
		t_search.setCaretColor(new Color(255,255,255));
		t_search.setBorder(null);
//--------------------------Menu------------------------------------
		b_menu.addActionListener(this);
		b_su1.addActionListener(this);
		b_u1.addActionListener(this);
		b_vc1.addActionListener(this);
		b_vt1.addActionListener(this);
		b_vb1.addActionListener(this);
		b_vl1.addActionListener(this);
		b_o1.addActionListener(this);		
		
		b_menu.setPressedIcon(new ImageIcon("Image/Button_Menu_2.png"));
		b_menu.setRolloverIcon(new ImageIcon("Image/Button_Menu_2.png"));
		
		b_su1.setRolloverIcon(new ImageIcon("Image/Button_SU_l2.png"));
		b_u1.setRolloverIcon(new ImageIcon("Image/Button_U_l2.png"));
		b_vc1.setRolloverIcon(new ImageIcon("Image/Button_VC_l2.png"));
		b_vt1.setRolloverIcon(new ImageIcon("Image/Button_VT_l2.png"));
		b_vb1.setRolloverIcon(new ImageIcon("Image/Button_VB_l2.png"));
		b_vl1.setRolloverIcon(new ImageIcon("Image/Button_VL_l2.png"));
		b_o1.setRolloverIcon(new ImageIcon("Image/Button_O_l2.png"));
		
		b_su1.setPressedIcon(new ImageIcon("Image/Button_SU_l2.png"));
		b_u1.setPressedIcon(new ImageIcon("Image/Button_U_l2.png"));
		b_vc1.setPressedIcon(new ImageIcon("Image/Button_VC_l2.png"));
		b_vt1.setPressedIcon(new ImageIcon("Image/Button_VT_l2.png"));
		b_vb1.setPressedIcon(new ImageIcon("Image/Button_VB_l2.png"));
		b_vl1.setPressedIcon(new ImageIcon("Image/Button_VL_l2.png"));
		b_o1.setPressedIcon(new ImageIcon("Image/Button_O_l2.png"));
//------------------------------------------------------------------
	} 
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b_minimize){
			this.setExtendedState(JFrame.ICONIFIED);
			
		}
		else if(e.getSource() == this.b_close){
			System.exit(0);
		}
		else if(e.getSource() == this.b_menu){
			if(this.pd_left.getWidth() == 250){
				
				this.b_su1.setIcon(new ImageIcon("Image/Button_SU_s1.png"));
				this.b_u1.setIcon(new ImageIcon("Image/Button_U_s1.png"));
				this.b_vc1.setIcon(new ImageIcon("Image/Button_VC_s1.png"));
				this.b_vt1.setIcon(new ImageIcon("Image/Button_VT_s1.png"));
				this.b_vb1.setIcon(new ImageIcon("Image/Button_VB_s1.png"));
				this.b_vl1.setIcon(new ImageIcon("Image/Button_VL_s1.png"));
				this.b_o1.setIcon(new ImageIcon("Image/Button_O_s1.png"));
				this.pd_left.setPreferredSize(new Dimension(80,800));
				this.l_kong1.setPreferredSize(new Dimension(80,80));
				this.l_kong2.setPreferredSize(new Dimension(80,80));
				this.pd_center.setPreferredSize(new Dimension(1320,800));
				
				this.b_su1.setRolloverIcon(new ImageIcon("Image/Button_SU_s2.png"));
				this.b_u1.setRolloverIcon(new ImageIcon("Image/Button_U_s2.png"));
				this.b_vc1.setRolloverIcon(new ImageIcon("Image/Button_VC_s2.png"));
				this.b_vt1.setRolloverIcon(new ImageIcon("Image/Button_VT_s2.png"));
				this.b_vb1.setRolloverIcon(new ImageIcon("Image/Button_VB_s2.png"));
				this.b_vl1.setRolloverIcon(new ImageIcon("Image/Button_VL_s2.png"));
				this.b_o1.setRolloverIcon(new ImageIcon("Image/Button_O_s2.png"));

				this.b_su1.setPressedIcon(new ImageIcon("Image/Button_SU_s2.png"));
				this.b_u1.setPressedIcon(new ImageIcon("Image/Button_U_s2.png"));
				this.b_vc1.setPressedIcon(new ImageIcon("Image/Button_VC_s2.png"));
				this.b_vt1.setPressedIcon(new ImageIcon("Image/Button_VT_s2.png"));
				this.b_vb1.setPressedIcon(new ImageIcon("Image/Button_VB_s2.png"));
				this.b_vl1.setPressedIcon(new ImageIcon("Image/Button_VL_s2.png"));
				this.b_o1.setPressedIcon(new ImageIcon("Image/Button_O_s2.png"));
			}
			else{
				this.b_su1.setIcon(new ImageIcon("Image/Button_SU_l1.png"));
				this.b_u1.setIcon(new ImageIcon("Image/Button_U_l1.png"));
				this.b_vc1.setIcon(new ImageIcon("Image/Button_VC_l1.png"));
				this.b_vt1.setIcon(new ImageIcon("Image/Button_VT_l1.png"));
				this.b_vb1.setIcon(new ImageIcon("Image/Button_VB_l1.png"));
				this.b_vl1.setIcon(new ImageIcon("Image/Button_VL_l1.png"));
				this.b_o1.setIcon(new ImageIcon("Image/Button_O_l1.png"));
				this.pd_left.setPreferredSize(new Dimension(250,800));
				this.l_kong1.setPreferredSize(new Dimension(250,80));
				this.l_kong2.setPreferredSize(new Dimension(250,80));
				this.pd_center.setPreferredSize(new Dimension(1150,800));
				
				this.b_su1.setRolloverIcon(new ImageIcon("Image/Button_SU_l2.png"));
				this.b_u1.setRolloverIcon(new ImageIcon("Image/Button_U_l2.png"));
				this.b_vc1.setRolloverIcon(new ImageIcon("Image/Button_VC_l2.png"));
				this.b_vt1.setRolloverIcon(new ImageIcon("Image/Button_VT_l2.png"));
				this.b_vb1.setRolloverIcon(new ImageIcon("Image/Button_VB_l2.png"));
				this.b_vl1.setRolloverIcon(new ImageIcon("Image/Button_VL_l2.png"));
				this.b_o1.setRolloverIcon(new ImageIcon("Image/Button_O_l2.png"));
				
				this.b_su1.setPressedIcon(new ImageIcon("Image/Button_SU_l2.png"));
				this.b_u1.setPressedIcon(new ImageIcon("Image/Button_U_l2.png"));
				this.b_vc1.setPressedIcon(new ImageIcon("Image/Button_VC_l2.png"));
				this.b_vt1.setPressedIcon(new ImageIcon("Image/Button_VT_l2.png"));
				this.b_vb1.setPressedIcon(new ImageIcon("Image/Button_VB_l2.png"));
				this.b_vl1.setPressedIcon(new ImageIcon("Image/Button_VL_l2.png"));
				this.b_o1.setPressedIcon(new ImageIcon("Image/Button_O_l2.png"));
			}
		}
		else if(e.getSource() == this.b_add){
			if(a == 1){
				FrmSuAdd dlg = new FrmSuAdd(this,"",true);
				dlg.setVisible(true);
				this.reloadsutable();
			}
			else if(a == 4){
				FrmVtAdd dlg = new FrmVtAdd(this,"",true);
				dlg.setVisible(true);
				this.reloadvttable();
			}
			else if(a == 5){
				FrmVbAdd dlg = new FrmVbAdd(this,"",true);
				dlg.setVisible(true);
				this.reloadvbtable();
			}
			else if(a == 6){
				FrmVlAdd dlg = new FrmVlAdd(this,"",true);
				dlg.setVisible(true);
				this.reloadvltable();
			}
		}
		else if(e.getSource() == this.b_modify){
			if(a == 4){
				FrmVtModify dlg = new FrmVtModify(this,"",true,this.allvt.get(this.datavttable.getSelectedRow()));
				dlg.setVisible(true);
				this.reloadvttable();
			}
			else if(a == 5){
				FrmVbModify dlg = new FrmVbModify(this,"",true,this.allvb.get(this.datavbtable.getSelectedRow()));
				dlg.setVisible(true);
				this.reloadvbtable();
			}
			else if(a == 6){
				FrmVlModify dlg = new FrmVlModify(this,"",true,this.allvl.get(this.datavltable.getSelectedRow()));
				dlg.setVisible(true);
				this.reloadvltable();
			}
		}
		else if(e.getSource() == this.b_modifypwd){
			FrmSuModifypwd dlg = new FrmSuModifypwd(this,"",true,this.allsu.get(this.datasutable.getSelectedRow()).getSuName());
			dlg.setVisible(true);
			this.reloadsutable();
		}
		else if(e.getSource() == this.b_lock){
			try {
				(new UsersManager()).lock(this.allu.get(this.datautable.getSelectedRow()).getUNum());
				this.reloadutable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == this.b_search){
			if(this.a == 5){
				String str = this.t_search.getText();
				this.searchvbtable(str);
			}
		}
		else if(e.getSource() == this.b_del){
			if(a == 1){
				try {
					(new SysUsersManager()).del(this.allsu.get(this.datasutable.getSelectedRow()).getSuNum());
					this.reloadsutable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			else if(a == 2){
				try {
					(new UsersManager()).del(this.allu.get(this.datautable.getSelectedRow()).getUNum());
					this.reloadutable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			else if(a == 3){
				try {
					(new VehiclesManager()).del(this.allvc.get(this.datavctable.getSelectedRow()).getVcNum());
					this.reloadvctable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			else if(a == 4){
				try {
					new VehicleTypeManager().del(this.allvt.get(this.datavttable.getSelectedRow()).getVtNum());
					this.reloadvttable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			else if(a == 5){
				try {
					new VehicleBrandManager().del(this.allvb.get(this.datavbtable.getSelectedRow()).getVbNum());
					this.reloadvbtable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}	
			}
			else if(a == 6){
				try {
					new VehicleLineManager().del(this.allvl.get(this.datavltable.getSelectedRow()).getVlNum());
					this.reloadvltable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}	
			}
			else if(a == 7){
				try {
					new OrdersManager().del(this.allo.get(this.dataotable.getSelectedRow()).getONum());
					this.reloadotable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		}
		else if(e.getSource() == this.b_su1){
			this.a = 1;
			this.b_add.setVisible(true);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(true);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
			this.pd_center = this.pdc_su;
			this.reloadsutable();
		}
		else if(e.getSource() == this.b_u1){
			this.a = 2;
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(true);
			this.reloadutable();
		}
		else if(e.getSource() == this.b_vc1){
			this.a = 3;
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
			this.reloadvctable();
		}
		else if(e.getSource() == this.b_vt1){
			this.a = 4;
			this.b_add.setVisible(true);
			this.b_modify.setVisible(true);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
			this.pd_center = this.pdc_vt;
			this.reloadvttable();
		}
		else if(e.getSource() == this.b_vb1){
			this.a = 5;
			this.b_add.setVisible(true);
			this.b_modify.setVisible(true);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
			this.pd_center = this.pdc_vb;
			this.reloadvbtable();
		}
		else if(e.getSource() == this.b_vl1){
			this.a = 6;
			this.b_add.setVisible(true);
			this.b_modify.setVisible(true);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
			this.pd_center = this.pdc_vl;
			this.reloadvltable();
		}
		else if(e.getSource() == this.b_o1){
			this.a = 7;
			this.b_add.setVisible(false);
			this.b_modify.setVisible(false);
			this.b_modifypwd.setVisible(false);
			this.b_del.setVisible(true);
			this.b_lock.setVisible(false);
			this.reloadotable();
		}
	}
	
	public static void main(String[] args) {
		new FrmSuMain();
	}

}
class MouseEventListener implements MouseInputListener {
	Point origin;
	 //鼠标拖拽想要移动的目标组件
	JPanel p = new JPanel();
	JFrame frm;
	public MouseEventListener(JFrame Frm,JPanel p) {
		this.p = p;
	    this.frm = Frm;
	    origin = new Point();
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {}
 
    /**
    * 记录鼠标按下时的点
    */
    @Override
    public void mousePressed(MouseEvent e) {
    	origin.x = e.getX(); 
        origin.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
 
     /**
     * 鼠标移进标题栏时，设置鼠标图标为移动图标
     */
     @Override
     public void mouseEntered(MouseEvent e) {
       //this.p.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
     }
     
     /**
     * 鼠标移出标题栏时，设置鼠标图标为默认指针
     */
     @Override
     public void mouseExited(MouseEvent e) {
       //this.p.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
     }
 
     /**
     * 鼠标在标题栏拖拽时，设置窗口的坐标位置
     * 窗口新的坐标位置 = 移动前坐标位置+（鼠标指针当前坐标-鼠标按下时指针的位置）
     */
     @Override
     public void mouseDragged(MouseEvent e) {
    	Point f = this.frm.getLocation();
    	Point p = this.p.getLocation();
    	this.frm.setLocation(
    			f.x + (e.getX() - origin.x), 
    			f.y + (e.getY() - origin.y)); 
     }
 
     @Override
     public void mouseMoved(MouseEvent e) {}
}
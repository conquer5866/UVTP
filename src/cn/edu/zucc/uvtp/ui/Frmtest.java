package cn.edu.zucc.uvtp.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Frmtest extends JFrame implements ActionListener{
	public String[] slist = {"J","List"};
	public String[] sComboBox = {"Combo","Box"};
	
	private JLabel lb = new JLabel("文本");					//文本
	private JButton btn = new JButton("按钮");				//按钮
	private JButton btn2 = new JButton("按钮2");
	//private JColorChooser = new JColorChooser();		//颜色选择
	private JCheckBox cBox = new JCheckBox("CheckBox"); 			//图形化，on 或 off
	private JRadioButton rb = new JRadioButton("RadioButton");		//同上
	private JList list = new JList(slist);					//可滚动文本项列表
	private JComboBox cb =new JComboBox(sComboBox);				//显示菜单选择
	private JTextField tf = new JTextField(20);			//单行文本输入框
	private JPasswordField pf = new JPasswordField(20);	//密码输入框
	private JTextArea ta = new JTextArea();				//多行文本输入框
	private ImageIcon m = new ImageIcon("D:/图片/Saved Pictures/yongshi.png");				//图标界面
	private JScrollBar sb = new JScrollBar();			//滚动条
	private JOptionPane op = new JOptionPane();			//提示输入值的标准信息
	private JFileChooser fc = new JFileChooser();		//文件选择
	private JProgressBar pb = new JProgressBar();		//任务百分比进度
	private JSlider slider = new JSlider(JSlider.HORIZONTAL);				//旋转图形化选择值
	private JSpinner pinner = new JSpinner();			//有序列表选择
	private JPanel p2 =new JPanel();
	private JPanel p = new JPanel();

	public Frmtest(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		//this.setSize(500, 600);
		//this.setResizable(false);		//禁止修改窗口大小
		//this.setUndecorated(true);	//取消顶部栏
		//frame.setBorder(null);
		//this.setTitle("Test");
		//btn2.setOpaque(false);
		//btn2.setContentAreaFilled(false);
		btn2.setVisible(true);
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(lb);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.add(btn);
		p2.add(btn2);
		p.add(p2);
		p.add(cBox);
		p.add(rb);
		p.add(list);
		p.add(cb);
		p.add(tf);
		p.add(pf);
		p.add(ta);
		p.add(new JLabel(m));
		p.add(sb);
		p.add(op);
		p.add(fc);
		p.add(pb);
		slider.setMaximum(50);
		slider.setMinimum(10);
		slider.setValue(15);
		slider.setExtent(30);
		p.add(slider);
		p.add(pinner);
		this.btn.addActionListener(this);
		this.getContentPane().add(p,BorderLayout.NORTH);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btn){
			//this.btn2.setOpaque(false);
			//this.btn2.setContentAreaFilled(false);
			if(this.btn2.isVisible() ? false : true)
				this.btn2.setVisible(true);
			else
				this.btn2.setVisible(false);
			//this.pack();
		}
	}
}

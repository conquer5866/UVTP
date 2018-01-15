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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import cn.edu.zucc.uvtp.control.SysUsersManager;
import cn.edu.zucc.uvtp.control.UsersManager;
import cn.edu.zucc.uvtp.ui.diyclass.ImageButton;
import cn.edu.zucc.uvtp.ui.susers.FrmSuMain;
import cn.edu.zucc.uvtp.util.BaseException;

public class FrmLogin extends JFrame implements ActionListener{
	private int a = 0;
	private JPanel p_all = new JPanel();
	private JPanel p_center = new JPanel();
	private JPanel p_down = new JPanel();
	
	private JPanel pt_uname = new JPanel();
	private JPanel pt_pwd = new JPanel();
	private JTextField t_uname = new JTextField(11);
	private JPasswordField t_pwd = new JPasswordField(11);
	
	private ImageButton b_signin = new ImageButton(new ImageIcon("Image/Button_signin.png"));
	private ImageButton b_register = new ImageButton(new ImageIcon("Image/Button_register.png"));
	private ImageButton  b_quit = new ImageButton(new ImageIcon("Image/Button_quit.png"));
	private JLabel l_or = new JLabel();
	
	public FrmLogin(){
		this.setResizable(false);																	//禁止修改窗口大小
		this.setUndecorated(true);																	//取消顶部栏
		this.setSize(600, 570);
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		//-----------------------------------------------------------------

		Font font = new Font("等线",Font.PLAIN,28);
		t_uname.setFont(font);
		t_uname.setPreferredSize(new Dimension(224,40));
		t_uname.setForeground(new Color(105,105,105));
		t_uname.setBackground(new Color(28,28,28));
		t_uname.setCaretColor(new Color(105,105,105));
		t_uname.setBorder(null);
		
		t_pwd.setFont(font);
		t_pwd.setPreferredSize(new Dimension(224,40));
		t_pwd.setForeground(new Color(200,200,200));
		t_pwd.setBackground(new Color(28,28,28));
		t_pwd.setCaretColor(new Color(105,105,105));
		t_pwd.setBorder(null);
		
		
		
		//-------------------------------------------------------------------
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		p_all.setLayout(flowLayout);
		p_all.setPreferredSize(new Dimension(600,300));
		p_all.setBackground(new Color(54,54,54));
		p_all.add(new JLabel(new ImageIcon("Image/Logo.png")));
		//-------------------------------------------------------------------
		flowLayout = new FlowLayout(FlowLayout.CENTER);
		flowLayout.setHgap(0);
		flowLayout.setVgap(5);
		p_center.setLayout(flowLayout);
		p_center.setPreferredSize(new Dimension(600,120));
		p_center.setBackground(new Color(54,54,54));
		
		flowLayout = new FlowLayout(FlowLayout.CENTER);
		flowLayout.setHgap(0);
		pt_uname.setLayout(flowLayout);
		pt_uname.setPreferredSize(new Dimension(600,45));
		pt_uname.setOpaque(false);
		pt_uname.add(new JLabel(new ImageIcon("Image/l_uname.png")));
		pt_uname.add(t_uname);
		
		flowLayout = new FlowLayout(FlowLayout.CENTER);
		flowLayout.setHgap(0);
		pt_pwd.setLayout(flowLayout);
		pt_pwd.setPreferredSize(new Dimension(600,45));
		pt_pwd.setOpaque(false);
		pt_pwd.add(new JLabel(new ImageIcon("Image/l_pwd.png")));
		pt_pwd.add(t_pwd);

		p_center.add(pt_uname);
		p_center.add(pt_pwd);
		
		flowLayout = new FlowLayout(FlowLayout.CENTER);
		flowLayout.setHgap(0);
		flowLayout.setVgap(15);
		p_down.setLayout(flowLayout);
		p_down.setPreferredSize(new Dimension(600,150));
		p_down.setBackground(new Color(54,54,54));
		
		font = new Font("微软雅黑",font.BOLD,24);
		l_or.setText("OR");
		l_or.setFont(font);
		l_or.setPreferredSize(new Dimension(40,40));
		l_or.setOpaque(false);
		l_or.setForeground(new Color(200,200,200));
		p_down.add(b_signin);
		p_down.add(l_or);
		p_down.add(b_register);
		p_down.add(b_quit);
		
		b_signin.addActionListener(this);
		b_register.addActionListener(this);
		b_quit.addActionListener(this);
		
		MouseEventListener mouseListener = new MouseEventListener(this);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		
		this.getContentPane().add(p_all,new BorderLayout().NORTH);
		this.getContentPane().add(p_center,new BorderLayout().CENTER);
		this.getContentPane().add(p_down,new BorderLayout().SOUTH);
		this.setVisible(true);
		
		b_signin.setRolloverIcon(new ImageIcon("Image/Button_signin2.png"));
		b_signin.setPressedIcon(new ImageIcon("Image/Button_signin2.png"));
		b_register.setRolloverIcon(new ImageIcon("Image/Button_register2.png"));
		b_register.setPressedIcon(new ImageIcon("Image/Button_register2.png"));
		b_quit.setRolloverIcon(new ImageIcon("Image/Button_quit2.png"));
		b_quit.setPressedIcon(new ImageIcon("Image/Button_quit2.png"));
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.b_quit){
			System.exit(0);
		}
		else if(e.getSource() == this.b_signin){
			String uname = this.t_uname.getText();
			String pwd = new String(this.t_pwd.getPassword());
			if(uname.length() == 4){
				try {
					(new SysUsersManager()).login(uname, pwd);
					new FrmSuMain();
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
			else{
				try {
					if(this.a >= 3){
						(new UsersManager()).lock((new UsersManager()).getUnum(uname));
					}
					(new UsersManager()).login(uname, pwd);
					new FrmUMain();
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					this.a = this.a + 1;
					e1.printStackTrace();
					return ;
				}
			}
		}
		else if(e.getSource() == b_register){
			FrmURegister dlg = new FrmURegister(this,"",true);
			this.setVisible(false);
			dlg.setVisible(true);
		}
	}
}
class MouseEventListener implements MouseInputListener {
	Point origin;
	 //鼠标拖拽想要移动的目标组件
	JFrame frm;
	public MouseEventListener(JFrame Frm) {
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
    	this.frm.setLocation(
    			f.x + (e.getX() - origin.x), 
    			f.y + (e.getY() - origin.y));
     }
 
     @Override
     public void mouseMoved(MouseEvent e) {}
}
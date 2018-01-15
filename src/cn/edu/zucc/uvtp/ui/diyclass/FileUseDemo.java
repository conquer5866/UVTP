package cn.edu.zucc.uvtp.ui.diyclass;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import cn.edu.zucc.uvtp.model.Users;
import cn.edu.zucc.uvtp.ui.users.FrmUMain;

//目录和文件的创建、删除和更名

public class FileUseDemo extends JFrame {

	String fileName = "";

	public FileUseDemo() {

	}

	public void DeleteFileAction(String filestr) {
		File sfile = new File(filestr); // 源文件
		try {
			sfile.delete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void CopyFile(Frame Frm) {
		JFileChooser fileChooser = new JFileChooser(); // 实例化文件选择器
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // 设置文件选择模式,此处为文件和目录均可
		if (fileChooser.showOpenDialog(Frm) == JFileChooser.APPROVE_OPTION) { // 弹出文件选择器,并判断是否点击了打开按钮
			String fileName = fileChooser.getSelectedFile().getAbsolutePath(); // 得到选择文件或目录的绝对路径
			try {
				FileInputStream input = new FileInputStream(fileName);
				FileOutputStream output = new FileOutputStream("Image/User/" + Users.currentu.getUNum() + ".png");
				int in = input.read();
				while (in != -1) {
					output.write(in);
					in = input.read();
				}
			} catch (IOException e1) {
				System.out.println(e1.toString());
			}
		}
	}
}
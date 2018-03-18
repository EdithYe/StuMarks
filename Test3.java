package ex15;

//包含主函数

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import ex15.StuModel;
import ex15.StuAddDiag;

public class Test3 extends JFrame implements ActionListener {
private static final long serialVersionUID = 1L;
//定义一些控件
 JPanel jp1,jp2,jp3,jp4;
 JLabel jl1,jl2;
 JMenuItem jb1,jb2,jb3,jb4;
 static JTable jt;
 JScrollPane jsp;
 JMenuBar menubar;
 JButton j1,j2,j3,j4;
 
 StuModel sm; 

 //定义连接数据库的变量
 Statement stat = null;
 PreparedStatement ps;
 Connection ct = null;
 ResultSet rs = null;
 
 public static void main(String[] args){
  new Test3();
 }
 
 //构造函数
 public Test3(){
	 
	 super("学生成绩管理系统");
	 JMenuBar menuBar = new JMenuBar();
     JMenu file = new JMenu("菜单");
           
     jb1 = new JMenuItem("查询");
     jb1.addActionListener(this);   
     jp1 = new JPanel();  
       
     jb2 = new JMenuItem("录入");
     jb2.addActionListener(this);
     jp2 = new JPanel(); 
     
     jb3 = new JMenuItem("修改");
     jb3.addActionListener(this);
     jp3 = new JPanel(); 
     
     jb4 = new JMenuItem("删除");
     jb4.addActionListener(this);
     jp4 = new JPanel();  
     
     file.add(jb1);
     // 设置菜单分隔符
     file.addSeparator();
     file.add(jb2);
     file.addSeparator();
     file.add(jb3);
     file.addSeparator();
     file.add(jb4);

     menuBar.add(file);
     
     // 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
     setJMenuBar(menuBar);
  
  //创建模型对象
  sm = new StuModel();

  //初始化
  jt = new JTable(sm);  
  jsp = new JScrollPane(jt);
  
  //将jsp放入到jframe中
  this.add(jsp);
  //this.add(jp1,"North");
  this.setSize(600, 400);
  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  this.setVisible(true);
 
 }
 public void actionPerformed(ActionEvent arg0) {
  //判断是哪个子菜单被点击
	 if(arg0.getSource() == jb1){	     
		  System.out.println("查询...");
		  StuFindDiag sf=new StuFindDiag(this,"查询成绩",true);
		  //构建一个数据模型类，并更新
		  sm = new StuModel();
		  //更新jtable
		   jt.setModel(sm);
   }
  
  //添加界面
if(arg0.getSource() == jb2){
	 System.out.println("录入...");	
	  StuAddDiag sa=new StuAddDiag(this, "录入成绩", true);
	  //构建一个数据模型类，并更新
	  sm = new StuModel();
	  //更新jtable
	   jt.setModel(sm);
	  }
  
	 
	 //修改界面
 if(arg0.getSource() == jb3){	     
	  System.out.println("修改...");	
	  StuUpDiag su=new StuUpDiag(this, "修改成绩", true);
	  //构建一个数据模型类，并更新
	  sm = new StuModel();
	  //更新jtable
	   jt.setModel(sm);
	   }
	 
  else if(arg0.getSource() == jb4){
	  System.out.println("修改...");	
	  StuDeleteDiag sd=new StuDeleteDiag(this, "删除成绩", true);
	  //构建一个数据模型类，并更新
	  sm = new StuModel();
	  //更新jtable
	   jt.setModel(sm);
  } 
 }}


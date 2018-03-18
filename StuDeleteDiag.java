package ex15;
//删除
import java.awt.event.ActionListener;

import javax.swing.*;
import ex15.StuModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
public class StuDeleteDiag extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	//定义我需要的swing组件
	 JLabel jl1;
	 JTextField jf1;
	 JPanel jp1,jp2,jp3;
	 JButton jb1,jb2;
	 StuModel sm;
	 //owner代表父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
	 public StuDeleteDiag(Frame owner,String title, boolean modal){
	  //调用父类方法
	  super(owner,title,modal);
	  
	  jl1 = new JLabel("输入需要删除成绩的学生学号");  
	  
	  jf1 = new JTextField(10);
	  
	  jb1 = new JButton("删除");
	  jb1.addActionListener(this);
	  jb2 = new JButton("取消");
	  jb2.addActionListener(this);
	  
	  jp1 = new JPanel();
	  jp2 = new JPanel();
	  jp3 = new JPanel();
	  
	  //设置布局
	  jp1.setLayout(new GridLayout(6,1));
	  jp2.setLayout(new GridLayout(6,1));
	  
	  jp3.add(jb1);
	  jp3.add(jb2);
	  
	  jp1.add(jl1);
	  
	  jp2.add(jf1);
	  
	  this.add(jp1, BorderLayout.WEST);
	  this.add(jp2, BorderLayout.CENTER);
	  this.add(jp3, BorderLayout.SOUTH);
	  
	  this.setSize(300,200);
	  this.setVisible(true);
	 }

	@Override
	 public void actionPerformed(ActionEvent e) {
	  // TODO Auto-generated method stub
	  if(e.getSource() == jb1){
	   try{
	    //1.加载驱动
	    Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("加载成功");
	    //2.连接数据库
	    //定义几个常量
	    String url = "jdbc:mysql://localhost:3306/test_database";
	    String user = "root";
	    String passwd = "134679";
	    Connection conn = DriverManager.getConnection(url, user,passwd);
		  Statement state = conn.createStatement();//用于向数据库传递SQL语句对象
		  
		  String id = this.jf1.getText().trim();
		  String sqll = "select * from stu where stuID= '"+ id +"'" ;//查询对应学号学生成绩	  
		  ResultSet rs = state.executeQuery(sqll);		  
		  if(!rs.next()){//若不存在该学号则出现提示		 			  	 
				  JOptionPane.showMessageDialog(this, "该学号不存在，请重新输入");
				  return ;			 
		  }  	
		  else{
		  String sql = "update stu set stuMarks=null where stuID="+id;//更新数据库的sql语句，将成绩置空	  	 
		  state.executeUpdate(sql);}
		  conn.close();
		  
		 }catch (ClassNotFoundException ea) {
		     ea.printStackTrace();
	} 		       
	 catch (SQLException eb) {
		eb.printStackTrace();
	}				 
	}	
	else if(e.getSource() == jb2){//如果点击取消按钮
	this.dispose();//释放资源，关闭
	}
	}
}

package ex15;

//录入
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class StuAddDiag extends JDialog implements ActionListener {
private static final long serialVersionUID = 1L;
	//定义我需要的swing组件
JLabel jl1,jl2;
JTextField jf1,jf2;
JPanel jp1,jp2,jp3;
JButton jb1,jb2;

//owner代表父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
public StuAddDiag(Frame owner,String title, boolean modal){
//调用父类方法
	super(owner,title,modal);

	 jl1 = new JLabel("学号");  
	  jl2 = new JLabel("成绩");
	  
	  jf1 = new JTextField(10);
	  jf2 = new JTextField(10);
	  
	  jb1 = new JButton("录入");
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
	  jp1.add(jl2);
	  
	  jp2.add(jf1);
	  jp2.add(jf2);
	  
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
  //与编译语句对象
 
  String id = this.jf1.getText().trim();
  
  String sqll = "select * from stu where stuID= '"+ id +"'" ;//查询对应学号学生成绩
  ResultSet rs = state.executeQuery(sqll);
  
  if(!rs.next()){//若学号不存在
	  JOptionPane.showMessageDialog(this, "该学号不存在，请重新输入");
	  return;
  }
  else{
	  rs.previous();
  }
  
  while(rs.next()){
	  int num=rs.getInt(7);//获取成绩
	  if(num!=0){//若已经有成绩则出现提示
		  JOptionPane.showMessageDialog(this, "已经存在成绩");
		  return ;
	  }
  }
   
  String marks=this.jf2.getText().trim();//获取输入框的值	  	  
  String sql = "update stu SET stuMarks='"+marks+"'where stuID="+id;//更新数据库的sql语句	  
  state.executeUpdate(sql);
  
  rs.close();
  state.close();
 }catch(Exception arg1){
  arg1.printStackTrace();
 }
 
}
else if(e.getSource() == jb2){//如果点击取消按钮
	this.dispose();//关闭学生对话框
}
}

}

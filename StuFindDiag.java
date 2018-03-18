package ex15;
//查询
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StuFindDiag extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel jl1,jl2,jl3;
	JTextField jf1;
	JButton jb1,jb2;
	JPanel jp1,jp2;
	//StuModel sm;
	 JTable jt;
//owner代表父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
	public StuFindDiag(Frame owner,String title, boolean modal){
		//调用父类方法
		super(owner,title,modal);
		jl1 = new JLabel("请输入学生ID号");
		jf1 = new JTextField(6);
		jl2 = new JLabel("学生成绩是：");
		jl3 = new JLabel();
		jb1 = new JButton("查找");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.addActionListener(this);
		
		jp1 = new JPanel();
		jp2=new JPanel();
		jp1.setLayout(new GridLayout(6,1));
		jp1.add(jl1);
		jp1.add(jf1);
		jp1.add(jl2);
		jp1.add(jl3);
		
		jp2.add(jb1);
		jp2.add(jb2);
		
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.CENTER);
		
		this.setSize(300,200);
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		
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
			  
			  //在数据库中查找对应id号的学生
			  String id = this.jf1.getText().trim();
			  String sql = "select * from stu where stuID= '"+ id +"'";
			
			  Connection conn = DriverManager.getConnection(url, user,passwd);
			  Statement state = conn.createStatement();
			  ResultSet rs = state.executeQuery(sql);
			  
			  if(!rs.next()){//若不存在该学号则出现提示		 			  	 
				  JOptionPane.showMessageDialog(this, "该学号不存在，请重新输入");
				  return ;			 
			  }
			  else{
				  rs.previous();
			  }
			  while(rs.next()){
				int marks=rs.getInt(7);//从第七列的中选择对应的成绩
//				System.out.println("stumarks="+marks);
				String mark=String.valueOf(marks);//将int转换为string
				jl3.setText(mark);//将成绩输出在jl3
			  }
			  
			  
			  rs.close();		  
			  state.close();
			 
			  
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

package ex15;
//ɾ��
import java.awt.event.ActionListener;

import javax.swing.*;
import ex15.StuModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
public class StuDeleteDiag extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	//��������Ҫ��swing���
	 JLabel jl1;
	 JTextField jf1;
	 JPanel jp1,jp2,jp3;
	 JButton jb1,jb2;
	 StuModel sm;
	 //owner��������,title�Ǵ��ڵ�����,modalָ����ģʽ����()���߷�ģʽ����
	 public StuDeleteDiag(Frame owner,String title, boolean modal){
	  //���ø��෽��
	  super(owner,title,modal);
	  
	  jl1 = new JLabel("������Ҫɾ���ɼ���ѧ��ѧ��");  
	  
	  jf1 = new JTextField(10);
	  
	  jb1 = new JButton("ɾ��");
	  jb1.addActionListener(this);
	  jb2 = new JButton("ȡ��");
	  jb2.addActionListener(this);
	  
	  jp1 = new JPanel();
	  jp2 = new JPanel();
	  jp3 = new JPanel();
	  
	  //���ò���
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
	    //1.��������
	    Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("���سɹ�");
	    //2.�������ݿ�
	    //���弸������
	    String url = "jdbc:mysql://localhost:3306/test_database";
	    String user = "root";
	    String passwd = "134679";
	    Connection conn = DriverManager.getConnection(url, user,passwd);
		  Statement state = conn.createStatement();//���������ݿ⴫��SQL������
		  
		  String id = this.jf1.getText().trim();
		  String sqll = "select * from stu where stuID= '"+ id +"'" ;//��ѯ��Ӧѧ��ѧ���ɼ�	  
		  ResultSet rs = state.executeQuery(sqll);		  
		  if(!rs.next()){//�������ڸ�ѧ���������ʾ		 			  	 
				  JOptionPane.showMessageDialog(this, "��ѧ�Ų����ڣ�����������");
				  return ;			 
		  }  	
		  else{
		  String sql = "update stu set stuMarks=null where stuID="+id;//�������ݿ��sql��䣬���ɼ��ÿ�	  	 
		  state.executeUpdate(sql);}
		  conn.close();
		  
		 }catch (ClassNotFoundException ea) {
		     ea.printStackTrace();
	} 		       
	 catch (SQLException eb) {
		eb.printStackTrace();
	}				 
	}	
	else if(e.getSource() == jb2){//������ȡ����ť
	this.dispose();//�ͷ���Դ���ر�
	}
	}
}

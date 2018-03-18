package ex15;
//�޸�
import javax.swing.*;

//import ex15.StuModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StuUpDiag extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
//��������Ҫ��swing���
 JLabel jl1,jl2;
 JTextField jf1,jf2;
 JPanel jp1,jp2,jp3;
 JButton jb1,jb2;
 //StuModel sm;
 //owner��������,title�Ǵ��ڵ�����,modalָ����ģʽ����()���߷�ģʽ����
 public StuUpDiag(Frame owner,String title, boolean modal){
  //���ø��෽��
  super(owner,title,modal);
  
  jl1 = new JLabel("ѧ��");  
  jl2 = new JLabel("�ɼ�");
  
  jf1 = new JTextField(10);
  jf2 = new JTextField(10);
  
  jb1 = new JButton("�޸�");
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
	  String marks=this.jf2.getText().trim();//��ȡ������ֵ
	  
	  String sqll = "select * from stu where stuID= '"+ id +"'" ;//��ѯ��Ӧѧ��ѧ���ɼ�	  
	  ResultSet rs = state.executeQuery(sqll);		  
	  if(!rs.next()){//�������ڸ�ѧ���������ʾ		 			  	 
			  JOptionPane.showMessageDialog(this, "��ѧ�Ų����ڣ�����������");
			  return ;			 
	  } 
	  else{
		  rs.previous();
	  }
	  
	  String sql = "update stu SET stuMarks='"+marks+"'where stuID="+id;//�������ݿ��sql���	  	 
	  state.executeUpdate(sql);
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

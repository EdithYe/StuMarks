package ex15;
//��ѯ
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
//owner��������,title�Ǵ��ڵ�����,modalָ����ģʽ����()���߷�ģʽ����
	public StuFindDiag(Frame owner,String title, boolean modal){
		//���ø��෽��
		super(owner,title,modal);
		jl1 = new JLabel("������ѧ��ID��");
		jf1 = new JTextField(6);
		jl2 = new JLabel("ѧ���ɼ��ǣ�");
		jl3 = new JLabel();
		jb1 = new JButton("����");
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
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
			  //1.��������
			  Class.forName("com.mysql.jdbc.Driver");
			  System.out.println("���سɹ�");
			  //2.�������ݿ�
			  //���弸������
			  String url = "jdbc:mysql://localhost:3306/test_database";
			  String user = "root";
			  String passwd = "134679";	
			  
			  //�����ݿ��в��Ҷ�Ӧid�ŵ�ѧ��
			  String id = this.jf1.getText().trim();
			  String sql = "select * from stu where stuID= '"+ id +"'";
			
			  Connection conn = DriverManager.getConnection(url, user,passwd);
			  Statement state = conn.createStatement();
			  ResultSet rs = state.executeQuery(sql);
			  
			  if(!rs.next()){//�������ڸ�ѧ���������ʾ		 			  	 
				  JOptionPane.showMessageDialog(this, "��ѧ�Ų����ڣ�����������");
				  return ;			 
			  }
			  else{
				  rs.previous();
			  }
			  while(rs.next()){
				int marks=rs.getInt(7);//�ӵ����е���ѡ���Ӧ�ĳɼ�
//				System.out.println("stumarks="+marks);
				String mark=String.valueOf(marks);//��intת��Ϊstring
				jl3.setText(mark);//���ɼ������jl3
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
		else if(e.getSource() == jb2){//������ȡ����ť
			this.dispose();//�ͷ���Դ���ر�
		}
	}
}

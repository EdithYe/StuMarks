package ex15;

//����������

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
//����һЩ�ؼ�
 JPanel jp1,jp2,jp3,jp4;
 JLabel jl1,jl2;
 JMenuItem jb1,jb2,jb3,jb4;
 static JTable jt;
 JScrollPane jsp;
 JMenuBar menubar;
 JButton j1,j2,j3,j4;
 
 StuModel sm; 

 //�����������ݿ�ı���
 Statement stat = null;
 PreparedStatement ps;
 Connection ct = null;
 ResultSet rs = null;
 
 public static void main(String[] args){
  new Test3();
 }
 
 //���캯��
 public Test3(){
	 
	 super("ѧ���ɼ�����ϵͳ");
	 JMenuBar menuBar = new JMenuBar();
     JMenu file = new JMenu("�˵�");
           
     jb1 = new JMenuItem("��ѯ");
     jb1.addActionListener(this);   
     jp1 = new JPanel();  
       
     jb2 = new JMenuItem("¼��");
     jb2.addActionListener(this);
     jp2 = new JPanel(); 
     
     jb3 = new JMenuItem("�޸�");
     jb3.addActionListener(this);
     jp3 = new JPanel(); 
     
     jb4 = new JMenuItem("ɾ��");
     jb4.addActionListener(this);
     jp4 = new JPanel();  
     
     file.add(jb1);
     // ���ò˵��ָ���
     file.addSeparator();
     file.add(jb2);
     file.addSeparator();
     file.add(jb3);
     file.addSeparator();
     file.add(jb4);

     menuBar.add(file);
     
     // ���ò˵�����ʹ�����ַ�ʽ���ò˵������Բ�ռ�ò��ֿռ�
     setJMenuBar(menuBar);
  
  //����ģ�Ͷ���
  sm = new StuModel();

  //��ʼ��
  jt = new JTable(sm);  
  jsp = new JScrollPane(jt);
  
  //��jsp���뵽jframe��
  this.add(jsp);
  //this.add(jp1,"North");
  this.setSize(600, 400);
  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  this.setVisible(true);
 
 }
 public void actionPerformed(ActionEvent arg0) {
  //�ж����ĸ��Ӳ˵������
	 if(arg0.getSource() == jb1){	     
		  System.out.println("��ѯ...");
		  StuFindDiag sf=new StuFindDiag(this,"��ѯ�ɼ�",true);
		  //����һ������ģ���࣬������
		  sm = new StuModel();
		  //����jtable
		   jt.setModel(sm);
   }
  
  //��ӽ���
if(arg0.getSource() == jb2){
	 System.out.println("¼��...");	
	  StuAddDiag sa=new StuAddDiag(this, "¼��ɼ�", true);
	  //����һ������ģ���࣬������
	  sm = new StuModel();
	  //����jtable
	   jt.setModel(sm);
	  }
  
	 
	 //�޸Ľ���
 if(arg0.getSource() == jb3){	     
	  System.out.println("�޸�...");	
	  StuUpDiag su=new StuUpDiag(this, "�޸ĳɼ�", true);
	  //����һ������ģ���࣬������
	  sm = new StuModel();
	  //����jtable
	   jt.setModel(sm);
	   }
	 
  else if(arg0.getSource() == jb4){
	  System.out.println("�޸�...");	
	  StuDeleteDiag sd=new StuDeleteDiag(this, "ɾ���ɼ�", true);
	  //����һ������ģ���࣬������
	  sm = new StuModel();
	  //����jtable
	   jt.setModel(sm);
  } 
 }}


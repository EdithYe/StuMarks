package ex15;

import java.io.Serializable;

/*
 * �����ҵ�һ��stu���ģ��
 * ���԰Ѷ�ѧ����Ĳ���ȫ����װ�������
 * ����ˢ�¡��������ݿ�
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;

public class StuModel extends AbstractTableModel{
private static final long serialVersionUID = 1L;

//rowData��������ݣ�columnNames�������
 Vector<Serializable> rowData,columnNames;
   
 //�����������ݿ�ı���
 Statement stat = null;
 Connection ct = null;
 ResultSet rs = null;
 
 //��ʼ��
 public void init(String sql){
  if(sql.equals("")){
   sql = "select * from stu";
  }
  //�м�
  //��������
  columnNames = new Vector<Serializable>();
  columnNames.add("ѧ��");
  columnNames.add("����");
  columnNames.add("����");
  columnNames.add("�༶");
  columnNames.add("ϵ��");
  columnNames.add("��ַ");
  columnNames.add("�ɼ�");
    
  //rowData��Ŷ���
  rowData = new Vector<Serializable>();
    
  try{
  //1.��������
  Class.forName("com.mysql.jdbc.Driver");
  System.out.println("���سɹ�");
  //2.�������ݿ�
  //���弸������
  String url = "jdbc:mysql://localhost:3306/test_database";
  String user = "root";
  String passwd = "134679";
     
  ct = DriverManager.getConnection(url,user,passwd);
  stat = ct.createStatement();//����stat����
  rs = stat.executeQuery(sql);//��ѯ���
     
  while(rs.next()){
  Vector<Serializable> hang = new Vector<Serializable>();
  hang.add(rs.getString(1));
  hang.add(rs.getString(2));
  hang.add(rs.getString(3));
  hang.add(rs.getString(4));
  hang.add(rs.getString(5));
  hang.add(rs.getString(6));
  hang.add(rs.getString(7));
  //���뵽rowData��
  rowData.add(hang);
      
  }
     
 }catch(Exception e){
  e.printStackTrace();
 }finally{
  try{
  if(rs!=null){
  rs.close();
  rs = null;
   }
  if(stat != null){
  stat.close();
  stat = null;
   }
  if(ct != null){
  ct.close();
  ct = null;
   }
    }catch(Exception e){
   e.printStackTrace();
   }
  }
 }
 
 //���캯����ͨ�����ݵ�sql������������ģ��
 public StuModel(String sql){
  this.init(sql);
 }
 
 //���캯�������ڳ�ʼ�� ����ģ�ͣ���                                                                                                              
 public StuModel(){
  this.init("");
 }
 
 //�õ����ж�����
 public int getRowCount() {
  // TODO Auto-generated method stub
  return this.rowData.size();
 }

 //�õ����ж�����
 public int getColumnCount() {
  // TODO Auto-generated method stub
  return this.columnNames.size();
 }

 //�õ�ĳ��ĳ�е�����
 @SuppressWarnings("unchecked")
public Object getValueAt(int row, int column) {
  // TODO Auto-generated method stub
  return ((Vector<Serializable>)(this.rowData.get(row))).get(column);
 }
 
 //�õ���������
 public String getColumnName(int column) {
  // TODO Auto-generated method stub
  return (String)this.columnNames.get(column);
 }
}


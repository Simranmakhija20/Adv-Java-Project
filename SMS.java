import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.List;
import org.jfree.data.category.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;



class Student{
private int rno;
private String name;
private int s1;
private int s2;
private int s3;

Student(){}

Student(int rno, String name, int s1, int s2, int s3){
this.rno = rno;
this.name = name;
this.s1 = s1;
this.s2 = s2;
this.s3 = s3;
}

public String toString(){
	return rno + " " + name + " " + s1 + " " + s2 + " " + s3;
}

public void setRno(int rno){
	this.rno = rno;
}

public int getRno(){
	return rno;
}

public void setName(String name){
	this.name = name;
}

public String getName(){
	return name;
}

public void sets1(int s1){
	this.s1 = s1;
}

public int gets1(){
	return s1;
}

public void sets2(int s2){
	this.s2 = s2;
}

public int gets2(){
	return s2;
}

public void sets3(int s3){
	this.s3 = s3;
}

public int gets3(){
	return s3;
}

}

class SMS{
public static void main(String args[]){

MainFrame m = new MainFrame();

}
}

class MainFrame extends JFrame{
Container c;
JButton btnAdd, btnView, btnUpdate, btnDelete, btnCharts;

MainFrame(){

c = getContentPane();
c.setLayout(null);

Color c1 = new Color(204, 0, 0);
c.setBackground(c1);

Font f = new Font("Arial", Font.BOLD, 20);

btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");
btnCharts = new JButton("Charts");

btnAdd.setFont(f);
btnView.setFont(f);
btnUpdate.setFont(f);
btnDelete.setFont(f);
btnCharts.setFont(f);

btnAdd.setBounds(40, 30, 150, 40);
btnView.setBounds(210, 30, 150, 40);
btnUpdate.setBounds(40, 100, 150, 40);
btnDelete.setBounds(210, 100, 150, 40);
btnCharts.setBounds(125, 170, 150, 40);

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(btnCharts);

ActionListener a1 = (ae) -> {AddFrame a = new AddFrame(); dispose();};
btnAdd.addActionListener(a1);

ActionListener a2 = (ae) -> {ViewFrame a = new ViewFrame(); dispose();};
btnView.addActionListener(a2);

ActionListener a3 = (ae) -> {UpdateFrame a = new UpdateFrame(); dispose();};
btnUpdate.addActionListener(a3);

ActionListener a4 = (ae) -> {DeleteFrame a = new DeleteFrame(); dispose();};
btnDelete.addActionListener(a4);

ActionListener a5 = (ae) -> {ChartFrame a = new ChartFrame(); dispose();};
btnCharts.addActionListener(a5);

setTitle("S.M.S");
setSize(400, 400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}


class AddFrame extends JFrame{
Container c;
JLabel lblRno, lblName, lblMarks1, lblMarks2, lblMarks3;
JTextField txtRno, txtName, txtMarks1, txtMarks2, txtMarks3;
JButton btnSave, btnBack;

AddFrame(){

c = getContentPane();
FlowLayout f1 = new FlowLayout();
f1.setVgap(15);
f1.setHgap(25);
c.setLayout(f1);

Color c1 = new Color(0, 0, 153);
Color c2 = new Color(255, 255, 255);
c.setBackground(c1);

Font f = new Font("Arial", Font.BOLD, 20);

lblRno = new JLabel("Enter Rno : ");
txtRno = new JTextField(25);
lblName = new JLabel("Enter Name : ");
txtName = new JTextField(25);
lblMarks1 = new JLabel("Enter Sub Marks 1 : ");
txtMarks1 = new JTextField(25);
lblMarks2 = new JLabel("Enter Sub Marks 2 : ");
txtMarks2 = new JTextField(25);
lblMarks3 = new JLabel("Enter Sub Marks 3 : ");
txtMarks3 = new JTextField(25);
btnSave = new JButton("Save");
btnBack = new JButton("Back");


lblRno.setFont(f);
lblName.setFont(f);
lblMarks1.setFont(f);
lblMarks2.setFont(f);
lblMarks3.setFont(f);
btnSave.setFont(f);
btnBack.setFont(f);

lblRno.setForeground(c2);
lblName.setForeground(c2);
lblMarks1.setForeground(c2);
lblMarks2.setForeground(c2);
lblMarks3.setForeground(c2);


c.add(lblRno);
c.add(txtRno);
c.add(lblName);
c.add(txtName);
c.add(lblMarks1);
c.add(txtMarks1);
c.add(lblMarks2);
c.add(txtMarks2);
c.add(lblMarks3);
c.add(txtMarks3);
c.add(btnSave);
c.add(btnBack);

ActionListener a1 = (ae) -> {

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

try{
	s = sf.openSession();
	System.out.println("connected");
	t = s.beginTransaction();
	

	try{
		int rno = Integer.parseInt(txtRno.getText());	
		if(rno > 0){
			String name = txtName.getText();
			if(name != null && name.length() >= 2 && name.matches("[a-zA-Z ]+")){
				try{
					int s1 = Integer.parseInt(txtMarks1.getText());
					int s2 = Integer.parseInt(txtMarks2.getText());
					int s3 = Integer.parseInt(txtMarks3.getText());

					if(s1 > 0 && s1 < 100 && s2 > 0 && s2 < 100 && s3 > 0 && s3 < 100){
						Student stu = new Student(rno, name, s1, s2, s3);
						s.save(stu);
						t.commit();
						JOptionPane.showMessageDialog(c, "Student added");
				
					}
					else{
						JOptionPane.showMessageDialog(c, "Enter all marks in the range from 0 to 100");
						t.rollback();
					}
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(c, "Invalid Marks");
					t.rollback();
				}
			}
			else{
				JOptionPane.showMessageDialog(c, "Invalid Name");
				t.rollback();
			}

		
		}
		else{
			JOptionPane.showMessageDialog(c, "Rno cannot be Negative");
			t.rollback();
		}
		
	}
	catch(NumberFormatException e){
		JOptionPane.showMessageDialog(c, "Invalid Rno");
	}
	
}
catch(Exception e){
	t.rollback();
	JOptionPane.showMessageDialog(c, "Student already exists");
}
finally{
	s.close();
	System.out.println("disconnected");
}


};
btnSave.addActionListener(a1);

ActionListener a2 = (ae) -> {MainFrame m = new MainFrame(); dispose();};
btnBack.addActionListener(a2);

setTitle("Add Student");
setSize(350, 500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}


class ViewFrame extends JFrame{
Container c;
TextArea txtData;
JButton btnBack;

ViewFrame(){

c = getContentPane();
c.setLayout(new FlowLayout());

Font f = new Font("Arial", Font.BOLD, 25);
Font f2 = new Font("Arial", Font.BOLD, 13);

Color c1 = new Color(102, 0, 0);
c.setBackground(c1);

txtData = new TextArea(22, 45);
btnBack = new JButton("Back");

btnBack.setFont(f);

c.add(txtData);
c.add(btnBack);

ActionListener a1 = (ae) -> {MainFrame m = new MainFrame(); dispose();};
btnBack.addActionListener(a1);

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

try{
	s = sf.openSession();
	System.out.println("connected");
	t = s.beginTransaction();
	List stu = s.createQuery("from Student").list();
	for (Object obj : stu) {
  		txtData.append(obj.toString() + "\n");
		txtData.setFont(f2);
	}
	t.commit();
}
catch(Exception e){
	t.rollback();
	JOptionPane.showMessageDialog(c, "issue " + e);
}
finally{
	s.close();
	System.out.println("disconnected");
}	

setTitle("View Student");
setSize(700, 450);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}



class UpdateFrame extends JFrame{
Container c;
JLabel lblRno, lblName, lblMarks1, lblMarks2, lblMarks3;
JTextField txtRno, txtName, txtMarks1, txtMarks2, txtMarks3;
JButton btnSave, btnBack;

UpdateFrame(){

c = getContentPane();
FlowLayout f1 = new FlowLayout();
f1.setVgap(15);
f1.setHgap(25);
c.setLayout(f1);

Color c1 = new Color(51, 0, 102);
Color c2 = new Color(255, 255, 255);
c.setBackground(c1);

Font f = new Font("Arial", Font.BOLD, 20);

lblRno = new JLabel("Enter Rno : ");
txtRno = new JTextField(25);
lblName = new JLabel("Enter Name : ");
txtName = new JTextField(25);
lblMarks1 = new JLabel("Enter Sub Marks 1 : ");
txtMarks1 = new JTextField(25);
lblMarks2 = new JLabel("Enter Sub Marks 2 : ");
txtMarks2 = new JTextField(25);
lblMarks3 = new JLabel("Enter Sub Marks 3 : ");
txtMarks3 = new JTextField(25);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

lblRno.setFont(f);
lblName.setFont(f);
lblMarks1.setFont(f);
lblMarks2.setFont(f);
lblMarks3.setFont(f);
btnSave.setFont(f);
btnBack.setFont(f);

lblRno.setForeground(c2);
lblName.setForeground(c2);
lblMarks1.setForeground(c2);
lblMarks2.setForeground(c2);
lblMarks3.setForeground(c2);

c.add(lblRno);
c.add(txtRno);
c.add(lblName);
c.add(txtName);
c.add(lblMarks1);
c.add(txtMarks1);
c.add(lblMarks2);
c.add(txtMarks2);
c.add(lblMarks3);
c.add(txtMarks3);
c.add(btnSave);
c.add(btnBack);

ActionListener a1 = (ae) ->{

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

try{
	s = sf.openSession();
	System.out.println("connected");
	t = s.beginTransaction();
	try{
		int rno = Integer.parseInt(txtRno.getText());
		if(rno > 0){
			String name = txtName.getText();
			if(name != null && name.length() >= 2 && name.matches("[a-zA-Z ]+")){
				try{
					int s1 = Integer.parseInt(txtMarks1.getText());
					int s2 = Integer.parseInt(txtMarks2.getText());
					int s3 = Integer.parseInt(txtMarks3.getText());

					if(s1 > 0 && s1 < 100 && s2 > 0 && s2 < 100 && s3 > 0 && s3 < 100){
						try{
							Student stu = (Student)s.load(Student.class, rno);
							stu.setRno(rno);
							stu.setName(name);
							stu.sets1(s1);
							stu.sets2(s2);
							stu.sets3(s3);
							s.update(stu);
							t.commit();
							JOptionPane.showMessageDialog(c, "Student updated");

						}
						catch(Exception e){
							t.rollback();
							JOptionPane.showMessageDialog(c, "Student does not exists");
						}
						
					}
					else{
						JOptionPane.showMessageDialog(c, "Enter all marks in the range from 0 to 100");
						t.rollback();
					}
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(c, "Invalid Marks");
					t.rollback();
				}
			}
			else{
				JOptionPane.showMessageDialog(c, "Invalid name");
				t.rollback();
			}
		}
		else{
			t.rollback();
			JOptionPane.showMessageDialog(c, "Rno cannot be negative");
		}
	}
	catch(NumberFormatException e ){
		JOptionPane.showMessageDialog(c, "Invalid Rno");
		t.rollback();
	}
}
catch(Exception e){
	t.rollback();
	JOptionPane.showMessageDialog(c, "issue " + e);
}
finally{
	s.close();
	System.out.println("disconnected");
}

};

btnSave.addActionListener(a1);

ActionListener a2 = (ae) -> {MainFrame m = new MainFrame(); dispose();};
btnBack.addActionListener(a2);

setTitle("Update Student");
setSize(350, 500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}


}


class DeleteFrame extends JFrame{
Container c;
JLabel lblRno;
JTextField txtRno;
JButton btnDelete, btnBack;

DeleteFrame(){

c = getContentPane();
c.setLayout(null);

Font f = new Font("Arial", Font.BOLD, 30);

Color c1 = new Color(51, 102, 0);
c.setBackground(c1);

lblRno = new JLabel("Enter Rno : ");
txtRno = new JTextField(20);
btnDelete = new JButton("Delete");
btnBack = new JButton("Back");

lblRno.setBounds(100, 30, 200, 70);
txtRno.setBounds(100, 90, 200, 30);
btnDelete.setBounds(100, 150, 200, 30);
btnBack.setBounds(100, 210, 200, 30);

lblRno.setForeground(Color.WHITE);
lblRno.setFont(f);
txtRno.setFont(f);
btnDelete.setFont(f);
btnBack.setFont(f);

c.add(lblRno);
c.add(txtRno);
c.add(btnDelete);
c.add(btnBack);

ActionListener a1 = (ae) -> {
	
Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

try{
	s = sf.openSession();
	System.out.println("connected");
	t = s.beginTransaction();
	try{
		int rno = Integer.parseInt(txtRno.getText());
		if(rno < 0){
			JOptionPane.showMessageDialog(c, "Rno cannot be negative");
			t.rollback();
		}
		else{
			try{
				Student stu = (Student) s.get(Student.class, rno);
				s.delete(stu);
				t.commit();
				JOptionPane.showMessageDialog(c, "Student deleted");
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(c, "Student does not exists");
				t.rollback();
			}
		}
		
	}
	catch(NumberFormatException e){
		JOptionPane.showMessageDialog(c, "Invalid rno");
		t.rollback();
	}				
	
}
catch(Exception e){
	t.rollback();
	JOptionPane.showMessageDialog(c, "issue " + e);
}
finally{
	s.close();
	System.out.println("disconnected");
}

};
btnDelete.addActionListener(a1);

ActionListener a2 = (ae) -> {MainFrame m = new MainFrame(); dispose();};
btnBack.addActionListener(a2);

setTitle("Delete Student");
setSize(400, 400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}

class ChartFrame extends JFrame{

ChartFrame(){

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sf = cfg.buildSessionFactory();
Session s = null;
Transaction t = null;

DefaultCategoryDataset ds = new DefaultCategoryDataset();

try{
	s = sf.openSession();
	System.out.println("connected");
	t = s.beginTransaction();
	List stu = s.createQuery("from Student").list();
	for (Object obj : stu) {
		Student stu1 = (Student)obj;
		ds.addValue(stu1.gets1(), stu1.getName(), "S1");
		ds.addValue(stu1.gets2(), stu1.getName(), "S2");
		ds.addValue(stu1.gets3(), stu1.getName(), "S3");
	}
	
	JFreeChart ch = ChartFactory.createBarChart("Student Performance", "Subject", "Marks", ds, PlotOrientation.VERTICAL, true, true, false);

	ChartPanel cp = new ChartPanel(ch);
	setContentPane(cp);

	t.commit();
	
	
}
catch(Exception e ){
	t.rollback();
	System.out.println("issue " + e);
}
finally{
	s.close();
	System.out.println("disconnected");
}

setTitle("Marks Charts");
setSize(800, 600);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}

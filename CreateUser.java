import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class CreateUser extends JFrame implements ActionListener{
  private JLabel LabelTitle,UserId,UserName,Password,Status;
  private JTextField JFUserId,JFUserName,JFStatus;
  private JPasswordField JFPassword;
  private JButton Submit,Back , Logout;
  private JPanel Panel9;
  private int flag=0;
  private int r,g,b;

  public CreateUser(){
    super("Create New User");
    this.setSize(400,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    Panel9 = new JPanel();
    Panel9.setLayout(null);

    LabelTitle = new JLabel("Create New User");
    LabelTitle.setBounds(150,20,200,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel9.add(LabelTitle);

    UserId = new JLabel("User ID ");
    UserId.setBounds(20,70,100,30);
    Panel9.add(UserId);

    UserName = new JLabel("User Name");
    UserName.setBounds(20,120,100,30);
    Panel9.add(UserName);

    Password = new JLabel("Password");
    Password.setBounds(20,170,100,30);
    Panel9.add(Password);

    Status = new JLabel("Status");
    Status.setBounds(20,220,100,30);
    Panel9.add(Status);

    JFUserId = new JTextField();
    JFUserId.setBounds(90,70,100,30);
    Panel9.add(JFUserId);

    JFUserName = new JTextField();
    JFUserName.setBounds(90,120,100,30);
    Panel9.add(JFUserName);

    JFPassword = new JPasswordField();
    JFPassword.setBounds(90,170,100,30);
    Panel9.add(JFPassword);

    JFStatus = new JTextField();
    JFStatus.setBounds(90,220,100,30);
    Panel9.add(JFStatus);

    Submit = new JButton("Submit");
    Submit.setBounds(230,140,100,50);
    Submit.addActionListener(this);
    Panel9.add(Submit);

    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel9.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel9.add(Logout);

    this.add(Panel9);

  }

  public void insertDB(){
    String query = "INSERT INTO user VALUES ('"+JFUserId.getText()+"','"+JFUserName.getText()+"','"+JFPassword.getText()+"','"+JFStatus.getText()+"');";
		System.out.println(query);
    Connection con = null;
    Statement stm = null;
    try
		{
			Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver Loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group8travelagencymanagementsystem","root","");
      System.out.println("Connected To Database");
			stm = con.createStatement();
      System.out.println("Statement Created");
			stm.execute(query);
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
      flag=1;
        }

        finally{
          try{

            if(stm!=null){
              stm.close();
              System.out.println("Statement Closed");
            }
            if(con!=null){
              con.close();
              System.out.println("Connection Closed");
            }
          }
          catch(Exception ex){}
        }

        if(flag==1){
          JOptionPane.showMessageDialog(this,"User Id Already Booked");
          flag = 0;
        }
        else if(flag==0){
          JOptionPane.showMessageDialog(this,"User Created");
        }



    }






  public void actionPerformed(ActionEvent ae)
	{
    String ButtonClicked = ae.getActionCommand();

    if(ButtonClicked.equals(Back.getText())){
      AdminHome adh = new AdminHome();
      adh.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(Logout.getText())){
      Login L = new Login();
      L.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(Submit.getText())){
      System.out.println("Entering Database: ... ");
      insertDB();
    }
    else{}
	}
}

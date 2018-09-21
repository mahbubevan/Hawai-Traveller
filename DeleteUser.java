import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class DeleteUser extends JFrame implements ActionListener{
  private JLabel LabelTitle,UserId;
  private JTextField JFUserId;
  private JButton Delete,Back,Logout;
  private JPanel Panel10;
  private int flag =0 ;
  private int r,g,b;


  public DeleteUser(){
    super("Delete User");
    this.setSize(400,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Panel10 = new JPanel();
    Panel10.setLayout(null);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    LabelTitle = new JLabel("Enter User ID To Delete");
    LabelTitle.setBounds(150,20,200,30);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    Panel10.add(LabelTitle);

    UserId = new JLabel("User ID");
    UserId.setBounds(50,120,100,50);
    UserId.setFont(new Font("Times New Roman",Font.BOLD,18));
    Panel10.add(UserId);

    JFUserId = new JTextField();
    JFUserId.setBounds(130,130,100,30);
    Panel10.add(JFUserId);

    Delete = new JButton("Delete");
    Delete.setBounds(250,120,100,50);
    Delete.addActionListener(this);
    Panel10.add(Delete);

    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel10.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel10.add(Logout);

    this.add(Panel10);

  }

  public void deleteFromDB(){
    String query = "DELETE from user where UserId="+JFUserId.getText()+";";
    Connection con = null;
    Statement stm = null;
    System.out.println(query);
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group8travelagencymanagementsystem","root","");
      stm = con.createStatement();
      stm.execute(query);
    }
        catch(Exception ex)
    {
      System.out.println("Exception : " +ex.getMessage());
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

        JOptionPane.showMessageDialog(this,"User Deleted");
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
    else if(ButtonClicked.equals(Delete.getText())){
      deleteFromDB();
    }
    else{}
	}






}

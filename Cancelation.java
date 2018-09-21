import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class Cancelation extends JFrame implements ActionListener{
  private JLabel LabelTitle,TicketNo;
  private JTextField TicketField;
  private JButton Cancel , Back , Logout;
  private JPanel Panel5;
  private String UserName,Password,Status;
  private int UserId,UserIFDB;
  private int r,g,b;
  private int flag =0;

  public Cancelation(String UserName,String Password,String Status,int UserId){
    super("Cancel Ticket");

    this.setSize(400,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    this.UserName = UserName;
    this.Password = Password;
    this.Status   = Status;
    this.UserId   = UserId;

		Panel5 = new JPanel();
		Panel5.setLayout(null);

    LabelTitle = new JLabel("Give your Booking Id no and click on cancel");
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setBounds(50,20,350,50);
    Panel5.add(LabelTitle);

    TicketNo = new JLabel("Booking Id: ");
    TicketNo.setBounds(30,120,100,50);
    TicketNo.setFont(new Font("Times New Roman",Font.BOLD,18));
    Panel5.add(TicketNo);

    TicketField = new JTextField();
    TicketField.setBounds(130,130,100,30);
    Panel5.add(TicketField);

    Cancel = new JButton("Cancel");
    Cancel.setBounds(250,120,100,50);
    Cancel.addActionListener(this);
    Panel5.add(Cancel);

    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel5.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel5.add(Logout);


    this.add(Panel5);
  }

  public void actionPerformed(ActionEvent ae){
    String ButtonClicked = ae.getActionCommand();

    if(ButtonClicked.equals(Back.getText())){
      UserHome ush = new UserHome(UserName,Password,Status,UserId);
      ush.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(Logout.getText())){
      Login L = new Login();
      L.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(Cancel.getText())){
      System.out.println(UserId);
      if(TicketField.getText().equals("")){
        flag=1;
        JOptionPane.showMessageDialog(this,"Give Your Book Id");
        flag=0;
      }

      else {

        cancelFromDB();
        UserIdFromDB();
        JOptionPane.showMessageDialog(this,"Booking Canceled");

      }


    }
    else{}
  }

  public void cancelFromDB(){
    String query = "DELETE FROM `booking` WHERE `booking`.`BookId`='"+TicketField.getText()+"' AND `booking`.`UserId`='"+this.UserId+"';";
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
          catch(Exception ex){         }
          }
  }

  public void UserIdFromDB(){

        String query = "SELECT `booking`.`UserId` FROM `booking` WHERE `booking`.`BookId`="+TicketField.getText()+";";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        System.out.println(query);

        try{
          Class.forName("com.mysql.jdbc.Driver");
          System.out.println("Driver Loaded");
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group8travelagencymanagementsystem","root","");
          System.out.println("Connection Established");
          st = con.createStatement();
          System.out.println("Statement Created");
          rs = st.executeQuery(query);
          System.out.println("Results Received");
          rs.next();
          UserIFDB = rs.getInt("UserId");
          System.out.println(UserIFDB);
          System.out.println(UserId);

        } catch(Exception ex){
          System.out.println("Exception: "+ex.getMessage());
        }
        finally{
          try{
            if(rs!=null){
              rs.close();
              System.out.println("Results Set Closed");
            }
            if(st!=null){
              st.close();
              System.out.println("Statement Closed");
            }
            if(con!=null){
              con.close();
              System.out.println("Connection Closed");
            }
          }
          catch(Exception ex){}
        }
  }

}

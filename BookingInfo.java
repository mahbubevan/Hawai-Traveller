import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class BookingInfo extends JFrame implements ActionListener{
  private JLabel LabelTitle,UserNameLabel,StatusLabel,PasswordLabel;
  private JLabel UI,UN,PN,PD,PC,BI,PI;
  private JLabel JFUI,JFUN,JFPN,JFPD,JFPC,JFBI,JFPI;
  private JButton Back , Logout;
  private JPanel Panel7;
  private String UserName,Password,Status;
  private int UserId;
  private int r,g,b,i=120;

  public BookingInfo(String UserName,String Password,String Status,int UserId){
    super("User Details");

    this.setSize(900,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    this.UserName = UserName;
    this.Password = Password;
    this.Status   = Status;
    this.UserId   = UserId;

		Panel7 = new JPanel();
		Panel7.setLayout(null);

    LabelTitle = new JLabel("Your Currently Booking Information");
    LabelTitle.setBounds(100,20,320,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel7.add(LabelTitle);

    UserNameLabel = new JLabel("Name: "+this.UserName);
    UserNameLabel.setBounds(30,40,100,50);
    Panel7.add(UserNameLabel);

    PasswordLabel = new JLabel ("Password: "+this.Password);
    PasswordLabel.setBounds(150,40,100,50);
    Panel7.add(PasswordLabel);

    StatusLabel = new JLabel ("Status: "+this.Status);
    StatusLabel.setBounds(450,40,100,50);
    Panel7.add(StatusLabel);

    UI = new JLabel("User Id");
    UI.setBounds(25,90,50,30);
    Panel7.add(UI);

    UN = new JLabel("User Name");
    UN.setBounds(80,90,100,30);
    Panel7.add(UN);

    PN = new JLabel("Package Name");
    PN.setBounds(180,90,350,30);
    Panel7.add(PN);

    PD = new JLabel("Date");
    PD.setBounds(470,90,100,30);
    Panel7.add(PD);

    PC = new JLabel("Cost");
    PC.setBounds(590,90,100,30);
    Panel7.add(PC);

    BI = new JLabel("Book Id");
    BI.setBounds(640,90,100,30);
    Panel7.add(BI);

    PI = new JLabel("Package Id");
    PI.setBounds(720,90,100,30);
    Panel7.add(PI);

    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel7.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel7.add(Logout);
    showFromDB();
    this.add(Panel7);
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
    else{}
  }

  public void showFromDB(){
    String query = "SELECT user.*,package.*,booking.* FROM `user`,`package`,`booking` where `user`.`UserName`='"+this.UserName+"' and user.UserId=booking.UserId and package.PackageId=booking.PackageId";
    Connection con = null;
    Statement stm = null;
    ResultSet rs = null;
    System.out.println(query);
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group8travelagencymanagementsystem","root","");
      stm = con.createStatement();
      System.out.println("Statement Created");
      rs = stm.executeQuery(query);
      System.out.println("ResultSet Created");


      while(rs.next()){
        System.out.println("Hello");
        String Name = rs.getString("UserName");
        String Status = rs.getString("Status");
        int UserId = rs.getInt("UserId");
        String PackageName = rs.getString("PackageName");
        String PackageDate = rs.getString("DATE");
        String PackageCost = rs.getString("Cost");
        int PackageId = rs.getInt("PackageId");
        int BookId = rs.getInt("BookId");

      System.out.println(Name+UserId+PackageName+PackageDate+PackageCost+BookId+PackageId);
        JFUI = new JLabel(""+UserId);
        JFUI.setBounds(25,i,50,30);
        Panel7.add(JFUI);

        JFUN = new JLabel(""+Name);
        JFUN.setBounds(80,i,100,30);
        Panel7.add(JFUN);

        JFPN = new JLabel(""+PackageName);
        JFPN.setBounds(180,i,350,30);
        Panel7.add(JFPN);

        JFPD = new JLabel(""+PackageDate);
        JFPD.setBounds(440,i,150,30);
        Panel7.add(JFPD);

        JFPC = new JLabel(""+PackageCost);
        JFPC.setBounds(590,i,100,30);
        Panel7.add(JFPC);

        JFBI = new JLabel(""+BookId);
        JFBI.setBounds(660,i,100,30);
        Panel7.add(JFBI);

        JFPI = new JLabel(""+PackageId);
        JFPI.setBounds(730,i,100,30);
        Panel7.add(JFPI);

        i=i+40;

      }
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
      }

}

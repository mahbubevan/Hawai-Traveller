import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class UserInfo extends JFrame implements ActionListener{
  private JLabel LabelTitle,UI,UN,PN,PD,PC,BI,PI;
  private JLabel JFUI,JFUN,JFPN,JFPD,JFPC,JFBI,JFPI;
  private JButton Back , Logout;
  private JPanel Panel13;
  private int i=90;
  private int r,g,b;

  public UserInfo(){
    super("User Details Panel");
    this.setSize(900,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    Panel13 = new JPanel();
    Panel13.setLayout(null);

    LabelTitle = new JLabel("All User Booking Info");
    LabelTitle.setBounds(250,20,200,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel13.add(LabelTitle);

    UI = new JLabel("User Id");
    UI.setBounds(25,60,50,30);
    Panel13.add(UI);

    UN = new JLabel("User Name");
    UN.setBounds(80,60,100,30);
    Panel13.add(UN);

    PN = new JLabel("Package Name");
    PN.setBounds(180,60,350,30);
    Panel13.add(PN);

    PD = new JLabel("Date");
    PD.setBounds(470,60,100,30);
    Panel13.add(PD);

    PC = new JLabel("Cost");
    PC.setBounds(590,60,100,30);
    Panel13.add(PC);

    BI = new JLabel("Book Id");
    BI.setBounds(640,60,100,30);
    Panel13.add(BI);

    PI = new JLabel("Package Id");
    PI.setBounds(720,60,100,30);
    Panel13.add(PI);

    Back = new JButton("Back");
    Back.setBounds(200,300,100,50);
    Back.addActionListener(this);
    Panel13.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(315,300,100,50);
    Logout.addActionListener(this);
    Panel13.add(Logout);

    showFromDB();
    this.add(Panel13);

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
    else{}
	}

  public void showFromDB(){
    String query = "SELECT user.*,package.*,booking.* FROM `user`,`package`,`booking` where user.UserId=booking.UserId and package.PackageId=booking.PackageId;";
    Connection con = null;
    Statement stm = null;
    ResultSet rs = null;
    System.out.println(query);
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group8travelagencymanagementsystem","root","");
      stm = con.createStatement();
      rs = stm.executeQuery(query);


      while(rs.next()){
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
        Panel13.add(JFUI);

        JFUN = new JLabel(""+Name);
        JFUN.setBounds(80,i,100,30);
        Panel13.add(JFUN);

        JFPN = new JLabel(""+PackageName);
        JFPN.setBounds(180,i,350,30);
        Panel13.add(JFPN);

        JFPD = new JLabel(""+PackageDate);
        JFPD.setBounds(440,i,150,30);
        Panel13.add(JFPD);

        JFPC = new JLabel(""+PackageCost);
        JFPC.setBounds(590,i,100,30);
        Panel13.add(JFPC);

        JFBI = new JLabel(""+BookId);
        JFBI.setBounds(660,i,100,30);
        Panel13.add(JFBI);

        JFPI = new JLabel(""+PackageId);
        JFPI.setBounds(730,i,100,30);
        Panel13.add(JFPI);

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

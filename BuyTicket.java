import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class BuyTicket extends JFrame implements ActionListener{
  private JLabel LabelTitle,Cost,Bus,LabelPId,LabelPName,LabelPDate,LabelPCost;
  private JLabel RouteName,RouteDate,RouteCost,SerialNumber;
  private JButton Payment,BookNow , Back , Logout;
  private JPanel Panel3;
  private JTextField JBookNow,JUserId;
  private String UserName,Password,Status;
  private int UserId;
  private int i=90;
  private int r,g,b;
  private int flag=0;


  public BuyTicket(String UserName,String Password,String Status,int UserId){
    super("Book Ticket Now");

    this.setSize(700,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    this.UserName = UserName;
    this.Password = Password;
    this.Status   = Status;
    this.UserId   = UserId;

		Panel3 = new JPanel();
		Panel3.setLayout(null);

    LabelTitle = new JLabel("Booking Going On");
    LabelTitle.setBounds(250,20,200,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel3.add(LabelTitle);

    SerialNumber = new JLabel("Serial No");
    SerialNumber.setBounds(50,50,250,50);
    Panel3.add(SerialNumber);

    RouteName = new JLabel("Route Name");
    RouteName.setBounds(120,50,250,50);
    Panel3.add(RouteName);

    RouteDate = new JLabel("Date");
    RouteDate.setBounds(390,50,250,50);
    Panel3.add(RouteDate);

    RouteCost = new JLabel("Cost");
    RouteCost.setBounds(550,50,250,50);
    Panel3.add(RouteCost);

    JBookNow = new JTextField("Serial No");
    JBookNow.setBounds(200,320,100,40);
    Panel3.add(JBookNow);

    JUserId = new JTextField("User ID");
    JUserId.setBounds(315,320,100,40);
    Panel3.add(JUserId);

    BookNow = new JButton("Book Now");
    BookNow.setBounds(430,320,100,40);
    BookNow.addActionListener(this);
    Panel3.add(BookNow);

    Payment = new JButton("Make Payment");
    Payment.setBounds(430,380,150,50);
    Payment.addActionListener(this);
    Panel3.add(Payment);

    Back = new JButton("Back");
    Back.setBounds(200,380,100,50);
    Back.addActionListener(this);
    Panel3.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(315,380,100,50);
    Logout.addActionListener(this);
    Panel3.add(Logout);
    DbPackage();
    this.add(Panel3);
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

    else if(ButtonClicked.equals(BookNow.getText())){
      int  count = getBookinId()+1;
      System.out.println(count);
      int UserIdFromJ = Integer.parseInt(JUserId.getText());
      if(this.UserId==UserIdFromJ){
        DbBookNow(count);
        JOptionPane.showMessageDialog(this,"Mr."+UserName+", your ticket has been booked. Please make payment within 24 hour. And your Booking Id is "+count);
    }

    else{
      JOptionPane.showMessageDialog(this,"Mr."+UserName+", your User Id is "+UserId+" For Booking use your own user id. Thank You");
    }
    }
    else if(ButtonClicked.equals(Payment.getText())){
      Payment P = new Payment(UserName,Password,Status,UserId);
      P.setVisible(true);
      this.setVisible(false);

    }
    else{}
  }

  public void DbPackage(){

    String query = "SELECT `PackageId`,`PackageName`, `Date`,`Cost` FROM `package`;";
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

      while(rs.next()){
        //System.out.println("hjhj");
        String PId = rs.getString("PackageId");
        String PName = rs.getString("PackageName");
        String PDate = rs.getString("Date");
        double PCost = rs.getDouble("Cost");

        //int p = Integer.parseInt(PId);
        System.out.println(PId+PName+PDate+PCost);

        LabelPId = new JLabel(""+PId);
        LabelPId.setBounds(50,i,200,30);
        Panel3.add(LabelPId);

        LabelPName = new JLabel(""+PName);
        LabelPName.setBounds(120,i,300,30);
        Panel3.add(LabelPName);

        LabelPDate = new JLabel(""+PDate);
        LabelPDate.setBounds(390,i,200,30);
        Panel3.add(LabelPDate);

        LabelPCost = new JLabel(""+PCost);
        LabelPCost.setBounds(550,i,200,30);
        Panel3.add(LabelPCost);

        i = i+40;
      }

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

  public void DbBookNow(int BookId){
    String query = "INSERT INTO booking VALUES ('"+BookId+"','"+JUserId.getText()+"','"+JBookNow.getText()+"');";
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
      st.execute(query);

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

  public int getBookinId(){
    int Bid = 0;
    String query = "Select `BookId` FROM `booking` group by `BookId`;";
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
      rs.last();

      Bid = rs.getInt("BookId");
      System.out.println(Bid);


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

    return Bid;
  }

}

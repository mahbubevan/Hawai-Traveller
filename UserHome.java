import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class UserHome extends JFrame implements ActionListener{
  private JLabel LabelTitle;
  private JButton BuyTicket,BookATour,Cancelation,Payment,BookingInfo,Logout;
  private JPanel Panel2;
  private String UserName,Password,Status;
  private int UserId;
  private int r,g,b;


  public UserHome(String UserName,String Password,String Status,int UserId){
    super(" User Home Window");
    this.setSize(400,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    Panel2 = new JPanel();
    Panel2.setLayout(null);

    LabelTitle = new JLabel("User Panel");
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setBounds(150,20,200,30);
    Panel2.add(LabelTitle);

    BuyTicket = new JButton("Buy Ticket");
    BuyTicket.setBounds(55,80,130,50);
    BuyTicket.addActionListener(this);
    Panel2.add(BuyTicket);

    BookATour = new JButton("Book Tour");
    BookATour.setBounds(220,80,130,50);
    BookATour.addActionListener(this);
    Panel2.add(BookATour);

    Cancelation = new JButton("Cancel Ticket");
    Cancelation.setBounds(55,180,130,50);
    Cancelation.addActionListener(this);
    Panel2.add(Cancelation);

    Payment = new JButton("Payment");
    Payment.setBounds(220,180,130,50);
    Payment.addActionListener(this);
    Panel2.add(Payment);

    BookingInfo = new JButton("Your Details");
    BookingInfo.setBounds(55,280,130,50);
    BookingInfo.addActionListener(this);
    Panel2.add(BookingInfo);

    Logout = new JButton("Logout");
    Logout.setBounds(220,280,130,50);
    Logout.addActionListener(this);
    Panel2.add(Logout);


    this.add(Panel2);
    this.UserName = UserName;
    this.Password = Password;
    this.Status   = Status;
    this.UserId   = UserId;
  }

  public void actionPerformed(ActionEvent ae)
	{
    String ButtonClicked = ae.getActionCommand();

    if(ButtonClicked.equals(Logout.getText())){
      System.out.println("Logged Out");
      Login L = new Login();
      this.setVisible(false);
      L.setVisible(true);
    }
    else if(ButtonClicked.equals(BuyTicket.getText())){
      BuyTicket BT = new BuyTicket(UserName,Password,Status,UserId);
      BT.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(BookATour.getText())){
      BookATour BAT = new BookATour(UserName,Password,Status,UserId);
      BAT.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(Cancelation.getText())){
      Cancelation C = new Cancelation(UserName,Password,Status,UserId);
      C.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(Payment.getText())){
      Payment P = new Payment(UserName,Password,Status,UserId);
      P.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(BookingInfo.getText())){
      BookingInfo BI = new BookingInfo(UserName,Password,Status,UserId);
      BI.setVisible(true);
      this.setVisible(false);
    }
    else{}
	}
}

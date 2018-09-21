import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class Payment extends JFrame implements ActionListener{
  private JLabel LabelTitle;
  private JButton Pay , Back , Logout,Proceed;
  private JRadioButton Bkash,DBBL,MasterCard,VisaCard;
  private ButtonGroup RadioButtonGroup;
  private JPanel Panel6;
  private String UserName,Password,Status;
  private int UserId;
  private int r,g,b;

  public Payment(String UserName,String Password,String Status,int UserId){
    super("Secure Payment");

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

		Panel6 = new JPanel();
		Panel6.setLayout(null);

    LabelTitle = new JLabel("Pay Within 24 Hour");
    LabelTitle.setBounds(100,20,200,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel6.add(LabelTitle);

    Bkash = new JRadioButton("Bkash");
    Bkash.setBounds(20,50,100,50);
    Panel6.add(Bkash);

    DBBL = new JRadioButton("DBBL");
    DBBL.setBounds(20,110,100,50);
    Panel6.add(DBBL);

    MasterCard = new JRadioButton("MasterCard");
    MasterCard.setBounds(20,170,100,50);
    Panel6.add(MasterCard);

    VisaCard = new JRadioButton("VisaCard");
    VisaCard.setBounds(20,240,100,50);
    Panel6.add(VisaCard);

    RadioButtonGroup = new ButtonGroup();
    RadioButtonGroup.add(Bkash);
    RadioButtonGroup.add(DBBL);
    RadioButtonGroup.add(MasterCard);
    RadioButtonGroup.add(VisaCard);

    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel6.add(Back);

    Proceed = new JButton("Proceed");
    Proceed.setBounds(190,150,100,50);
    Proceed.addActionListener(this);
    Panel6.add(Proceed);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel6.add(Logout);

    this.add(Panel6);
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


}

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class BookATour extends JFrame implements ActionListener{
  private JLabel LabelTitle,Package,Cost,Time;
  private JButton BookNow , Back , Logout;
  private JPanel Panel4;
  private String UserName,Password,Status;
  private int UserId;
  private int r,g,b;

  public BookATour(String UserName,String Password,String Status,int UserId){
    super("Book Package Now");

    this.setSize(600,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.UserName = UserName;
    this.Password = Password;
    this.Status   = Status;
    this.UserId   = UserId;

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

		Panel4 = new JPanel();
		Panel4.setLayout(null);

    LabelTitle = new JLabel("No Tour Package Available Right Now");
    LabelTitle.setBounds(100,5,400,350);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    Panel4.add(LabelTitle);

    //Package = new JLabel("Package");
    //Package.setBounds(50,50,250,50);
    //Panel4.add(Package);

    Back = new JButton("Back");
    Back.setBounds(200,380,100,50);
    Back.addActionListener(this);
    Panel4.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(315,380,100,50);
    Logout.addActionListener(this);
    Panel4.add(Logout);

    this.add(Panel4);
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

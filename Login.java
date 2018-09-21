import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class Login extends JFrame implements ActionListener{
  private JLabel LabelTitle, UserLabel, PasswordLabel;
  private JTextField UserName;
  private JPasswordField UserPassword;
  private JButton ButtonLogin, ButtonClose;
  private JRadioButton RadioUser, RadioAdmin;
  private ButtonGroup RadioButtonGroup;
  private boolean flag;
  private JPanel Panel1;
  private int r,g,b;

  public Login(){
    super("Login Or Registration");
    this.setSize(500,350);
    //JFrame.getContentPane().setBackground(new java.awt.Color(204, 166, 166));
    //@Override
    this.setBackground(Color.RED);
    //JFrame.setOpaque(false);
    //Container c = JFrame.getContentPane();
    //c.setBackground(Color.red);
    //this.setOpaque(true);

  /*  private Container c;
        public MySimpleLayout(String str) {
            super(str);
            c=getContentPane();
            c.setLayout(null);
            c.setBackground(Color.WHITE);
        }*/
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    Panel1 = new JPanel();
    Panel1.setLayout(null);

    LabelTitle = new JLabel("Welcome To Hawai Travel Agency");
    LabelTitle.setBounds(120,25,300,50);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("arial",Font.BOLD,16));
    Panel1.add(LabelTitle);

    UserLabel = new JLabel("User Name: ");
    UserLabel.setBounds(25,80,80,50);
    Panel1.add(UserLabel);

    UserName = new JTextField();
    UserName.setBounds(100,95,80,25);
    Panel1.add(UserName);

    PasswordLabel = new JLabel("Password: ");
    PasswordLabel.setBounds(25,130,80,50);
    Panel1.add(PasswordLabel);

    UserPassword = new JPasswordField();
    UserPassword.setBounds(100,145,80,25);
    Panel1.add(UserPassword);

    ButtonLogin = new JButton("Login");
    ButtonLogin.setBounds(100,220,80,35);
    ButtonLogin.addActionListener(this);
    Panel1.add(ButtonLogin);

    ButtonClose = new JButton("Close");
    ButtonClose.setBounds(220,220,80,35);
    ButtonClose.addActionListener(this);
    Panel1.add(ButtonClose);

    RadioUser = new JRadioButton("User");
    RadioUser.setBounds(300,80,100,50);
    RadioUser.setSelected(true);
    Panel1.add(RadioUser);

    RadioAdmin = new JRadioButton("Admin");
    RadioAdmin.setBounds(300,120,100,50);
    Panel1.add(RadioAdmin);

    RadioButtonGroup = new ButtonGroup();
    RadioButtonGroup.add(RadioUser);
    RadioButtonGroup.add(RadioAdmin);

    this.add(Panel1);
  }

  public void actionPerformed(ActionEvent ae)
	{
    String ButtonClicked = ae.getActionCommand();

    if(ButtonClicked.equals(ButtonLogin.getText())){
      System.out.println("Login");
      flag = true ;
      check();
    }
    else if(ButtonClicked.equals(ButtonClose.getText())){
      System.out.println("Exit");
      System.exit(0);
    }
    else{}
	}

  public void check(){
    String query = "SELECT `UserId`,`UserName`, `Password`,`Status` FROM `user`;";
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    System.out.println(query);

    try{
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver Loaded");
      con = DriverManager.getConnection("jdbc:mysql://localhost/group8travelagencymanagementsystem","root","");
      System.out.println("Connection Established");
      st = con.createStatement();
      System.out.println("Statement Created");
      rs = st.executeQuery(query);
      System.out.println("Results Received");

      while(rs.next()){
        String Name = rs.getString("UserName");
        String Password = rs.getString("Password");
        String Status = rs.getString("Status");
        int UserId = rs.getInt("UserId");

        if(Name.equals(UserName.getText())){
          flag = false;
          if(Password.equals(UserPassword.getText())){
            if(RadioUser.isSelected() && Status.equals(RadioUser.getText())){
              UserHome ush = new UserHome(Name,Password,Status,UserId);
              this.setVisible(false);
              ush.setVisible(true);
              System.out.println(UserId);
            }
            else if(RadioAdmin.isSelected() && Status.equals(RadioAdmin.getText())){
              AdminHome adh = new AdminHome(Name,Password,Status);
              adh = new AdminHome();
              this.setVisible(false);
              adh.setVisible(true);
            }
            else if(RadioUser.isSelected() && Status.equals(RadioAdmin.getText())){
              JOptionPane.showMessageDialog(this,"Select Valid User");
            }
            else if(RadioAdmin.isSelected() && Status.equals(RadioUser.getText())){
              JOptionPane.showMessageDialog(this,"Select Valid User");
            }
          }
          else{
            JOptionPane.showMessageDialog(this,"Invalid Password");
          }
        }
      }
      if(flag){
        JOptionPane.showMessageDialog(this,"Invalid User Name");
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

}

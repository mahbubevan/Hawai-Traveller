import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class UserList extends JFrame implements ActionListener{
  private JLabel LabelTitle;
  private JPanel Panel16;
  private JButton Back, Logout;
  private int r,g,b;

  public UserList(){
    super("User Details");

    this.setSize(700,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);



		Panel16 = new JPanel();
		Panel16.setLayout(null);

    LabelTitle = new JLabel("User Informations");
    LabelTitle.setBounds(100,20,320,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel16.add(LabelTitle);



    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel16.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel16.add(Logout);
    showFromDB();
    this.add(Panel16);
  }

  public void actionPerformed(ActionEvent ae){
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
  }

  public void showFromDB(){
    String query = "SELECT `UserId`,`UserName`, `Password`,`Status` FROM `user`;";
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
        int UserIdFDB = rs.getInt("UserId");
        String UserNameFDB = rs.getString("UserName");
        String PasswordFDB = rs.getString("Password");
        String StatusFDB = rs.getString("Status");
        System.out.println(UserIdFDB+UserNameFDB+PasswordFDB+StatusFDB);


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

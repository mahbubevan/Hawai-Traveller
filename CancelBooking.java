import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class CancelBooking extends JFrame implements ActionListener{
  private JLabel LabelTitle,JPackageId;
  private JTextField JFPackageId;
  private JButton Back , Logout,DeletePackage;
  private JPanel Panel12;
  private int r,g,b,flag=0;

  public CancelBooking(){
    super("Delete Package");
    this.setSize(400,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    Panel12 = new JPanel();
    Panel12.setLayout(null);

    LabelTitle = new JLabel("Delete Package");
    LabelTitle.setBounds(150,20,200,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel12.add(LabelTitle);

    JPackageId = new JLabel("Package Id: ");
    JPackageId.setBounds(30,120,100,50);
    JPackageId.setFont(new Font("Times New Roman",Font.BOLD,18));
    Panel12.add(JPackageId);

    JFPackageId = new JTextField();
    JFPackageId.setBounds(130,130,100,30);
    Panel12.add(JFPackageId);

    DeletePackage = new JButton("Delete");
    DeletePackage.setBounds(250,120,100,50);
    DeletePackage.addActionListener(this);
    Panel12.add(DeletePackage);


    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel12.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel12.add(Logout);

    this.add(Panel12);

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
    else if(ButtonClicked.equals(DeletePackage.getText())){
      deletePDB();
    }
    else{}
	}

  public void deletePDB(){
    String query = "DELETE FROM `package` WHERE `package`.`PackageId` ='"+JFPackageId.getText()+"';";
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
        if(JFPackageId.getText().equals("")){
          flag=1;
          JOptionPane.showMessageDialog(this,"No Package Id");
          flag=0;
        }
        else if(flag==0){
        JOptionPane.showMessageDialog(this,"Package Canceled");
      }
  }

}

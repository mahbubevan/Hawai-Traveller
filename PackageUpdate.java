import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class PackageUpdate extends JFrame implements ActionListener{
  private JLabel LabelTitle,JPackageId,JPackageName,JPackageDate,JPackageCost;
  private JTextField JFPackageId,JFPackageName,JFPackageDate,JFPackageCost;
  private JButton Back , Logout,AddPackage;
  private JPanel Panel11;
  private int r,g,b,flag=0;


  public PackageUpdate(){
    super("Package Panel");
    this.setSize(400,400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    Panel11 = new JPanel();
    Panel11.setLayout(null);

    LabelTitle = new JLabel("Add New Package");
    LabelTitle.setBounds(150,20,200,30);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    Panel11.add(LabelTitle);

    JPackageId = new JLabel("Package Id: ");
    JPackageId.setBounds(20,70,100,30);
    Panel11.add(JPackageId);

    JPackageName = new JLabel("Package Name: ");
    JPackageName.setBounds(20,120,100,30);
    Panel11.add(JPackageName);

    JPackageDate = new JLabel("Date: ");
    JPackageDate.setBounds(20,170,100,30);
    Panel11.add(JPackageDate);

    JPackageCost = new JLabel("Cost: ");
    JPackageCost.setBounds(20,220,100,30);
    Panel11.add(JPackageCost);

    JFPackageId = new JTextField();
    JFPackageId.setBounds(120,70,100,30);
    Panel11.add(JFPackageId);

    JFPackageName = new JTextField();
    JFPackageName.setBounds(120,120,250,30);
    Panel11.add(JFPackageName);

    JFPackageDate = new JTextField();
    JFPackageDate.setBounds(120,170,100,30);
    Panel11.add(JFPackageDate);

    JFPackageCost = new JTextField();
    JFPackageCost.setBounds(120,220,100,30);
    Panel11.add(JFPackageCost);

    AddPackage = new JButton("Add Package");
    AddPackage.setBounds(235,160,120,50);
    AddPackage.addActionListener(this);
    Panel11.add(AddPackage);

    Back = new JButton("Back");
    Back.setBounds(20,300,100,50);
    Back.addActionListener(this);
    Panel11.add(Back);

    Logout = new JButton("Logout");
    Logout.setBounds(200,300,100,50);
    Logout.addActionListener(this);
    Panel11.add(Logout);

    this.add(Panel11);

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
    else if(ButtonClicked.equals(AddPackage.getText())){
      insertPDB();
    }
    else{}
	}

  public void insertPDB(){
    String query = "INSERT INTO `package` (`PackageId`, `PackageName`, `Date`, `Cost`) VALUES('"+JFPackageId.getText()+"','"+JFPackageName.getText()+"','"+JFPackageDate.getText()+"','"+JFPackageCost.getText()+"');";
		System.out.println(query);
    Connection con = null;
    Statement stm = null;
    try
		{
			Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver Loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group8travelagencymanagementsystem","root","");
      System.out.println("Connected To Database");
			stm = con.createStatement();
      System.out.println("Statement Created");
			stm.execute(query);
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
      flag=1;
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

        if(flag==1){
          JOptionPane.showMessageDialog(this,"Package Id Already Booked");
          flag = 0;
        }
        else if(flag==0){
          JOptionPane.showMessageDialog(this,"Package Created");
        }
  }
}

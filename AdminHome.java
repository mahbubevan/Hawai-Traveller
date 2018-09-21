import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.awt.geom.*;

class AdminHome extends JFrame implements ActionListener{
  private JLabel LabelTitle;
  private JButton CreateUser,DeleteUser,PackageUpdate,CancelBooking,UserInfo,Logout,PackageDetails,UserList;
  private JPanel Panel8;
  public  String UserName,Password,Status;
  private int r,g,b;


  public AdminHome(){
    super(" Admin Home Window");
    this.setSize(420,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Random p = new Random();
    r = p.nextInt(255);
    g = p.nextInt(255);
    b = p.nextInt(255);

    Panel8 = new JPanel();
    Panel8.setLayout(null);

    LabelTitle = new JLabel("Admin Panel");
    LabelTitle.setBounds(150,20,200,30);
    LabelTitle.setForeground(new Color(r,g,b));
    LabelTitle.setOpaque(true);
    LabelTitle.setFont(new Font("",Font.BOLD,16));
    Panel8.add(LabelTitle);

    CreateUser = new JButton("Create User");
    CreateUser.setBounds(55,80,130,50);
    CreateUser.addActionListener(this);
    Panel8.add(CreateUser);

    DeleteUser = new JButton("Delete User");
    DeleteUser.setBounds(220,80,130,50);
    DeleteUser.addActionListener(this);
    Panel8.add(DeleteUser);

    PackageUpdate = new JButton("Add Package");
    PackageUpdate.setBounds(55,180,130,50);
    PackageUpdate.addActionListener(this);
    Panel8.add(PackageUpdate);


    CancelBooking = new JButton("Delete Package");
    CancelBooking.setBounds(220,180,130,50);
    CancelBooking.addActionListener(this);
    Panel8.add(CancelBooking);

    UserInfo = new JButton("User Details");
    UserInfo.setBounds(55,280,130,50);
    UserInfo.addActionListener(this);
    Panel8.add(UserInfo);

    UserList = new JButton("User List");
    UserList.setBounds(220,280,130,50);
    UserList.addActionListener(this);
    Panel8.add(UserList);

    Logout = new JButton("Logout");
    Logout.setBounds(55,380,130,50);
    Logout.addActionListener(this);
    Panel8.add(Logout);


    this.add(Panel8);
  }
  public AdminHome(String UserName,String Password,String Status){
    this.UserName = UserName;
    this.Password = Password;
    this.Status   = Status;
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
    else if(ButtonClicked.equals(CreateUser.getText())){
      CreateUser CU = new CreateUser();
      CU.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(DeleteUser.getText())){
      DeleteUser DU = new DeleteUser();
      DU.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(PackageUpdate.getText())){
      PackageUpdate PU = new PackageUpdate();
      PU.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(CancelBooking.getText())){
      CancelBooking CB = new CancelBooking();
      CB.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(UserInfo.getText())){
      UserInfo UI = new UserInfo();
      UI.setVisible(true);
      this.setVisible(false);
    }
    else if(ButtonClicked.equals(UserList.getText())){
      UserList UL = new UserList();
      UL.setVisible(true);
      this.setVisible(false);
    }
    else{}
	}
}

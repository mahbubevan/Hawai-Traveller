
import java.sql.*;

public class DataBaseTest{


  public static void main(String []args){


            String query = "SELECT `booking`.`UserId` FROM `booking` WHERE `booking`.`BookId`=1;";
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            System.out.println(query);

            try{
             int UserIFDB;
              Class.forName("com.mysql.jdbc.Driver");
              System.out.println("Driver Loaded");
              con = DriverManager.getConnection("jdbc:mysql://localhost:3306/group8travelagencymanagementsystem","root","");
              System.out.println("Connection Established");
              st = con.createStatement();
              System.out.println("Statement Created");
              rs = st.executeQuery(query);
              System.out.println("Results Received");
              rs.next();
              UserIFDB = rs.getInt("UserId");
              System.out.println(UserIFDB);

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

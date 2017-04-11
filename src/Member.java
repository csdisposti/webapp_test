package org.example;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by cdisp on 3/15/2017.
 */
public class Member {

    private long memId;         //MemberID
    private long acctNo;        //AccountNo
    private String emailUsNa;   //Email_UserName
    private String fName;       //Fname
    private String lName;       //Lname
    private String phone1;      //Phone1
    private String phone2;      //Phone2
    private String emerCoNa;    //EmergencyContactName
    private String memCom;      //MemberComments

    //default constructor
    //public Member() {
    //    this(0L,0L, "email@email.com", "FirstName", "LastName", "Phone1", "Phone2", "EmergencyContact", "MemberComments");
    //}

    //constructor
    public Member(long memId, long acctNo, String emailUsNa, String fName,
                  String lName, String phone1, String phone2, String emerCoNa,
                  String memCom) {
        this.memId = memId;
        this.acctNo = acctNo;
        this.emailUsNa = emailUsNa;
        this.fName = fName;
        this.lName = lName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.emerCoNa = emerCoNa;
        this.memCom = memCom;
    }
    //empty constructor
    public Member()
    {
        this.memId = 0L;
        this.acctNo = 0L;
        this.emailUsNa = null;
        this.fName = null;
        this.lName = null;
        this.phone1 = null;
        this.phone2 = null;
        this.emerCoNa = null;
        this.memCom = null;
    }

    //get Member's ID
    public long getMemId() {
        return memId;
    }

    //set Member's ID
    public void setMemId(long memId) {
        this.memId = memId;
    }

    //get Member's Account Number
    public long getAcctNo() {
        return acctNo;
    }

    //set Member's Account Number
    public void setAcctNo(long acctNo) {
        this.acctNo = acctNo;
    }

    //get Member's Email/User Name
    public String getEmailUsNa() {
        return emailUsNa;
    }

    //set Member's Email/User Name
    public void setEmailUsNa(String emailUsNa) {
        this.emailUsNa = emailUsNa;
    }

    //get Member's First Name
    public String getfName() {
        return fName;
    }

    //set Member's First Name
    public void setfName(String fName) {
        this.fName = fName;
    }

    //get Member's Last Name
    public String getlName() {
        return lName;
    }

    //set Member's Last Name
    public void setlName(String lName) {
        this.lName = lName;
    }

    //get Member's Phone 1
    public String getPhone1() {
        return phone1;
    }

    //set Member's Phone 1
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    //get Member's Phone 2
    public String getPhone2() {
        return phone2;
    }

    //set Member's Phone 2
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    //get Member's Emergency Contact Name
    public String getEmerCoNa() {
        return emerCoNa;
    }

    //set Member's Emergency Contact Name
    public void setEmerCoNa(String emerCoNa) {
        this.emerCoNa = emerCoNa;
    }

    //get Member's Comments
    public String getMemCom() {
        return memCom;
    }

    //set Member's Comments
    public void setMemCom(String memCom) {
        this.memCom = memCom;
    }

    //read data from database to this class
    public void readFromDatabase(String fname, String lname) throws Exception
    {
        java.sql.Connection connection;


        String username = "MasterAscend";
        String password = "AscendMasterKey";


        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("database.properties");
        Properties prop = new Properties();
        prop.load(inputStream);
        String url = prop.getProperty("jdbc.url");
        String driver = prop.getProperty("jdbc.driver");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet rs = statement.executeQuery("SELECT * FROM tblMember WHERE FName='"+fname+"' AND LName='"+lname+"';");

            if (rs != null) {
                //makes sure the resultSet isn't in the header info
                rs.next();

                this.memId = rs.getLong("MemberID");
                this.acctNo = rs.getLong(2);
                this.emailUsNa = rs.getString(3);
                this.fName = rs.getString(4);
                this.lName = rs.getString(5);
                this.phone1 = rs.getString(6);
                this.phone2 = rs.getString(7);
                this.emerCoNa = rs.getString(8);
                this.memCom = rs.getString(9);
            }
        } catch (Exception e)
        {
            System.err.println("err");
            e.printStackTrace();
        } finally
        {
            try
            {
                connection.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String toString() {
        return "<p>Member ID: "+this.memId+"</p><p>Account Number: "+this.acctNo+"</p><p>Email: "+
                this.emailUsNa+"</p><p>First Name: "+this.fName+"</p><p>Last Name: "+this.lName+"</p><p>Phone 1: "+
                this.phone1+"</p><p>Phone 2: "+this.phone2+"</p><p>Emergency Contact: "+this.emerCoNa+"</p><p>Member Comments: "+this.memCom+"</p>";
    }
}

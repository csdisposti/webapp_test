package org.example;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.*;

public class Search extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");

        try{

            String username = "MasterAscend";
            String password = "AscendMasterKey";
            Properties prop = new Properties();
            //prop.load(new FileInputStream("database.properties"));
           // String url = prop.getProperty("jdbc.url");
            //String driver = prop.getProperty("jdbc.driver");
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://rds-mysql-ascend.chsmj6exlpbc.us-east-1.rds.amazonaws.com:3306/AscendDB", "MasterAscend", "AscendMasterKey");

            Statement stat = conn.createStatement();

           /* Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@mcndesktop07:1521:xe","sandeep","welcome");*/

            PreparedStatement ps=conn.prepareStatement("SELECT * FROM tblMember WHERE FName='"+fname+"' AND LName='"+lname+"';");

           //ps.setString(1, name);

            out.print("<table width=25% border=1>");

            out.print("<center><h1>Result:</h1></center>");

            ResultSet rs=ps.executeQuery();

                     /* Printing column names */

            ResultSetMetaData rsmd=rs.getMetaData();
/*test*/
            while(rs.next())

            {

                out.print("<tr>");

                out.print("<td>"+rsmd.getColumnName(1)+"</td>");

                out.print("<td>"+rs.getLong(1)+"</td></tr>");

                out.print("<tr><td>"+rsmd.getColumnName(2)+"</td>");

                out.print("<td>"+rs.getLong(2)+"</td></tr>");

                out.print("<tr><td>"+rsmd.getColumnName(3)+"</td>");

                out.print("<td>"+rs.getString(3)+"</td></tr>");

                out.print("<tr><td>"+rsmd.getColumnName(4)+"</td>");

                out.print("<td>"+rs.getString(4)+"</td></tr>");

                out.print("<tr><td>"+rsmd.getColumnName(5)+"</td>");

                out.print("<td>"+rs.getString(5)+"</td></tr>");
            }

            out.print("</table>");



        }catch (Exception e2)

        {

            e2.printStackTrace();

        }



        finally{out.close();

        }

    }


} 
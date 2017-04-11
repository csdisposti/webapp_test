package org.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();

        //String user = request.getParameter("user");
       // String pw = request.getParameter("pw");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");


        try{
            Member m = new Member();
            m.readFromDatabase(fname, lname);

            request.getSession().setAttribute("m", m);
            request.getSession().setAttribute("fn", fname);
            //request.getSession().setAttribute("ln", lg);
                //RequestDispatcher rd = request.getRequestDispatcher("/member.jsp");
                //rd.forward(request, response);

                //fname = rs.getString(4);
                //lname = rs.getString(5);

               //request.getAttribute("fname");
               //request.getAttribute("lname");


               request.getRequestDispatcher("/member.jsp").forward(request, response);



        }catch (Exception e2)

        {

            e2.printStackTrace();

        }



        finally{out.close();

        }

    }
    /*protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setAttribute("fname",request.getParameter("fname"));

    }
*/
} 
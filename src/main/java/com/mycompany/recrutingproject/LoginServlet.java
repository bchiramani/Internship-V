
package com.mycompany.recrutingproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amani
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // get login and password
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        System.out.println(login);
        
        //database 
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vneuronproject?useSSL=false","root","");
            
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select * from users where login='"+login+"' and password = '"+password+"'");
            if (rs.next()){
               HttpSession session = request.getSession(true);	    
               session.setAttribute("user",login); 
               response.sendRedirect("home.html");
               out.println("truee");
            }else{
                out.println("wrong login or password");
            }
            con.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
}

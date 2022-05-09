/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.recrutingproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet(name = "TemplateServlet", urlPatterns = {"/TemplateServlet"})
public class TemplateServlet extends HttpServlet {

    // save work
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title=request.getParameter("title");
        String firstPart=request.getParameter("firstPart");
        String secondPart=request.getParameter("secondPart");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vneuronproject?useSSL=false","root","");
            HttpSession session = request.getSession(false);	    
            String login=(String)session.getAttribute("user");

            PreparedStatement ps=con.prepareStatement("insert into templates values(?,?,?,?)");  
            ps.setString(1,title); 
            ps.setString(1,firstPart);
            ps.setString(1,secondPart);
            ps.setString(1,login);  
            int i=ps.executeUpdate();  
            con.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
        
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vneuronproject?useSSL=false","root","");
                HttpSession session = request.getSession(false);	    
                String login=(String)session.getAttribute("user");
                Statement stm = con.createStatement();
                ResultSet result = stm.executeQuery("Select * from templates where user=(select id from users where login='"+login+"')");
                writer.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"+"<tr><th>Title</th><th>FirstPart</th><th>SecondPart</th></tr>");
                while(result.next()){
                  writer.println("<tr>"
                                  + "<td><center>"+result.getString("title")+"</center></td>"
                                  + "<td><center>"+result.getString("firstName")+"</center></td>"
                                  +"<td><center>"+result.getString("secondPart")+"</center></td>"
                                  +"<td><center>"
                                     + "<form action=\"WorkServlet/:id\"  method=\"get\"><input type=\"submit\" id=\"login-form-submit\" class=\"btn  btn-primary\" value=\"show work\">\n</form>" 
                                  +"</center></td>"
                          
                                 + "</tr>");
                }
                writer.println("</table>");
                
             
            }catch(Exception e){
                System.out.println(e.getMessage());

        }
    
    }
    
    
    
          
          

             

 

}

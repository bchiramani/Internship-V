/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
@WebServlet(name = "WorkServlet", urlPatterns = {"/WorkServlet/:id"})
public class WorkServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // get login and password
        String id=request.getParameter("id");
        
        //database 
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vneuronproject?useSSL=false","root","");
            
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select * from templates where id='"+id+"'");
            if (rs.next()){
                
               response.sendRedirect("template.html");
              
            }
            con.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }

   
}

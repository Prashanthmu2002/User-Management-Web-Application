
package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class Userservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    String url = "jdbc:mysql://localhost:3306/demo_db";
    String user = "root";       
    String password = "admin123";   

    // ========= POST METHOD (Insert User) ===========
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("username");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name) VALUES(?)");
            ps.setString(1, name);
            ps.executeUpdate();

            out.println("<h2>User '" + name + "' added successfully!</h2>");
            out.println("<br><a href='UserServlet'>View Users</a>");
            out.println("<br><a href='index.html'>Add More</a>");

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    // ========= GET METHOD (Fetch & Display Users) ===========
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Registered Users</h2>");
        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr><th>ID</th><th>Name</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td></tr>");
            }

            out.println("</table>");
            out.println("<br><a href='index.html'>Add New User</a>");

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}



















































































//package com.demo;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/UserServlet")
//public class Userservlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    // Database connection details
//    String url = "jdbc:mysql://localhost:3306/demo_db";
//    String user = "root";       
//    String password = "admin123";   
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String name = request.getParameter("username");
//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection(url, user, password);
//
//            //insert
//            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name) VALUES(?)");
//            ps.setString(1, name);
//            ps.executeUpdate();
//
//            out.println("<html><body>");
//            out.println("<h2>Successfully added '" + name + "' to database!</h2>");
//            out.println("<br><a href='index.html'>Go Back</a>");
//            out.println("</body></html>");
//
//            con.close();
//        } catch (Exception e) {
//            out.println("Error: " + e.getMessage());
//        }
//    }
//}























































































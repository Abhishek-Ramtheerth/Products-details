package com.abhi;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class ProSignupController extends HttpServlet{
   
	private static final String QUERY = "INSERT INTO springmvc.product1(proId,proName,proPrice) VALUES(?, ?, ?)";
	@RequestMapping("/login")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");

		int pId = Integer.parseInt(req.getParameter("proId"));
		String pName = req.getParameter("proName");
		int pPrice = Integer.parseInt(req.getParameter("proPrice"));

		System.out.println("proId:" + pId);
		System.out.println("proName:" + pName);
		System.out.println("proPrice:" + pPrice);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
			PreparedStatement ps = con.prepareStatement(QUERY);

			ps.setInt(1, pId);
			ps.setString(2, pName);
			ps.setInt(3, pPrice);
			int nora = ps.executeUpdate();
			pw.println("<h1>"+pId+" stored into databse</h1>");
			if(nora>0) {
				System.out.println("Login Successfully!!");
			}
			else {
				System.out.println("<font color=red size=18>Login Falied!!");
				
			}
			ps.close();
			con.close();
		}

		catch (SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
}
	
		
	
}

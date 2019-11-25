package com.Exercise35.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * This are a servlet to create a product
 * @author ivang
 * 
 * @since 25/11/19
 * @see import javax.servlet.http.HttpServlet;
   @see import javax.servlet.http.HttpServletRequest;
   @see import javax.servlet.http.HttpServletResponse;
 *
 */

@WebServlet("/createServlet")
public class createServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
/**
	 * Metodo doPost de mi servlet createservlet
	 * 
	 * @param request Este parametro me sirve para recibir los datos del cliente
	 * @param response Este parametro me sirve para enviar enviar los datos al cliente
	 * 
	 * @return el objeto  response con la respuesta de la base de datos
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output =response.getWriter();
		
		//se obtienen los valores de los textboxes del frontend
		String nameProduct=request.getParameter("txtNameProduct");
		double priceProduct =Double.parseDouble(request.getParameter("txtPriceProduct"));
		
		//1-declaracion de la variable
		String urlServidor = "jdbc:mysql://localhost:3306/tienda?useSSL=false&serverTimezone=UTC";
		String nombreUsuario="root";
		String contrasena="admin";
		int nRowsAffected=0;
		
		//2-declaracion de objetos
		Connection conn=null;
		Statement stmnt=null;
		ResultSet rs=null;
		
		//3-Instancion del driver
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			conn=DriverManager.getConnection(urlServidor, nombreUsuario, contrasena);
			
			stmnt = conn.createStatement();
			
			nRowsAffected =stmnt.executeUpdate("INSERT INTO productos (Nombre_Producto,Precio_Producto)VALUES('"+nameProduct+"',"+priceProduct+")");
			
				if (nRowsAffected!=0) {
					output.append("registro añadido con exito!");
				}
				else {
					output.append("registro NO fue añadido");
				}
				
				stmnt.close();
				conn.close();
			
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		output.close();
	}

}

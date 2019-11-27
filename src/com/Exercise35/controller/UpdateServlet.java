package com.Exercise35.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Declaramos Variables
		String urlServer="jdbc:mysql://localhost:3306/tienda?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="admin";
		String idUsuario=request.getParameter("txtIDProdUpdate");
		String Usuariotxt=request.getParameter("txtProdUpdate");
		int idUsuario1=Integer.parseInt(idUsuario);
		String sentenciaSQL="UPDATE productos SET Nombre_Producto='"+Usuariotxt+"' WHERE Id_Producto="+idUsuario1+";";
		//String sentenciaSQL2="select * from productos where Id_Producto="+idUsuario1;
		
		
		
		// 2. Declaramos objetos
		Connection conn=null;

		Statement stmnt=null;

		//ResultSet rs=null;

		
		
		try {
			//3. Instanciamos en Driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			//4. Abrimos conexión
			conn=DriverManager.getConnection(urlServer,username,pass);
						
			//5.Preparamos el statent
			stmnt=conn.createStatement();
		
			//6.Ejecutamos sentencia SQL
			//rs=stmnt.executeQuery(sentenciaSQL);
			stmnt.executeUpdate(sentenciaSQL);

				
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			//8. Cerramos la conexiones
			try {
				//rs.close();
				stmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}

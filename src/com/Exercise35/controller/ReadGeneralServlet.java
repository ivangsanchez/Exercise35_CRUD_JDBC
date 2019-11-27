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

/**
 * Servlet que obtiene los listados de los productos
 * @author Iván Sánchez
 * @version 1.0
 * 
 * <p> Este es un listado de los productos de mi base de datos</p>
 *
 */

@WebServlet("/ReadGeneralServlet")
public class ReadGeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Declaramos Variables
		String urlServer="jdbc:mysql://localhost:3306/tienda?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="admin";
		String sentenciaSQL="select * from productos";
		
		// 2. Declaramos objetos
		Connection conn=null;
		Statement stmnt=null;
		ResultSet rs=null;
		
		
		try {
			
			//3. Instanciamos en Driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			//4. Abrimos conexión
			conn=DriverManager.getConnection(urlServer,username,pass);
			
			//5.Preparamos el statent
			stmnt=conn.createStatement();
			
			//6.Ejecutamos sentencia SQL
			rs=stmnt.executeQuery(sentenciaSQL);
			
			//7. Procesamos la salida
			while(rs.next())
			{
				response.getWriter().append("<p>"); // se crea un parrafo (solo para dar formato)
				response.getWriter().append("Id_Producto: "+rs.getInt(1)); //se obtinene la primera columna de la tabla solicitada
				//response.getWriter().append("idProducto: "+rs.getString("Id_Producto"));
				response.getWriter().append("<br/>");	//salto de linea
				response.getWriter().append("Nombre Producto: "+rs.getString(2));//se obtinene la segunda columna de la tabla solicitada
				response.getWriter().append("<br/>");	//salto de linea
				response.getWriter().append("Precio Producto: "+rs.getInt(3)); //se obtinene la tercera columna de la tabla solicitada
				response.getWriter().append("</p>"); //se cierra el parrafo
				
			}
					
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			//8. Cerramos la conexiones
			try {
				rs.close();
				stmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
	}

}

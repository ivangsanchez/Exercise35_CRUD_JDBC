package com.Exercise35.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.Exercise35.model.Products;

@WebServlet("/ReadIndividuallServlet")
public class ReadIndividuallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// se añade una configuracion para que pueda leer Ñ
		response.setContentType("text/html charset='utf-8'");
		// se crea un objeto  de printwriter para hacer mas limpio a la hora de imprimir
		PrintWriter output = response.getWriter();
		
		
		// 1. Declaramos Variables
				String urlServer="jdbc:mysql://localhost:3306/tienda?useSSL=false&serverTimezone=UTC";
				String username="root";
				String pass="admin";
				
				// ****forma mal hecha****
				//String idUsuario=request.getParameter("txIdProducto");
				//int idUsuario1=Integer.parseInt(idUsuario);
				
				// se crea un objeto de la clase Products
				Products myProduct=new Products();
				
				// se utiliza el set de la clase productos y se le coloca el identificador del text box llamado txtIdProducto
				myProduct.setIdProduct(Integer.parseInt(request.getParameter("txIdProducto")));
				
				String sentenciaSQL="select * from productos where Id_Producto="+myProduct.getIdProduct();
				
								
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
					// no se pone el while porque solo se lee uno
					 rs.next();
						response.getWriter().append("<p>"); // se crea un parrafo (solo para dar formato)
						response.getWriter().append("Id_Producto: "+rs.getInt(1)); //se obtinene la primera columna de la tabla solicitada
						//response.getWriter().append("idProducto: "+rs.getString("Id_Producto"));
						response.getWriter().append("<br/>");	//salto de linea
						response.getWriter().append("Nombre Producto: "+rs.getString(2));//se obtinene la segunda columna de la tabla solicitada
						response.getWriter().append("<br/>");	//salto de linea
						response.getWriter().append("Precio Producto: "+rs.getInt(3)); //se obtinene la tercera columna de la tabla solicitada
						response.getWriter().append("</p>"); //se cierra el parrafo
					 /*
					  * FORMA DE IMPRIMIR CON EL OBJETO CREADO DE OUTPUT
					  * 
					 	output.append("<p>"); // se crea un parrafo (solo para dar formato)
						output.append("Id_Producto: "+rs.getInt(1)); //se obtinene la primera columna de la tabla solicitada
						//response.getWriter().append("idProducto: "+rs.getString("Id_Producto"));
						output.append("<br/>");	//salto de linea
						output.append("Nombre Producto: "+rs.getString(2));//se obtinene la segunda columna de la tabla solicitada
						output.append("<br/>");	//salto de linea
						output.append("Precio Producto: "+rs.getInt(3)); //se obtinene la tercera columna de la tabla solicitada
						output.append("</p>"); //se cierra el parrafo
					 */
					 				
					
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						| ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
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
				
				output.close();
	}

}

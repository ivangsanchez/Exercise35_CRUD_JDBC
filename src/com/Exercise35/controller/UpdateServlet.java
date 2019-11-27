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


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
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

				// se crea un objeto de la clase Products
				Products myProduct=new Products();
				
				// se utiliza el set de la clase productos y se le coloca el identificador del text box llamado txtIdProducto
				myProduct.setIdProduct(Integer.parseInt(request.getParameter("txIdProducto")));
				myProduct.setNameProduct(request.getParameter("txtNameProduct"));
				myProduct.setPriceProduct(Double.parseDouble("txtPriceProduct"));
				
				String sentenciaSQLRead="SELECT from productos where Id_Producto="+myProduct.getIdProduct();
				String sentenciaSQLUpdate="UPDATE from productos where Id_Producto="+myProduct.getIdProduct();
				
				int rowsAfected=0;
								
				// 2. Declaramos objetos
				Connection conn=null;
				Statement stmnt=null;
				
				
				
				try {
					//3. Instanciamos en Driver
					Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
					
					//4. Abrimos conexión
					conn=DriverManager.getConnection(urlServer,username,pass);
					
					//5.Preparamos el statent
					stmnt=conn.createStatement();
					
					//6.Ejecutamos sentencia SQL
					rowsAfected=stmnt.executeUpdate(sentenciaSQL);
					
					//7. Procesamos la salida
					if(rowsAfected>0)
					{
						output.append("registro borrado con exito!");
					}
					else
					{
						output.append("registro encontrado con exito");
					}
				
					
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

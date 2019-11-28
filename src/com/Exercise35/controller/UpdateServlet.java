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
	
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output = response.getWriter();
		
		//1. Declaramos variables
		Products myProduct = new Products();
		myProduct.setIdProduct(Integer.parseInt(request.getParameter("txtIdProduct")));
		myProduct.setNameProduct(request.getParameter("txtNameProduct"));
		myProduct.setPriceProduct(Double.parseDouble(request.getParameter("txtPriceProduct")));
		
		String urlServer="jdbc:mysql://localhost:3306/tienda?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="admin";
		//String sentenciaSQLRead="SELECT * FROM Productos WHERE idProducto="+myProduct.getIdProduct();
		String sentenciaSQLUpdate="UPDATE productos SET Nombre_Producto='"+myProduct.getNameProduct()+"', Precio_Producto="+myProduct.getPriceProduct()+" WHERE Id_Producto="+myProduct.getIdProduct();
		int rowsAffected=0;
		
		//2. Declaramos objetos
		Connection conn = null;
		Statement stmnt = null;
		ResultSet rs = null;
		try
		{
			//3. Instanciamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			//4. Abrimos la conexión
			conn = DriverManager.getConnection(urlServer, username, pass);
			//5. Preparamos el statement
			stmnt = conn.createStatement();
			
			//6. Ejecutamos la sentencia sql

				rowsAffected=stmnt.executeUpdate(sentenciaSQLUpdate);
			//7. Procesamos los datos
				if(rowsAffected>0)
				{
					output.append("Registro Modificado con éxito!!!");
				}
				else
				{
					output.append("Registro NO pudo ser Modificado!!!");
				}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				stmnt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//8. Cerramos la conexión
		output.close();
		
	}

}



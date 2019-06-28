package it.objectmethod.worldmap.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.worldmap.dao.imp.NationDao;


public class ContinentiServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<String>continenti = new ArrayList<String>();
		NationDao nationDao = new NationDao();
		
		try {
			continenti = nationDao.getAllContinent();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		request.setAttribute("risultato", continenti);
		request.getRequestDispatcher("/Continenti.jsp").forward(request, response);

	}

}

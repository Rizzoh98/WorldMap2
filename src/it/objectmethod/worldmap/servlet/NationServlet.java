package it.objectmethod.worldmap.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.worldmap.dao.imp.NationDao;
import it.objectmethod.worldmap.domain.Nation;

public class NationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<Nation> nazioni = null;
		String continent = null;
		
		continent= request.getParameter("continent");
		
		if(continent == null) 	
			continent = (String)session.getAttribute("continent");
		else
			session.setAttribute("continent", continent);
		
		NationDao nationDao = new NationDao();
		
		try {	
			nazioni = nationDao.getAllNation(continent);
		} catch (Exception e) {

			e.printStackTrace();
		}

		request.setAttribute("risultato", nazioni);
		request.getRequestDispatcher("/Nazioni.jsp").forward(request, response);

	}

}

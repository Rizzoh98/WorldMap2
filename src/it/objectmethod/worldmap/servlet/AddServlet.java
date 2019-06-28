package it.objectmethod.worldmap.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.worldmap.dao.imp.CityDao;
import it.objectmethod.worldmap.domain.City;

public class AddServlet extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String city = null;
		String countrycode = null;
		ArrayList<City> citta = null;
		CityDao cityDao = null;
		String order = null;
		String orderP=null;

		
		HttpSession session = request.getSession();
		
		city = request.getParameter("addcity");
		cityDao = new CityDao();
		session = request.getSession();
		
		countrycode=(String)session.getAttribute("nation");
		
		if(order == null) {
			order="Az";
			orderP="pMin";
			}
		
		try {
			cityDao.addCity(city, countrycode);
			citta = cityDao.getAllCity(countrycode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("order", order);
		request.setAttribute("orderP", orderP);
		request.setAttribute("risultato", citta);
		request.getRequestDispatcher("/Citta.jsp?nation="+countrycode).forward(request, response);
		
	}
	
}

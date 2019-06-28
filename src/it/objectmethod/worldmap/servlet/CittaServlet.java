package it.objectmethod.worldmap.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.objectmethod.worldmap.dao.imp.CityDao;
import it.objectmethod.worldmap.domain.City;

public class CittaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String countrycode = null;
		List<City> citta =  new ArrayList<City>();
		CityDao cityDao = null;
		String order = null;
		String orderP=null;
		String order2 =null;
		
		countrycode = request.getParameter("nation");
		
		if(countrycode == null)
			countrycode= (String)session.getAttribute("nation");
		else	
			session.setAttribute("nation", countrycode);
		
		order = request.getParameter("order");
		if(order == null) {
			order="Az";
			orderP="pMin";
			}

		cityDao = new CityDao();
		
		if(order.equals("Az")) {
			order="Za";
			order2= "Name ASC";
			orderP="pMin";
		}
		else if(order.equals("Za"))
		{
			order="Az";
			order2= "Name DESC";
			orderP="pMin";
		}
		else if(order.equals("pMin"))
		{
			order="Az";
			orderP="pMax";
			order2= "Population ASC";
		}else if(order.equals("pMax"))
		{
			order="Az";
			orderP="pMin";
			order2= "Population DESC";
		}

		try {
			citta = cityDao.orderCity(countrycode,order2);
		} catch (Exception e) {

			e.printStackTrace();
		}

		request.setAttribute("order", order);
		request.setAttribute("orderP", orderP);
		request.setAttribute("countrycode", countrycode);
		request.setAttribute("risultato", citta);
		request.getRequestDispatcher("/Citta.jsp").forward(request, response);
	}

}

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtilisateurDao;
import forms.Loginfrom;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_CONNEXION = "/WEB-INF/authentification.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp");
			dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub;
		
		Loginfrom form = new Loginfrom(request);
		if(form.login()) {
			HttpSession session = request.getSession();
			session.setAttribute("session", form.getCredidentials().get("login"));
			request.setAttribute("login", form.getCredidentials().get("login"));
			response.sendRedirect("list");
		} else {
			request.setAttribute("errors", form.getErrors());
			getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(request, response);
		}
		

	}

}
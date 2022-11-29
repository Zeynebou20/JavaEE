package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.UtilisateurDao;
import forms.Updateuserfrom;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VUE_MODIFIER_UTILISATEUR = "/WEB-INF/modifierUtilisateur.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		if( id != null && id.matches("[0-9]+")) {
		ArrayList<Utilisateur> utilisateur = (ArrayList<Utilisateur>)UtilisateurDao.utilisateurs();
		for(Utilisateur u : utilisateur) {
			if(u.getId() == Integer.parseInt(id)) {
				request.setAttribute("nom", u.getNom());
				request.setAttribute("prenom", u.getPrenom());
				request.setAttribute("login", u.getLogin());
				request.setAttribute("password", u.getPassword());
				request.setAttribute("passwordconf", u.getPassword());
			}
				
			}
		}
		
		getServletContext().getRequestDispatcher(VUE_MODIFIER_UTILISATEUR).forward(request, response);;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Updateuserfrom form = new Updateuserfrom(request);
		if(form.modifier()) {
			response.sendRedirect("list");
		} else {
			request.setAttribute("errors", form.getErrors());
			request.setAttribute("status", form.isStatus());
			request.setAttribute("statusMessage", form.getStatusMessage());
			this.doGet(request, response);
			getServletContext().getRequestDispatcher(VUE_MODIFIER_UTILISATEUR).forward(request, response);
		}
	}

}
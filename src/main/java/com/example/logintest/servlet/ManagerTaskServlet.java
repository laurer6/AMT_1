package com.example.logintest.servlet;



import com.example.logintest.integration.UtilisateurDAOLocal;
import com.example.logintest.integration.ContactDAOLocal;
import model.Contact;
import model.Utilisateur;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/managerTask")
public class ManagerTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ContactDAOLocal contactDAO;
    @Inject
    private UtilisateurDAOLocal utilisateurDAO;

    public ManagerTaskServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Contact> contacts = contactDAO.getContacts();
        request.setAttribute("contacts", contacts);

        List<Utilisateur> utilisateurs = utilisateurDAO.getUtilisateurs();
        request.setAttribute("utilisateurs", utilisateurs);

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/managerTaskView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
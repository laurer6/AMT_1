package com.example.logintest.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.logintest.bean.UserAccount;
import com.example.logintest.integration.*;
import com.example.logintest.utils.AppUtils;
import com.example.logintest.utils.DataDAO;
import model.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private UtilisateurDAOLocal utilisateurDAO;
    @Inject
    private ClientDAOLocal clientDAO;
    @Inject
    private AdministateurDAOLocal administrateurDAO;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Utilisateur> utilisateurs = utilisateurDAO.getUtilisateurs();
        List<Client> clients = clientDAO.getClient();
        List<Administrateur> administrateurs = administrateurDAO.getAdmin();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        //UserAccount userAccount = DataDAO.findUser(userName, password);

        UserAccount userAccount2 = DataDAO.findUser2(userName, password, utilisateurs,clients,administrateurs);

        if (userAccount2 == null) {
            String errorMessage = "Invalid userName or Password";
            if(userName.isEmpty()) errorMessage = " le userName doit être renseigné";
            else if(password.isEmpty()) errorMessage = " le mot de passe doit être renseigné";
            if(userName.isEmpty() && password.isEmpty()) errorMessage = " il faut renseigner un userName et un mot de passe";
            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount2);

        //
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            // Par défaut, après l'achèvement de la connexion
            // redirigez à la page /userInfo
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }

    }

}
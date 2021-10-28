package com.example.logintest.servlet;

import com.example.logintest.bean.UserAccount;
import com.example.logintest.integration.ClientDAOLocal;
import com.example.logintest.integration.TrajetDAOLocal;
import com.example.logintest.integration.VehiculeDAOLocal;
import com.example.logintest.utils.AppUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/rendreVoiture")
public class RendreVoitureServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private TrajetDAOLocal trajetDAO;
    @Inject
    private ClientDAOLocal clientDAO;
    @Inject
    private VehiculeDAOLocal vehiculeDAO;
    @Inject

    public RendreVoitureServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //UserAccount usr = AppUtils.getLoginedUser(request.getSession());
        //request.setAttribute("usr",usr);


        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/rendreVoitureView.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserAccount user = AppUtils.getLoginedUser(request.getSession());
        request.setAttribute("user",user);



        String kilometre = request.getParameter("kilometre");
        String duree = request.getParameter("duree");

        int km = 0;
        int jour = 0;

        List<String> errors = new ArrayList<>();

        if (kilometre == null || kilometre.trim().equals("")) {
            errors.add("Le nom doit être renseigné");
        }
        else {
            try {
                km = Integer.parseInt(kilometre);

            } catch (NumberFormatException e ){
                errors.add("Les kilometres doivent être numérique");
            }
        }
        if (duree == null || duree.trim().equals("")) {
            errors.add("Le téléphone doit être renseigné");
        }
        else {
            try {
                jour = Integer.parseInt(duree);

            } catch (NumberFormatException e ){
                errors.add("La durée doit être numérique");
            }
        }
        if(user.getTrajet() == 0){
               String test2 = "Pas de trajet en cours";
               errors.add(test2);
               request.setAttribute("errors", test2);
        }

        if (errors.size() == 0) {



                String test2 = "km = " + kilometre + " duree " + duree + " " + user.getTrajet();
                errors.add(test2);
                request.setAttribute("errors", test2);


                vehiculeDAO.setEmplacement();
                clientDAO.deleteTrajet(user.getId());
                trajetDAO.supTrajet(user.getTrajet());
                user.setTrajet(0);

            }

        else {

                request.setAttribute("errors", errors);

        }









        doGet(request, response);
    }

}

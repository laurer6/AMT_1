package com.example.logintest.servlet;

import com.example.logintest.bean.UserAccount;
import com.example.logintest.integration.ClientDAOLocal;
import com.example.logintest.integration.PrixDAOLocal;
import com.example.logintest.integration.TrajetDAOLocal;
import com.example.logintest.integration.VehiculeDAOLocal;
import com.example.logintest.utils.AppUtils;
import com.example.logintest.utils.DataDAO;
import model.Vehicule;
import model.Prix;

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
    private PrixDAOLocal prixDAO;

    public RendreVoitureServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


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
        int minutes = 0;

        List<String> errors = new ArrayList<>();

        if (kilometre == null || kilometre.trim().equals("")) {
            errors.add("Les kilometres doivent  être renseigné");
        }
        else {
            try {
                km = Integer.parseInt(kilometre);

            } catch (NumberFormatException e ){
                errors.add("Les kilometres doivent être numérique");
            }
        }
        if (duree == null || duree.trim().equals("")) {
            errors.add("La durée doit être renseigné");
        }
        else {
            try {
                minutes = Integer.parseInt(duree);

            } catch (NumberFormatException e ){
                errors.add("La durée doit être numérique");
            }
        }
        if(user.getTrajetId() == 0){
               String test2 = "Pas de trajet en cours";
               errors.add(test2);
               request.setAttribute("errors", test2);
        }

        if (errors.size() == 0) {

                //Le vehicule sera à la place de destination, le trajet du client sera supprimé
               vehiculeDAO.setEmplacement(user.getTrajet().getVehicule_id(),user.getTrajet().getDestination_emplacement_id(),
                      user.getTrajet().getDestination_station_id());
                clientDAO.deleteTrajet(user.getId());
                trajetDAO.supTrajet(user.getTrajetId());
                user.setTrajetId(0);

                List<Prix> prix = prixDAO.getPrix();

                // TODO calcul à refaire
                float prixVehicule = DataDAO.prixVehicule(prix, user.getVehicule().getCategorie(), minutes);

                float prixTotal = prixVehicule * km * minutes;

                String test3 = "PRIX POUR  "+ user.getVehicule().getCategorie()  + " " + prixVehicule + " " + prixTotal;
                errors.add(test3);
                request.setAttribute("errors", test3);

                clientDAO.setSolde( (user.getSolde()-prixTotal),user.getId());

                user.setSolde(user.getSolde() - prixTotal);

            }

        else {
                request.setAttribute("errors", errors);

        }

        doGet(request, response);
    }

}

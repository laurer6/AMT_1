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
        if(user.getTrajetId() == 0){
               String test2 = "Pas de trajet en cours";
               errors.add(test2);
               request.setAttribute("errors", test2);
        }

        if (errors.size() == 0) {

                //String test2 = "km = " + kilometre + " duree " + duree + " trajet " + user.getTrajet().getId() + " " + user.getVehicule().getCategorie();
                //errors.add(test2);
                //request.setAttribute("errors", test2);


                //Le vehicule sera à la place de destination, le trajet du client sera supprimé
                vehiculeDAO.setEmplacement(user.getTrajet().getVehicule_id(),user.getTrajet().getDestination_emplacement_id(),
                        user.getTrajet().getDestination_station_id());
                clientDAO.deleteTrajet(user.getId());
                trajetDAO.supTrajet(user.getTrajetId());
                user.setTrajetId(0);


                float coutLocation = 0;

                List<Vehicule> vehicules = vehiculeDAO.getVehiculeViaID();

                List<Prix> prix = prixDAO.getPrix();

                float prixVehicule = DataDAO.prixVehicule(prix, user.getVehicule().getCategorie());

                float prixTotal = prixVehicule * km * jour;


                String test3 = "PRIX DE MES COUILLES POUR  "+ user.getVehicule().getCategorie()  + " " + prixVehicule + " " + prixTotal;
                errors.add(test3);
                request.setAttribute("errors", test3);

                clientDAO.setSolde( (user.getSolde()-prixTotal),user.getId());

                //Client client = clientDAO.getClient(user.getId());
                //client.setSolde(client.getSolde() - prixTotal);

                user.setSolde(user.getSolde() - prixTotal);




            }

        else {

                request.setAttribute("errors", errors);

        }

        doGet(request, response);
    }

}

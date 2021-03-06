package ch.heig.amt2021.servlet;

import ch.heig.amt2021.bean.UserAccount;
import ch.heig.amt2021.integration.ClientDAO;
import ch.heig.amt2021.integration.PrixDAO;
import ch.heig.amt2021.integration.TrajetDAO;
import ch.heig.amt2021.integration.VehiculeDAO;
import ch.heig.amt2021.model.Prix;
import ch.heig.amt2021.utils.AppUtils;
import ch.heig.amt2021.utils.DataDAO;

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
    private TrajetDAO trajetDAO;
    @Inject
    private ClientDAO clientDAO;
    @Inject
    private VehiculeDAO vehiculeDAO;
    @Inject
    private PrixDAO prixDAO;

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
            errors.add("Les kilometres doivent  ??tre renseign??");
        }
        else {
            try {
                km = Integer.parseInt(kilometre);

            } catch (NumberFormatException e ){
                errors.add("Les kilometres doivent ??tre num??rique");
            }
        }
        if (duree == null || duree.trim().equals("")) {
            errors.add("La dur??e doit ??tre renseign??");
        }
        else {
            try {
                minutes = Integer.parseInt(duree);

            } catch (NumberFormatException e ){
                errors.add("La dur??e doit ??tre num??rique");
            }
        }
        if(user.getTrajetId() == 0){
               String test2 = "Pas de trajet en cours";
               errors.add(test2);
               request.setAttribute("errors", test2);
        }

        if (errors.size() == 0) {

                //Le vehicule sera ?? la place de destination, le trajet du client sera supprim??
               vehiculeDAO.setEmplacement(user.getTrajet().getVehicule_id(),user.getTrajet().getDestination().getId(),
                      user.getTrajet().getDestination().getStationId());
                clientDAO.deleteTrajet(user.getId());
                trajetDAO.supTrajet(user.getTrajetId());
                user.setTrajetId(0);

                List<Prix> prix = prixDAO.getPrix();

                // TODO calcul ?? refaire
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

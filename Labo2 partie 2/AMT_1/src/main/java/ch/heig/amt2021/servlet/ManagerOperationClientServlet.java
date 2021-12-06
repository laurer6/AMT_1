package ch.heig.amt2021.servlet;

import ch.heig.amt2021.bean.UserAccount;
import ch.heig.amt2021.integration.*;
import ch.heig.amt2021.model.*;
import ch.heig.amt2021.utils.DataDAO;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/operationClient")
public class ManagerOperationClientServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Inject
    private UtilisateurDAO utilisateurDAO;
    @Inject
    private ClientDAO clientDAO;
    @Inject
    private AdministrateurDAO administrateurDAO;
    @Inject
    private VehiculeDAO vehiculeDAO;
    @Inject
    private TrajetDAO trajetDAO;


    public ManagerOperationClientServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Utilisateur> utilisateurs = utilisateurDAO.getUtilisateurs();
        List<Client> clients = clientDAO.getClient();
        List<Administrateur> administrateurs = administrateurDAO.getAdmins();
        List<Trajet> trajets = trajetDAO.getTrajets();
        List<Vehicule> vehicules = vehiculeDAO.getVehicules();

        List<UserAccount> listUser = DataDAO.listUtilisateursDetail(utilisateurs,clients,administrateurs,trajets,vehicules);
        List<UserAccount> listClient = DataDAO.listClient(listUser, clients);

        request.setAttribute("listUser", listClient);

        request.getRequestDispatcher("/WEB-INF/views/operationClientView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Utilisateur> utilisateurs = utilisateurDAO.getUtilisateurs();
        List<Client> clients = clientDAO.getClient();
        List<Administrateur> administrateurs = administrateurDAO.getAdmins();
        List<Trajet> trajets = trajetDAO.getTrajets();
        List<Vehicule> vehicules = vehiculeDAO.getVehicules();

        List<UserAccount> listUser = DataDAO.listUtilisateursDetail(utilisateurs,clients,administrateurs,trajets,vehicules);

        int utilisateur_idADelete = Integer.parseInt(request.getParameter("clientNum"));

        UserAccount utilisateurADelete = DataDAO.getUserViaId(listUser, utilisateur_idADelete);

        String error;

        if(utilisateurADelete.getTrajet() != null){
            error = "Le client a un trajet en cours";
        }
        else if(utilisateurADelete.getSolde() > 0){
            error = "Le solde du client n'est pas nul";
        }
        else {

            clientDAO.deleteClient(utilisateur_idADelete);
            utilisateurDAO.deleteUtilisateur(utilisateurADelete.getId());

            error = "Le client numéro " +  utilisateur_idADelete + " avec le login " + utilisateurADelete.getUserName() + " a bien été " +
                   "supprimé";

        }



        request.setAttribute("errorMessage12", error);


        doGet(request, response);
    }
}

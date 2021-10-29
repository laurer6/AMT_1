package com.example.logintest.servlet;

import com.example.logintest.bean.EmplacementUtilisation;
import com.example.logintest.bean.UserAccount;
import com.example.logintest.integration.*;
import com.example.logintest.utils.DataDAO;
import model.*;

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

@WebServlet("/managerTask")
public class ManagerTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Inject
    private UtilisateurDAOLocal utilisateurDAO;
    @Inject
    private ClientDAOLocal clientDAO;
    @Inject
    private TrajetDAOLocal trajetDAO;
    @Inject
    private VehiculeDAOLocal vehiculeDAO;
    @Inject
    private AdministateurDAOLocal administrateurDAO;


    public ManagerTaskServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Utilisateur> utilisateurs = utilisateurDAO.getUtilisateurs();
        request.setAttribute("utilisateurs", utilisateurs);
        List<Client> clients = clientDAO.getClient();
        List<Administrateur> administrateurs = administrateurDAO.getAdmin();
        List<Trajet> trajets = trajetDAO.getTrajets();
        List<Vehicule> vehicules = vehiculeDAO.getVehiculeViaID();



        //affichage des stations selon les consignes
        /*
        List<String> listeComplete = new ArrayList<String>();

        for(Utilisateur ut : utilisateurs){

            String solde = "";
            String admin = "";
            String vehicule = "";

            for(Administrateur ad : administrateurs){
                if(ad.getUtilisateur_id() == ut.getId()){
                    admin = "(Administrateur)";
                }
                else{
                    for(Client cl : clients){
                        if(cl.getUtilisateur_id() == ut.getId()) {
                            solde = " | solde : " + cl.getSolde();
                            for (Trajet tr : trajets) {
                                for (Vehicule vh : vehicules) {
                                    if (vh.getId() == tr.getVehicule_id() && cl.getTrajet_id() == tr.getId()) {
                                        vehicule = " | vehicule en cours d'utilisation : " + vh.getCategorie();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            listeComplete.add(ut.getId() + " " + admin + " " +ut.getLogin() + solde + vehicule) ;
        }

        request.setAttribute("listes",listeComplete);

         */

        int page = 1;
        int recordsPerPage = 4;

        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<UserAccount> listUser = DataDAO.listUtilisateursDetail(utilisateurs,clients,administrateurs,trajets,vehicules);

        List<UserAccount> listeUsrView = DataDAO.ViewUser(listUser,(page-1)*recordsPerPage,
                recordsPerPage);

        int noOfRecords = listUser.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listes2", listeUsrView);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/managerTaskView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newUserName = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");

        List<String> errors = new ArrayList<>();

        if(newUserName == null || newUserName.trim().equals("")){
            errors.add("Un nom d'utilisateur doit être renseigné");
        }
        if(newPassword == null || newPassword.trim().equals("")){
            errors.add("Un mot de passe doit être renseigné");
        }

        if (errors.size() == 0) {

            Utilisateur utilisateur = new Utilisateur(newUserName,newPassword);
            utilisateurDAO.add(utilisateur);

            //avec l'id autoincrémenté
            utilisateur = utilisateurDAO.getUtilisateur(newUserName);
            clientDAO.addClient(utilisateur.getId());


        }
        else{
            request.setAttribute("errors", errors);
        }



        doGet(request, response);
    }

}
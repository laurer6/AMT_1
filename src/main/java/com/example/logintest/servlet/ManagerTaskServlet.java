package com.example.logintest.servlet;

import com.example.logintest.integration.*;
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
        //request.setAttribute("clients", clients);

        List<Administrateur> administrateurs = administrateurDAO.getAdmin();
        //request.setAttribute("administrateurs", administrateurs);

        List<Trajet> trajets = trajetDAO.getTrajets();
        //request.setAttribute("trajets", trajets);

        List<Vehicule> vehicules = vehiculeDAO.getVehicule();
        //request.setAttribute("vehicules", vehicules);


        //affichage des stations selon les consignes

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
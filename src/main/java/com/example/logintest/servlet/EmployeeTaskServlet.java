package com.example.logintest.servlet;

import com.example.logintest.bean.EmplacementUtilisation;
import com.example.logintest.bean.UserAccount;
import com.example.logintest.bean.VehiculeUtilisation;
import com.example.logintest.integration.*;
import com.example.logintest.utils.AppUtils;
import com.example.logintest.utils.DataDAO;
import model.*;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employeeTask")
public class EmployeeTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private StationDAOLocal stationDAO;
    @Inject
    private EmplacementDAOLocal emplacementDAO;
    @Inject
    private VehiculeDAOLocal vehiculeDAO;
    @Inject
    private UtilisateurDAOLocal utilisateurDAO;
    @Inject
    private ClientDAOLocal clientDAO;
    @Inject
    private TrajetDAOLocal trajetDAO;

    public EmployeeTaskServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Utilisateur> utilisateurs = utilisateurDAO.getUtilisateurs();

        List<Vehicule> vehicules = vehiculeDAO.getVehiculeViaID();
        request.setAttribute("vehicules",vehicules);

        List<Client> clients = clientDAO.getClient();

        List<Trajet> trajets = trajetDAO.getTrajets();

        List<Station> stations = stationDAO.getStations();
        request.setAttribute("stations", stations);

        List<Emplacement> emplacements = emplacementDAO.getEmplacements();
        request.setAttribute("emplacements",emplacements);

        List<EmplacementUtilisation> emplacementsTotal = DataDAO.GenerationEmplacement(stations,emplacements,vehicules,trajets);
        List<EmplacementUtilisation> emplacementLibres = DataDAO.EmplacementLibre(emplacementsTotal);
        request.setAttribute("emplacementsLibres",emplacementLibres);

        List<VehiculeUtilisation> vehiculesTotal = DataDAO.GenerationVehicule(vehicules,trajets,clients);
        List<VehiculeUtilisation> vehiculeLibre = DataDAO.VehiculeLibre(vehiculesTotal);
        request.setAttribute("vehiculeLibre",vehiculeLibre);

        request.getRequestDispatcher("/WEB-INF/views/employeeTaskView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int numeroStationDepart = Integer.parseInt(request.getParameter("stationDepart"));
        int numeroStationArrive = Integer.parseInt(request.getParameter("stationArrive"));
        int numeroVehicule = Integer.parseInt(request.getParameter("voitureChoisit"));

        List<Vehicule> vehicules = vehiculeDAO.getVehiculeViaID();
        List<Trajet> trajets = trajetDAO.getTrajets();
        List<Station> stations = stationDAO.getStations();
        List<Emplacement> emplacements = emplacementDAO.getEmplacements();

        List<EmplacementUtilisation> emplacementsTotal = DataDAO.GenerationEmplacement(stations,emplacements,vehicules,trajets);
        List<EmplacementUtilisation> emplacementLibres = DataDAO.EmplacementLibre(emplacementsTotal);

        int noEmplacementDepart = DataDAO.idEmplacementLibre(emplacementLibres, numeroStationDepart, 0);
        int noEmplacementArrive = DataDAO.idEmplacementLibre(emplacementLibres, numeroStationArrive, noEmplacementDepart);

        UserAccount user = AppUtils.getLoginedUser(request.getSession());

        if(user.getSolde() <= 0){
            String test = "Solde insufisant pour reserver un trajet";
            request.setAttribute("errorMessage12", test);


        }
        else if(user.getTrajetId() != 0){
            String test = "Trajet déjà en cours";
            request.setAttribute("errorMessage12", test);
        }
        else if(noEmplacementDepart == 0 || noEmplacementArrive == 0){
            String test;
            if(noEmplacementDepart == 0) {
                 test = "Pas d'emplacement pour la station de départ";
            }
            else {
                test = "Pas d'emplacement pour la station de départ";
            }
            request.setAttribute("errorMessage12", test);


        }
        else {

            int userID = user.getId();

            trajetDAO.addTrajet(numeroVehicule, noEmplacementArrive, numeroStationArrive);

            Trajet trajet = trajetDAO.getTrajetViaVehicule(numeroVehicule);
            Vehicule vehicule = vehiculeDAO.getVehiculeViaID(numeroVehicule);

            String test2 = "station: " + numeroStationDepart + " emplacement no " + noEmplacementDepart + " à station: " + numeroStationArrive +
                    " emplacement no " + noEmplacementArrive + " vehicule " + numeroVehicule + " trajet " + trajet.getId() + " user id " + userID;
            request.setAttribute("errorMessage12", test2);

            user.setTrajetId(trajet.getId()); // sinon seulement mis à jour au prochain login
            user.setTrajet(trajet);
            user.setVehicule(vehicule);

            clientDAO.setTrajet(trajet.getId(), user.getId());
            vehiculeDAO.setEmplacement(numeroVehicule, noEmplacementDepart, numeroStationDepart);
        }


        doGet(request, response);
    }

}
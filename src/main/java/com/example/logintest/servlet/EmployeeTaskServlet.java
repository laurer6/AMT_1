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
import javax.servlet.RequestDispatcher;
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

        List<Vehicule> vehicules = vehiculeDAO.getVehicule();
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



        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/employeeTaskView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int numeroStationDepart = Integer.parseInt(request.getParameter("stationDepart"));
        int numeroStationArrive = Integer.parseInt(request.getParameter("stationArrive"));
        int numeroVehicule = Integer.parseInt(request.getParameter("voitureChoisit"));

        List<Vehicule> vehicules = vehiculeDAO.getVehicule();
        List<Trajet> trajets = trajetDAO.getTrajets();
        List<Station> stations = stationDAO.getStations();
        List<Emplacement> emplacements = emplacementDAO.getEmplacements();

        List<EmplacementUtilisation> emplacementsTotal = DataDAO.GenerationEmplacement(stations,emplacements,vehicules,trajets);
        List<EmplacementUtilisation> emplacementLibres = DataDAO.EmplacementLibre(emplacementsTotal);

        int noEmplacementDepart = DataDAO.idEmplacementLibre(emplacementLibres, numeroStationDepart, 0);
        int noEmplacementArrive = DataDAO.idEmplacementLibre(emplacementLibres, numeroStationArrive, noEmplacementDepart);

        UserAccount user = AppUtils.getLoginedUser(request.getSession());

        if(noEmplacementDepart == 0 || noEmplacementArrive == 0){
            String test;
            if(noEmplacementDepart == 0) {
                 test = "Pas d'emplacement pour la station de départ";
            }
            else {
                test = "Pas d'emplacement pour la station de départ";
            }
            request.setAttribute("errorMessage12", test);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        int userID = user.getId();

        trajetDAO.addTrajet(numeroVehicule,noEmplacementArrive,numeroStationArrive);

        Trajet tr = trajetDAO.getTrajet(numeroVehicule);

        String test2 = "station: "+ numeroStationDepart +" emplacement no " + noEmplacementDepart + " à station: " + numeroStationArrive +
                " emplacement no " + noEmplacementArrive + " vehicule " + numeroVehicule + " trajet " +  tr.getId() + " user id " + userID;
        request.setAttribute("errorMessage12", test2);

        // int numeroProchainTrajet = trajetDAO.getIdTrajet(trajets,numeroVehicule, noEmplacementArrive,numeroStationArrive);

        clientDAO.setTrajet(tr.getId(),user.getId());
        vehiculeDAO.setEmplacement(numeroVehicule,noEmplacementDepart,numeroStationDepart);

        vehiculeDAO.setEmplacement(1,noEmplacementDepart, numeroStationDepart);

        //clientDAO.deleteTrajet(6);

        //trajetDAO.addTrajet(4,2,2);


        //  RequestDispatcher dispatcher //
        //         = this.getServletContext().getRequestDispatcher("/WEB-INF/views/employeeTaskView.jsp");

        //   dispatcher.forward(request, response);
        //int categoryId = Integer.parseInt(request.getParameter("emp"));

        //request.setAttribute("selectedCatId", categoryId);

        /*
        String adresseEmplacement = request.getParameter("adresseEmplacement");
        String numeroEmplacement = request.getParameter("numeroEmplacement");

        if(!adresseEmplacement.isEmpty() && !numeroEmplacement.isEmpty()){

            String errorMessage = "KOKPOKh234 ";

            request.setAttribute("errorMessage12", errorMessage);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/employeeTaskView.jsp");

            dispatcher.forward(request, response);

        }

        response.sendRedirect(request.getContextPath() + "/employeeTask");

         */

        doGet(request, response);
    }

}
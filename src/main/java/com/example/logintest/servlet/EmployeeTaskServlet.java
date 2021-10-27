package com.example.logintest.servlet;

import com.example.logintest.bean.EmplacementUtilisation;
import com.example.logintest.bean.VehiculeUtilisation;
import com.example.logintest.integration.*;
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

        int numeroEmplacement1 = Integer.parseInt(request.getParameter("stationDepart"));
        int numeroEmplacement2 = Integer.parseInt(request.getParameter("stationArrive"));
        int numeroVoiture = Integer.parseInt(request.getParameter("voitureChoisit"));

        List<Vehicule> vehicules = vehiculeDAO.getVehicule();
        List<Trajet> trajets = trajetDAO.getTrajets();
        List<Station> stations = stationDAO.getStations();
        List<Emplacement> emplacements = emplacementDAO.getEmplacements();

        List<EmplacementUtilisation> emplacementsTotal = DataDAO.GenerationEmplacement(stations,emplacements,vehicules,trajets);
        List<EmplacementUtilisation> emplacementLibres = DataDAO.EmplacementLibre(emplacementsTotal);

        int emplacementdispo1 = DataDAO.idEmplacementLibre(emplacementLibres, numeroEmplacement1, 0);
        int emplacementdispo2 = DataDAO.idEmplacementLibre(emplacementLibres, numeroEmplacement2, emplacementdispo1);

        String test = "" + emplacementdispo1 + " à " + emplacementdispo2 + " vehicule " + numeroVoiture;

        request.setAttribute("errorMessage12", test);

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
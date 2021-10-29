package com.example.logintest.servlet;

import com.example.logintest.bean.EmplacementUtilisation;
import com.example.logintest.integration.EmplacementDAOLocal;
import com.example.logintest.integration.StationDAOLocal;
import com.example.logintest.integration.TrajetDAOLocal;
import com.example.logintest.integration.VehiculeDAOLocal;
import com.example.logintest.utils.DataDAO;
import model.Emplacement;
import model.Station;
import model.Trajet;
import model.Vehicule;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/stationEtEmplacment")
public class StationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private StationDAOLocal stationDAO;
    @Inject
    private EmplacementDAOLocal emplacementDAO;
    @Inject
    private VehiculeDAOLocal vehiculeDAO;
    @Inject
    private TrajetDAOLocal trajetDAO;

    public StationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        List<Station> stations = stationDAO.getStations();
        request.setAttribute("stations", stations);

        List<Emplacement> emplacements = emplacementDAO.getEmplacements();
        request.setAttribute("emplacements",emplacements);

        List<Vehicule> vehicules = vehiculeDAO.getVehiculeViaID();
        request.setAttribute("vehicules",vehicules);

        List<Trajet> trajets = trajetDAO.getTrajets();

        //affichage des stations selon les consignes

        List<String> listeComplete = new ArrayList<String>();

        List<EmplacementUtilisation> emplacementLibres = DataDAO.GenerationEmplacement(stations,emplacements,vehicules,trajets);
        request.setAttribute("emplacementsLibres",emplacementLibres);

        for(Station st : stations){
            //listeComplete.add(st.getAdresse());

            for (Emplacement ep : emplacements){
                String occupe = "Libre";
                if(ep.getStation_id() == st.getId()){
                    for(Vehicule vh : vehicules){
                        if(vh.getEmplacement_id() == ep.getId() && vh.getStation_id() == st.getId()){
                            occupe = vh.getCategorie();
                        }
                    }
                    listeComplete.add(st.getAdresse() + " emplacement no " + ep.getId() + " : " + occupe);
                }
            }
        }

        request.setAttribute("listes",listeComplete);

        int page = 1;
        int recordsPerPage = 10;

        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<EmplacementUtilisation> listeEmpView = DataDAO.ViewEmplacement(emplacementLibres,(page-1)*recordsPerPage,
                        recordsPerPage);

        int noOfRecords = emplacementLibres.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listeEmpView", listeEmpView);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/stationView.jsp");
        view.forward(request, response);








        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/stationView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }


}

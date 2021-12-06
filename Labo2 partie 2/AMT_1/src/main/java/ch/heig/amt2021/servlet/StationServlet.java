package ch.heig.amt2021.servlet;

import ch.heig.amt2021.bean.EmplacementUtilisation;
import ch.heig.amt2021.integration.*;
import ch.heig.amt2021.model.*;
import ch.heig.amt2021.utils.DataDAO;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/stationEtEmplacment")
public class StationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private StationDAO stationDAO;
    @Inject
    private EmplacementDAO emplacementDAO;
    @Inject
    private VehiculeDAO vehiculeDAO;
    @Inject
    private TrajetDAO trajetDAO;
    @Inject
    private UtilisateurDAO utilisateurDAO;

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


        List<Vehicule> vehicules = vehiculeDAO.getVehicules();
        request.setAttribute("vehicules",vehicules);

        List<Trajet> trajets = trajetDAO.getTrajets();


        List<EmplacementUtilisation> emplacementLibres = DataDAO.GenerationEmplacement(stations,emplacements,vehicules,trajets);
        request.setAttribute("emplacementsLibres",emplacementLibres);

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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }


}

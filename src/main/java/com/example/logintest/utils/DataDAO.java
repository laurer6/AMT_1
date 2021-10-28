package com.example.logintest.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.logintest.bean.EmplacementUtilisation;
import com.example.logintest.bean.UserAccount;
import com.example.logintest.bean.VehiculeUtilisation;
import com.example.logintest.config.SecurityConfig;
import model.*;


public class DataDAO {

    private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();


    // Find a User by userName and password.

    public static UserAccount findUser2(String userName, String password, List<Utilisateur> utilisateurs,
                                        List<Client> clients, List<Administrateur> administrateurs,
                                        List<Vehicule> vehicules, List<Trajet> trajets) {
        UserAccount u = new UserAccount();

        for(Utilisateur ut : utilisateurs){
            if(ut.getPassword().equals(password) && ut.getLogin().equals(userName)){

                u = new UserAccount(ut.getLogin(),ut.getPassword(),SecurityConfig.ROLE_EMPLOYEE);
                for(Administrateur ad : administrateurs){
                    if(ad.getUtilisateur_id() == ut.getId()){

                        u = new UserAccount(ut.getLogin(), ut.getPassword(),  //
                                SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);
                        u.setAdmin(true);
                    }
                }
                for(Client cl : clients){
                    if(cl.getUtilisateur_id() == ut.getId()){
                        u.setSolde(cl.getSolde());
                        u.setTrajetId(cl.getTrajet_id());
                        for(Trajet tr : trajets){
                            if(tr.getId() == cl.getTrajet_id()) {
                                u.setTrajet(tr);
                                for(Vehicule vh: vehicules){
                                    if(vh.getId() == tr.getVehicule_id()) u.setVehicule(vh);
                                }
                            }
                        }
                    }
                }
                u.setId(ut.getId());
                return u;
            }
        }

        return null;
    }

    //Genére une liste d'emplacement avec tout les détail nécessaire, grâce à toute les listes dérivées des tables SQL

    public static List<EmplacementUtilisation> GenerationEmplacement(List<Station> stations, List<Emplacement> emplacements, List<Vehicule> vehicules, List<Trajet> trajets){

        List<EmplacementUtilisation> empL = new ArrayList<>();

        int i = 0;
        for(Station st: stations){
            for(Emplacement em : emplacements){
                EmplacementUtilisation empl1 = new EmplacementUtilisation();
                empl1.setAdresse(st.getAdresse());
                empl1.setStation_id(st.getId());
                if(em.getStation_id() == st.getId()){
                    i += 1;
                    empl1.setEmplacement_id(em.getId());
                    for(Vehicule vh : vehicules){
                        if(vh.getEmplacement_id() == em.getId() && vh.getStation_id() == em.getStation_id()){
                            empl1.setOccupe(true);
                            empl1.setVehicule(vh);
                        }
                    }
                    for(Trajet tr: trajets){
                        if(tr.getDestination_emplacement_id() == em.getId() && tr.getDestination_station_id() == em.getStation_id()){
                            empl1.setReserve(true);
                        }
                    }
                    empl1.setId(i);
                    empL.add(empl1);
                }
            }
        }
        return empL;
    }

    public static List<VehiculeUtilisation> GenerationVehicule(List<Vehicule> vehicules, List<Trajet> trajets, List<Client> clients){

        List<VehiculeUtilisation> vhU = new ArrayList<>();

        for(Vehicule vh: vehicules){
            VehiculeUtilisation veh1 = new VehiculeUtilisation();
            veh1.setId(vh.getId());
            veh1.setMatricule(vh.getMatricule());
            veh1.setCategorie(vh.getCategorie());
            veh1.setEmplacement_id(vh.getEmplacement_id());
            veh1.setStation_id(vh.getStation_id());
            for(Trajet tr: trajets){
                if(tr.getVehicule_id() == vh.getId()){
                    veh1.setTrajet_id(tr.getVehicule_id());
                    veh1.setReserve(true);
                    for(Client cl : clients){
                        if(cl.getTrajet_id() == tr.getId()) veh1.setUser_id(cl.getUtilisateur_id());
                    }
                }
            }
            vhU.add(veh1);
        }
    return vhU;
    }

    // pour l'affichage avec pagination dans la page station
    public static List<EmplacementUtilisation> ViewEmplacement(List<EmplacementUtilisation> emp, int offset, int noOfPage){

        List<EmplacementUtilisation> listEmp = new ArrayList<>();

        for(int i = offset; i < offset + noOfPage; i++){
            if(i < emp.size())
            listEmp.add(emp.get(i));
        }
        return listEmp;
    }

    // Retourne une liste uniquement d'emplacement libre
    public static List<EmplacementUtilisation> EmplacementLibre(List<EmplacementUtilisation> emplacement){

        List<EmplacementUtilisation> listEmp = new ArrayList<>();

        for(EmplacementUtilisation emp: emplacement){
            if(!emp.isOccupe() && !emp.isReserve()) listEmp.add(emp);
        }
        return listEmp;
    }

    // Retourne une liste uniquement de véhicule libre
    public static List<VehiculeUtilisation> VehiculeLibre(List<VehiculeUtilisation> vehicule){

        List<VehiculeUtilisation> listVh = new ArrayList<>();

        for(VehiculeUtilisation vh : vehicule){
            if(!vh.isReserve()){
                listVh.add(vh);
            }
        }
        return listVh;
    }

    //Retourne l'id d'un emplacement libre en raport avec la station choisit, retourne 0 si pas trouvé
    public static int idEmplacementLibre (List<EmplacementUtilisation> EmplacementLibre, int idStation, int idAutreEmplacementId){

        for(EmplacementUtilisation emp : EmplacementLibre){
            if(emp.getStation_id() == idStation && emp.getEmplacement_id() != idAutreEmplacementId) return emp.getEmplacement_id();
        }
        return 0;
    }

    public static float prixVehicule (List<Prix> prix, String categorieUser){

                for (Prix pr : prix) {
                    if (pr.getCateogire().equals(categorieUser)) {
                        return pr.getPrix1();
                    }
                }

        return 0;
    }


}
package com.example.logintest.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.logintest.bean.EmplacementLibre;
import com.example.logintest.bean.UserAccount;
import com.example.logintest.bean.VehiculeUtilisation;
import com.example.logintest.config.SecurityConfig;
import model.*;


public class DataDAO {

    private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

    static {
        initUsers();
    }

    private static void initUsers() {

        // This user has a role as EMPLOYEE.
        UserAccount emp = new UserAccount("employee1", "123",  //
                SecurityConfig.ROLE_EMPLOYEE);

        // This user has 2 roles EMPLOYEE and MANAGER.
        UserAccount mng = new UserAccount("manager1", "123",  //
                SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);

        mapUsers.put(emp.getUserName(), emp);
        mapUsers.put(mng.getUserName(), mng);
    }

    // Find a User by userName and password.
    public static UserAccount findUser(String userName, String password) {
        UserAccount u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    // Find a User by userName and password.

    public static UserAccount findUser2(String userName, String password, List<Utilisateur> utilisateurs, List<Client> clients, List<Administrateur> administrateurs) {
        UserAccount u = new UserAccount();

        for(Utilisateur ut : utilisateurs){
            if(ut.getPassword().equals(password) && ut.getLogin().equals(userName)){
               // u.setUserName(ut.getLogin());
               // u.setPassword(ut.getPassword());
                u = new UserAccount(ut.getLogin(),ut.getPassword(),SecurityConfig.ROLE_EMPLOYEE);
                for(Administrateur ad : administrateurs){
                    if(ad.getUtilisateur_id() == ut.getId()){
                        // ut.setAdmin(true);
                        u = new UserAccount(ut.getLogin(), ut.getPassword(),  //
                                SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);
                        u.setAdmin(true);
                    }
                }
                for(Client cl : clients){
                    if(cl.getUtilisateur_id() == ut.getId()){
                        u.setSolde(cl.getSolde());
                        u.setTrajet(cl.getTrajet_id());
                    }
                }
                return u;
            }
        }

        return null;
    }


    public static List<EmplacementLibre> GenerationEmplacement(List<Station> stations, List<Emplacement> emplacements,List<Vehicule> vehicules, List<Trajet> trajets){

        List<EmplacementLibre> empL = new ArrayList<>();

        int i = 0;
        for(Station st: stations){
            for(Emplacement em : emplacements){
                EmplacementLibre empl1 = new EmplacementLibre();
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
    public static List<EmplacementLibre> ViewEmplacement(List<EmplacementLibre> emp, int offset, int noOfPage){

        List<EmplacementLibre> listEmp = new ArrayList<>();

        for(int i = offset; i < offset + noOfPage; i++){
            if(i < emp.size())
            listEmp.add(emp.get(i));
        }

        return listEmp;

    }

}
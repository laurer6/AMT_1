package ch.heig.amt2021.utils;

import ch.heig.amt2021.bean.EmplacementUtilisation;
import ch.heig.amt2021.bean.UserAccount;
import ch.heig.amt2021.bean.VehiculeUtilisation;
import ch.heig.amt2021.config.SecurityConfig;
import ch.heig.amt2021.model.*;

import java.util.ArrayList;
import java.util.List;


public class DataDAO {


    // Find a User by userName and password.
    public static UserAccount findUser2(String userName, String password, List<Utilisateur> utilisateurs,
                                        List<Client> clients, List<Administrateur> administrateurs,
                                        List<Vehicule> vehicules, List<Trajet> trajets) {
        UserAccount u;

        for(Utilisateur ut : utilisateurs){
            if(ut.getPassword().equals(password) && ut.getLogin().equals(userName)){

                u = new UserAccount(ut.getLogin(),ut.getPassword(),SecurityConfig.ROLE_EMPLOYEE);
                for(Administrateur ad : administrateurs){
                    if(ad.getId() == ut.getId()){

                        u = new UserAccount(ut.getLogin(), ut.getPassword(),  //
                                SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);
                        u.setAdmin(true);
                    }
                }

                for(Client cl : clients){
                    if(cl.getId() == ut.getId()){
                        u.setSolde(cl.getSolde());
                        u.setTrajetId(cl.getTrajet().getId());
                        for(Trajet tr : trajets){
                            if(tr.getId() == cl.getTrajet().getId()) {
                                u.setTrajetId(tr.getId());
                                for(Vehicule vh: vehicules){
                                    if(vh.getId() == tr.getVehicule().getId()) u.setVehicule(vh);
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

    public static UserAccount getUserViaId(List<UserAccount> listUser, int id){

        for(UserAccount usr: listUser){
            if(usr.getId() == id) return usr;
        }

        return null;

    }

    //Gen??re une liste d'emplacement avec tout les d??tail n??cessaire, gr??ce ?? toute les listes d??riv??es des tables SQL

    public static List<EmplacementUtilisation> GenerationEmplacement(List<Station> stations, List<Emplacement> emplacements, List<Vehicule> vehicules, List<Trajet> trajets){

        List<EmplacementUtilisation> empL = new ArrayList<>();

        int i = 0;
        for(Station st: stations){
            for(Emplacement em : emplacements){
                EmplacementUtilisation empl1 = new EmplacementUtilisation();
                empl1.setAdresse(st.getAdresse());
                empl1.setStation_id(st.getId());
                if(em.getStationId() == st.getId()){
                    i += 1;
                    empl1.setEmplacement_id(em.getId());
                    for(Vehicule vh : vehicules){
                        if(vh.getEmplacement().getId() == em.getId() && vh.getEmplacement().getStationId() == em.getStationId()){
                            empl1.setOccupe(true);
                            empl1.setVehicule(vh);
                        }
                    }
                    for(Trajet tr: trajets){
                        if(tr.getDestination().getId() == em.getId() && tr.getDestination().getStationId() == em.getStationId()){
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

    public static List<UserAccount> listUtilisateursDetail(List<Utilisateur> utilisateurs, List<Client> clients, List<Administrateur> admin, List<Trajet> trajets,List<Vehicule> vehicules) {
        List<UserAccount> userList = new ArrayList<>();

        for(Utilisateur ut : utilisateurs){
            UserAccount user = new UserAccount();
            user.setId(ut.getId());
            user.setUserName(ut.getLogin());
            user.setPassword(ut.getPassword());
            for(Administrateur ad: admin){
                if(ad.getId() == ut.getId()){
                    user.setAdmin(true);
                }
            }
            for(Client cl: clients){
                if(cl.getId() == ut.getId()){
                    user.setSolde(cl.getSolde());
                    for(Trajet tr: trajets){
                        if(tr.getId() == cl.getTrajet().getId()){
                            user.setTrajet(tr);
                            for(Vehicule vh : vehicules){
                                if(vh.getId() == tr.getVehicule_id()){
                                    user.setVehicule(vh);
                                }
                            }
                        }
                    }
                }
            }
            userList.add(user);

        }

        return userList;


    }

    public static List<VehiculeUtilisation> GenerationVehicule(List<Vehicule> vehicules, List<Trajet> trajets, List<Client> clients){

        List<VehiculeUtilisation> vhU = new ArrayList<>();

        for(Vehicule vh: vehicules){
            VehiculeUtilisation veh1 = new VehiculeUtilisation();
            veh1.setId(vh.getId());
            veh1.setMatricule(vh.getMatricule());
            veh1.setCategorie(vh.getCategorie());
            veh1.setEmplacement_id(vh.getEmplacement().getId());
            veh1.setStation_id(vh.getEmplacement().getStationId());
            for(Trajet tr: trajets){
                if(tr.getVehicule_id() == vh.getId()){
                    veh1.setTrajet_id(tr.getVehicule_id());
                    veh1.setReserve(true);
                    for(Client cl : clients){
                        if(cl.getTrajet().getId() == tr.getId()) veh1.setUser_id(cl.getId());
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

    // pour l'affichage avec pagination dans la page admin
    public static List<UserAccount> ViewUser(List<UserAccount> usr, int offset, int noOfPage){

        List<UserAccount> listUsr = new ArrayList<>();

        for(int i = offset; i < offset + noOfPage; i++){
            if(i < usr.size())
                listUsr.add(usr.get(i));
        }
        return listUsr;
    }

    public static List<UserAccount> listClient(List<UserAccount> userTotal, List<Client> client){
        List<UserAccount> listUsr = new ArrayList<>();

        for(UserAccount usr : userTotal) {
            for (Client cl : client) {
                if (cl.getId() == usr.getId()) listUsr.add(usr);
            }
        }


        return listUsr;


    }



    // Retourne une liste uniquement d'emplacement libre
    public static List<EmplacementUtilisation> EmplacementLibre(List<EmplacementUtilisation> emplacement){

        List<EmplacementUtilisation> listEmp = new ArrayList<>();

        for(EmplacementUtilisation emp: emplacement){
            if(!emp.isOccupe() && !emp.isReserve()) listEmp.add(emp);
        }
        return listEmp;
    }

    // Retourne une liste uniquement de v??hicule libre
    public static List<VehiculeUtilisation> VehiculeLibre(List<VehiculeUtilisation> vehicule){

        List<VehiculeUtilisation> listVh = new ArrayList<>();

        for(VehiculeUtilisation vh : vehicule){
            if(!vh.isReserve()){
                listVh.add(vh);
            }
        }
        return listVh;
    }

    //Retourne l'id d'un emplacement libre en raport avec la station choisit, retourne 0 si pas trouv??
    public static int idEmplacementLibre (List<EmplacementUtilisation> EmplacementLibre, int idStation, int idAutreEmplacementId){

        for(EmplacementUtilisation emp : EmplacementLibre){
            if(emp.getStation_id() == idStation && emp.getEmplacement_id() != idAutreEmplacementId) return emp.getEmplacement_id();
        }
        return 0;
    }

    public static float prixVehicule (List<Prix> prix, String categorieUser, int minutes){

                for (Prix pr : prix) {
                    if (pr.getId().equals(categorieUser)) {
                        if(minutes > 0 && minutes < 60)  return pr.getPrix1();
                        else if(minutes > 60 && minutes < 180) return pr.getPrix2();
                        else if(minutes >= 180) return  pr.getPrix3();
                    }
                }

        return 0;
    }


}
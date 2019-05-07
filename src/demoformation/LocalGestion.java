/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoformation;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import formation.DAO.LocalDAO;
import formation.DAO.DAO;
import formation.info.Local;
import myconnections.DBConnection;
import java.util.PropertyResourceBundle;

/**
 *
 * @author valentin.lor
 */
public class LocalGestion {

    public static void main(String[] args) {
        LocalGestion gc = new LocalGestion();
        gc.gestion();

    }

    Scanner sc = new Scanner(System.in);
    Local locActuel = null;
    DAO<Local> localDAO = null;

    public LocalGestion() {

    }

    public void gestion() {

        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }

        System.out.println("------------------------------");
        System.out.println("      Connexion Établie       ");
        System.out.println("------------------------------");

        localDAO = new LocalDAO() {

        };

        localDAO.setConnection(dbConnect);

        int ch = 0;
        do {
            System.out.println("1.Nouveau \n2.Recherche\n3.Modification\n4.Suppression\n5.Recherche sur base de la description\n6.Quitter");
            System.out.print("choix :");
            ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    nouveau();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modif();
                    break;
                case 4:
                    sup();
                    break;
                case 5:
                    rechpartielle();
                case 6:
                    System.out.println("bye");
                    break;
                default:
                    System.out.println("choix incorrect");
            }

        } while (ch != 6);
        DBConnection.closeConnection();
    }

    public void nouveau() {

        System.out.print("Sigle :");
        String sigle = sc.nextLine();
        System.out.print("Places :");
        int places = sc.nextInt();
        sc.skip("\n");
        System.out.print("Description:");
        String description = sc.nextLine();
        locActuel = new Local(0, sigle, places, description);
        try {
            locActuel = localDAO.create(locActuel);
            System.out.println("Vous avez créé le local suivant : ");
            System.out.println("Sigle: " + locActuel.getSigle() + "     Places: " + locActuel.getPlaces() + "       Description: " + locActuel.getDescription());
            //System.out.println("local actuel : " + locActuel);
        } catch (SQLException e) {
            System.out.println("Erreur :" + e);
        }

    }

    public void recherche() {
        try {
            System.out.println("numéro recherché :");
            String nc = sc.nextLine();
            locActuel = localDAO.read(nc);

            System.out.println("Sigle: " + locActuel.getSigle() + "     Places: " + locActuel.getPlaces() + "       Description: " + locActuel.getDescription());
            System.out.println("local actuel : " + locActuel.getSigle());

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void modif() {
        try {
            System.out.println("Sigle :");
            String sigle = sc.nextLine();
            locActuel.setSigle(sigle);
            System.out.println("Places:");
            int places = sc.nextInt();
            sc.skip("\n");
            locActuel.setPlaces(places);
            System.out.println("Description :");
            String description = sc.nextLine();
            locActuel.setDescription(description);
            localDAO.update(locActuel);
            System.out.println("Nouvelles infos du local ID: " + locActuel.getIdlocal() + " | Sigle: " + locActuel.getSigle() + "     Places: " + locActuel.getPlaces() + "       Description: " + locActuel.getDescription());
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }

    public void sup() {
        try {
            System.out.println("id du local à supprimer :");
            int num = sc.nextInt();
            locActuel = localDAO.read(num);

            localDAO.delete(locActuel);

            System.out.println("Vous avez supprimé le local suivant:  ID: " + locActuel.getIdlocal() + "  Sigle" + locActuel.getSigle() + "  Places: " + locActuel.getPlaces() + "   Description: " + locActuel.getDescription());
        } catch (SQLException e) {

            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void rechpartielle() {
        System.out.println("local recherché : ");
        String desc = sc.nextLine();
        try {
            List<Local> alc = ((LocalDAO) localDAO).rechDesc(desc);
            for (Local loc : alc) {
                System.out.println(loc.getDescription());
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }
}

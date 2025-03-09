package ca.cal.tp1;

import ca.cal.tp1.persistance.*;
import ca.cal.tp1.service.EmprunteurService;

import java.time.LocalDate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {
        // Votre script qui utilise votre API ici
        TcpServer.startTcpServer();

        //EmprunteurService emprunteurServiceJDBC = new EmprunteurService(new CdRepositoryJDBC(), new DvdRepositoryJDBC(), new LivreRepositoryJDBC());
        EmprunteurService emprunteurServiceJPA = new EmprunteurService(new EmprunteurRepositoryJPA(), new DocumentRepositoryJPA(), new EmpruntDetailsRepositoryJPA(), new EmpruntRepositoryJPA());



//        emprunteurServiceJDBC.saveLivre("titre JDBC", LocalDate.of(2021, 1, 1),1,"ISBN","auteur","editeur",1);
//        System.out.println(emprunteurServiceJDBC.getLivre(1L));
//
//        emprunteurServiceJDBC.saveCd("titre JDBC",LocalDate.of(2022, 1, 1),1,"artiste",1, "genre");
//        System.out.println(emprunteurServiceJDBC.getCd(1L));
//
//        emprunteurServiceJDBC.saveDvd("titre JDBC",LocalDate.of(2023, 1, 1),1,"directeur", 1, "genre");
//        System.out.println(emprunteurServiceJDBC.getDvd(1L));

        System.out.println("#####################");
        System.out.println("#Section getDocument#");
        System.out.println("#####################");
        emprunteurServiceJPA.saveLivre("titre JPA", LocalDate.of(2021, 1, 1),1,"ISBN","auteur","editeur",1);
        System.out.println(emprunteurServiceJPA.getDocument(1L));

        emprunteurServiceJPA.saveCd("titre JPA",LocalDate.of(2021, 1, 1),1,"artiste",1, "genre");
        System.out.println(emprunteurServiceJPA.getDocument(2L));

        emprunteurServiceJPA.saveDvd("titre JPA",LocalDate.of(2021, 1, 1),1,"directeur", 1, "genre");
        System.out.println(emprunteurServiceJPA.getDocument(3L));

        emprunteurServiceJPA.saveDvd("TITLE JPA",LocalDate.of(2021, 1, 1),1,"directeur", 1, "genre");
        System.out.println(emprunteurServiceJPA.getDocument(4L));

        emprunteurServiceJPA.saveDvd("title JPA",LocalDate.of(2021, 1, 1),1,"directeur", 1, "genre");
        System.out.println(emprunteurServiceJPA.getDocument(5L));


        System.out.println("###########################");
        System.out.println("#Section ajouterEmprunteur#");
        System.out.println("###########################");
        emprunteurServiceJPA.ajouterEmprunteur("nom", "email", "numTelephone");
        System.out.println("########################");
        System.out.println("#Section saveExemplaire#");
        System.out.println("########################");
        emprunteurServiceJPA.saveExemplaire(30, 1L);
        emprunteurServiceJPA.saveExemplaire(30, 2L);

        System.out.println("###########################");
        System.out.println("#Section rechercheDocument#");
        System.out.println("###########################");
        emprunteurServiceJPA.rechercheDocument("titre", LocalDate.of(2021, 1, 1));
        emprunteurServiceJPA.rechercheDocument("titre");
        emprunteurServiceJPA.rechercheDocument("JPA");
        emprunteurServiceJPA.rechercheDocument(LocalDate.of(2021, 1, 1));

        System.out.println("###########################");
        System.out.println("#Section emrpunterDocument#");
        System.out.println("###########################");
        List<Long> list1 = new ArrayList<>();
        list1.add(1L);
        emprunteurServiceJPA.emprunterDocument(list1, 1L);

        List<Long> list2 = new ArrayList<>();
        list2.add(2L);
        list2.add(3L);
        emprunteurServiceJPA.emprunterDocument(list2, 1L);
        list2.add(4L);
        emprunteurServiceJPA.emprunterDocument(list2, 1L);

        System.out.println("################################");
        System.out.println("#Section getDocumentsEmprunteur#");
        System.out.println("################################");
        emprunteurServiceJPA.getDocumentsEmprunteur(1L);

        System.out.println("########################");
        System.out.println("#Section getDocumentFin#");
        System.out.println("########################");
        System.out.println(emprunteurServiceJPA.getDocument(1L));
        System.out.println(emprunteurServiceJPA.getDocument(2L));
        System.out.println(emprunteurServiceJPA.getDocument(3L));

        Thread.currentThread().join();
    }
}
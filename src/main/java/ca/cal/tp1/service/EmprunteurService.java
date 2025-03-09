package ca.cal.tp1.service;

import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.service.DTO.*;
import ca.cal.tp1.persistance.InterfaceRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprunteurService {

    private InterfaceRepository<EmprunteurDTO> emprunteurRepository;
    private InterfaceRepository<DocumentDTO> documentRepository;
    private InterfaceRepository<EmpruntDetailsDTO> empruntDetailsRepository;
    private InterfaceRepository<EmpruntDTO> empruntRepository;

    public EmprunteurService(InterfaceRepository<EmprunteurDTO> emprunteurRepository, InterfaceRepository<DocumentDTO> documentRepository, InterfaceRepository<EmpruntDetailsDTO> empruntDetailsRepository, InterfaceRepository<EmpruntDTO> empruntRepository) {
        this.emprunteurRepository = emprunteurRepository;
        this.documentRepository = documentRepository;
        this.empruntDetailsRepository = empruntDetailsRepository;
        this.empruntRepository = empruntRepository;
    }

    public DocumentDTO getDocument(Long id) {
        return documentRepository.get(id);
    }

    public void saveLivre(String titre, LocalDate anneePublication, int nombreExemplaire, String ISBN, String auteur, String editeur, int nombrePages) {
        documentRepository.save(new LivreDTO(titre, anneePublication, nombreExemplaire, ISBN, auteur, editeur, nombrePages));
    }

    public void saveDvd(String titre, LocalDate anneePublication, int nombreExemplaire, String directeur, int duree, String genre) {
        documentRepository.save(new DvdDTO(titre, anneePublication, nombreExemplaire, directeur, duree, genre));
    }

    public void saveCd(String titre, LocalDate anneePublication, int nombreExemplaire, String artiste, int duree, String genre) {
        documentRepository.save(new CdDTO(titre, anneePublication, nombreExemplaire, artiste, duree, genre));
    }

    public void ajouterEmprunteur(String nom, String email, String numTelephone) {
        emprunteurRepository.save(new EmprunteurDTO(nom, email, numTelephone));
    }

    public void saveExemplaire(int nmbreExemplaire, Long idDocument) {
        DocumentDTO document = documentRepository.get(idDocument);
        document.setDocumentID(idDocument);
        document.setNombreExemplaire(nmbreExemplaire);
        documentRepository.save(document);
    }

    public void rechercheDocument(String titreSubString, LocalDate anneePublication) {
        List<DocumentDTO> documents = documentRepository.get(titreSubString, anneePublication);
        System.out.println("\n \n");
        for (DocumentDTO document : documents) {
            System.out.println(document);
        }
        System.out.println("\n \n");
    }

    public void rechercheDocument(String titreSubString) {
        List<DocumentDTO> documents = documentRepository.get(titreSubString);
        System.out.println("\n \n");
        for (DocumentDTO document : documents) {
            System.out.println(document);
        }
        System.out.println("\n \n");
    }

    public void rechercheDocument(LocalDate anneePublication) {
        List<DocumentDTO> documents = documentRepository.get(anneePublication);
        System.out.println("\n \n");
        for (DocumentDTO document : documents) {
            System.out.println(document);
        }
        System.out.println("\n \n");
    }

    public void emprunterDocument(List<Long> idDocuments, Long idEmprunteur) {
        LocalDate aujourdhui = LocalDate.now();
        List<EmpruntDetailsDTO> empruntDetails = new ArrayList<>();
        EmprunteurDTO emprunteur = emprunteurRepository.get(idEmprunteur);

        EmpruntDTO emprunt = new EmpruntDTO(aujourdhui, "nouveau", empruntDetails, emprunteur);
        for (int i = 0; i < idDocuments.toArray().length; i++) {
            DocumentDTO documentCourant = documentRepository.get(idDocuments.get(i));
            documentCourant.setDocumentID(idDocuments.get(i));
            if (documentCourant.getNombreExemplaire() <= 0)
                return;
            EmpruntDetailsDTO empruntDetailsCourant = new EmpruntDetailsDTO(aujourdhui.plusWeeks(documentCourant.getDureeEmpruntSem()), "nouveau", emprunt, documentCourant);
            empruntDetails.add(empruntDetailsCourant);
        }
        empruntRepository.save(emprunt);
    }

    public void getDocumentsEmprunteur(Long idEmprunteur) {
        EmprunteurDTO emprunteur = emprunteurRepository.get(idEmprunteur);
        List<EmpruntDTO> emprunts = empruntRepository.get(emprunteur);

        for (EmpruntDTO emprunt : emprunts) {
            List<EmpruntDetailsDTO> empruntDetails = empruntDetailsRepository.get(emprunt);
            for (EmpruntDetailsDTO empruntDetail : empruntDetails) {
                System.out.println(empruntDetail);
            }
        }
    }

    public List<EmpruntDetailsDTO> getItems() {
        List<EmpruntDetailsDTO> emprunts = new ArrayList<>();
        return emprunts;
    }
}
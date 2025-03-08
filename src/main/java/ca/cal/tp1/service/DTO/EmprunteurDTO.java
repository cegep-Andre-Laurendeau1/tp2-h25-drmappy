package ca.cal.tp1.service.DTO;
import ca.cal.tp1.modele.Amende;
import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.Emprunteur;

import java.util.List;

public class EmprunteurDTO extends UtilisateurDTO {
    List<Amende> amendes;
    List<Emprunt> emprunts;

    public EmprunteurDTO(String nom, String prenom, String courriel) {
        super(nom, prenom, courriel);
        this.amendes = null;
        this.emprunts = null;
    }
    public Emprunteur toModele() {
        return new Emprunteur(this.getNom(), this.getEmail(), this.getNumTelephone());
    }
}

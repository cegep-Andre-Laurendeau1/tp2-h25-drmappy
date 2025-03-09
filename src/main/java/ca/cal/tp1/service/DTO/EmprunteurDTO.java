package ca.cal.tp1.service.DTO;
import ca.cal.tp1.modele.Amende;
import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.Emprunteur;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
public class EmprunteurDTO extends UtilisateurDTO {
    List<Amende> amendes;
    List<Emprunt> emprunts;

    public EmprunteurDTO(Long id, String nom, String courriel, String numTelephone, List<Amende> amendes, List<Emprunt> emprunts){
        super(id,nom,courriel, numTelephone);
        this.amendes = amendes;
        this.emprunts = emprunts;
    }
    public EmprunteurDTO(String nom, String courriel, String numTelephone, List<Amende> amendes, List<Emprunt> emprunts) {
        super(nom, courriel, numTelephone);
        this.amendes = amendes;
        this.emprunts = emprunts;
    }
    public EmprunteurDTO(String nom, String courriel, String numTelephone){
        super(nom, courriel, numTelephone);
        this.amendes = null;
        this.emprunts = new ArrayList<>();
    }
    public Emprunteur toModele() {
        return new Emprunteur(this.getId(), this.getNom(), this.getEmail(), this.getNumTelephone());
    }

    @Override
    public String toString() {
        return "EmprunteurDTO{" +
                "id="+getId()+
                "nom="+getNom()+
                "prenom="+getEmail()+
                "numTelephone="+getNumTelephone()+
                "amendes=" + amendes +
                '}';
    }
}

package ca.cal.tp1.modele;

import ca.cal.tp1.service.DTO.EmprunteurDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Emprunteur extends Utilisateur {
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    List<Amende> amendes;
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    List<Emprunt> emprunts;
    public Emprunteur(String nom, String email, String  numTelephone) {
        super(nom, email, numTelephone);
    }
    public Emprunteur(Long id, String nom, String email, String numTelephone) {
        super(id, nom, email, numTelephone);
        this.amendes = amendes;
        this.emprunts = emprunts;
    }
    public EmprunteurDTO toEmprunteurDTO() {
        return new EmprunteurDTO(this.getId(), this.getNom(), this.getEmail(), this.getNumTelephone(), null, this.getEmprunts());
    }

    @Override
    public String toString() {
        return "Emprunteur{" +
                "nom='" + getNom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", numTelephone='" + getNumTelephone() + '\'' +
                "amendes=" + amendes +
                ", emprunts=" + emprunts +
                '}';
    }
}

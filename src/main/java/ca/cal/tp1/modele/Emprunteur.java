package ca.cal.tp1.modele;

import ca.cal.tp1.service.DTO.EmprunteurDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Emprunteur extends Utilisateur {
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    List<Amende> amendes;
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    List<Emprunt> emprunts;
    public Emprunteur(String nom, String email, String numTelephone) {
        super(nom, email, numTelephone);
    }

    public EmprunteurDTO toDTO() {
        return new EmprunteurDTO(this.getNom(), this.getEmail(), this.getNumTelephone());
    }
}

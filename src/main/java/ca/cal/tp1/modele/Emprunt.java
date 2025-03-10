package ca.cal.tp1.modele;

import ca.cal.tp1.service.DTO.EmpruntDTO;
import ca.cal.tp1.service.DTO.EmpruntDetailsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emprunt {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dateEmprunt;
    private String status;
    @ManyToOne
    private  Emprunteur emprunteur = null;
    @OneToMany
    private List<EmpruntDetails> empruntDetails = new ArrayList<>();

    public Emprunt(LocalDate dateEmprunt, String status, List<EmpruntDetails> empruntDetails, Emprunteur emprunteur) {
        this.dateEmprunt = dateEmprunt;
        this.status = status;
        this.empruntDetails = empruntDetails;
        this.emprunteur = emprunteur;
    }
    public Emprunt(LocalDate dateEmprunt, String status, Emprunteur emprunteur) {
        this.dateEmprunt = dateEmprunt;
        this.status = status;
        this.emprunteur = emprunteur;
    }
    public EmpruntDTO toEmpruntDTO() {
        List<EmpruntDetailsDTO> empruntDetailsDTO = new ArrayList<>();
        System.out.println(empruntDetails);
        for (EmpruntDetails empruntDetails : this.empruntDetails) {
            empruntDetailsDTO.add(empruntDetails.toEmpruntDetailsDTO());
        }
        return new EmpruntDTO(this.dateEmprunt, this.status, empruntDetailsDTO, this.emprunteur.toEmprunteurDTO());
    }
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateEmprunt=" + dateEmprunt +
                ", status='" + status + '\'' +
                ", emprunteur=" + emprunteur +
                ", empruntDetails=" + empruntDetails +

                '}';
    }

    public void addEmpruntDetails(EmpruntDetails empruntDetails) {
        this.empruntDetails.add(empruntDetails);
    }
}
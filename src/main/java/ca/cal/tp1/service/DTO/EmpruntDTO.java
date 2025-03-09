package ca.cal.tp1.service.DTO;

import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.EmpruntDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmpruntDTO {
    private Long id;
    private LocalDate dateEmprunt;
    private String status;
    private EmprunteurDTO emprunteur;

    private List<EmpruntDetailsDTO> empruntDetails;

    public EmpruntDTO(LocalDate dateEmprunt, String status, List<EmpruntDetailsDTO> empruntDetails, EmprunteurDTO emprunteur) {
        this.dateEmprunt = dateEmprunt;
        this.status = status;
        this.empruntDetails = empruntDetails;
        this.emprunteur = emprunteur;
    }
    public EmpruntDTO(LocalDate dateEmprunt, String status, EmprunteurDTO emprunteur) {
        this.dateEmprunt = dateEmprunt;
        this.status = status;
        this.emprunteur = emprunteur;
    }
    public Emprunt toModele() {
        List<EmpruntDetails> empruntDetails = new ArrayList<>();
        for (EmpruntDetailsDTO empruntDetailsDTO : this.empruntDetails) {
            empruntDetails.add(empruntDetailsDTO.toModele());
        }
        return new Emprunt(this.dateEmprunt, this.status, empruntDetails, this.emprunteur.toModele());
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
    public void setEmpruntDetails(List<EmpruntDetailsDTO> empruntDetails) {
        this.empruntDetails = empruntDetails;
    }
}

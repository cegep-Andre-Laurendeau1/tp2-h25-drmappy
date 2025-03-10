package ca.cal.tp1.service.DTO;

import ca.cal.tp1.modele.EmpruntDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmpruntDetailsDTO {
    private Long id;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourActuelle;
    private String status;

    private EmpruntDTO emprunt;

    private DocumentDTO document;

    public EmpruntDetailsDTO(LocalDate dateRetourPrevue, LocalDate dateRetourActuelle, String status) {
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
    }

    public EmpruntDetailsDTO(LocalDate dateRetourPrevue, String status, EmpruntDTO emprunt, DocumentDTO document) {
        this.dateRetourPrevue = dateRetourPrevue;
        this.status = status;
        this.emprunt = emprunt;
        this.document = document;
    }

    public EmpruntDetailsDTO(Long id, LocalDate dateRetourPrevue, LocalDate dateRetourActuelle, String status) {
        this.id = id;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
    }

    public EmpruntDetails toModele() {
        if(this.document == null) {
            return new EmpruntDetails(this.dateRetourPrevue, this.dateRetourActuelle, this.status);
        }
        return new EmpruntDetails(this.dateRetourPrevue, this.dateRetourActuelle, this.status, this.document.toModele());
    }


    public Long getId() {
        return id;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDate getDateRetourActuelle() {
        return dateRetourActuelle;
    }

    public String getStatus() {
        return status;
    }

    public String toString() {
        return "EmpruntDetails{" +
                "id=" + id +
                ", dateRetourPrevue=" + dateRetourPrevue +
                ", dateRetourActuelle=" + dateRetourActuelle +
                ", status='" + status + '\'' +
                ", document=" + document +
                '}';
    }
}
package ca.cal.tp1.modele;

import ca.cal.tp1.service.DTO.EmpruntDetailsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmpruntDetails {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourActuelle;
    private String status;

    @ManyToOne
    private Emprunt emprunt;

    @ManyToOne
    private Document document;

    public Document getDocument() {
        return document;
    }

    public EmpruntDetails(LocalDate dateRetourPrevue, LocalDate dateRetourActuelle, String status) {
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
    }
    public EmpruntDetails(LocalDate dateRetourPrevue, String status,  Document document) {
        this.dateRetourPrevue = dateRetourPrevue;
        this.status = status;
        this.document = document;
    }

    public EmpruntDetails(LocalDate dateRetourPrevue, LocalDate dateRetourActuelle, String status, Emprunt emprunt, Document document) {
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
        this.emprunt = emprunt;
        this.document = document;
    }

    public EmpruntDetails(LocalDate dateRetourPrevue, LocalDate dateRetourActuelle, String status, Document document) {
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
        this.document = document;
    }

    public EmpruntDetails(LocalDate dateRetourPrevue, String status, Emprunt empruntSansDetails, Document document) {
        this.dateRetourPrevue = dateRetourPrevue;
        this.status = status;
        this.emprunt = empruntSansDetails;
        this.document = document;
    }

    public EmpruntDetailsDTO toEmpruntDetailsDTO() {
        return new EmpruntDetailsDTO(this.dateRetourPrevue, this.dateRetourActuelle, this.status);
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

package ca.cal.tp1.modele;

import ca.cal.tp1.service.DTO.DocumentDTO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Document {

    @Id
    @GeneratedValue
    private Long id;

    private String titre;
    private LocalDate anneePublication;
    private int nombreExemplaire;
    public Document( String titre, LocalDate anneePublication, int nombreExemplaire){
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.nombreExemplaire = nombreExemplaire;
    }
    public Document(Long id, String titre, LocalDate anneePublication, int nombreExemplaire){
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.nombreExemplaire = nombreExemplaire;
        this.id = id;
    }
    public abstract DocumentDTO toDTO();
    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public LocalDate getAnneePublication() {
        return anneePublication;
    }

    public int getNombreExemplaire() {
        return nombreExemplaire;
    }
    public void setNombreExemplaire(int nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }
    public abstract int getDureeEmpruntSem() ;
    @Override
    public String toString() {
        return "Document{" +
                "documentID=" + id +
                ", titre='" + titre + '\'' +
                ", anneePublication=" + anneePublication +
                ", nombreExemplaire=" + nombreExemplaire +
                '}';
    }
}
package ca.cal.tp1.modele;

import ca.cal.tp1.service.DTO.DvdDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class Dvd extends Document{
    @Id
    @GeneratedValue
    private Long id;
    private String directeur;
    private int duree;
    private String genre;
    private final int dureeEmpruntSem = 1;

    public Dvd(Long id, String titre, LocalDate anneePublication, int nombreExemplaire, String directeur, int duree, String genre) {
        super(titre, anneePublication, nombreExemplaire);
        this.id = id;
        this.directeur = directeur;
        this.duree = duree;
        this.genre = genre;
    }
    public Dvd(String titre, LocalDate anneePublication, int nombreExemplaire, String directeur, int duree, String genre) {
        super(titre, anneePublication, nombreExemplaire);
        this.directeur = directeur;
        this.duree = duree;
        this.genre = genre;
    }

    public String getDirecteur() {
        return directeur;
    }

    public int getDuree() {
        return duree;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public DvdDTO toDTO() {
        return new DvdDTO(this.id, this.getTitre(), this.getAnneePublication(), this.getNombreExemplaire(), this.directeur, this.duree, this.genre);
    }



    public int getDureeEmpruntSem() {
        return dureeEmpruntSem;
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "id=" + getId() +
                ", titre='" + getTitre() + '\'' +
                ", anneePublication=" + getAnneePublication() +
                ", nombreExemplaire=" + getNombreExemplaire() +
                "directeur='" + directeur + '\'' +
                ", duree=" + duree +
                ", genre='" + genre + '\'' +
                ", dureeEmpruntSem=" + dureeEmpruntSem +
                '}';
    }
}

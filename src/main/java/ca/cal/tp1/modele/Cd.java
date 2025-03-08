package ca.cal.tp1.modele;

import ca.cal.tp1.service.DTO.CdDTO;
import ca.cal.tp1.service.DTO.DocumentDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class Cd extends Document{
    @Id
    @GeneratedValue
    private Long id;
    private String artiste;
    private int duree;
    private String genre;
    private final int dureeEmpruntSem = 2;

    public Cd(String titre, LocalDate anneePublication, int nombreExemplaire, String artiste, int duree, String genre) {
        super(titre, anneePublication, nombreExemplaire);
        this.artiste = artiste;
        this.duree = duree;
        this.genre = genre;
    }

    public Cd(Long id, String titre, LocalDate anneePublication, int nombreExemplaire, String artiste, int duree, String genre) {
        super(id, titre, anneePublication, nombreExemplaire);
        this.artiste = artiste;
        this.duree = duree;
        this.genre = genre;
    }

    public int getDuree() {
        return duree;
    }


    public String getGenre() {
        return genre;
    }

    @Override
    public DocumentDTO toDTO() {
        return new CdDTO(this.id, this.getTitre(), this.getAnneePublication(), this.getNombreExemplaire(), this.artiste, this.duree, this.genre);
    }

    public int getDureeEmpruntSem() {
        return dureeEmpruntSem;
    }

    public String getArtiste() {
        return artiste;
    }

    @Override
    public String toString() {
        return "Cd{" +
                "id=" + getId() +
                ", titre='" + getTitre() + '\'' +
                ", anneePublication=" + getAnneePublication() +
                ", nombreExemplaire=" + getNombreExemplaire() +
                "artiste='" + artiste + '\'' +
                ", duree=" + duree +
                ", genre='" + genre + '\'' +
                ", dureeEmpruntSem=" + dureeEmpruntSem +
                '}';
    }

}

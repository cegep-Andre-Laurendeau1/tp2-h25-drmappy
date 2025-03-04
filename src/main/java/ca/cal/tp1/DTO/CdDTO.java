package ca.cal.tp1.DTO;

import ca.cal.tp1.modele.Cd;

import java.time.LocalDate;


public class CdDTO extends DocumentDTO {
    private String artiste;
    private int duree;
    private String genre;
    private final int dureeEmpruntSem = 2;

    public CdDTO(Long id, String titre, LocalDate anneePublication, int nombreExemplaire, String artiste, int duree, String genre) {
        super(id, titre, anneePublication, nombreExemplaire);
        this.artiste = artiste;
        this.duree = duree;
        this.genre = genre;
    }
    public CdDTO(Cd cd){
        super(cd.getId(), cd.getTitre(), cd.getAnneePublication(), cd.getNombreExemplaire());
        this.artiste = cd.getArtiste();
        this.duree = cd.getDuree();
        this.genre = cd.getGenre();
    }

    public int getDuree() {
        return duree;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getGenre() {
        return genre;
    }

    public int getDureeEmpruntSem() {
        return dureeEmpruntSem;
    }

    @Override
    public String toString() {
        return "Cd{" +
                "id=" + getId() +
                ", titre='" + getTitre() + '\'' +
                ", anneePublication=" + getAnneePublication() +
                ", nombreExemplaire=" + getNombreExemplaire() +
                "director='" + artiste + '\'' +
                ", duree=" + duree +
                ", genre='" + genre + '\'' +
                ", dureeEmpruntSem=" + dureeEmpruntSem +
                '}';
    }
}

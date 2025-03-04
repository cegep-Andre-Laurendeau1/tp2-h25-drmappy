package ca.cal.tp1.DTO;

import java.time.LocalDate;


public abstract class DocumentDTO {
    private Long id;
    private String titre;
    private LocalDate anneePublication;
    private int nombreExemplaire;
    public DocumentDTO(Long id, String titre, LocalDate anneePublication, int nombreExemplaire){
        this.id = id;
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.nombreExemplaire = nombreExemplaire;
    }
    public long getId() {
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

    public void setDocumentID(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnneePublication(LocalDate anneePublication) {
        this.anneePublication = anneePublication;
    }

    public void setNombreExemplaire(int nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }

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
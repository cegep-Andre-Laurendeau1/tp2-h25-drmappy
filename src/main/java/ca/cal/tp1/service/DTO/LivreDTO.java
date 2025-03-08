package ca.cal.tp1.service.DTO;

import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Livre;

import java.time.LocalDate;

public class LivreDTO extends DocumentDTO {
    private String ISBN;
    private String auteur;
    private String editeur;
    private int nombrePages;
    private final int dureeEmpruntSem = 3;

    public LivreDTO(Long id, String titre, LocalDate anneePublication, int nombreExemplaire, String ISBN, String auteur, String editeur, int nombrePages) {
        super(id, titre, anneePublication, nombreExemplaire);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nombrePages = nombrePages;
    }
    public LivreDTO(LivreDTO livre){
        super(livre.getId(), livre.getTitre(), livre.getAnneePublication(), livre.getNombreExemplaire());
        this.ISBN = livre.getISBN();
        this.auteur = livre.getAuteur();
        this.editeur = livre.getEditeur();
        this.nombrePages = livre.getNombrePages();
    }
    public Livre toLivre(){
        return new Livre(getId(), getTitre(), getAnneePublication(), getNombreExemplaire(), ISBN, auteur, editeur, nombrePages);
    }
    public LivreDTO(String titre, LocalDate anneePublication, int nombreExemplaire, String ISBN, String auteur, String editeur, int nombrePages){
        super(titre, anneePublication, nombreExemplaire);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nombrePages = nombrePages;
    }


    public String getISBN() {
        return ISBN;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public int getNombrePages() {
        return nombrePages;
    }

    public int getDureeEmpruntSem() {
        return dureeEmpruntSem;
    }

    @Override
    public Document toModele() {
        if (getId() == null)
            return new Livre(getTitre(), getAnneePublication(), getNombreExemplaire(), ISBN, auteur, editeur, nombrePages);
        return new Livre(getId(), getTitre(), getAnneePublication(), getNombreExemplaire(), ISBN, auteur, editeur, nombrePages);
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + getId()+
                ", titre='" + getTitre() + '\'' +
                ", anneePublication=" + getAnneePublication() +
                ", nombreExemplaire=" + getNombreExemplaire() +
                "ISBN='" + ISBN + '\'' +
                ", auteur='" + auteur + '\'' +
                ", editeur='" + editeur + '\'' +
                ", nombrePages=" + nombrePages +
                ", dureeEmpruntSem=" + dureeEmpruntSem +
                '}';
    }
}

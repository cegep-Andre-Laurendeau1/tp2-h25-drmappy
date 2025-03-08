package ca.cal.tp1.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String email;
    private String numTelephone;
    public UtilisateurDTO(String nom, String email, String numTelephone){
        this.nom = nom;
        this.email = email;
        this.numTelephone = numTelephone;
    }
}

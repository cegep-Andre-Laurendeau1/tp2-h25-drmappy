package ca.cal.tp1.persistance;

import ca.cal.tp1.DTO.LivreDTO;
import ca.cal.tp1.modele.Livre;

import java.sql.PreparedStatement;

public class LivreRepositoryJDBC extends RepositoryParent implements InterfaceRepository<Livre> {

    @Override
    public void save(Livre livre) {
        String sql = "INSERT INTO livre (id, titre, anneePublication, nombreExemplaire, ISBN, auteur, editeur, nombrePages) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, livre.getId());
            ps.setString(2, livre.getTitre());
            ps.setDate(3, java.sql.Date.valueOf(livre.getAnneePublication()));
            ps.setInt(4, livre.getNombreExemplaire());
            ps.setString(5, livre.getISBN());
            ps.setString(6, livre.getAuteur());
            ps.setString(7, livre.getEditeur());
            ps.setInt(8, livre.getNombrePages());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Livre get(Long id) {
        String sql = "SELECT * FROM livre WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            var result = ps.executeQuery();
            if(result.next()){
                return new Livre(
                        result.getLong("id"),
                        result.getString("titre"),
                        result.getDate("anneePublication").toLocalDate(),
                        result.getInt("nombreExemplaire"),
                        result.getString("ISBN"),
                        result.getString("auteur"),
                        result.getString("editeur"),
                        result.getInt("nombrePages")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM livre WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
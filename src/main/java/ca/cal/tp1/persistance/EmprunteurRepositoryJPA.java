package ca.cal.tp1.persistance;

import ca.cal.tp1.service.DTO.EmpruntDTO;
import ca.cal.tp1.service.DTO.EmprunteurDTO;
import ca.cal.tp1.modele.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class EmprunteurRepositoryJPA implements InterfaceRepository<EmprunteurDTO> {
    private final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("orders.pu");
    @Override
    public void save(EmprunteurDTO emprunteur) {

        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            entityManager.persist(emprunteur.toModele());
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public EmprunteurDTO get(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<Emprunteur> query = entityManager.createQuery(
                    "SELECT emprunteur FROM Emprunteur emprunteur " +
                            "WHERE emprunteur.id = :id", Emprunteur.class);
            query.setParameter("id", id);
            query.getSingleResult();
            entityManager.getTransaction().commit();
            return query.getSingleResult().toEmprunteurDTO();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmprunteurDTO> get(String titreSubString, LocalDate annePublication) {
        return List.of();
    }

    @Override
    public List<EmprunteurDTO> get(String titreSubString) {
        return List.of();
    }

    @Override
    public List<EmprunteurDTO> get(LocalDate annePublication) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<EmprunteurDTO> get(EmprunteurDTO emprunteur) {
        return List.of();
    }

    @Override
    public List<EmprunteurDTO> get(EmpruntDTO emprunt) {
        return List.of();
    }


}

package ca.cal.tp1.persistance;

import ca.cal.tp1.service.DTO.EmpruntDTO;
import ca.cal.tp1.service.DTO.EmprunteurDTO;
import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.EmpruntDetails;
import ca.cal.tp1.service.DTO.EmpruntDetailsDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDetailsRepositoryJPA implements InterfaceRepository<EmpruntDetailsDTO> {
    private final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("orders.pu");
    @Override
    public void save(EmpruntDetailsDTO EmpruntDetailsDTO) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            if(get(EmpruntDetailsDTO.getId()) != null){
                entityManager.merge(EmpruntDetailsDTO.toModele());
            }
            else {
                entityManager.persist(EmpruntDetailsDTO.toModele());
            }

            entityManager.getTransaction().commit();
        }
    }

    @Override
    public EmpruntDetailsDTO get(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<EmpruntDetails> query = entityManager.createQuery(
                    "SELECT EmpruntDetails FROM EmpruntDetails EmpruntDetails " +
                            "WHERE EmpruntDetails.id = :id", EmpruntDetails.class);
            query.setParameter("id", id);
            query.getSingleResult();
            entityManager.getTransaction().commit();
            return query.getSingleResult().toEmpruntDetailsDTO();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<EmpruntDetailsDTO> get(Emprunt emprunt) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<EmpruntDetails> query = entityManager.createQuery(
                    "SELECT EmpruntDetails FROM EmpruntDetails EmpruntDetails " +
                            "WHERE EmpruntDetails.emprunt = :emprunt", EmpruntDetails.class);
            query.setParameter("emprunt", emprunt);
            query.getResultList();
            entityManager.getTransaction().commit();
            List<EmpruntDetailsDTO> empruntDetailsDTOS = new ArrayList<>();
            for (int i = 0; i < query.getResultList().toArray().length; i++) {
                empruntDetailsDTOS.add(query.getResultList().get(i).toEmpruntDetailsDTO());
            }
            return empruntDetailsDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<EmpruntDetailsDTO> get(String titreSubString, LocalDate annePublication) {
        return List.of();
    }

    @Override
    public List<EmpruntDetailsDTO> get(String titreSubString) {
        return List.of();
    }

    @Override
    public List<EmpruntDetailsDTO> get(LocalDate annePublication) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<EmpruntDetailsDTO> get(EmprunteurDTO emprunteur) {
        return List.of();
    }

    @Override
    public List<EmpruntDetailsDTO> get(EmpruntDTO emprunt) {
        return List.of();
    }

}

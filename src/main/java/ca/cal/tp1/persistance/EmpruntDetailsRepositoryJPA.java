package ca.cal.tp1.persistance;

import ca.cal.tp1.modele.Emprunteur;
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
    public void save(EmpruntDetailsDTO empruntDetailsDTO) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();

            Emprunt emprunt = empruntDetailsDTO.getEmprunt().toModele();
            if (emprunt.getId() == null) {
                entityManager.persist(emprunt); // Ensure Emprunt is saved if it is new
            } else {
                emprunt = entityManager.merge(emprunt); // Merge the detached Emprunt instance
            }

            EmpruntDetails empruntDetails = empruntDetailsDTO.toModele();
            empruntDetails.setEmprunt(emprunt); // Set the saved or merged Emprunt instance

            if (get(empruntDetailsDTO.getId()) != null) {
                entityManager.merge(empruntDetails);
            } else {
                entityManager.persist(empruntDetails);
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
        List<EmpruntDetailsDTO> e = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            Emprunt empruntEntity = emprunt.toModele();
            if (empruntEntity.getId() == null) {
                entityManager.persist(empruntEntity); // Ensure Emprunt is saved if it is new
            } else {
                empruntEntity = entityManager.merge(empruntEntity); // Merge the detached Emprunt instance
            }
            TypedQuery<EmpruntDetails> query = entityManager.createQuery(
                    "SELECT EmpruntDetails FROM EmpruntDetails EmpruntDetails " +
                            "WHERE EmpruntDetails.emprunt = :emprunt", EmpruntDetails.class);
            query.setParameter("emprunt", empruntEntity);
            entityManager.getTransaction().commit();
            for (int i = 0; i < query.getResultList().toArray().length; i++) {
                e.add(query.getResultList().get(i).toEmpruntDetailsDTO());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }
}
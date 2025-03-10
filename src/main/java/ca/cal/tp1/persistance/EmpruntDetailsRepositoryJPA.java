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

            if (empruntDetails.getId() == null) {
                entityManager.persist(empruntDetails); // Save the new EmpruntDetails instance
            } else {
                entityManager.merge(empruntDetails); // Merge the detached EmpruntDetails instance
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
            EmpruntDetails empruntDetails = query.getSingleResult();
            entityManager.getTransaction().commit();
            return empruntDetails.toEmpruntDetailsDTO();
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
            List<EmpruntDetails> resultList = query.getResultList();
            entityManager.getTransaction().commit();
            List<EmpruntDetailsDTO> empruntDetailsDTOS = new ArrayList<>();
            for (EmpruntDetails details : resultList) {
                empruntDetailsDTOS.add(details.toEmpruntDetailsDTO());
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
            Emprunt empruntModele = emprunt.toModele();
            if (empruntModele.getId() == null) {
                entityManager.persist(empruntModele); // Ensure Emprunt is saved if it is new
            } else {
                empruntModele = entityManager.merge(empruntModele); // Merge the detached Emprunt instance
            }
            TypedQuery<EmpruntDetails> query = entityManager.createQuery(
                    "SELECT EmpruntDetails FROM EmpruntDetails EmpruntDetails " +
                            "WHERE EmpruntDetails.emprunt = :emprunt", EmpruntDetails.class);
            query.setParameter("emprunt", empruntModele);
            for (int i = 0; i < empruntModele.getEmpruntDetails().toArray().length; i++) {
                EmpruntDetails empruntDetails = empruntModele.getEmpruntDetails().get(i);
                if (empruntDetails.getId() == null) {
                    entityManager.persist(empruntDetails); // Ensure EmpruntDetails is saved if it is new
                } else {
                    empruntDetails = entityManager.merge(empruntDetails); // Merge the detached EmpruntDetails instance
                }
            }
            List<EmpruntDetails> resultList = query.getResultList();
            entityManager.getTransaction().commit();
            for (EmpruntDetails details : resultList) {
                e.add(details.toEmpruntDetailsDTO());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }
}
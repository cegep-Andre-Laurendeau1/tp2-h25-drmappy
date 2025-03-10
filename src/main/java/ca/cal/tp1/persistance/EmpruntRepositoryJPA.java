package ca.cal.tp1.persistance;

import ca.cal.tp1.modele.*;
import ca.cal.tp1.service.DTO.EmpruntDTO;
import ca.cal.tp1.service.DTO.EmpruntDetailsDTO;
import ca.cal.tp1.service.DTO.EmprunteurDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntRepositoryJPA implements InterfaceRepository<EmpruntDTO> {
    private final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("orders.pu");

    @Override
    public void save(EmpruntDTO emprunt) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Emprunteur emprunteur = emprunt.getEmprunteur().toModele();
            Emprunteur mergedEmprunteur = entityManager.merge(emprunteur);
            Emprunt empruntSansDetails = new Emprunt(emprunt.getDateEmprunt(), emprunt.getStatus(), mergedEmprunteur);
            entityManager.persist(empruntSansDetails);

            for (EmpruntDetailsDTO detailsDTO : emprunt.getEmpruntDetails()) {
                EmpruntDetails empruntDetailsCourant = detailsDTO.toModele();
                Document documentCourant = empruntDetailsCourant.getDocument();
                if (documentCourant != null) {
                    documentCourant.setNombreExemplaire(documentCourant.getNombreExemplaire() - 1);
                    Document mergedDocument = entityManager.merge(documentCourant);
                    EmpruntDetails empruntDetails = new EmpruntDetails(empruntDetailsCourant.getDateRetourPrevue(), empruntDetailsCourant.getDateRetourActuelle(), empruntDetailsCourant.getStatus(), empruntSansDetails, mergedDocument);
                    entityManager.persist(empruntDetails);
                    empruntSansDetails.addEmpruntDetails(empruntDetails); // Ensure EmpruntDetails is added to Emprunt
                } else {
                    throw new IllegalArgumentException("Document cannot be null");
                }
            }
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public EmpruntDTO get(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<Emprunt> query = entityManager.createQuery(
                    "SELECT emprunt FROM Emprunt emprunt " +
                            "WHERE emprunt.id = :id", Emprunt.class);
            query.setParameter("id", id);
            Emprunt emprunt = null;
            try {
                emprunt = query.getSingleResult();
            } catch (NoResultException e) {
                System.out.println("No emprunt found with id: " + id);
            }
            entityManager.getTransaction().commit();
            return emprunt != null ? emprunt.toEmpruntDTO() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmpruntDTO> get(EmprunteurDTO utilisateur) {
        Utilisateur user = utilisateur.toModele();
        List<EmpruntDTO> empruntDTOS = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            TypedQuery<Emprunt> query = entityManager.createQuery(
                    "SELECT emprunt FROM Emprunt emprunt " +
                            "WHERE emprunt.emprunteur = :user", Emprunt.class);
            query.setParameter("user", user);
            List<Emprunt> resultList = query.getResultList();
            entityManager.getTransaction().commit();
            for (Emprunt emprunt : resultList) {
                System.out.println(emprunt);
                emprunt.setEmpruntDetails(getEmpruntDetails(emprunt));
                empruntDTOS.add(emprunt.toEmpruntDTO());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empruntDTOS;
    }

    public List<EmpruntDetails> getEmpruntDetails(Emprunt emprunt) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            TypedQuery<EmpruntDetails> query = entityManager.createQuery(
                    "SELECT ed FROM EmpruntDetails ed WHERE ed.emprunt = :emprunt", EmpruntDetails.class);
            query.setParameter("emprunt", emprunt);
            List<EmpruntDetails> resultList = query.getResultList();
            entityManager.getTransaction().commit();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<EmpruntDTO> get(EmpruntDTO emprunt) {
        return List.of();
    }

    @Override
    public List<EmpruntDTO> get(String titreSubString, LocalDate annePublication) {
        return List.of();
    }

    @Override
    public List<EmpruntDTO> get(String titreSubString) {
        return List.of();
    }

    @Override
    public List<EmpruntDTO> get(LocalDate annePublication) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
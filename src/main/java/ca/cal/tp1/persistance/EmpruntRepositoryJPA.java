package ca.cal.tp1.persistance;

import ca.cal.tp1.modele.Emprunteur;
import ca.cal.tp1.service.DTO.EmpruntDTO;
import ca.cal.tp1.service.DTO.EmpruntDetailsDTO;
import ca.cal.tp1.service.DTO.EmprunteurDTO;
import ca.cal.tp1.modele.Document;
import ca.cal.tp1.modele.Emprunt;
import ca.cal.tp1.modele.EmpruntDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
            entityManager.persist(emprunteur); // Save the Emprunteur instance first
            Emprunt empruntSansDetails = new Emprunt(emprunt.getDateEmprunt(), emprunt.getStatus(), emprunteur);
            entityManager.persist(empruntSansDetails);

            for (EmpruntDetailsDTO detailsDTO : emprunt.getEmpruntDetails()) {
                EmpruntDetails empruntDetailsCourant = detailsDTO.toModele();
                Document documentCourant = empruntDetailsCourant.getDocument();
                documentCourant.setNombreExemplaire(documentCourant.getNombreExemplaire() - 1);
                EmpruntDetails empruntDetails = new EmpruntDetails(empruntDetailsCourant.getDateRetourPrevue(), empruntDetailsCourant.getDateRetourActuelle(), empruntDetailsCourant.getStatus(), empruntSansDetails, empruntDetailsCourant.getDocument());
                entityManager.persist(empruntDetails);

                entityManager.merge(documentCourant);
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
            query.getSingleResult();
            entityManager.getTransaction().commit();
            return query.getSingleResult().toEmpruntDTO();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<EmpruntDTO> get(EmprunteurDTO emprunteur) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<Emprunt> query = entityManager.createQuery(
                    "SELECT emprunt FROM Emprunt emprunt " +
                            "WHERE emprunt.emprunteur.id = :idEmprunteur", Emprunt.class);
            query.setParameter("idEmprunteur", emprunteur.getId());
            query.getResultList();
            entityManager.getTransaction().commit();
            List<EmpruntDTO> empruntDTOS = new ArrayList<>();
            for (Emprunt emprunt : query.getResultList()) {
                empruntDTOS.add(emprunt.toEmpruntDTO());
            }
            return empruntDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

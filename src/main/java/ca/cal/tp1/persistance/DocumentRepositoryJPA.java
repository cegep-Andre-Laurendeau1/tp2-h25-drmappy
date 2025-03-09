package ca.cal.tp1.persistance;

import ca.cal.tp1.service.DTO.DocumentDTO;
import ca.cal.tp1.service.DTO.EmpruntDTO;
import ca.cal.tp1.service.DTO.EmprunteurDTO;
import ca.cal.tp1.modele.Document;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepositoryJPA implements InterfaceRepository<DocumentDTO> {
    private final EntityManagerFactory entityManagerFactory=
            Persistence.createEntityManagerFactory("orders.pu");
    @Override
    public void save(DocumentDTO document) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            Document documentModele = document.toModele();
            entityManager.getTransaction().begin();
            if(get(documentModele.getId()) != null){
                entityManager.merge(documentModele);
            }
            else {
                entityManager.persist(documentModele);
            }

            entityManager.getTransaction().commit();
        }
    }

    @Override
    public DocumentDTO get(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<Document> query = entityManager.createQuery(
                    "SELECT document FROM Document document " +
                            "WHERE document.id = :id", Document.class);
            query.setParameter("id", id);
            query.getSingleResult();
            entityManager.getTransaction().commit();
            return query.getSingleResult().toDTO();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<DocumentDTO> get(EmprunteurDTO emprunteur) {
        return List.of();
    }

    @Override
    public List<DocumentDTO> get(EmpruntDTO emprunt) {
        return List.of();
    }


    public List<DocumentDTO> get(String titreSubString, LocalDate annePublication) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<Document> query = entityManager.createQuery(
                    "SELECT document FROM Document document " +
                            "WHERE document.titre LIKE :titreSubString " +
                            "AND document.anneePublication = :anneePublication ", Document.class);
            query.setParameter("titreSubString", "%"+titreSubString+"%");
            query.setParameter("anneePublication", annePublication);
            entityManager.getTransaction().commit();
            List<DocumentDTO> doc = new ArrayList<>();
            for (int i = 0; i < query.getResultList().toArray().length; i++) {
                doc.add(query.getResultList().get(i).toDTO());
            }
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentDTO> get(String titreSubString) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<Document> query = entityManager.createQuery(
                    "SELECT document FROM Document document " +
                            "WHERE document.titre LIKE :titreSubString ", Document.class);
            query.setParameter("titreSubString", "%"+titreSubString+"%");
            entityManager.getTransaction().commit();
            List<DocumentDTO> doc = new ArrayList<>();
            for (int i = 0; i < query.getResultList().toArray().length; i++) {
                doc.add(query.getResultList().get(i).toDTO());
            }
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentDTO> get(LocalDate annePublication) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.getTransaction().begin();
            TypedQuery<Document> query = entityManager.createQuery(
                    "SELECT document FROM Document document " +
                            "WHERE document.anneePublication = :anneePublication ", Document.class);
            query.setParameter("anneePublication", annePublication);
            entityManager.getTransaction().commit();
            List<DocumentDTO> doc = new ArrayList<>();
            for (int i = 0; i < query.getResultList().toArray().length; i++) {
                doc.add(query.getResultList().get(i).toDTO());
            }
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package ca.cal.tp1.persistance;

import ca.cal.tp1.service.DTO.EmpruntDTO;
import ca.cal.tp1.service.DTO.EmprunteurDTO;

import java.time.LocalDate;
import java.util.List;

public interface InterfaceRepository <T> {
    public void save(T object);

    public T get(Long id);

    public List<T> get(String titreSubString, LocalDate annePublication);

    public List<T> get(String titreSubString);

    public List<T> get(LocalDate annePublication);

    public void delete(Long id);

    public List<T> get(EmprunteurDTO emprunteur);
    public List<T> get(EmpruntDTO emprunt);
}
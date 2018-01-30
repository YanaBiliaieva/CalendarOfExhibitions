package exhibitions.model.dao;

import exhibitions.model.entities.Exposition;

import java.util.List;

public interface ExpositionDao {
    boolean createExpositionWithTickets(Exposition exposition, Integer hallId);

    Exposition getById(Integer id);

    List<Exposition> getAllExpositions();

    void update(Exposition exposition);

    void delete(Integer id);

    Integer getNumberOfTickets(Integer expositionId);
}

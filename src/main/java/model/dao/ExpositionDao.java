package model.dao;

import model.entities.Exposition;

import java.sql.Date;
import java.util.List;

public interface ExpositionDao {
    void createExposition(String theme, Date start, Date end, String description, int fk_id_ha, int price);

    Exposition getById(int id);

    List<List<String>> getAllExpositions();

    void update(Exposition exposition);

    void delete(int id);
}

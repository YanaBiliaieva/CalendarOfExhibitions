package exhibitions.model.dao;

import exhibitions.model.entities.Hall;

import java.util.List;

public interface HallDao {
    boolean createHall(Hall hall, Integer cityId);

    Hall getById(int id);

    List<Hall> getAllHalls();

    void update(Hall hall);

    void delete(int id);

    boolean getByName(String name);
}

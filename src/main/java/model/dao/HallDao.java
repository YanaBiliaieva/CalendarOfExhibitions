package model.dao;

import model.entities.Hall;

import java.util.List;

public interface HallDao {
    void createHall(String name, String address);

    Hall getById(int id);

    List<Hall> getAllHalls();

    void update(Hall hall);

    void delete(int id);
}

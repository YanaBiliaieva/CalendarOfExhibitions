package services;

import model.dao.implementation.ExpositionDaoImpl;
import model.entities.Exposition;

import java.util.List;
import java.util.Map;

public class ExhibitionsService {
    private static ExhibitionsService exhibitionsService = new ExhibitionsService();

    private ExhibitionsService() {
    }

    public static ExhibitionsService getExhibitionsService() {
        return exhibitionsService;
    }

    public static List<List<String>> getExhibitions() {
        ExpositionDaoImpl expositionDao=new ExpositionDaoImpl();
        List<List<String>> ex= expositionDao.getAllExpositions();

        return ex;


    }
}

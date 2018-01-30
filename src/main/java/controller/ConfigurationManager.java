package controller;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle RESOURCE_BUNDLE=ResourceBundle.getBundle("pages");
    //клас извлекает информацию из файла pages.properties
    private ConfigurationManager(){}
    public static String getProperty(String key){
        return RESOURCE_BUNDLE.getString(key);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class DBConfiguration {

    private static DBConfiguration instance;
    private Properties properties;

    public DBConfiguration() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("config\\config.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DBConfiguration getInstance() {
        if (instance == null) {
            instance = new DBConfiguration();
        }
        return instance;
    }

    public void load() throws FileNotFoundException, IOException {
        properties.store(new FileOutputStream("config\\config.properties"), "");
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}

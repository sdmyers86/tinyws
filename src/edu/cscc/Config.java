package edu.cscc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


/**
 * Process web server configuration
 * @author Shawn Myers
 */
public class Config {
    public static final String PORT = "port";
    public static final String DEFAULTPAGE = "defaultPage";
    public static final String DEFAULTFOLDER = "defaultFolder";

    private static final String CONFIG_FILE = "./TinyWS.xml";
    private static Properties properties;

    /**
     * Constructor
     */
    public Config() {
        try {
            readProperties();
        } catch (FileNotFoundException e) {
            TinyWS.fatalError("Cannot open file: "+CONFIG_FILE);
        } catch (IOException e) {
            TinyWS.fatalError(e.getMessage());
        }
    }

    /**
     * Initialize properties
     * @throws IOException - thrown if cannot read configuration file
     */
    public void readProperties() throws IOException {
        File file = new File("tinyws.xml");
        FileInputStream fin = new FileInputStream(file);
        properties = new Properties();
        properties.loadFromXML(fin);
        fin.close();
    }

    /**
     * Get a property value
     * @param key - key associated with property
     * @return property string
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Debug - list all current properties
     */
    public void dumpProperties() {
        Enumeration<Object> enuKeys = properties.keys();
        while (enuKeys.hasMoreElements()) {
            String key = (String) enuKeys.nextElement();
            String value = properties.getProperty(key);
            TinyWS.log(key + ": " + value);
        }
    }
}


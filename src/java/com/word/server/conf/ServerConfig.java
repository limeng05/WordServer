/**
 * @(#)ServerConfig.java, 2012-12-22. 
 * 
 * All rights reserved.
 */
package com.word.server.conf;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.word.server.exceptions.WordException;

/**
 *
 * @author Mn
 *
 */
public class ServerConfig {
    
    private static final Logger LOG = Logger.getLogger(ServerConfig.class);

    private static Configuration config;
    static {
        try {
            String wordHome = System.getProperty("word.home");
            config = new PropertiesConfiguration(wordHome + File.separator
                    + "conf" + File.separator + "server.properties");
        } catch (ConfigurationException e) {
            LOG.error(e.getMessage(), e);
            config = null;
        }
    }
    
    private static final String MONGO_CONFIG_NAME = "mongo";
    private static final String MONGO_DEFAULT_VALUE = "crazywords.cloudapp.net";
    
    public static String getMongoServer() throws WordException {
        if (config == null) {
            throw new WordException("config is null!", WordException.CODE_CONFIG_EXCEPTION);
        }
        return config.getString(MONGO_CONFIG_NAME, MONGO_DEFAULT_VALUE);
    }
}

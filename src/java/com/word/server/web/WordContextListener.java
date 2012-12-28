/**
 * @(#)WordContextListener.java, 2012-12-23. 
 * 
 * All rights reserved.
 */
package com.word.server.web;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Mn
 *
 */
public class WordContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String realPath = servletContextEvent.getServletContext().getRealPath("WEB-INF");
        System.err.println("Set word.home=" + realPath);
        System.setProperty("word.home", realPath);
        String configFileName = realPath + File.separator + "conf"
                + File.separator + "log4j.properties";
        PropertyConfigurator.configure(configFileName);
        System.err.println("init " + configFileName + " done!");
    }

}

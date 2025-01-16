package org.example;

import org.example.config.HibernateConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HibernateConfig.entityManagerFactory();
    }
}

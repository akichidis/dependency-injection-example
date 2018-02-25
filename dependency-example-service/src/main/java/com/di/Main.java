package com.di;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by tasos on 25/02/2018.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Context ctx = new Context();
        ctx.setParameter1("Parameter 1 has been set");

        Properties properties = loadProperties();
        String algorithmFactory = properties.getProperty("encryption.algorithm.factory");

        //Create class injector - the point via we can
        //load every external class
        ClassInjector injector = getClassInjector();

        //Create a new instance of a factory by providing the class name
        EncryptionAlgorithmFactory factory = (EncryptionAlgorithmFactory) injector.loadClass(algorithmFactory).getConstructor().newInstance();

        //Create the encryption algorithm (which relies on the external library)
        EncryptionAlgorithm encryptionAlgorithm = factory.create(ctx);

        //call the encrypt method
        encryptionAlgorithm.encrypt(new byte[]{(byte)10}, new byte[]{(byte)20});
    }


    /**
     * Loads the properties file (config.properties) from the resources directory
     * @return
     */
    private static Properties loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream;

        try {
            inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");

            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Creates the class injector which is useful for loading during run time classes from the JAR
     * files that are included on the "libs" folder.
     *
     * @return
     */
    private static ClassInjector getClassInjector() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        File libsFolder = new File("libs");

        ClassInjector injector = new ClassInjector(classLoader, libsFolder);

        Thread.currentThread().setContextClassLoader(injector);

        return injector;
    }
}

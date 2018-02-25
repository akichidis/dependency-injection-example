package com.di;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

/**
 * Created by tasos on 25/02/2018.
 */
public class ClassInjector extends URLClassLoader {

    public ClassInjector(ClassLoader parent, File jarsDir) {
        super(new URL[]{}, parent);
        addJarsToClasspath(jarsDir);
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException
    {
        try {
            return getParent().loadClass(name);
        } catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
            System.out.println("Class not found on parent classpath");
        }
        return this.findClass(name);
    }

    private void addJarsToClasspath(File jarsDir) {
        Objects.requireNonNull(jarsDir, "Please provide a jar dir");

        if (!jarsDir.exists()) {
            throw new RuntimeException("Jar directory not exists");
        }

        File[] jarFiles = jarsDir.listFiles(f -> f.getName().endsWith(".jar"));

        if (jarFiles == null) {
            return;
        }

        for (File file: jarFiles) {
            try {

                addURL(file.toURI().toURL());

                System.out.println("Loaded jar " + file.getAbsolutePath());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Couldn't load jar file: " + file.getName());
            }
        }
    }

}

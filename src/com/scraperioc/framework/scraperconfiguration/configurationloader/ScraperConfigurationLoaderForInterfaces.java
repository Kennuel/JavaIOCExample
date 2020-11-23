package com.scraperioc.framework.scraperconfiguration.configurationloader;

import com.scraperioc.framework.scraperconfiguration.ScraperConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class ScraperConfigurationLoaderForInterfaces implements ScraperConfigurationLoader {
    private Class mainClass;
    public ScraperConfigurationLoaderForInterfaces(Class mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public List<ScraperConfiguration> loadScraperConfigurations() {
        return this.findAllScrapingConfigurations();
    }

    private LinkedList<ScraperConfiguration> findAllScrapingConfigurations() {
        Class[] classArray = new Class[0];
        try {
            classArray = this.getClasses(mainClass.getPackageName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        LinkedList<ScraperConfiguration> scraperConfigurations = new LinkedList<>();
        Arrays.asList(classArray).stream().forEach(x -> {
            try {
                if(Arrays.stream(x.getInterfaces()).anyMatch(y -> ScraperConfiguration.class.isAssignableFrom(y)))
                    scraperConfigurations.add ((ScraperConfiguration)x.getConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });


        return scraperConfigurations;
    }

    private Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = mainClass.getClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL)resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}

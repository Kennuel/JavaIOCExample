package com.scraperioc.framework.scraperconfiguration.configurationloader;

import com.scraperioc.framework.errorhandling.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class ScraperConfigurationLoaderFactory {

    public enum Type { INTERFACE_LOADER }

    private static Map<Type, ScraperConfigurationLoader> typeScraperConfigurationLoaderMap = new HashMap<>();

    public ScraperConfigurationLoaderFactory(Class<?> mainClass) {

        typeScraperConfigurationLoaderMap.put(
                Type.INTERFACE_LOADER,
                new ScraperConfigurationLoaderForInterfaces(mainClass)
        );
    }

    public ScraperConfigurationLoader getScraperConfigurationLoader(Type type) {
        if(!typeScraperConfigurationLoaderMap.containsKey(type)) {
            throw new NotImplementedException();
        }

        return typeScraperConfigurationLoaderMap.get(type);
    }
}

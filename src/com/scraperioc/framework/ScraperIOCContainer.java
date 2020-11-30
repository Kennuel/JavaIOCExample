package com.scraperioc.framework;

import com.scraperioc.framework.runner.ScraperRunner;
import com.scraperioc.framework.runner.ScraperRunnerFactory;
import com.scraperioc.framework.scraperconfiguration.configurationloader.ScraperConfigurationLoader;
import com.scraperioc.framework.scraperconfiguration.configurationloader.ScraperConfigurationLoaderFactory;

/**
* A Simple Container class which directly runs all classes inside the classfiles implementing the interface ScraperConfiguration.
* 
* 
*/


public class ScraperIOCContainer {

    private static Class<?> mainClass;

    public static void startScrapping(Class<?> mainClass) {
        ScraperIOCContainer.mainClass = mainClass;
        ScraperIOCContainer.runScraping();
    }

    private static void runScraping() {
        ScraperRunner scraperRunner = instantiateRunner();

        instantiateLoader()
                .loadScraperConfigurations()
                .forEach(scraperRunner::runWithScraperConfiguration);
    }

    private static ScraperConfigurationLoader instantiateLoader() {
        return new ScraperConfigurationLoaderFactory(mainClass)
                .getScraperConfigurationLoader(ScraperConfigurationLoaderFactory.Type.INTERFACE_LOADER);
    }


    private static ScraperRunner instantiateRunner() {
        return new ScraperRunnerFactory().getScraperRunner(ScraperRunnerFactory.Type.PRINT);
    }

}

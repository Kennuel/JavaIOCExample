package com.scraperioc.framework.scraperconfiguration.configurationloader;

import com.scraperioc.framework.scraperconfiguration.ScraperConfiguration;

import java.util.List;

public interface ScraperConfigurationLoader {
    List<ScraperConfiguration> loadScraperConfigurations();
}

package com.scraperioc.framework.runner;

import com.scraperioc.framework.scraperconfiguration.ScraperConfiguration;

public interface ScraperRunner {
    void runWithScraperConfiguration(ScraperConfiguration scraperConfiguration);
}

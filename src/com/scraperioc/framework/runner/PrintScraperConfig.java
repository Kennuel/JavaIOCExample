package com.scraperioc.framework.runner;


import com.scraperioc.framework.scraperconfiguration.ScraperConfiguration;

public class PrintScraperConfig implements ScraperRunner {

    @Override
    public void runWithScraperConfiguration(ScraperConfiguration scraperConfiguration) {
        System.out.println(scraperConfiguration);
    }
}

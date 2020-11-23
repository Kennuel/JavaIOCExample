package com.scraperioc.framework.runner;

import com.scraperioc.framework.errorhandling.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class ScraperRunnerFactory {

    public enum Type { PRINT }

    private  Map<ScraperRunnerFactory.Type, ScraperRunner> typeToScraperRunner = new HashMap<>();

    public ScraperRunnerFactory() {
        typeToScraperRunner.put(Type.PRINT, new PrintScraperConfig());
    }

    public ScraperRunner getScraperRunner(Type scraperType) {
        if(!typeToScraperRunner.containsKey(scraperType)) {
            throw new NotImplementedException();
        }

        return typeToScraperRunner.get(scraperType);
    }
}

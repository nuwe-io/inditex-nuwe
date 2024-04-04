package com.inditex;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import org.infinispan.manager.EmbeddedCacheManager;

@org.springframework.context.annotation.Configuration
public class DataGridConfiguration {

    private final EmbeddedCacheManager cacheManager;

    @Autowired
    public DataGridConfiguration(EmbeddedCacheManager cacheManager) {
	this.cacheManager = cacheManager;
    }

    // Agregar configuraciones iniciales si es necesario

}


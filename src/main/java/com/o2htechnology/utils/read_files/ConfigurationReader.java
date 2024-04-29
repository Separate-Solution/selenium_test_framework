package com.o2htechnology.utils.read_files;

import com.o2htechnology.utils.common_utils.CommonUtils;
import com.o2htechnology.utils.logging.Logs;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.*;
import java.util.LinkedHashMap;

/**
 * ConfigurationReader class represents methods for reading properties files. Created as a singleton class with getInstance() as the single-point access of the class.
 */
public class ConfigurationReader {
    private static PropertiesConfiguration environmentPropertiesConfiguration;
    private static PropertiesConfiguration globalPropertiesConfiguration;
    private static LinkedHashMap<String, PropertiesConfiguration> environmentConfigMap = new LinkedHashMap<>();
    private static LinkedHashMap<String, PropertiesConfiguration> globalConfigMap = new LinkedHashMap<>();
    private static LinkedHashMap<String, PropertiesConfiguration> mainConfigMap = new LinkedHashMap<>();
    private static Boolean environmentLoaded = false;
    private static String environment;

    private static LinkedHashMap<String, PropertiesConfiguration> loadMainProperties() {
        Configurations mainConfigurations = new Configurations();
        try {
            PropertiesConfiguration mainPropertiesConfiguration = mainConfigurations.properties(new File(CommonUtils.getPropertiesDirectoryPath() + "main_configuration.properties"));
            mainConfigMap.put("main", mainPropertiesConfiguration);
            setEnvironment(CommonUtils.getEnvironment());
            if (environment != null) {
                environmentLoaded = true;
            }
        } catch (ConfigurationException configurationException) {
            Logs.logErrorMessage(configurationException.getMessage());
        }
        return mainConfigMap;
    }

    private static LinkedHashMap<String, PropertiesConfiguration> loadGlobalProperties() {
        Configurations globalConfigurations = new Configurations();
        try {
            globalPropertiesConfiguration = globalConfigurations.properties(new File(CommonUtils.getPropertiesDirectoryPath() + "global.properties"));
            globalConfigMap.put("global", globalPropertiesConfiguration);
        } catch (ConfigurationException configurationException) {
            Logs.logErrorMessage(configurationException.getMessage());
        }
        return globalConfigMap;
    }

    private static LinkedHashMap<String, PropertiesConfiguration> loadEnvProperties() {
        Configurations envConfigurations = new Configurations();
        try {
            environmentPropertiesConfiguration = envConfigurations.properties(new File(CommonUtils.getPropertiesDirectoryPath() + environment + ".properties"));
            environmentConfigMap.put(environment, environmentPropertiesConfiguration);
        } catch (ConfigurationException configurationException) {
            Logs.logErrorMessage(configurationException.getMessage());
        }
        return environmentConfigMap;
    }

    private static void setEnvironment(String mainEnvironment) {
        environment = mainEnvironment;
    }

    private static void checkEnvProperties() {
        if (mainConfigMap.get("main") == null) {
            loadMainProperties();
        }
        if (globalConfigMap.get("global") == null) {
            loadGlobalProperties();
        }
        if (environmentConfigMap.get(environment) == null) {
            loadEnvProperties();
        }
    }

    public static String getStringValue(String key) {
        checkEnvProperties();
        if (environmentLoaded) {
            return environmentConfigMap.get(environment).getString(key);
        } else {
            return globalConfigMap.get("global").getString(key);
        }
    }

    public static Boolean getBooleanValue(String key) {
        checkEnvProperties();
        if (environmentLoaded) {
            return environmentConfigMap.get(environment).getBoolean(key);
        } else {
            return globalConfigMap.get("global").getBoolean(key);
        }
    }

    public static float getFloatValue(String key) {
        checkEnvProperties();
        if (environmentLoaded) {
            return environmentConfigMap.get(environment).getFloat(key);
        } else {
            return globalConfigMap.get("global").getFloat(key);
        }
    }

    public static int getIntValue(String key) {
        checkEnvProperties();
        if (environmentLoaded) {
            return environmentConfigMap.get(environment).getInt(key);
        } else {
            return globalConfigMap.get("global").getInt(key);
        }
    }
}

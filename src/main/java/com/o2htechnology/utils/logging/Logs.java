package com.o2htechnology.utils.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class will implement log4j2 and log the INFO, ERROR, WARNING and DEBUG messages in the console.
 */
public class Logs {
    private final static Logger LOCAL_LOGGER = LogManager.getLogger(Logs.class);
    private final static Logger STAGING_LOGGER = LogManager.getLogger(Logs.class);
    private final static Logger LIVE_LOGGER = LogManager.getLogger(Logs.class);

    /**
     * This method will log all the INFO level messages in the console.
     *
     * @param message Takes message string as a parameter.
     */
    public static void logInfoMessage(String message) {
        if (System.getProperty("environment").equals("local")) {
            LOCAL_LOGGER.info(message);
        } else if (System.getProperty("environment").equals("staging")) {
            STAGING_LOGGER.info(message);
        } else if (System.getProperty("environment").equals("live")) {
            LIVE_LOGGER.info(message);
        }
    }

    /**
     * This method will log all the ERROR level messages in the console.
     *
     * @param message Takes message string as a parameter.
     */
    public static void logErrorMessage(String message) {
        if (System.getProperty("environment").equals("local")) {
            LOCAL_LOGGER.error(message);
        } else if (System.getProperty("environment").equals("staging")) {
            STAGING_LOGGER.error(message);
        } else if (System.getProperty("environment").equals("live")) {
            LIVE_LOGGER.error(message);
        }
    }

    /**
     * This method will log all the WARNING level messages in the console.
     *
     * @param message Takes message string as a parameter.
     */
    public static void logWarningMessage(String message) {
        if (System.getProperty("environment").equals("local")) {
            LOCAL_LOGGER.warn(message);
        } else if (System.getProperty("environment").equals("staging")) {
            STAGING_LOGGER.warn(message);
        } else if (System.getProperty("environment").equals("live")) {
            LIVE_LOGGER.warn(message);
        }
    }

    /**
     * This method will log all the DEBUG level messages in the console.
     *
     * @param message Tales message string as a parameter.
     */
    public static void logDebugMessage(String message) {
        if (System.getProperty("environment").equals("local")) {
            LOCAL_LOGGER.debug(message);
        } else if (System.getProperty("environment").equals("staging")) {
            STAGING_LOGGER.debug(message);
        } else if (System.getProperty("environment").equals("live")) {
            LIVE_LOGGER.debug(message);
        }
    }
}
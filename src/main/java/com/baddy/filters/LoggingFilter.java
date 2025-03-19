package com.baddy.filters;


import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class LoggingFilter implements ClientRequestFilter, ClientResponseFilter {
    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        logger.info(" -------- LOG REQUEST LOG  --------");
        if (requestContext.getEntity() != null) {
            logger.info(requestContext.getEntity().toString());
        }
        logger.info("uri: " + requestContext.getUri());
        if (!requestContext.getStringHeaders().isEmpty()) {
            for (String key : requestContext.getStringHeaders().keySet()) {
                logger.info("key: " + key + ", value: " + requestContext.getStringHeaders().getFirst(key));
            }
        }
        logger.info(" ----------------------------- ");
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {
        logger.info(" -------- LOG RESPONSE LOG  --------");
        for (var key : clientResponseContext.getHeaders().keySet()) {
            logger.info(key + ":" + clientResponseContext.getHeaders().getFirst(key));
        }
        logger.info(" --------  --------");
        StringBuilder response = new StringBuilder();

        if (clientResponseContext.hasEntity()) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            IOUtils.copy(clientResponseContext.getEntityStream(), stream);
            byte[] responseBytes = stream.toByteArray();
            response.append(new String(responseBytes));
            response.append("\n");
            clientResponseContext.setEntityStream(new ByteArrayInputStream(responseBytes));
        }

        logger.info(response.toString());
        logger.info(" ----------------------------- ");
    }
}


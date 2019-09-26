package com.example.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DavidArango on 9/20/19.
 */
public class ApiGatewayRequestHandler extends SpringBootRequestHandler<Message, Message> {

    @Autowired
    private ObjectMapper mapper;

    public Object handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Object response = super.handleRequest(this.convertEvent(request), context);
        return this.returnsOutput() ? this.convertOutputEvent(response) : (new APIGatewayProxyResponseEvent()).withStatusCode(Integer.valueOf(HttpStatus.OK.value()));
    }

    private MessageHeaders getHeaders(APIGatewayProxyRequestEvent event) {
        Map<String, Object> headers = new HashMap();
        if (event.getHeaders() != null) {
            headers.putAll(event.getHeaders());
        }

        headers.put("request", event);
        return new MessageHeaders(headers);
    }

    private Message convertEvent(APIGatewayProxyRequestEvent event) {
        return MessageBuilder.createMessage(event.getBody(), this.getHeaders(event));
    }

    private String serializeBody(Object body) {
        try {
            return this.mapper.writeValueAsString(body);
        } catch (JsonProcessingException var3) {
            throw new IllegalStateException("Cannot convert output", var3);
        }
    }

    private Map<String, String> toResponseHeaders(MessageHeaders messageHeaders) {
        Map<String, String> responseHeaders = new HashMap();
        messageHeaders.forEach((key, value) -> {
            String var10000 = (String) responseHeaders.put(key, value.toString());
        });
        return responseHeaders;
    }

    private APIGatewayProxyResponseEvent convertOutputEvent(Object output) {
        if (this.functionReturnsMessage(output)) {
            Message<?> message = (Message) output;
            return (new APIGatewayProxyResponseEvent()).withStatusCode((Integer) message.getHeaders().getOrDefault("statuscode", Integer.valueOf(HttpStatus.OK.value()))).withHeaders(this.toResponseHeaders(message.getHeaders())).withBody(this.serializeBody(message.getPayload()));
        } else {
            return (new APIGatewayProxyResponseEvent()).withStatusCode(Integer.valueOf(HttpStatus.OK.value())).withBody(this.serializeBody(output));
        }
    }

    private boolean functionReturnsMessage(Object output) {
        return output instanceof Message;
    }


}



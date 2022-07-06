package com.thanhbuitien.response;

import org.json.JSONObject;

import java.util.List;

public class ResponseFactory {

    public static Response getSuccessResponse(String message, List entities) {
        return new Response.ResponseBuilders().setCode(Response.OK_CODE).setStatus(Response.OK_STATUS)
                .setMessage(message).setData(entities).build();
    }

    public static Response getSuccessResponse(String message, Object entitie) {
        return new Response.ResponseBuilders().setCode(Response.OK_CODE).setStatus(Response.OK_STATUS)
                .setMessage(message).setData(entitie).build();
    }

    public static Response getSuccessResponse(String message, JSONObject entitie) {
        return new Response.ResponseBuilders().setCode(Response.OK_CODE).setStatus(Response.OK_STATUS)
                .setMessage(message).setData(entitie).build();
    }

    public static Response getServerErrorResponse(String message, Object entities) {
        return new Response.ResponseBuilders().setCode(Response.INTERNAL_ERROR_CODE).setStatus(Response.ERROR_STATUS)
                .setMessage(message).setData(entities).build();
    }

    public static Response getClientErrorResponse(String message, Object entities) {
        return new Response.ResponseBuilders().setCode(Response.CLIENT_ERROR_CODE).setStatus(Response.ERROR_STATUS)
                .setMessage(message).setData(entities).build();
    }
}

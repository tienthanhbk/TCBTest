package com.thanhbuitien.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thanhbuitien.exception.InternalServerException;
import com.thanhbuitien.response.Response;
import com.thanhbuitien.response.ResponseFactory;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Controller {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/ping", method = GET)
    public Response ping() {
        try {
            return ResponseFactory.getSuccessResponse("Pong", new ArrayList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalServerException("Internal server error");
        }
    }

}

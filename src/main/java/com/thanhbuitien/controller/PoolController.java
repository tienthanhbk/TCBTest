package com.thanhbuitien.controller;

import com.thanhbuitien.common.Utils;
import com.thanhbuitien.constant.Constants;
import com.thanhbuitien.exception.ClientErrorException;
import com.thanhbuitien.models.dto.PoolDto;
import com.thanhbuitien.models.dto.QuantileQueryDto;
import com.thanhbuitien.models.dto.QuantileQueryRes;
import com.thanhbuitien.service.IPoolService;
import com.thanhbuitien.service.impl.PoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thanhbuitien.exception.InternalServerException;
import com.thanhbuitien.response.Response;
import com.thanhbuitien.response.ResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/pool")
public class PoolController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private IPoolService poolService;

    public PoolController(PoolService poolService) {
        this.poolService = poolService;
    }

    @RequestMapping(value = "/ping", method = GET)
    public Response ping() {
        try {
            return ResponseFactory.getSuccessResponse("Pong", new ArrayList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new InternalServerException("Internal server error");
        }
    }

    @RequestMapping(value = "/get-by-id", method = GET)
    public Response addOrUpdate(@RequestHeader Long poolId) {
        try {
            List<Integer> values = poolService.getValuesById(poolId);

            return ResponseFactory.getSuccessResponse("Ok", new PoolDto(poolId, values));
        } catch (ClientErrorException e) {
            throw new ClientErrorException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientErrorException("Something goes wrong!");
        }
    }

    @RequestMapping(value = "/create-or-append", method = POST)
    public Response addOrUpdate(@RequestBody PoolDto poolDto) {
        try {
            Integer flag = poolService.insertOrUpdate(poolDto.getPoolId(), poolDto.getPoolValues());
            String status = flag.equals(Constants.FLAG_APPEND) ? Constants.STATUS_APPENDED : Constants.STATUS_INSERTED;
            return ResponseFactory.getSuccessResponse("Ok", status);
        } catch (ClientErrorException e) {
            throw new ClientErrorException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientErrorException("Something goes wrong!");
        }
    }

    @RequestMapping(value = "/query/quantile", method = POST)
    public Response queryQuantile(@RequestBody QuantileQueryDto queryDto) {
        try {
            QuantileQueryRes res = poolService.queryQuantile(queryDto.getPoolId(), queryDto.getPercentile());
            return ResponseFactory.getSuccessResponse("Ok", res);
        } catch (ClientErrorException e) {
            throw new ClientErrorException(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ClientErrorException("Something goes wrong!");
        }
    }

}

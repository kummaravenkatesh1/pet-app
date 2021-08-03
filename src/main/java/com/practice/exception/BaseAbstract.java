package com.practice.exception;


import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.practice.constants.CommonConstants;

@Transactional
public abstract class BaseAbstract {

    public Integer getStatusCode(String s) {
        Integer statusCode = null;
        if (s.equals(CommonConstants.CREATE)) {
            statusCode = HttpStatus.CREATED.value();
        } else if (s.equals(CommonConstants.UPDATE) || s.equals(CommonConstants.DELETE) || s.equals(CommonConstants.GET)) {
            statusCode = HttpStatus.OK.value();
        } else if (s.equals(CommonConstants.RECORD_FOUND)) {
            statusCode = HttpStatus.FOUND.value();
        }
        return statusCode;
    }

    public HttpStatus getStatus(String s) {
        HttpStatus statusCode = null;
        if (s.equals(CommonConstants.CREATE)) {
            statusCode = HttpStatus.CREATED;
        } else if (s.equals(CommonConstants.UPDATE) || s.equals(CommonConstants.DELETE) || s.equals(CommonConstants.GET)) {
            statusCode = HttpStatus.OK;
        } else if (s.equals(CommonConstants.RECORD_FOUND)) {
            statusCode = HttpStatus.FOUND;
        }
        return statusCode;
    }

    public void addError(String errorMessage, Map<String, Object> resMap) {
        resMap.put(CommonConstants.ERR_MESSAGE, errorMessage);
        resMap.put(CommonConstants.STATUS, false);
    }

    public void addRequiredParamError(Map<String, Object> resMap) {
        resMap.put(CommonConstants.ERR_MESSAGE, "Required parameters must not be empty.");
        resMap.put(CommonConstants.STATUS, false);
    }

    public void addServiceError(Map<String, Object> resMap) {
        resMap.put(CommonConstants.ERR_MESSAGE, "Oops!!! Something went wrong. Please try again.");
        resMap.put(CommonConstants.STATUS, false);
    }

    public ResponseEntity<Map<String, Object>> renderResponse(Map<String, Object> resMap) {
        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }

    protected boolean validateRequiredParams(String... params) {
        for (String param : params) {
            if (StringUtils.isEmpty(param)) {
                return false;
            }
        }
        return true;
    }
}

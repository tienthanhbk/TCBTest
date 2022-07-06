package com.thanhbuitien.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.json.JSONObject;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<E> {
    public static final int OK_CODE = 200;
    public static final int INTERNAL_ERROR_CODE = 500;
    public static final int CLIENT_ERROR_CODE = 400;
    public static final int OK_STATUS = 1;
    public static final int ERROR_STATUS = 0;


    private String message;
    private Integer code;
    private Integer status;
    private E result;
    // Paging information
    private Integer page;
    private Integer total_pages;
    private Integer perpage;

    private Response(ResponseBuilders<E> builder) {
        this.message = builder.getMessage();
        this.code = builder.getCode();
        this.status = builder.getStatus();
        this.result = builder.getData();

        this.page = builder.getPage();
        this.total_pages = builder.getTotalPages();
        this.perpage = builder.getPerpage();
    }

    public static class ResponseBuilders<E> {
        private String message;
        private Integer code;
        private Integer status;
        private E data;
        private Integer test;

        // Paging information
        private Integer page;
        private Integer total_pages;
        private Integer perpage;

        public String getMessage() {
            return message;
        }

        public ResponseBuilders<E> setMessage(String message) {
            this.message = message;
            return this;
        }

        public Integer getCode() {
            return code;
        }

        public ResponseBuilders<E> setCode(Integer code) {
            this.code = code;
            return this;
        }

        public Integer getStatus() {
            return status;
        }

        public ResponseBuilders<E> setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public E getData() {
            return data;
        }

        public ResponseBuilders<E> setData(E data) {
            this.data = data;
            return this;
        }

        public Response<E> build() {
            return new Response<>(this);
        }

        public String buildString() {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "ok");
            jsonObject.put("code", 200);
            jsonObject.put("status", 1);
            jsonObject.put("result", data);
            return jsonObject.toString();
        }

        public Integer getPage() {
            return page;
        }

        public Integer getTotalPages() {
            return total_pages;
        }

        public Integer getPerpage() {
            return perpage;
        }

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public E getResult() {
        return result;
    }

    public void setResult(E result) {
        this.result = result;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return total_pages;
    }

    public void setTotalPages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }
}


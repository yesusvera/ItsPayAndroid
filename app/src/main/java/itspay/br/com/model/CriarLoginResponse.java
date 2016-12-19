package itspay.br.com.model;

import java.util.Arrays;

/**
 * Created by yesus on 19/12/16.
 */
public class CriarLoginResponse {
    public String code;
    public String msg;
    public FieldError fieldErrors[];

    public CriarLoginResponse(){}

    public CriarLoginResponse(String code, String msg, FieldError[] fieldErrors){
        this.code = code;
        this.msg = msg;
        this.fieldErrors = fieldErrors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FieldError[] getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(FieldError[] fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String toString() {
        return "CriarLoginResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", fieldErrors=" + Arrays.toString(fieldErrors) +
                '}';
    }

    public static class FieldError {
        public String resource;
        public String field;
        public String code;
        public String msg;

        public FieldError(){}

        public FieldError(String resource, String field, String code, String msg){
            this.resource = resource;
            this.field = field;
            this.code = code;
            this.msg = msg;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "FieldError{" +
                    "resource='" + resource + '\'' +
                    ", field='" + field + '\'' +
                    ", code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }


}

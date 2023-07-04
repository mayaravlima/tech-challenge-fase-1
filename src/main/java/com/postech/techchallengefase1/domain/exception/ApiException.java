package com.postech.techchallengefase1.domain.exception;

public class ApiException extends RuntimeException {

        private Integer status;

        public ApiException(String message) {
            super(message);
        }

        public ApiException(String message, Integer status) {
            super(message);
            this.status = status;
        }

        public Integer getStatus() {
            return this.status;
        }

}

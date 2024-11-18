package com.lineate.traineeship.junit.dto;

public class ResponseDto {
    private ResponseStatus status;
    private ApodDto content;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public ApodDto getContent() {
        return content;
    }

    public void setContent(ApodDto content) {
        this.content = content;
    }
}

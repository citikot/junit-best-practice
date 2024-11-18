package com.lineate.traineeship.junit.dto;

import java.util.List;

public class ResponseListDto {
    private ResponseStatus status;
    private List<ApodDto> content;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public List<ApodDto> getContent() {
        return content;
    }

    public void setContent(List<ApodDto> content) {
        this.content = content;
    }
}

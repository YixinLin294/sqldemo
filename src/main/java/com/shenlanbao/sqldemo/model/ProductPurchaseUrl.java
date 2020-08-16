package com.shenlanbao.sqldemo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductPurchaseUrl {
    private String url;
    private MpRedirect mpRedirect;


    @Data
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class MpRedirect {
        private String appId;
        private String path;
    }
}

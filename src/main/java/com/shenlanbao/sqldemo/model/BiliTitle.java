package com.shenlanbao.sqldemo.model;

import lombok.Data;

@Data
public class BiliTitle {

    private Integer cid;
    private Integer page;
    private String from;
    private String part;
    private Integer duration;
    private String vid;
    private String weblink;
    private Dimension dimension;

    @Data
    private class Dimension {
        private Integer width;
        private Integer height;
        private Integer rotate;
    }
}

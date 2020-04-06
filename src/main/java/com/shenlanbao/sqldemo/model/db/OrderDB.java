package com.shenlanbao.sqldemo.model.db;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDB {
    private Integer id;
    private String serviceOrderNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private Integer paymentOrderId;
    private Integer consultantId;
    private Integer consultantGroupId;
    private String wbStatus;
    private Integer customerId;
    private String channel;
    private String remark;
    private String followUpStatus;
    private String type;
    private String listType;
    private String firstLevelStatus;
    private String secondLevelStatus;
    private String productName;
    private String createdBy;
    private Boolean deleted;
    private Integer wxCustomerId;
    private Integer preOrderId;
    private Boolean top;
    private String serviceLevel;
    private String coverageType;
    private String batchNumber;
    private LocalDateTime plannedContactDate;
    private Boolean autofilled;

}

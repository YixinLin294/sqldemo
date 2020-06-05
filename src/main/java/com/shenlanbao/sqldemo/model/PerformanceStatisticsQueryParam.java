package com.shenlanbao.sqldemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PerformanceStatisticsQueryParam {

    private String assignedTimeStart;

    private String assignedTimeEnd;


    private String queryTimeStart;

    private String queryTimeEnd;

    private String consultantTimeStart;

    private String consultantTimeEnd;

    private String surrenderTimeStart;

    private String surrenderTimeEnd;

    private Integer groupId;

    private Integer consultantId;

    private List<Integer> consultantIds;

    private List<Integer> customerIds;

    private String paymentStatus;

    private String src;

    private String queryType;

    private String premiumType;

    private String effectiveType;

    private Integer longInsurance; //长险:1 短险 0

    private Integer pageNo = 1;

    private Integer pageSize;

    private String initialBudgetRank;

    private String serviceLevel;

    private String tabType; //对应保费，订单，渠道流量分析

    private String name;

    private Integer conversionPeriod;

    private String selectConsultant;// 多选规划师，逗号分隔的字符串，eg："1,2,3"

    private String selectGroup;// 多选分组，逗号分隔的字符串，eg："1,2,3"

    private List<Integer> selectConsultants;

    private List<Integer> selectGroups;
}

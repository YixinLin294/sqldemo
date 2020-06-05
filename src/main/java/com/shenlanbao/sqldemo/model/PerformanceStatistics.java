package com.shenlanbao.sqldemo.model;

import lombok.Data;

@Data
public class PerformanceStatistics {

    private double netPremium;// 净保费
    private double historyNetPremium;// 历史净保费
    private double addNetPremium;// 累计净保费

    private Integer addDealNums;// 累计成交件数

    private Integer orderDealNums;// 成交订单数

    private HesitationPreium hesitationPreium;

    private double longRiskNetPremium; // 长险净保费
    private double shortRiskNetPremium;// 短险净保费
    private Integer longRiskAverageNums;// 长险净保费对应的保单件数
    private Integer shortRiskAverageNums;// 短险净保费对应的保单件数
    private double longRiskAveragePreium;// 长险净均件保费
    private double shortRiskAveragePreium;// 短险均件保费

    private double customerAveragePreium; // 保费（客均保费）
    private double addCustomerAveragePreium;// 累计成交净保费
    private Integer customerAverageNums;// 净保费对应的订单数
    private Integer customerIdAllSurrenderNums;// 犹豫期内所有退保数量

    private Integer consultantId;
    private Integer groupId;
    private String name;

    private double aAssignedNums; // A类分配的订单数
    private double bAssignedNums; // B类分配的订单数
    private double cAssignedNums; // C类分配的订单数
    private double lowAssignedNums; // 初级服务分配的订单数
    private double middleAssignedNums; // 中级服务分配的订单数
    private double highAssignedNums; // 高级服务分配的订单数
    private double assignedAllNums;// 分配订单
    private double dealNums; // 成交订单数
    private double aConversionPercent; // A类订单转化率
    private double bConversionPercent; // B类订单转化率
    private double cConversionPercent; // C类订单转化率
    private double lowConversionPercent; // 初级服务订单转化率
    private double middleConversionPercent; // 中级服务订单转化率
    private double highConversionPercent; // 高级服务订单转化率
    private double conversionPercent; // 订单转化率

    private double allCustomerAveragePremium;// 全体客均保费
    private double aCustomerAveragePremium;// A类客均保费
    private double bCustomerAveragePremium;// B类客均保费
    private double cCustomerAveragePremium;// C类客均保费
    private double lowCustomerAveragePremium;// 初级服务客均保费
    private double middleCustomerAveragePremium;// 中级服务客均保费
    private double highCustomerAveragePremium;// 高级服务客均保费

    private double sevenDaysConversionPercent;// 7天转化率
    private double fourteenDaysConversionPercent;// 14天转化率
    private double thirtyDaysConversionPercent;// 30天转化率

    private Integer id;

}

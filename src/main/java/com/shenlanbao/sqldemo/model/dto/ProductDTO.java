package com.shenlanbao.sqldemo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.shenlanbao.sqldemo.common.constant.CoverageType;
import com.shenlanbao.sqldemo.model.ProductPurchaseUrl;
import lombok.Data;

import java.util.List;


@Data
public class ProductDTO {

    private Integer id;
    private String name; //产品名称
    private String fullName; //保险产品全称
    private String coverageType; //产品险种
    private String coverageTypeChinese;
    private Integer companyId; //保险公司ID
    private String companyName; //保险公司名称
    private List<String> highlights; //产品特点
    private String coverageKeys;    //保障要点
    private Boolean alternative = false; //是否备选
    private Boolean available;
    private String url; //购买链接
    private String qxCode;
    private String slbUrl;
    private String purchaseChannel;
    private String purchaseRule;
    private String description;
    private Boolean longInsurance; //是否是长险
    private Integer hesitateTime; //犹豫期
    private Integer coverage;  //保额
    private Double premium;

    private Boolean requireTrial = false;
    private String supplier; //供应商
    private Boolean requireCoverageKeys; //是否需要编辑保障要点

    private String insuranceCode;//内部产品编码
    private String premiumProductCode;//内部产品测算编码

    private String h5Url;
    private String productUrl;

    private Integer hotRate;//产品热卖度，上周该产品购买数量
    private Integer isHot;//是否是热卖产品，0否，1是

    private ProductPurchaseUrl.MpRedirect mpRedirect;

    //测算因子相关字段
    //基本保额
    private List<String> coverages;
    //保障期限
    private List<String> insurantDate;
    //缴费年限
    private List<String> insureAge;
    //保费是否固定
    private boolean fixed;
    //固定保费
    private Double price;

    public String getCoverageTypeChinese() {
        return CoverageType.COVERAGE_TYPE_CN.get(this.getCoverageType());
    }

}

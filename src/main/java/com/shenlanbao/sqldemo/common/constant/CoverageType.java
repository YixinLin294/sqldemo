package com.shenlanbao.sqldemo.common.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lucas
 */
public class CoverageType {

    private CoverageType() {
    }

    public static final String LIFE = "LIFE";
    public static final String CRITICAL_ILLNESS = "CRITICAL_ILLNESS";
    public static final String MEDICAL = "MEDICAL";
    public static final String ACCIDENT = "ACCIDENT";
    public static final String CANCER_PREVENTION = "CANCER_PREVENTION";
    public static final String MILLION_MEDICAL = "MILLION_MEDICAL";
    public static final String SMALL_INPATIENT_MEDICAL = "SMALL_INPATIENT_MEDICAL";
    public static final String CANCER_PREVENTION_MEDICAL = "CANCER_PREVENTION_MEDICAL";
    public static final String ANNUITY_INSURANCE = "ANNUITY_INSURANCE";

    public static final List<String> ALL = Arrays.asList(LIFE, CRITICAL_ILLNESS, CANCER_PREVENTION, MEDICAL, MILLION_MEDICAL, CANCER_PREVENTION_MEDICAL, SMALL_INPATIENT_MEDICAL, ACCIDENT, ANNUITY_INSURANCE);

    public static final List<String> VERBAL_TRICK_ALL = Arrays.asList(LIFE, CRITICAL_ILLNESS, MEDICAL, ACCIDENT);

    public static final List<String> MEDICAL_ALL = Arrays.asList(MEDICAL, MILLION_MEDICAL, CANCER_PREVENTION_MEDICAL, SMALL_INPATIENT_MEDICAL);

    public static final Map<String, String> COVERAGE_TYPE_CN = new HashMap<>();

    public static final Map<String, Integer> HOT_PRODUCT_RATE = new HashMap<>();

    static {
        COVERAGE_TYPE_CN.put(LIFE, "寿险");
        COVERAGE_TYPE_CN.put(CRITICAL_ILLNESS, "重疾险");
        COVERAGE_TYPE_CN.put(MEDICAL, "医疗险");
        COVERAGE_TYPE_CN.put(ACCIDENT, "意外险");
        COVERAGE_TYPE_CN.put(CANCER_PREVENTION, "防癌险");
        COVERAGE_TYPE_CN.put(MILLION_MEDICAL, "百万医疗险");
        COVERAGE_TYPE_CN.put(SMALL_INPATIENT_MEDICAL, "小额住院医疗险");
        COVERAGE_TYPE_CN.put(CANCER_PREVENTION_MEDICAL, "防癌医疗险");
        COVERAGE_TYPE_CN.put(ANNUITY_INSURANCE, "年金险");

        /*
         * 1: 重疾险：取前10
         * 2: 寿险：取前10
         * 3: 百万医疗险，取前3
         * 4：小额住院医疗险：取前3
         * 5：防癌医疗险：取前3
         * 6：防癌险，取前3
         * 7：意外险：取前3
         * 8：年金险：取前3
         * */
        HOT_PRODUCT_RATE.put(CRITICAL_ILLNESS, 10);
        HOT_PRODUCT_RATE.put(LIFE, 10);
        HOT_PRODUCT_RATE.put(MEDICAL, 10);
        HOT_PRODUCT_RATE.put(MILLION_MEDICAL, 3);
        HOT_PRODUCT_RATE.put(SMALL_INPATIENT_MEDICAL, 3);
        HOT_PRODUCT_RATE.put(CANCER_PREVENTION_MEDICAL, 3);
        HOT_PRODUCT_RATE.put(CANCER_PREVENTION, 3);
        HOT_PRODUCT_RATE.put(ACCIDENT, 3);
        HOT_PRODUCT_RATE.put(ANNUITY_INSURANCE, 3);
    }


}

package com.wefly.common;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @program: test
 * @description: TODO
 * @author: HyJan
 * @create: 2020-06-05 18:14
 **/
@Data
@ToString
public class ResponseList {

    private List<TencentModelField> REPORT_LEVEL_ADVERTISER;
    private List<TencentModelField> REPORT_LEVEL_CAMPAIGN;
    private List<TencentModelField> REPORT_LEVEL_ADGROUP;
    private List<TencentModelField> REPORT_LEVEL_AD;
    private List<TencentModelField> REPORT_LEVEL_PROMOTED_OBJECT;
    private List<TencentModelField> REPORT_LEVEL_UNION_POSITION;
    private List<TencentModelField> REPORT_LEVEL_CREATIVE_TEMPLATE;
    private List<TencentModelField> REPORT_LEVEL_DEEPLINK_ADGROUP;
    private List<TencentModelField> REPORT_LEVEL_EXPAND_TARGETING_ADGROUP;
    private List<TencentModelField> REPORT_LEVEL_ADVERTISER_WECHAT;
    private List<TencentModelField> REPORT_LEVEL_CAMPAIGN_WECHAT;
    private List<TencentModelField> REPORT_LEVEL_ADGROUP_WECHAT;
    private List<TencentModelField> REPORT_LEVEL_AD_WECHAT;
    private List<TencentModelField> ALL;

}

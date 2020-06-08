package com.qw.wefly.core.modules.tencent.model.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.qw.wefly.core.modules.tencent.model.inner.TencentFiltering;
import lombok.Data;

import java.util.List;

@Data
public class ${className} {

<#list fields as attr>
    @JSONField(name = "${attr.code}")
    private ${attr.fieldName} ${attr.msg};
</#list>

}

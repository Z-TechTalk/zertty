package com.laoz.zertty.register.config;

import com.laoz.zertty.common.constant.ZerttyConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RegistryCondition implements Condition {

    //配置了zertty.register.center.type才会加载注册中心策略工厂
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return StringUtils.isNotBlank(context.getEnvironment().getProperty(ZerttyConstant.RegisterCenter.TYPE));
    }

}

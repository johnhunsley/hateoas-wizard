package com.johnhunsley.hateoas.wizard;

import com.johnhunsley.hateoas.wizard.model.WizardStepField;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class WizardStepFieldCallback implements ReflectionUtils.FieldCallback {
    private ConfigurableListableBeanFactory configurableBeanFactory;
    private Object bean;

    public WizardStepFieldCallback(ConfigurableListableBeanFactory configurableBeanFactory, Object bean) {
        this.configurableBeanFactory = configurableBeanFactory;
        this.bean = bean;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        if (!field.isAnnotationPresent(WizardStepField.class)) {
            return;
        }

        ReflectionUtils.makeAccessible(field);
        field.get(bean);
    }
}

package com.johnhunsley.hateoas.wizard.model;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  An abstract super type to be sub typed to represent each type in the Wizard if the inheritance model is used.
 *
 *  Enforces a sequence number to each sub type and a function for assessing whether or not the concrete sub type is
 *  complete or not based on the fields of the sub type instance being not null.
 *
 *  Note - model types which represent the Steps in the Wizard need only inherit from this class if not annotated with
 *  the {@link WizardStep} stereotype
 * </p>
 * @author jphunsley@gmail.com
 */
public abstract class AbstractStep {
    final int sequence;

    /**
     *
     * @param sequence
     */
    public AbstractStep(final int sequence) {
        this.sequence = sequence;
    }

    /**
     * <p>
     *    todo - rather than evaluate not null evaluate each property against a defined evaluator.
     * </p>
     * @return true if all the properties of the sub type are not null
     */
    public boolean isComplete() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Field[] fields = FieldUtils.getAllFields(this.getClass());

        for(Field field : fields) {
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(this, field.getName());
            if(propertyDescriptor != null && null == propertyDescriptor.getReadMethod().invoke(this)) return false;
        }

        return true;
    }
}

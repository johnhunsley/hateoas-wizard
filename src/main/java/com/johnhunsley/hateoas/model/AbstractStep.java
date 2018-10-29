package com.johnhunsley.hateoas.model;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;

/**
 * <p>
 *
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
    public boolean isComplete() {
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(this);
    }
}

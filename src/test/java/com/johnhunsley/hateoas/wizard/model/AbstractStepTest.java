package com.johnhunsley.hateoas.wizard.model;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class AbstractStepTest {

    @Test
    public void testIsComplete() {
        AbstractStep step1 = Mockito.mock(AbstractStep.class);
        step1.setMyInt(55);
        step1.setMyStr("something");

        try {
            assertTrue(step1.isComplete());

        } catch (Exception e) {
            e.printStackTrace();
            fail();

        }
    }

    @Test
    public void testIsNotComplete() {
        TestStep1 step1 = new TestStep1();
        step1.setMyInt(55);

        try {
            assertFalse(step1.isComplete());

        } catch (Exception e) {
            e.printStackTrace();
            fail();

        }
    }
}

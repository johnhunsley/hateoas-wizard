package com.johnhunsley.hateoas.wizard.model;

import java.util.Objects;

public class TestStep1 extends AbstractStep {
    private String myStr;
    private int myInt;

    public TestStep1() {
        super(1);
    }

    public String getMyStr() {
        return myStr;
    }

    public void setMyStr(String myStr) {
        this.myStr = myStr;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestStep1 testStep1 = (TestStep1) o;
        return myInt == testStep1.myInt &&
                Objects.equals(myStr, testStep1.myStr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(myStr, myInt);
    }
}

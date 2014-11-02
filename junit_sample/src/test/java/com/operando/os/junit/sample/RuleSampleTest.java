package com.operando.os.junit.sample;

import org.junit.*;
import org.junit.rules.ExpectedException;

public class RuleSampleTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void a() {
        RuleSample ruleSample = new RuleSample();
        exception.expect(IllegalStateException.class);
        exception.expectMessage("RuleSample!!");
        ruleSample.throwExcption();
    }
}

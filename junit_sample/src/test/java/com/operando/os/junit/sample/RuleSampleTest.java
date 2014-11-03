package com.operando.os.junit.sample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

public class RuleSampleTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void rule() {
        RuleSample ruleSample = new RuleSample();
        exception.expect(IllegalStateException.class);
        exception.expectMessage("RuleSample!!");
        ruleSample.throwExcption();
    }

    @Test
    public void try_catch() {
        RuleSample ruleSample = new RuleSample();
        try {
            ruleSample.throwExcption();
        } catch (IllegalStateException e) {
            assertEquals("RuleSample!!", e.getMessage());
        }
    }
}

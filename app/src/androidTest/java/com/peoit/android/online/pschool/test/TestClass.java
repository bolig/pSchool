package com.peoit.android.online.pschool.test;

import android.test.InstrumentationTestCase;

/**
 * author:libo
 * time:2015/7/15
 * E-mail:boli_android@163.com
 * last: ...
 */
public class TestClass extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 1;
        final int reality = 2;
        assertEquals(expected, reality);
    }
}

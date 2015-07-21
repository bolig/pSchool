package com.peoit.android.online.pschool.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.activity.LoginActivity;

/**
 * author:libo
 * time:2015/7/15
 * E-mail:boli_android@163.com
 * last: ...
 */
public class TestClass extends ActivityUnitTestCase<LoginActivity>{

//    public TestClass(Class<LoginActivity> activityClass) {
//        super(activityClass);
//    }

    public TestClass(){
        super(LoginActivity.class);
    }

    Intent intent = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        intent = new Intent(getInstrumentation().getTargetContext(), LoginActivity.class);
    }

    @MediumTest
    public void testLoginButton_lableText(){
        startActivity(intent, null, null);
        final TextView testBtn = (TextView) getActivity().findViewById(R.id.logb_btn_login);
        final String testText = "登录";

        assertEquals("no 登录", testText, testBtn.getText().toString());
    }
}

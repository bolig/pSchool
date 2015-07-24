package com.peoit.android.online.pschool.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.peoit.android.online.pschool.R;


public class BankIC_teacher_Fragment extends Fragment {
    private LinearLayout ll_check_in;
    private LinearLayout ll_grade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.act_bank_ic_teacher_fragemt, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ll_check_in = (LinearLayout) view.findViewById(R.id.bankl_ll_check_in);
        ll_grade = (LinearLayout) view.findViewById(R.id.bankl_ll_grade);
    }
}

package com.perkinszhu.www.shilednew.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.perkinszhu.www.shilednew.MainActivity;
import com.perkinszhu.www.shilednew.R;
import com.perkinszhu.www.shilednew.adapters.AccountGroupAdapter;
import com.perkinszhu.www.shilednew.tool.ToastUtils;

public class DashboardFragment extends DialogFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardFragment df = this;
        View view = View.inflate(MainActivity.mainActivity, R.layout.dailog_add_account_group, null);
        final EditText etTitle = (EditText) view.findViewById(R.id.et_group_title);
        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            if (!TextUtils.isEmpty(title)) {
                Log.d("分组名称:", title);
                AccountGroupAdapter.addAccountGroup(title);
                df.dismiss();
            } else {
                ToastUtils.tip( "输入框内容不能为空!");
            }
        });

        btnCancel.setOnClickListener(v -> df.dismiss());
        return view;
    }
}
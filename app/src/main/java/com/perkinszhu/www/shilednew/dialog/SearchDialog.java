package com.perkinszhu.www.shilednew.dialog;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perkinszhu.www.shilednew.MainActivity;
import com.perkinszhu.www.shilednew.R;
import com.perkinszhu.www.shilednew.adapters.AccountGroupAdapter;
import com.perkinszhu.www.shilednew.tool.ToastUtils;

public class SearchDialog {

    /**
     * 根据关键字搜素
     */
    public void search() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.mainActivity);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(MainActivity.mainActivity, R.layout.dailog_search_account, null);
        dialog.setView(view, 0, 0, 0, 0);

        final EditText etTitle = (EditText) view.findViewById(R.id.se_group_title);
        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            if (!TextUtils.isEmpty(title)) {
                String path = AccountGroupAdapter.searchAccount(title);
                ToastUtils.tip("搜索结果:\r\n" + path);
                dialog.dismiss();
            } else {
                ToastUtils.tip("输入框内容不能为空!");
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


}

package com.perkinszhu.www.shilednew.ui.dialogs;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.perkinszhu.www.shilednew.MainActivity;
import com.perkinszhu.www.shilednew.R;
import com.perkinszhu.www.shilednew.adapters.AccountGroupAdapter;
import com.perkinszhu.www.shilednew.tool.ToastUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:57
 */
public class AddAccountGroupDialog {
    public void add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.mainActivity);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(MainActivity.mainActivity, R.layout.dailog_add_account_group, null);
        dialog.setView(view, 0, 0, 0, 0);

        final EditText etTitle = (EditText) view.findViewById(R.id.et_group_title);
        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            if (!TextUtils.isEmpty(title)) {
                AccountGroupAdapter.addAccountGroup(title);
                dialog.dismiss();
            } else {
                ToastUtils.tip( "输入框内容不能为空!");
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}

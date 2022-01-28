package com.perkinszhu.www.shilednew.ui.dialogs;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.perkinszhu.www.shilednew.AccountActivity;
import com.perkinszhu.www.shilednew.R;
import com.perkinszhu.www.shilednew.adapters.AccountAdapter;
import com.perkinszhu.www.shilednew.bean.Account;
import com.perkinszhu.www.shilednew.tool.ToastUtils;

import org.apache.commons.lang3.RandomStringUtils;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:57
 */
public class AddAccountDialog {
    private static String TAG = AddAccountDialog.class.getName();

    public void addOrEdit(Account account) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.accountActivity);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(AccountActivity.accountActivity, R.layout.dailog_add_account, null);
        dialog.setView(view, 0, 0, 0, 0);

        final EditText userName = (EditText) view.findViewById(R.id.et_account_username);
        final EditText loginName = (EditText) view.findViewById(R.id.et_account_loginname);
        final EditText passWord = (EditText) view.findViewById(R.id.et_account_loginpassword);
        final EditText desc = (EditText) view.findViewById(R.id.et_account_desc);
        Button btnOK = (Button) view.findViewById(R.id.add_account_btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.add_account_btn_cancel);
        Button btnCreatePassWord = (Button) view.findViewById(R.id.add_account_btn_createpassword);

        if (account != null) {
            userName.setText(account.getUserName());
            loginName.setText(account.getLoginName());
            passWord.setText(account.getPassWord());
            desc.setText(account.getDesc());
        }
        btnOK.setOnClickListener(v -> {
            String userNameStr = userName.getText().toString();
            String loginNameStr = loginName.getText().toString();
            String passWordStr = passWord.getText().toString();
            String descStr = desc.getText().toString();

            if (!(TextUtils.isEmpty(userNameStr) || TextUtils.isEmpty(loginNameStr) || TextUtils.isEmpty(passWordStr))) {
                if (account != null) {
                    AccountAdapter.instance().update(new Account(loginNameStr, passWordStr, userNameStr, descStr));
                } else {
                    AccountAdapter.instance().addAccount(new Account(loginNameStr, passWordStr, userNameStr, descStr));
                }
                dialog.dismiss();
            } else {
                ToastUtils.tip("所有内容不能为空!");
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnCreatePassWord.setOnClickListener(v -> passWord.setText(createPassWord()));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private String createPassWord() {
        String reference = "QWERTYUPASDFGHJKLZXCVBNM23456789qwertyuipasdfghjkzxcvbnm!@#$%^&*()";
        return RandomStringUtils.random(10, reference);
    }
}

package com.perkinszhu.www.shield.dialogs;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perkinszhu.www.shield.AccountActivity;
import com.perkinszhu.www.shield.MainActivity;
import com.perkinszhu.www.shield.R;
import com.perkinszhu.www.shield.adapters.AccountAdapter;
import com.perkinszhu.www.shield.adapters.AccountGroupAdapter;
import com.perkinszhu.www.shield.bean.Account;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:57
 */
public class AddAccountDialog {
    private static String TAG = AddAccountDialog.class.getName();
    public void add() {

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

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userNameStr = userName.getText().toString();
                String loginNameStr = loginName.getText().toString();
                String passWordStr = passWord.getText().toString();
                String descStr = desc.getText().toString();

                if (!(TextUtils.isEmpty(userNameStr) || TextUtils.isEmpty(loginNameStr) || TextUtils.isEmpty(passWordStr))) {
                    AccountAdapter.instance().addAccount(new Account(loginNameStr, passWordStr, userNameStr,descStr));
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.mainActivity, "所有内容不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}

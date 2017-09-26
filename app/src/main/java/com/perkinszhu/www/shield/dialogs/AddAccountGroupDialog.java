package com.perkinszhu.www.shield.dialogs;

import android.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perkinszhu.www.shield.MainActivity;
import com.perkinszhu.www.shield.R;
import com.perkinszhu.www.shield.adapters.AccountGroupAdapter;
import com.perkinszhu.www.shield.tool.FileTool;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

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

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                if (!TextUtils.isEmpty(title)) {
                    AccountGroupAdapter.addAccountGroup(title);
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.mainActivity, "输入框内容不能为空!", Toast.LENGTH_SHORT).show();
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

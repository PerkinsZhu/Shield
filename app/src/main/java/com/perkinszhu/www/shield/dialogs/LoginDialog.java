package com.perkinszhu.www.shield.dialogs;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perkinszhu.www.shield.MainActivity;
import com.perkinszhu.www.shield.R;
import com.perkinszhu.www.shield.ShieldConfig;
import com.perkinszhu.www.shield.tool.EncryptTool;
import com.perkinszhu.www.shield.tool.FileTool;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-25
 * Time: 16:19
 */
public class LoginDialog {
    private static String DATA_FILE_URI = ShieldConfig.FILE_HOME+ "shutdown.dll";
    private String TAG = LoginDialog.class.getName();

    public void login() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.mainActivity);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(MainActivity.mainActivity, R.layout.dailog_set_password, null);
        dialog.setView(view, 0, 0, 0, 0);

        final EditText etPassword = (EditText) view.findViewById(R.id.et_password);
        etPassword.setHint(new SpannedString("请输入密码"));
        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                if (!TextUtils.isEmpty(password)) {
                    String MD5data = new String(Hex.encodeHex(DigestUtils.md5(password)));
                    Log.i(TAG, DATA_FILE_URI);
                    try {
                        String fileData = FileTool.readFileSdcardFile(DATA_FILE_URI);
                        Log.i(TAG,"密文："+fileData+"新密文："+MD5data);
                        if (fileData.length() == 0) {
                            FileTool.writeFileSdcardFile(DATA_FILE_URI, MD5data,false);
                            ok(password);
                        } else {
                            if (MD5data.equals(fileData)) {
                                ok(password);
                            } else {
                                Log.i(TAG,"密码错误，系统退出!");
                                Toast.makeText(MainActivity.mainActivity, "密码错误，系统退出!", Toast.LENGTH_SHORT).show();
                                System.exit(0);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.mainActivity, "输入框内容不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void ok(String password) {
        EncryptTool.setKey(password);
        Message msg = new Message();
        Bundle data = new Bundle();
        data.putString("state", "ok");
        msg.setData(data);
        MainActivity.mHandler.sendMessage(msg);
    }
}

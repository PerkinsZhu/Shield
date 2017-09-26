package com.perkinszhu.www.shield.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.perkinszhu.www.shield.MainActivity;
import com.perkinszhu.www.shield.R;
import com.perkinszhu.www.shield.ShieldConfig;
import com.perkinszhu.www.shield.bean.AccountGroup;
import com.perkinszhu.www.shield.tool.EncryptTool;
import com.perkinszhu.www.shield.tool.FileTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:14
 */
public class AccountGroupAdapter extends BaseAdapter {
    private static String TAG = AccountGroupAdapter.class.getName();
    private static List<AccountGroup> accountGroupList = new ArrayList<AccountGroup>();
    private static String ACCPOUNT_GROUP_PATH = ShieldConfig.FILE_HOME + "start.dll";
    private static LayoutInflater layoutInflater;


    public AccountGroupAdapter() {
        loadAccountGroup();
        layoutInflater = LayoutInflater.from(MainActivity.mainActivity);
    }


    private void loadAccountGroup() {
        try {
            String accountGroupStr = FileTool.readFileSdcardFile(ACCPOUNT_GROUP_PATH);
            accountGroupStr = EncryptTool.decrypt(accountGroupStr);
            Log.i(TAG, "---解密结果：" + accountGroupStr);
            if (accountGroupStr != null && accountGroupStr.trim().length() > 0) {
                accountGroupList = JSONObject.parseArray(accountGroupStr, AccountGroup.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return accountGroupList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountGroupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_account_group, null);
        ((TextView) convertView.findViewById(R.id.account_group)).setText(accountGroupList.get(position).getTitle());
        return convertView;
    }

    public static void addAccountGroup(String title) {
        accountGroupList.add(new AccountGroup(title));
        updateAccountToFile();
    }

    public static AccountGroup get(int position) {
        return accountGroupList.get(position);
    }

    public static void updateAccountToFile() {
        String json = JSON.toJSONString(accountGroupList);
        try {
            String temp = EncryptTool.encrypt(json);
            FileTool.writeFileSdcardFile(ACCPOUNT_GROUP_PATH, temp, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


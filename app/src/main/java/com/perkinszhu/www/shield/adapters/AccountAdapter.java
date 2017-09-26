package com.perkinszhu.www.shield.adapters;

import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.perkinszhu.www.shield.MainActivity;
import com.perkinszhu.www.shield.R;
import com.perkinszhu.www.shield.ShieldConfig;
import com.perkinszhu.www.shield.bean.Account;
import com.perkinszhu.www.shield.bean.AccountGroup;
import com.perkinszhu.www.shield.tool.EncryptTool;
import com.perkinszhu.www.shield.tool.FileTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:14
 */
public class AccountAdapter extends BaseAdapter {
    private static String TAG = AccountAdapter.class.getName();
    private List<Account> accountList;
    private static String ACCPOUNT_GROUP_PATH = ShieldConfig.FILE_HOME + "start.dll";
    private LayoutInflater layoutInflater;
    private static AccountAdapter accountGroupAdapter = new AccountAdapter();
    private AccountGroup accountGroup;

    public static AccountAdapter instance() {

        return accountGroupAdapter;
    }

    public void init(AccountGroup group) {
        accountGroup = group;
        accountList = group.getAccounts();
    }

    private AccountAdapter() {
        layoutInflater = LayoutInflater.from(MainActivity.mainActivity);
    }


    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.content_account_views, null);
        Account account = accountList.get(position);
        ((TextView) convertView.findViewById(R.id.content_account_username)).setText(account.getUserName());
        ((TextView) convertView.findViewById(R.id.content_account_loginname)).setText(account.getLoginName());
        ((TextView) convertView.findViewById(R.id.content_account_password)).setText(account.getPassWord());
        return convertView;
    }

    public void addAccount(Account account) {
        if (account == null) {
            return;
        }
        accountList.add(account);
        AccountGroupAdapter.updateAccountToFile();
    }

    public Account get(int position) {
        return accountList.get(position);

    }
}


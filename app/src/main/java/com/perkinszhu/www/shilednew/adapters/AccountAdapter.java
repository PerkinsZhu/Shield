package com.perkinszhu.www.shilednew.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.perkinszhu.www.shilednew.AccountActivity;
import com.perkinszhu.www.shilednew.MainActivity;
import com.perkinszhu.www.shilednew.R;
import com.perkinszhu.www.shilednew.config.ShieldConfig;
import com.perkinszhu.www.shilednew.bean.Account;
import com.perkinszhu.www.shilednew.bean.AccountGroup;
import com.perkinszhu.www.shilednew.ui.dialogs.AddAccountDialog;
import com.perkinszhu.www.shilednew.ui.dialogs.DialogTool;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:14
 */
public class AccountAdapter extends BaseAdapter implements Callable {
    private static String TAG = AccountAdapter.class.getName();
    private List<Account> accountList;
    private static String ACCPOUNT_GROUP_PATH = ShieldConfig.FILE_HOME + "start.dll";
    private LayoutInflater layoutInflater;
    private static AccountAdapter accountGroupAdapter = new AccountAdapter();
    private AccountGroup accountGroup;
    private View convertView;

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
        ((TextView) convertView.findViewById(R.id.content_account_password)).setText("*************");
        ((TextView) convertView.findViewById(R.id.content_account_desc)).setText(account.getDesc());
        ((Button) convertView.findViewById(R.id.content_account_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealPosition = position;
                deleteAccount();
            }
        });
        ((Button) convertView.findViewById(R.id.content_account_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealPosition = position;
                editAccount();
            }
        });
        return convertView;
    }

    private void editAccount() {
        new AddAccountDialog().addOrEdit(accountList.get(dealPosition));
    }

    private int dealPosition;//点击按钮所在的position

    private void deleteAccount() {
        DialogTool.showNormalDialog("确定删除\"" + accountList.get(dealPosition).getLoginName() + "\"吗？", this, AccountActivity.accountActivity);
    }

    public void addAccount(Account account) {
        if (account == null) {
            return;
        }
        accountList.add(account);
        AccountGroupAdapter.updateAccountToFile();
        notifyDataSetChanged();
    }

    public Account get(int position) {
        return accountList.get(position);
    }

    @Override
    public Object call() throws Exception {
        notifyDataSetChanged();
        accountList.remove(dealPosition);
        AccountGroupAdapter.updateAccountToFile();
        return null;
    }

    public void showPassword(View view, int position) {
        ((TextView) view.findViewById(R.id.content_account_password)).setText(accountList.get(position).getPassWord());
    }

    public void update(Account newAccount) {
        accountList.set(dealPosition,newAccount);
        notifyDataSetChanged();
        AccountGroupAdapter.updateAccountToFile();
    }
}


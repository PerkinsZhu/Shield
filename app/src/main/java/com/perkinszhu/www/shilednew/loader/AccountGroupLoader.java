package com.perkinszhu.www.shilednew.loader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.perkinszhu.www.shilednew.AccountActivity;
import com.perkinszhu.www.shilednew.MainActivity;
import com.perkinszhu.www.shilednew.adapters.AccountGroupAdapter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:44
 */
public class AccountGroupLoader {
    private ListView accountGroupView;
    private String TAG = AccountGroupLoader.class.getName();

    public void load(ListView accountGroupView) {
        this.accountGroupView = accountGroupView;
        AccountGroupAdapter accountGroupAdapter = new AccountGroupAdapter();
        accountGroupView.setAdapter(accountGroupAdapter);
        accountGroupView.setOnItemClickListener((parent, view, position, id) -> showAccounts(position));
        accountGroupView.setFastScrollEnabled(true);
    }

    private void showAccounts(int position) {

        Intent intent = new Intent(MainActivity.mainActivity, AccountActivity.class);
        Bundle bundle = new Bundle();// 创建 email 内容
        bundle.putInt("position", position);
        intent.putExtra("emil", bundle);// 封装 email
        MainActivity.mainActivity.startActivity(intent);
    }
}

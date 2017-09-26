package com.perkinszhu.www.shield.loader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.perkinszhu.www.shield.AccountActivity;
import com.perkinszhu.www.shield.MainActivity;
import com.perkinszhu.www.shield.R;
import com.perkinszhu.www.shield.adapters.AccountAdapter;
import com.perkinszhu.www.shield.adapters.AccountGroupAdapter;
import com.perkinszhu.www.shield.bean.AccountGroup;

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
        //TODO 加载内容
        AccountGroupAdapter accountGroupAdapter = new AccountGroupAdapter();
        accountGroupView.setAdapter(accountGroupAdapter);
        accountGroupView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showAccounts(position);
            }
        });
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

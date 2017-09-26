package com.perkinszhu.www.shield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.perkinszhu.www.shield.adapters.AccountAdapter;
import com.perkinszhu.www.shield.adapters.AccountGroupAdapter;
import com.perkinszhu.www.shield.bean.AccountGroup;
import com.perkinszhu.www.shield.dialogs.AddAccountDialog;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 10:44
 */
public class AccountActivity extends Activity {
    private static String TAG = AccountActivity.class.getName();
    public static  AccountActivity accountActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.accountActivity = this;
        int position = getIntent().getBundleExtra("emil").getInt("position");
        AccountAdapter.instance().init(AccountGroupAdapter.get(position));


        setContentView(R.layout.activity_account_views);
        ListView accountLists = (ListView) findViewById(R.id.account_item);
        accountLists.setAdapter(AccountAdapter.instance());
        loadAddAccount();

    }

    private void loadAddAccount() {
        Button addAccountButton = (Button) findViewById(R.id.add_account);
        addAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAccount();
            }
        });
    }

    private void addAccount() {
        new AddAccountDialog().add();
    }
}

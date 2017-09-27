package com.perkinszhu.www.shield;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.perkinszhu.www.shield.dialogs.AddAccountGroupDialog;
import com.perkinszhu.www.shield.dialogs.LoginDialog;
import com.perkinszhu.www.shield.dialogs.PassWordDialog;
import com.perkinszhu.www.shield.loader.AccountGroupLoader;

public class MainActivity extends AppCompatActivity {


    public static Activity mainActivity;
    public static Handler mHandler;

    private LoginDialog loginDialog;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                   new AddAccountGroupDialog().add();
                    return true;
                case R.id.navigation_notifications:
                    new PassWordDialog().edit();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        loginDialog = new LoginDialog();
        loginDialog.login();
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                String state = data.getString("state");
                if ("ok".equals(state)) {
                    loadContent();
                }
            }
        };
    }
    private void loadContent() {
        new AccountGroupLoader().load((ListView) findViewById(R.id.list_account_group));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

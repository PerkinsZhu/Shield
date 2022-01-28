package com.perkinszhu.www.shilednew;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.perkinszhu.www.shilednew.databinding.ActivityMainBinding;
import com.perkinszhu.www.shilednew.dialog.PassWordDialog;
import com.perkinszhu.www.shilednew.dialog.SearchDialog;
import com.perkinszhu.www.shilednew.loader.AccountGroupLoader;
import com.perkinszhu.www.shilednew.ui.dialogs.AddAccountGroupDialog;
import com.perkinszhu.www.shilednew.ui.dialogs.LoginDialog;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1024;

    private ActivityMainBinding binding;

    public static Activity mainActivity;
    public static Handler mHandler;
    private LoginDialog loginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        loginDialog = new LoginDialog();
        loginDialog.login();
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                String state = data.getString("state");
                if ("ok".equals(state)) {
                    initContent();
                    loadContent();
                } else if ("reload".equals(state)) {
                    //修改密码后重新加载数据
                    loadContent();
                }
            }
        };
    }

    private void initContent() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        requestPermission();
    }

    private void loadContent() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        new AccountGroupLoader().load((ListView) findViewById(R.id.list_account_group));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (Environment.isExternalStorageManager()) {
                //已有权限
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 先判断有没有权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //已有权限
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        } else {
            //已有权限
        }
    }

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
                case R.id.navigation_home:
                    new SearchDialog().search();
                    return true;
            }
            return false;
        }
    };
}
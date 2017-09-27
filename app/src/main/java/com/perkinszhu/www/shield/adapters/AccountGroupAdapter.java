package com.perkinszhu.www.shield.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.perkinszhu.www.shield.MainActivity;
import com.perkinszhu.www.shield.R;
import com.perkinszhu.www.shield.ShieldConfig;
import com.perkinszhu.www.shield.bean.AccountGroup;
import com.perkinszhu.www.shield.dialogs.DialogTool;
import com.perkinszhu.www.shield.tool.EncryptTool;
import com.perkinszhu.www.shield.tool.FileTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:14
 */
public class AccountGroupAdapter extends BaseAdapter implements Callable {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_account_group, null);
        ((TextView) convertView.findViewById(R.id.account_group)).setText(accountGroupList.get(position).getTitle());
        ((Button) convertView.findViewById(R.id.group_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealPosition = position;
                deleteGroupItem();
            }
        });
        return convertView;
    }
    //FIXME 取消全局变量通信方式
    private int dealPosition;//保存点击的item
    private void deleteGroupItem() {
        DialogTool.showNormalDialog("确定要删除\"" + get(dealPosition).getTitle() + "\"吗？", this,MainActivity.mainActivity);
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

    @Override
    public Object call() throws Exception {
        notifyDataSetChanged();
        accountGroupList.remove(dealPosition);
        updateAccountToFile();
        return null;
    }
}


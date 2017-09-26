package com.perkinszhu.www.shield.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: PerkinsZhu
 * Date: 2017-09-26
 * Time: 9:00
 */
public class AccountGroup {
    private String title;
    private List<Account> accounts ;

    public AccountGroup() {
        super();
    }

    public AccountGroup(String title) {
        this.title = title;
        accounts = new ArrayList<>(1);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

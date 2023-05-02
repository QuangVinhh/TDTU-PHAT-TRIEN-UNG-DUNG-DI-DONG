package com.example.mtvi_ver2.database.data;

public class DataAccounts {
    int account_id;
    String account_name;
    String account_email;
    String account_password;
    String account_check;

    public DataAccounts(){

    }

    @Override
    public String toString() {
        return "DataAccounts{" +
                "account_id=" + account_id +
                ", account_name='" + account_name + '\'' +
                ", account_email='" + account_email + '\'' +
                ", account_password='" + account_password + '\'' +
                ", account_check='" + account_check + '\'' +
                '}';
    }

    public DataAccounts(int account_id, String account_name, String account_email, String account_password, String account_check) {
        this.account_id = account_id;
        this.account_name = account_name;
        this.account_email = account_email;
        this.account_password = account_password;
        this.account_check = account_check;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public String getAccount_check() {
        return account_check;
    }

    public void setAccount_check(String account_check) {
        this.account_check = account_check;
    }
}

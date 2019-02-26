package com.example.a92385.a2018ydhldemo.AccountManager;

import com.example.a92385.a2018ydhldemo.R;

public class Accounts {

   private String id;
   private int img = R.drawable.love;
    private String License;
   private String owner;
   private String Balance;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicense() {
        return License;
    }

    public void setLicense(String lincense) {
        this.License = lincense;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }
}

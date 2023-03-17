package com.example.exercise04;

import android.graphics.Bitmap;

public class Profile {
    Bitmap avatar;
    String name_ava;
    String major;
    String name;
    String phone;
    String email;
    String address;
    String homepage;

    public Profile(Bitmap avatar, String name_ava, String major, String name, String phone, String email, String address, String homepage) {
        this.avatar = avatar;
        this.name_ava = name_ava;
        this.major = major;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.homepage = homepage;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getName_ava() {
        return name_ava;
    }

    public void setName_ava(String name_ava) {
        this.name_ava = name_ava;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}

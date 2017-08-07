package com.example.francisco.w2d1;

/**
 * Created by FRANCISCO on 07/08/2017.
 */

public class MyContact {
    String name;
    String phone;

    public MyContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

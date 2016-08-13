package com.example.wang.mynotedemo.model;

/**
 * Created by wang on 16/8/13.
 */
public class LoginResult {

    /**
     * status : success
     * customer_id : 1
     */

    private String status;
    private String customer_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}

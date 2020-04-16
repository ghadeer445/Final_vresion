package com.project2.haj.helper.Models;

public class Order {

    private String id;
    private String delivary_date;
    private String tank_size;
    private String address;
    private String payment_type;
    private String mobile_number;
    private String user_id;
    private String user_name;
    private String status;
    private String created_date;
    private String updated_date;

    public Order(String id, String delivary_date, String tank_size, String address, String payment_type, String mobile_number, String user_id, String user_name, String status, String created_date, String updated_date) {
        this.id = id;
        this.delivary_date = delivary_date;
        this.tank_size = tank_size;
        this.address = address;
        this.payment_type = payment_type;
        this.mobile_number = mobile_number;
        this.user_id = user_id;
        this.user_name = user_name;
        this.status = status;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelivary_date() {
        return delivary_date;
    }

    public void setDelivary_date(String delivary_date) {
        this.delivary_date = delivary_date;
    }

    public String getTank_size() {
        return tank_size;
    }

    public void setTank_size(String tank_size) {
        this.tank_size = tank_size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }
}

package com.example.myapplication.Model;

public class Cart
{
    private String pid, proName,price;

    public Cart()
    {
    }

    public Cart(String pid, String proName, String price) {
        this.pid = pid;
        this.proName = proName;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

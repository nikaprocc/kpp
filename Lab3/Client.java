package com.labs;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String phone;

    // Конструктор без параметрів
    public Client() {}

    // Конструктор з параметрами
    public Client(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Client{name='" + name + "', phone='" + phone + "'}";
    }
}

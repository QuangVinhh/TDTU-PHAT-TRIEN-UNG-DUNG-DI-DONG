package com.example.mtvi_ver2.database.data;

public class DataServices {
    int service_id;
    String service_name;
    String service_price;
    String service_detail;

    public DataServices(){

    }

    public DataServices(int service_id, String service_name, String service_price, String service_detail) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.service_price = service_price;
        this.service_detail = service_detail;
    }

    @Override
    public String toString() {
        return "DataServices{" +
                "service_id=" + service_id +
                ", service_name='" + service_name + '\'' +
                ", service_price='" + service_price + '\'' +
                ", service_detail='" + service_detail + '\'' +
                '}';
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }

    public String getService_detail() {
        return service_detail;
    }

    public void setService_detail(String service_detail) {
        this.service_detail = service_detail;
    }
}

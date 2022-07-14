package Model;

import java.util.List;

public class KeluarKendaraanModel {
    Boolean status;
    private List<DataKendaraan> data;
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DataKendaraan> getData() {
        return data;
    }

    public void setData(List<DataKendaraan> data) {
        this.data = data;
    }

    public class DataKendaraan {
        private int id;
        private String license_plate;
        private int total_price;

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLicense_plate() {
            return license_plate;
        }

        public void setLicense_plate(String license_plate) {
            this.license_plate = license_plate;
        }
    }
}

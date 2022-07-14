package Model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class KendaraanModel {

    // RESPONSE
    private String status;
    private Data data;

    public KendaraanModel(String status, Data data) {
        this.status = status;
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    }

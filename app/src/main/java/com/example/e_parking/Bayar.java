package com.example.e_parking;

import static com.example.e_parking.R.id.jmlh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import Model.DetailModel;
import Model.KeluarKendaraanModel;
import Model.KendaraanModel;
import Model.SimpanKendaraanModel;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bayar extends AppCompatActivity {
    String token;
    Button var_bayar;
    int id;
    LoadingDialog loadingDialog = new LoadingDialog(Bayar.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        getBayar();

        var_bayar = findViewById(R.id.btn_simpan);

        var_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();
                SimpanKendaraan(simpanKendaraan());
            }
        });
    }

    public SimpanKendaraanModel simpanKendaraan(){
        SimpanKendaraanModel simpan = new SimpanKendaraanModel();
        TextView jumlah = findViewById(jmlh);
        int var_jmlh = Integer.parseInt((String) jumlah.getText());
        simpan.setTotal_price(var_jmlh);

        return simpan;
    }

    public void SimpanKendaraan(SimpanKendaraanModel simpan){
        ApiService.endpoint().SimpanKendaraan("Bearer " + Preferences.getKEY_Token(getBaseContext()),getIntent().getExtras().getInt("ID"),simpan).enqueue(new Callback<SimpanKendaraanModel>() {
            @Override
            public void onResponse(Call<SimpanKendaraanModel> call, Response<SimpanKendaraanModel> response) {
                if (response.isSuccessful()){
                    SimpanKendaraanModel simpan = response.body();
                    if(simpan.getStatus() != true){
                        Toast.makeText(Bayar.this, simpan.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(Bayar.this, simpan.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Bayar.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<SimpanKendaraanModel> call, Throwable t) {

            }
        });
    }

    public void getBayar(){
        ApiService.endpoint().GetKendaraan("Bearer " + Preferences.getKEY_Token(getBaseContext()), getIntent().getExtras().getInt("ID")).enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                DetailModel detailModel = response.body();
                TextView license = (TextView) findViewById(R.id.nop);
                TextView masuk = (TextView) findViewById(R.id.JAMmasuk);
                TextView keluar = (TextView) findViewById(R.id.JAMkeluar);
                TextView jumlah = (TextView) findViewById(jmlh);
                String license_plate = detailModel.getLicense_plate();
                license.setText(license_plate);

                int JamMasuk = detailModel.getIn_time();
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date((long) JamMasuk * 1000);
                masuk.setText(fmt.format(date));

                int JamKeluar = detailModel.getOut_time();
                SimpleDateFormat fmtout = new SimpleDateFormat("dd-MM-yyyy");
                Date dateout = new Date((long) JamKeluar * 1000);
                keluar.setText(fmtout.format(dateout));

                int vehicle_price = detailModel.getVehicle_price();

                int jumlah_total = (((JamKeluar - JamMasuk)/3600)+1) * vehicle_price;

                jumlah.setText(String.valueOf(jumlah_total));
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {

            }
        });
    }

}
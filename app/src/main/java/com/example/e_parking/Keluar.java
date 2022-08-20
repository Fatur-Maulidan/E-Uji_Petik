package com.example.e_parking;

import static com.example.e_parking.R.id.jmlh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.DetailModel;
import Model.KeluarKendaraanModel;
import Model.SimpanKendaraanModel;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Keluar extends AppCompatActivity {

    private ListView listview;
    String Token;
    int initialize;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;

    private List<KeluarKendaraanModel.DataKendaraan> datas = new ArrayList<>();
    LoadingDialog loadingDialog = new LoadingDialog(Keluar.this);
    final ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keluar);
        Intent i = getIntent();
        Token = Preferences.getKEY_Token(getBaseContext());
        initialize = i.getExtras().getInt("Initialize");
        loadingDialog.startLoadingDialog();
        setupView();
        setupRecycleView();
        getDatafromAPI();
    }

    private void setupRecycleView(){
        mainAdapter = new MainAdapter(datas, new MainAdapter.OnAdapterListener() {
            @Override
            public void onClick(KeluarKendaraanModel.DataKendaraan data) {
                loadingDialog.startLoadingDialog();
                getBayar(data);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setupView(){
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void getDatafromAPI(){

        ApiService.endpoint().Kendaraan("Bearer " + Token,Preferences.getKey_Id(getBaseContext()),initialize).enqueue(new Callback<KeluarKendaraanModel>() {
            @Override
            public void onResponse(Call<KeluarKendaraanModel> call, Response<KeluarKendaraanModel> response) {
                if (response.isSuccessful()){
                    List<KeluarKendaraanModel.DataKendaraan> datas = response.body().getData();
                    mainAdapter.setData(datas);
                    loadingDialog.dissmissDialog();
                }
            }
            @Override
            public void onFailure(Call<KeluarKendaraanModel> call, Throwable t) {

            }
        });
    }

    public void getBayar(KeluarKendaraanModel.DataKendaraan dataKendaraan){
        ApiService.endpoint().GetKendaraan("Bearer " + Preferences.getKEY_Token(getBaseContext()),dataKendaraan.getId()).enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                DetailModel detailModel = response.body();
                int jumlah_total = (((detailModel.getOut_time() - detailModel.getIn_time())/3600)+1) * detailModel.getVehicle_price();
                SimpanKendaraan(simpanKendaraan(jumlah_total), dataKendaraan.getId());
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {

            }
        });
    }

    public SimpanKendaraanModel simpanKendaraan(int jumlah){
        SimpanKendaraanModel simpan = new SimpanKendaraanModel();
        simpan.setTotal_price(jumlah);

        return simpan;
    }

    public void SimpanKendaraan(SimpanKendaraanModel simpan, int id){
        ApiService.endpoint().SimpanKendaraan("Bearer " + Preferences.getKEY_Token(getBaseContext()),id,simpan).enqueue(new Callback<SimpanKendaraanModel>() {
            @Override
            public void onResponse(Call<SimpanKendaraanModel> call, Response<SimpanKendaraanModel> response) {
                if (response.isSuccessful()){
                    SimpanKendaraanModel simpan = response.body();
                    if(simpan.getStatus() != true){
                        Toast.makeText(Keluar.this, simpan.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Keluar.this, simpan.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Keluar.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<SimpanKendaraanModel> call, Throwable t) {

            }
        });
    }

}
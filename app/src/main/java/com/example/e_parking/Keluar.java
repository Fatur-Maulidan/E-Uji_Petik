package com.example.e_parking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.KeluarKendaraanModel;
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
    final ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keluar);
        Intent i = getIntent();
        Token = Preferences.getKEY_Token(getBaseContext());
        initialize = i.getExtras().getInt("Initialize");

        setupView();
        setupRecycleView();
        getDatafromAPI();
    }

    private void setupRecycleView(){
        mainAdapter = new MainAdapter(datas, new MainAdapter.OnAdapterListener() {
            @Override
            public void onClick(KeluarKendaraanModel.DataKendaraan data) {
//                Toast.makeText(keluar.this, "",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Keluar.this, Bayar.class);
                i.putExtra("ID",data.getId());
                i.putExtra("Initialize",initialize);
                startActivity(i);
                finish();
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
                }
            }
            @Override
            public void onFailure(Call<KeluarKendaraanModel> call, Throwable t) {

            }
        });
    }
}
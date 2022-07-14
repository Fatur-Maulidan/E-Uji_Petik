package com.example.e_parking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.KeluarKendaraanModel;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<KeluarKendaraanModel.DataKendaraan> datas;
    private OnAdapterListener listener;

    public MainAdapter(List<KeluarKendaraanModel.DataKendaraan> datas, OnAdapterListener listener){
        this.datas = datas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        KeluarKendaraanModel.DataKendaraan data = datas.get(position);
        holder.txtpol.setText(data.getLicense_plate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtpol;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtpol = itemView.findViewById(R.id.nomorPolisi);
        }
    }

    public void setData(List<KeluarKendaraanModel.DataKendaraan> data){
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }

    interface OnAdapterListener{
        void onClick(KeluarKendaraanModel.DataKendaraan data);

    }

//        private List<Keluarmobilmodel.Dataa> dataList = new ArrayList<>();
//        private Context context;
//
//        public ListViewAdapter(Context context, List<Keluarmobilmodel.Dataa> dataList){
//            this.context = context;
//            this.dataList = dataList;
//        }
//
//        @Override
//        public int getCount() {
//            return dataList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return dataList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup parent) {
//            if(view==null){
//                view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
//            }
//
//            TextView nopolisi = (TextView) findViewById(R.id.nomorPolisi);
//
//            final Keluarmobilmodel.Dataa thisDataa = dataList.get(position);
//
//            nopolisi.setText(thisDataa.getLicense_plate());
//
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String[] dataList = {
//                            String.valueOf(thisDataa.getId()),
//                            thisDataa.getLicense_plate()
//                    };
//                    openDetailActivity(dataList);
//                }
//            });
//            return view;
//        }
//
//        private void openDetailActivity(String[] data){
//            Intent i = new Intent(keluar.this, Bayar.class);
//            i.putExtra("ID",data[0]);
//            i.putExtra("License",data[1]);
//            startActivity(i);
//        }

}

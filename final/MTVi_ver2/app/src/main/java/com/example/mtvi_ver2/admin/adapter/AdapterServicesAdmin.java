package com.example.mtvi_ver2.admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataServices;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemServices;

import java.util.ArrayList;

public class AdapterServicesAdmin extends RecyclerView.Adapter<AdapterServicesAdmin.ServicesAdminViewHolder>{

    ArrayList<DataServices> mDataServices;
    InterfaceClickItemServices interfaceClickItemServices;

    public AdapterServicesAdmin(ArrayList<DataServices> mDataServices, InterfaceClickItemServices mListener) {
        this.mDataServices = mDataServices;
        this.interfaceClickItemServices = mListener;
    }

    @NonNull
    @Override
    public ServicesAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_services_admin, parent, false);
        return new ServicesAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdminViewHolder holder, int position) {
        DataServices services = mDataServices.get(position);

        /*---false---*/
        if(services == null){
            return;
        }

        /*---true---*/
        holder.FSA_viewService_name.setText(services.getService_name());
        holder.FSA_viewService_price.setText(services.getService_price() + " VND / month");
        holder.FSA_viewService_detail.setText(services.getService_detail());
        holder.FSA_adapter_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceClickItemServices.onClickItemServices(services);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mDataServices != null){
            return mDataServices.size();
        }
        return 0;
    }

    public class ServicesAdminViewHolder extends RecyclerView.ViewHolder {

        CardView FSA_adapter_cardView;
        TextView FSA_viewService_name, FSA_viewService_price, FSA_viewService_detail;

        public ServicesAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            FSA_viewService_name = itemView.findViewById(R.id.FSA_viewService_name);
            FSA_viewService_price = itemView.findViewById(R.id.FSA_viewService_price);
            FSA_viewService_detail = itemView.findViewById(R.id.FSA_viewService_detail);
            FSA_adapter_cardView = itemView.findViewById(R.id.FSA_adapter_cardView);

        }

    }
}

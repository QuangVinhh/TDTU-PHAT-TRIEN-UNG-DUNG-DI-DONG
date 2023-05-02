package com.example.mtvi_ver2.admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemAccounts;

import java.util.ArrayList;

public class AdapterAccountsAdmin extends RecyclerView.Adapter<AdapterAccountsAdmin.AccountsAdminViewHolder>{

    ArrayList<DataAccounts> mDataAccounts;
    InterfaceClickItemAccounts interfaceClickItemAccounts;

    public AdapterAccountsAdmin(ArrayList<DataAccounts> mDataAccounts, InterfaceClickItemAccounts mListener) {
        this.mDataAccounts = mDataAccounts;
        this.interfaceClickItemAccounts = mListener;
    }

    @NonNull
    @Override
    public AdapterAccountsAdmin.AccountsAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_accounts_admin, parent, false);
        return new AdapterAccountsAdmin.AccountsAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAccountsAdmin.AccountsAdminViewHolder holder, int position) {
        DataAccounts accounts = mDataAccounts.get(position);

        /*---false---*/
        if(accounts == null){
            return;
        }

        /*---true---*/
        holder.FAA_viewAccount_name.setText(accounts.getAccount_name());
        holder.FAA_viewAccount_email.setText(accounts.getAccount_email());
        holder.FAA_viewAccount_check.setText(accounts.getAccount_check());
        holder.FAA_adapter_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceClickItemAccounts.onClickItemAccounts(accounts);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mDataAccounts != null){
            return mDataAccounts.size();
        }
        return 0;
    }

    public class AccountsAdminViewHolder extends RecyclerView.ViewHolder {

        CardView FAA_adapter_cardView;
        TextView FAA_viewAccount_name, FAA_viewAccount_email, FAA_viewAccount_check;

        public AccountsAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            FAA_adapter_cardView = itemView.findViewById(R.id.FAA_adapter_cardView);
            FAA_viewAccount_name = itemView.findViewById(R.id.FAA_viewAccount_name);
            FAA_viewAccount_email = itemView.findViewById(R.id.FAA_viewAccount_email);
            FAA_viewAccount_check = itemView.findViewById(R.id.FAA_viewAccount_check);

        }

    }
}

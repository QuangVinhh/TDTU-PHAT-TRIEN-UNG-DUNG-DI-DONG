package com.example.mtvi_ver2.admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.data.DataServices;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemAccounts;

import java.util.ArrayList;

public class AdapterAccountsAdmin extends RecyclerView.Adapter<AdapterAccountsAdmin.AccountsAdminViewHolder> implements Filterable {

    ArrayList<DataAccounts> mDataAccounts;
    ArrayList<DataAccounts> mDataAccountsOld;
    InterfaceClickItemAccounts interfaceClickItemAccounts;

    public AdapterAccountsAdmin(ArrayList<DataAccounts> mDataAccounts, InterfaceClickItemAccounts mListener) {
        this.mDataAccounts = mDataAccounts;
        this.interfaceClickItemAccounts = mListener;
        this.mDataAccountsOld = mDataAccounts;
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

    /*---search---*/
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mDataAccounts = mDataAccountsOld;
                } else {
                    ArrayList<DataAccounts> listMoviesSearch = new ArrayList<>();
                    for(DataAccounts accounts : mDataAccountsOld){
                        if(accounts.getAccount_name().toLowerCase().contains(strSearch.toLowerCase())){
                            listMoviesSearch.add(accounts);
                        }
                    }

                    mDataAccounts = listMoviesSearch;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataAccounts;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataAccounts = (ArrayList<DataAccounts>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

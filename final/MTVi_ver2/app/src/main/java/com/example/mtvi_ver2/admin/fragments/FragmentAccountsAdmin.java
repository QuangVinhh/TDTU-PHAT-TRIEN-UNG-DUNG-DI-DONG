package com.example.mtvi_ver2.admin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterAccountsAdmin;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.sqlite.AccountsDAO;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemAccounts;

import java.util.ArrayList;

public class FragmentAccountsAdmin extends Fragment {

    ArrayList<DataAccounts> dataAccounts = new ArrayList<>();
    DataAccounts accounts = new DataAccounts();
    AdapterAccountsAdmin adapterAccountsAdmin;
    RecyclerView FAA_recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts_admin, container, false);
        FAA_recyclerView = view.findViewById(R.id.FAA_recyclerView);

        /*---data---*/
        dataAccounts = AccountsDAO.readAccounts(getActivity());

        /*---adapter and recyclerview---*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        FAA_recyclerView.setLayoutManager(linearLayoutManager);

        adapterAccountsAdmin = new AdapterAccountsAdmin(dataAccounts, new InterfaceClickItemAccounts() {
            @Override
            public void onClickItemAccounts(DataAccounts accounts) {
                showDialogViewAccount(accounts);
            }
        });
        FAA_recyclerView.setAdapter(adapterAccountsAdmin);
    
        return view;
    }

    /*=============================================================================================*/
    /*---method || show dialog view account---*/
    /*=============================================================================================*/
    private void showDialogViewAccount(DataAccounts accounts) {
        Toast.makeText(getActivity(), "VIEW ACCOUNT", Toast.LENGTH_SHORT).show();
    }
}
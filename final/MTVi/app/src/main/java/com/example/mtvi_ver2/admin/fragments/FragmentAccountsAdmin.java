package com.example.mtvi_ver2.admin.fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterAccountsAdmin;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.sqlite.AccountsDAO;
import com.example.mtvi_ver2.main.MainActivity;
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
                /*---show dialog view account---*/
            }
        });
        FAA_recyclerView.setAdapter(adapterAccountsAdmin);
    
        return view;
    }

    /*=============================================================================================*/
    /*---method || show dialog view account---*/
    /*=============================================================================================*/


    /*=============================================================================================*/
    /*---method || menu search---*/
    /*=============================================================================================*/

    @Override
    public void onCreate(@NonNull Bundle saveInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_admin_services, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getActivity().SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterAccountsAdmin.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterAccountsAdmin.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
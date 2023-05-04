package com.example.mtvi_ver2.user.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterServicesAdmin;
import com.example.mtvi_ver2.database.data.DataServices;
import com.example.mtvi_ver2.database.sqlite.ServicesDAO;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemServices;
import com.example.mtvi_ver2.user.adapters.AdapterServicesUser;

import java.util.ArrayList;


public class FragmentServicesUser extends Fragment {

    ArrayList<DataServices> dataServices = new ArrayList<>();
    DataServices services = new DataServices();
    AdapterServicesUser adapterServicesUser;
    RecyclerView FSU_recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services_user, container, false);
        FSU_recyclerView = view.findViewById(R.id.FSU_recyclerView);

        /*---data---*/
        dataServices = ServicesDAO.readServices(getActivity());

        /*---adapter and recyclerview---*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        FSU_recyclerView.setLayoutManager(linearLayoutManager);

        adapterServicesUser = new AdapterServicesUser(dataServices, new InterfaceClickItemServices() {
            @Override
            public void onClickItemServices(DataServices services) {
                showDialogViewService(services);
            }
        });
        FSU_recyclerView.setAdapter(adapterServicesUser);

        return view;
    }

    /*=============================================================================================*/
    /*---method || event button add service---*/
    /*=============================================================================================*/
    private void showDialogViewService(DataServices services) {
    }
}
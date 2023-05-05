package com.example.mtvi_ver2.admin.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterServicesAdmin;
import com.example.mtvi_ver2.database.data.DataServices;
import com.example.mtvi_ver2.main.MainActivity;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemServices;
import com.example.mtvi_ver2.database.sqlite.ServicesDAO;

import java.util.ArrayList;
public class FragmentServicesAdmin extends Fragment {
    ArrayList<DataServices> dataServices = new ArrayList<>();
    DataServices services = new DataServices();
    AdapterServicesAdmin adapterServicesAdmin;
    RecyclerView FSA_recyclerView;
    Button FSA_button_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services_admin, container, false);
        FSA_recyclerView = view.findViewById(R.id.FSA_recyclerView);
        FSA_button_add = view.findViewById(R.id.FSA_button_add);

        /*---data---*/
        dataServices = ServicesDAO.readServices(getActivity());

        /*---adapter and recyclerview---*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        FSA_recyclerView.setLayoutManager(linearLayoutManager);

        adapterServicesAdmin = new AdapterServicesAdmin(dataServices, new InterfaceClickItemServices() {
            @Override
            public void onClickItemServices(DataServices services) {
                showDialogViewService(services);
            }
        });
        FSA_recyclerView.setAdapter(adapterServicesAdmin);

        /*---event button add service---*/
        EventButtonAddService();

        return view;
    }

    /*=============================================================================================*/
    /*---method || event button add service---*/
    /*=============================================================================================*/
    private void EventButtonAddService() {
        FSA_button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddService();
            }
        });
    }

    /*=============================================================================================*/
    /*---method || show dialog add service---*/
    /*=============================================================================================*/
    private void showDialogAddService() {
        AlertDialog.Builder builderAddService = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_services_admin, null);
        builderAddService.setView(view);
        builderAddService.setCancelable(false);
        Dialog dialog = builderAddService.create();
        dialog.show();

        /*---find view by id---*/
        EditText FSA_addService_name = view.findViewById(R.id.FSA_addService_name);
        EditText FSA_addService_price = view.findViewById(R.id.FSA_addService_price);
        EditText FSA_addService_detail = view.findViewById(R.id.FSA_addService_detail);
        Button FSA_addService_add = view.findViewById(R.id.FSA_addService_add);
        Button FSA_addService_cancel = view.findViewById(R.id.FSA_addService_cancel);

        /*---event button add service---*/
        FSA_addService_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FSA_addService_name_value = FSA_addService_name.getText().toString();
                String FSA_addService_price_value = FSA_addService_price.getText().toString();
                String FSA_addService_detail_value = FSA_addService_detail.getText().toString();

                /*---check---*/
                if(TextUtils.isEmpty(FSA_addService_name.getText().toString())) {
                    FSA_addService_name.requestFocus();
                    FSA_addService_name.setError("Please enter service's name !");
                } else if(TextUtils.isEmpty(FSA_addService_price.getText().toString())) {
                    FSA_addService_price.requestFocus();
                    FSA_addService_price.setError("Please enter service's price !");
                } else if(TextUtils.isEmpty(FSA_addService_detail.getText().toString())) {
                    FSA_addService_detail.requestFocus();
                    FSA_addService_detail.setError("Please choose service's detail !");
                } else {

                    FSA_addService_name.setError(null);
                    FSA_addService_price.setError(null);
                    FSA_addService_detail.setError(null);

                    services.setService_name(FSA_addService_name_value);
                    services.setService_price(FSA_addService_price_value);
                    services.setService_detail(FSA_addService_detail_value);

                    dataServices.add(services);

                    if(ServicesDAO.insertServices(getActivity(), services)){
                        Toast.makeText(getActivity(), "INSERT SERVICE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                        dataServices.clear();
                        dataServices.addAll(ServicesDAO.readServices(getActivity()));
                        adapterServicesAdmin.notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "INSERT SERVICE FAILED !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*---event button cancel service---*/
        FSA_addService_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /*=============================================================================================*/
    /*---method || show dialog view (edit / delete) service---*/
    /*=============================================================================================*/
    private void showDialogViewService(DataServices services) {
        AlertDialog.Builder builderAddService = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_view_services_admin, null);
        builderAddService.setView(view);
        builderAddService.setCancelable(false);
        Dialog dialog = builderAddService.create();
        dialog.show();

        /*---find view by id---*/
        EditText FSA_editService_name = view.findViewById(R.id.FSA_editService_name);
        EditText FSA_editService_price = view.findViewById(R.id.FSA_editService_price);
        EditText FSA_editService_detail = view.findViewById(R.id.FSA_editService_detail);
        Button FSA_editService_edit = view.findViewById(R.id.FSA_editService_edit);
        Button FSA_editService_cancel = view.findViewById(R.id.FSA_editService_cancel);
        Button FSA_editService_delete = view.findViewById(R.id.FSA_editService_delete);

        /*---set data---*/
        FSA_editService_name.setText(services.getService_name());
        FSA_editService_price.setText(services.getService_price());
        FSA_editService_detail.setText(services.getService_detail());

        /*---event button edit---*/
        FSA_editService_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEditServices();
            }

            private void showDialogEditServices() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("EDIT SERVICE " + "\"" + services.getService_name() + "\"");
                builder.setMessage("Are you sure want to edit service " + "\"" + services.getService_name() + "\"" + " ?");

                /*---edit---*/
                builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        /*---check---*/
                        if(TextUtils.isEmpty(FSA_editService_name.getText().toString())) {
                            FSA_editService_name.requestFocus();
                            FSA_editService_name.setError("Please enter service's name !");
                        } else if(TextUtils.isEmpty(FSA_editService_price.getText().toString())) {
                            FSA_editService_price.requestFocus();
                            FSA_editService_price.setError("Please enter service's price !");
                        } else if(TextUtils.isEmpty(FSA_editService_detail.getText().toString())) {
                            FSA_editService_detail.requestFocus();
                            FSA_editService_detail.setError("Please choose service's detail !");
                        } else {

                            FSA_editService_name.setError(null);
                            FSA_editService_price.setError(null);
                            FSA_editService_detail.setError(null);

                            services.setService_name(FSA_editService_name.getText().toString());
                            services.setService_price(FSA_editService_price.getText().toString());
                            services.setService_detail(FSA_editService_detail.getText().toString());

                            if(ServicesDAO.updateServices(getActivity(), services)){
                                Toast.makeText(getActivity(), "UPDATE SERVICE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                                dataServices.clear();
                                dataServices.addAll(ServicesDAO.readServices(getActivity()));
                                adapterServicesAdmin.notifyDataSetChanged();
                                dialog.dismiss();
                            } else{
                                Toast.makeText(getActivity(), "UPDATE SERVICE FAILED !!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                /*---cancel---*/
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        /*---event button delete---*/
        FSA_editService_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDeleteServices();
            }

            private void showDialogDeleteServices() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("DELETE SERVICE " + "\"" + services.getService_name() + "\"");
                builder.setMessage("Are you sure want to delete service " + "\"" + services.getService_name() + "\"" + " ?");

                /*---delete---*/
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ServicesDAO.deleteServices(getActivity(), services.getService_id())){
                            Toast.makeText(getActivity(), "DELETE SERVICE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                            dataServices.clear();
                            dataServices.addAll(ServicesDAO.readServices(getActivity()));
                            adapterServicesAdmin.notifyDataSetChanged();
                            dialog.dismiss();
                        } else{
                            Toast.makeText(getActivity(), "DELETE SERVICE FAILED !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                /*---cancel---*/
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        /*---event button cancel---*/
        FSA_editService_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

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
                adapterServicesAdmin.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterServicesAdmin.getFilter().filter(newText);
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
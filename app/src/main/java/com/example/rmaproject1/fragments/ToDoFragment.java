package com.example.rmaproject1.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaproject1.R;
import com.example.rmaproject1.activities.TicketDetailsActivity;
import com.example.rmaproject1.model.Ticket;
import com.example.rmaproject1.model.User;
import com.example.rmaproject1.recycler.adapter.TicketAdapter;
import com.example.rmaproject1.recycler.differ.TicketDiffItemCallback;
import com.example.rmaproject1.viewmodels.SharedViewModel;

public class ToDoFragment extends Fragment {

    private RecyclerView recyclerView;
    private SharedViewModel sharedViewModel;
    private TicketAdapter ticketAdapter;
    private EditText searchTODOTickets;

    public ToDoFragment() {
        super(R.layout.fragment_todo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        initView(view);
        initObservers();
        initRecycler();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.listRvTodo);
        searchTODOTickets = view.findViewById(R.id.searchTODOTickets);
    }

    private void initObservers() {

        sharedViewModel.getTodoTickets().observe(getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });

        searchTODOTickets.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                sharedViewModel.searchToDoTickets(s.toString());
            }
        });
    }

    private final ActivityResultLauncher<Intent> ticketDetailsResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Ticket ticket = result.getData().getParcelableExtra("ticket");
                    sharedViewModel.updateTicket(ticket);
                    ticketAdapter.notifyDataSetChanged();
                }
            });

    private void initRecycler() {
        ticketAdapter = new TicketAdapter(sharedViewModel, new TicketDiffItemCallback(), ticket -> {
            Intent intent = new Intent(getActivity(), TicketDetailsActivity.class);
            intent.putExtra("ticket", ticket);
            //startActivity(intent);
            ticketDetailsResultLauncher.launch(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(ticketAdapter);
    }
}

package com.example.rmaproject1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmaproject1.R;
import com.example.rmaproject1.activities.AppActivity;
import com.example.rmaproject1.recycler.adapter.TicketAdapter;
import com.example.rmaproject1.recycler.differ.TicketDiffItemCallback;
import com.example.rmaproject1.viewmodels.SharedViewModel;

public class ToDoFragment extends Fragment {

    private RecyclerView recyclerView;
    private SharedViewModel sharedViewModel;
    private TicketAdapter ticketAdapter;

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
        recyclerView = view.findViewById(R.id.listRv);
    }

    private void initObservers() {
        sharedViewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });
    }

    private void initRecycler() {
        ticketAdapter = new TicketAdapter(new TicketDiffItemCallback(), ticket -> {
            Toast.makeText(getActivity(), ticket.getId() + "", Toast.LENGTH_SHORT).show();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(ticketAdapter);
    }
}

package com.example.rmaproject1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rmaproject1.R;
import com.example.rmaproject1.recycler.adapter.TicketAdapter;
import com.example.rmaproject1.recycler.differ.TicketDiffItemCallback;
import com.example.rmaproject1.viewmodels.SharedViewModel;

public class StatsFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private TicketAdapter ticketAdapter;
    private TextView toDoTotal;
    private TextView toDoEnhancements;
    private TextView toDoBugs;
    private TextView inProgressTotal;
    private TextView inProgressEnhancements;
    private TextView inProgressBugs;
    private TextView doneTotal;
    private TextView doneEnhancements;
    private TextView doneBugs;


    public StatsFragment() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        initObservers();
        initView(view);
        updateValues();

    }

    private void initView(View view){
        toDoTotal = view.findViewById(R.id.toDoTotal);
        toDoEnhancements = view.findViewById(R.id.todoTotalEnhancements);
        toDoBugs = view.findViewById(R.id.toDoBugs);
        inProgressTotal = view.findViewById(R.id.inProgressTotal);
        inProgressEnhancements = view.findViewById(R.id.inProgressEnhancements);
        inProgressBugs = view.findViewById(R.id.inProgressBugs);
        doneTotal = view.findViewById(R.id.doneTotal);
        doneEnhancements = view.findViewById(R.id.doneEnhancements);
        doneBugs = view.findViewById(R.id.doneBugs);
    }

    private void updateValues(){
        toDoTotal.setText(String.valueOf(sharedViewModel.getTodoTickets().getValue().size()));
        toDoEnhancements.setText(String.valueOf(sharedViewModel.getTodoTickets().getValue().stream().filter(ticket -> ticket.getTicketType().equalsIgnoreCase("Enhancement")).count()));
        toDoBugs.setText(String.valueOf(sharedViewModel.getTodoTickets().getValue().stream().filter(ticket -> ticket.getTicketType().equalsIgnoreCase("Bug")).count()));
        inProgressTotal.setText(String.valueOf(sharedViewModel.getInProgressTickets().getValue().size()));
        inProgressBugs.setText(String.valueOf(sharedViewModel.getInProgressTickets().getValue().stream().filter(ticket -> ticket.getTicketType().equalsIgnoreCase("Bug")).count()));
        inProgressEnhancements.setText(String.valueOf(sharedViewModel.getInProgressTickets().getValue().stream().filter(ticket -> ticket.getTicketType().equalsIgnoreCase("Enhancement")).count()));
        doneTotal.setText(String.valueOf(sharedViewModel.getDoneTickets().getValue().size()));
        doneBugs.setText(String.valueOf(sharedViewModel.getDoneTickets().getValue().stream().filter(ticket -> ticket.getTicketType().equalsIgnoreCase("Bug")).count()));
        doneEnhancements.setText(String.valueOf(sharedViewModel.getDoneTickets().getValue().stream().filter(ticket -> ticket.getTicketType().equalsIgnoreCase("Enhancement")).count()));

    }

    private void initObservers(){
        sharedViewModel.getInProgressTicketsFullList().observe(getViewLifecycleOwner(), tickets -> {
            updateValues();
        });

        sharedViewModel.getDoneTicketsFullList().observe(getViewLifecycleOwner(), tickets -> {
            updateValues();
        });

        sharedViewModel.getTodoTicketsFullList().observe(getViewLifecycleOwner(), tickets -> {
            updateValues();
        });
    }
}

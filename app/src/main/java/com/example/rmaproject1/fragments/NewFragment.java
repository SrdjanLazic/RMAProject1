package com.example.rmaproject1.fragments;

import static com.example.rmaproject1.viewmodels.SharedViewModel.ID_COUNTER;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rmaproject1.R;
import com.example.rmaproject1.model.Ticket;
import com.example.rmaproject1.viewmodels.SharedViewModel;

public class NewFragment extends Fragment {

    private Spinner prioritySpinner;
    private Spinner typeSpinner;
    private Button addTicket;
    private EditText estimatedTime;
    private EditText ticketName;
    private EditText ticketDescription;
    private SharedViewModel sharedViewModel;

    public NewFragment() {
        super(R.layout.fragment_addnewticket);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        init(view);
    }

    private void init(View view){
        initView(view);
        initListeners();
        //initObservers();
    }

    private void initView(View view){
        prioritySpinner = view.findViewById(R.id.spinnerPriority);
        typeSpinner = view.findViewById(R.id.spinnerType);
        addTicket = view.findViewById(R.id.addNewTicketButton);
        ticketName = view.findViewById(R.id.ticketTitle);
        ticketDescription = view.findViewById(R.id.ticketDescription);
        estimatedTime = view.findViewById(R.id.estimatedTime);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.priority, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ticket_type, android.R.layout.simple_spinner_item);

        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        prioritySpinner.setAdapter(priorityAdapter);
        typeSpinner.setAdapter(typeAdapter);

    }

    private void initListeners(){
        addTicket.setOnClickListener(v -> {
            if(checkFields()){
                Ticket ticket = new Ticket(ID_COUNTER++, ticketName.getText().toString(), ticketDescription.getText().toString(),
                        Integer.parseInt(estimatedTime.getText().toString()), prioritySpinner.getSelectedItem().toString(),  typeSpinner.getSelectedItem().toString());
                sharedViewModel.addTicket(ticket);
            } else {
                Toast.makeText(this.getActivity(), "You have to fill in all the fields first.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initObservers() {
//        sharedViewModel.getTickets().observe(this, cars -> {
//            carAdapter.submitList(cars);
//        });
    }

    private boolean checkFields(){
        return !ticketName.getText().toString().isEmpty() && !ticketDescription.getText().toString().isEmpty() && !estimatedTime.getText().toString().isEmpty();
    }
}

package com.example.rmaproject1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rmaproject1.R;
import com.example.rmaproject1.model.Ticket;

public class EditTicketActivity extends AppCompatActivity {

    private Ticket ticket;
    private EditText ticketName;
    private EditText ticketDescription;
    private EditText estimatedTime;
    private Spinner priority;
    private Spinner type;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);

        Intent intent = getIntent();
        if(intent != null){
            Bundle data = getIntent().getExtras();
            this.ticket = data.getParcelable("ticket");
        }

        initView();
    }

    // TODO Srediti view
    private void initView(){
        priority = findViewById(R.id.spinnerPriorityEdit);
        type = findViewById(R.id.spinnerTypeEdit);
        save = findViewById(R.id.saveTicketButtonEdit);
        ticketName = findViewById(R.id.ticketTitleEdit);
        ticketDescription = findViewById(R.id.ticketDescriptionEdit);
        estimatedTime = findViewById(R.id.estimatedTimeEdit);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.priority, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.ticket_type, android.R.layout.simple_spinner_item);

        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ticketName.setText(ticket.getTitle());
        ticketDescription.setText(ticket.getDescription());
        estimatedTime.setText(String.valueOf(ticket.getEstimatedTime()));
        priority.setAdapter(priorityAdapter);
        type.setAdapter(typeAdapter);
        priority.setSelection(priorityAdapter.getPosition(ticket.getPriority()));
        type.setSelection(typeAdapter.getPosition(ticket.getTicketType()));

        save.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            ticket.setTitle(ticketName.getText().toString());
            ticket.setDescription(ticketDescription.getText().toString());
            ticket.setPriority(priority.getSelectedItem().toString());
            ticket.setEstimatedTime(Integer.valueOf(estimatedTime.getText().toString()));
            ticket.setTicketType(type.getSelectedItem().toString());
            returnIntent.putExtra("ticket", ticket);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
    }


}

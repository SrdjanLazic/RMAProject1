package com.example.rmaproject1.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rmaproject1.R;
import com.example.rmaproject1.fragments.EditTicketFragment;
import com.example.rmaproject1.fragments.ToDoFragment;
import com.example.rmaproject1.model.Ticket;
import com.example.rmaproject1.model.TicketStatus;

public class TicketDetailsActivity extends AppCompatActivity {

    private Ticket ticket;
    private TextView ticketTitle;
    private ImageView ticketIcon;
    private TextView ticketPriority;
    private Button loggedTime;
    private TextView estimation;
    private TextView ticketDescription;
    private Button editTicket;
    private TextView ticketType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if(intent != null){
            Bundle data = getIntent().getExtras();
            this.ticket = data.getParcelable("ticket");
        }

        initView();
        setText();
        initListeners();
    }

    private void initView(){
        ticketTitle = findViewById(R.id.detailViewTitle);
        ticketIcon = findViewById(R.id.detailViewIcon);
        ticketPriority = findViewById(R.id.detailViewPriority);
        loggedTime = findViewById(R.id.loggedTimeButton);
        estimation = findViewById(R.id.detailViewEstimation);
        ticketDescription = findViewById(R.id.ticketDescription);
        editTicket = findViewById(R.id.buttonEditTicket);
        ticketType = findViewById(R.id.detailViewType);
    }

    private void setText(){

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String userType = sharedPreferences.getString("type", "");

        if (ticket.getTicketType().equalsIgnoreCase("Bug")) {
            ticketIcon.setImageResource(R.drawable.ic_baseline_bug_report_24);
        } else {
            ticketIcon.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24);
        }

        if(userType.equalsIgnoreCase("user")){
            editTicket.setVisibility(View.INVISIBLE);
        }

        ticketTitle.setText(ticket.getTitle());
        ticketPriority.setText(ticket.getPriority());
        loggedTime.setText(String.valueOf(ticket.getLoggedTime()));
        estimation.setText(String.valueOf(ticket.getEstimatedTime()));
        ticketDescription.setText(ticket.getDescription());
        ticketType.setText(ticket.getTicketType());
        ticketDescription.setMovementMethod(new ScrollingMovementMethod());

        paintTicket();
    }

    private final ActivityResultLauncher<Intent> ticketDetailsResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Ticket ticket = result.getData().getParcelableExtra("ticket");
                    this.ticket = ticket;
                    ticketTitle.setText(ticket.getTitle());
                    ticketDescription.setText(ticket.getDescription());
                    ticketPriority.setText(ticket.getPriority());
                    ticketType.setText(ticket.getTicketType());
                    estimation.setText(String.valueOf(ticket.getEstimatedTime()));
                    if(ticket.getTicketType().equals("Bug")) {
                        ticketType.setText(ticket.getTicketType());
                        ticketIcon.setImageResource(R.drawable.ic_baseline_bug_report_24);
                    } else {
                        ticketType.setText(ticket.getTicketType());
                        ticketIcon.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24);
                    }
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("ticket", ticket);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
            });

    private void initListeners(){
        Intent returnIntent = new Intent();
        loggedTime.setOnLongClickListener(view -> {
            if(ticket.getLoggedTime() > 0) {
                ticket.setLoggedTime(ticket.getLoggedTime() - 1);
                loggedTime.setText(String.valueOf(ticket.getLoggedTime()));
                returnIntent.putExtra("ticket", ticket);
                setResult(Activity.RESULT_OK, returnIntent);
            } else {
                Toast.makeText(this, "Cannot decrement below 0.", Toast.LENGTH_SHORT).show();
            }
            return true;
        });

        loggedTime.setOnClickListener(view -> {
            ticket.setLoggedTime(ticket.getLoggedTime() + 1);
            loggedTime.setText(String.valueOf(ticket.getLoggedTime()));
            returnIntent.putExtra("ticket", ticket);
            setResult(Activity.RESULT_OK, returnIntent);
        });

        editTicket.setOnClickListener(view -> {
            //Toast.makeText(getActivity(), ticket.getId() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EditTicketActivity.class);
            intent.putExtra("ticket", ticket);
            //startActivity(intent);
            ticketDetailsResultLauncher.launch(intent);
        });

    }

    private void paintTicket(){
        if (ticket.getStatus() == TicketStatus.TODO)
            ticketIcon.setColorFilter(Color.parseColor("#b71c1c"));
        else if(ticket.getStatus() == TicketStatus.IN_PROGRESS){
            ticketIcon.setColorFilter(Color.parseColor("#e65100"));
        } else
            ticketIcon.setColorFilter(Color.parseColor("#1b5e20"));
    }
}
package com.example.rmaproject1.recycler.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rmaproject1.R;
import com.example.rmaproject1.model.Ticket;
import com.example.rmaproject1.model.TicketStatus;
import com.example.rmaproject1.viewmodels.SharedViewModel;

import java.util.function.Consumer;


public class TicketAdapter extends ListAdapter<Ticket, TicketAdapter.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;
    public static SharedViewModel sharedViewModel;


    public TicketAdapter(SharedViewModel sharedViewModel, @NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
        TicketAdapter.sharedViewModel = sharedViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Ticket ticket = getItem(position);
            onTicketClicked.accept(ticket);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }

    // unutrasnja klasa
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public ViewHolder(@NonNull View view, Context context, Consumer<Integer> onItemClicked) {
            super(view);
            this.context = context;
            view.setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket) {
            SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences(itemView.getContext().getPackageName(), Context.MODE_PRIVATE);
            ImageView ticketIcon = itemView.findViewById(R.id.ticketIconListItem);

            if (ticket.getTicketType().equalsIgnoreCase("enhancement")) {
                ticketIcon.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24);
                setIconColor(ticketIcon, ticket);
            } else {
                ticketIcon.setImageResource(R.drawable.ic_baseline_bug_report_24);
                setIconColor(ticketIcon, ticket);
            }

            ((TextView) itemView.findViewById(R.id.ticketTitleListItem)).setText(ticket.getTitle());
            ((TextView) itemView.findViewById(R.id.ticketDescriptionListItem)).setText(ticket.getDescription());

            ImageView firstButton = itemView.findViewById(R.id.ticketListItemFirstButton);
            ImageView secondButton = itemView.findViewById(R.id.ticketListItemSecondButton);

            // Provera da li je trenutni korisnik admin ili user:
            if (sharedPreferences.getString("type", "").equalsIgnoreCase("admin")) {
                if (ticket.getStatus() == TicketStatus.TODO) {
                    firstButton.setImageResource(R.drawable.ic_baseline_chevron_right_24);
                    secondButton.setImageResource(R.drawable.ic_baseline_delete_24);

                    firstButton.setOnClickListener(v -> {
                        TicketAdapter.sharedViewModel.moveTicket(ticket, ticket.getStatus(), TicketStatus.IN_PROGRESS);
                    });

                    secondButton.setOnClickListener(v -> {
                        TicketAdapter.sharedViewModel.removeTicket(ticket, ticket.getStatus());
                    });

                } else if (ticket.getStatus() == TicketStatus.IN_PROGRESS) {
                    firstButton.setImageResource(R.drawable.ic_baseline_chevron_right_24);
                    secondButton.setImageResource(R.drawable.ic_baseline_chevron_left_24);

                    firstButton.setOnClickListener(v -> {
                        TicketAdapter.sharedViewModel.moveTicket(ticket, ticket.getStatus(), TicketStatus.DONE);
                    });

                    secondButton.setOnClickListener(v -> {
                        TicketAdapter.sharedViewModel.moveTicket(ticket, ticket.getStatus(), TicketStatus.TODO);
                    });
                } else {
                    firstButton.setVisibility(View.INVISIBLE);
                    secondButton.setVisibility(View.INVISIBLE);
                }

            } else if (sharedPreferences.getString("type", "").equalsIgnoreCase("user")) {
                if (ticket.getStatus() == TicketStatus.TODO) {
                    firstButton.setImageResource(R.drawable.ic_baseline_chevron_right_24);
                    secondButton.setVisibility(View.INVISIBLE);

                    firstButton.setOnClickListener(v -> {
                        TicketAdapter.sharedViewModel.moveTicket(ticket, ticket.getStatus(), TicketStatus.IN_PROGRESS);
                    });
                } else if (ticket.getStatus() == TicketStatus.IN_PROGRESS) {
                    firstButton.setImageResource(R.drawable.ic_baseline_chevron_right_24);
                    secondButton.setImageResource(R.drawable.ic_baseline_chevron_left_24);

                    firstButton.setOnClickListener(v -> {
                        TicketAdapter.sharedViewModel.moveTicket(ticket, ticket.getStatus(), TicketStatus.DONE);
                    });

                    secondButton.setOnClickListener(v -> {
                        TicketAdapter.sharedViewModel.moveTicket(ticket, ticket.getStatus(), TicketStatus.TODO);
                    });
                } else {
                    firstButton.setVisibility(View.INVISIBLE);
                    secondButton.setVisibility(View.INVISIBLE);
                }
            }
        }

        private void setIconColor(ImageView ticketIcon, Ticket ticket) {
            if (ticket.getStatus() == TicketStatus.TODO)
                ticketIcon.setColorFilter(Color.parseColor("#b71c1c"));
            else if(ticket.getStatus() == TicketStatus.IN_PROGRESS){
                ticketIcon.setColorFilter(Color.parseColor("#e65100"));
            } else
                ticketIcon.setColorFilter(Color.parseColor("#2C8D33"));
        }
    }
}

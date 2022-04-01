package com.example.rmaproject1.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rmaproject1.R;
import com.example.rmaproject1.model.Ticket;

import java.util.function.Consumer;


public class TicketAdapter extends ListAdapter<Ticket, TicketAdapter.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;

    public TicketAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
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

        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket) {
//            ImageView imageView = itemView.findViewById(R.id.carPictureIv);
//            Glide
//                .with(context)
//                .load(car.getPicture())
//                .circleCrop()
//                .into(imageView);

            ((TextView) itemView.findViewById(R.id.ticketTitleListItem)).setText(ticket.getTitle());
            ((TextView) itemView.findViewById(R.id.ticketDescriptionListItem)).setText(ticket.getDescription());
        }

    }
}

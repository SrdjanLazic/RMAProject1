package com.example.rmaproject1.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.example.rmaproject1.model.Ticket;

// Proverava da li se promenio neki item u RecyclerView-u

public class TicketDiffItemCallback extends DiffUtil.ItemCallback<Ticket> {
    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getTitle().equals(newItem.getTitle())
                && oldItem.getDescription().equals(newItem.getDescription())
                && oldItem.getEstimatedTime().equals(newItem.getEstimatedTime())
                && oldItem.getPriority().equals(newItem.getPriority())
                && oldItem.getLoggedTime().equals(newItem.getLoggedTime())
                && oldItem.getTicketType().equals(newItem.getTicketType());
    }
}

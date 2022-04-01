package com.example.rmaproject1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rmaproject1.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SharedViewModel extends ViewModel {

    public static int ID_COUNTER = 0;

    private final MutableLiveData<List<Ticket>> ticketsLiveData = new MutableLiveData<>();
    private ArrayList<Ticket> ticketsTempList = new ArrayList<>();

    public SharedViewModel() {
        for (int i = 0; i < 10; i++){
            Ticket ticket = new Ticket(ID_COUNTER++,"title" + i, "description" + i, 2, "High", "Bug");
            ticketsTempList.add(ticket);
        }

        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTempList);
        ticketsLiveData.setValue(listToSubmit);
    }

    public LiveData<List<Ticket>> getTickets() {
        return ticketsLiveData;
    }

    public int addTicket(Ticket ticket) {
        ticketsTempList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTempList);
        ticketsLiveData.setValue(listToSubmit);
        return ticket.getId();
    }

    public void removeTicket(int id) {
        Optional<Ticket> ticketObject = ticketsTempList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            ticketsTempList.remove(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTempList);
            ticketsLiveData.setValue(listToSubmit);
        }
    }

}

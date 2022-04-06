package com.example.rmaproject1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rmaproject1.model.Ticket;
import com.example.rmaproject1.model.TicketStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class SharedViewModel extends ViewModel {

    public static int ID_COUNTER = 0;

    private final MutableLiveData<List<Ticket>> doneTickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> todoTickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgressTickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> doneTicketsFullList = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgressTicketsFullList = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> todoTicketsFullList = new MutableLiveData<>();
    private List<Ticket> doneTicketsTemp = new ArrayList<>();
    private List<Ticket> todoTicketsTemp = new ArrayList<>();
    private List<Ticket> inProgressTicketsTemp = new ArrayList<>();
    private String[] types = {"Bug", "Enhancement"};
    private String[] priorities = {"Highest", "High", "Medium", "Low", "Lowest"};
    private TicketStatus[] ticketStatus = TicketStatus.values();


    public SharedViewModel() {
        Random random = new Random();
        for (int i = 0; i < 30; i++){
            String priority = priorities[random.nextInt(priorities.length)];
            String type = types[random.nextInt(types.length)];
            TicketStatus status = ticketStatus[random.nextInt(ticketStatus.length)];
            Ticket ticket = new Ticket(ID_COUNTER++,type + i, "description" + i, 2, priority, type, status);
            System.out.println(ID_COUNTER);

            if(status == TicketStatus.DONE)
                doneTicketsTemp.add(ticket);
            else if (status == TicketStatus.TODO)
                todoTicketsTemp.add(ticket);
            else
                inProgressTicketsTemp.add(ticket);
        }

        ArrayList<Ticket> doneMediatorList = new ArrayList<>(doneTicketsTemp);
        ArrayList<Ticket> todoMediatorList = new ArrayList<>(todoTicketsTemp);
        ArrayList<Ticket> inProgressMediatorList = new ArrayList<>(inProgressTicketsTemp);
        doneTickets.setValue(doneMediatorList);
        doneTicketsFullList.setValue(doneMediatorList);
        todoTickets.setValue(todoMediatorList);
        todoTicketsFullList.setValue(todoMediatorList);
        inProgressTickets.setValue(inProgressMediatorList);
        inProgressTicketsFullList.setValue(inProgressMediatorList);

    }

    public void searchDoneTickets(String searchTerm) {
        List<Ticket> filteredList = doneTicketsTemp.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(searchTerm.toLowerCase())
                                    || ticket.getDescription().toLowerCase().startsWith(searchTerm.toLowerCase())).collect(Collectors.toList());
        doneTickets.setValue(filteredList);
    }

    public void searchToDoTickets(String filter) {
        List<Ticket> filteredList = todoTicketsTemp.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())
                || ticket.getDescription().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        todoTickets.setValue(filteredList);
    }

    public void searchInProgressTickets(String filter) {
        List<Ticket> filteredList = inProgressTicketsTemp.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())
                || ticket.getDescription().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        inProgressTickets.setValue(filteredList);
    }

    public void moveTicket(Ticket ticket, TicketStatus source, TicketStatus destination){
        ticket.setStatus(destination);
        if(destination == TicketStatus.TODO){
            todoTicketsTemp.add(ticket);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(todoTicketsTemp);
            todoTickets.setValue(listToSubmit);
            todoTicketsFullList.setValue(listToSubmit);
            removeTicket(ticket, source);
        } else if(destination == TicketStatus.IN_PROGRESS){
            inProgressTicketsTemp.add(ticket);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgressTicketsTemp);
            inProgressTickets.setValue(listToSubmit);
            inProgressTicketsFullList.setValue(listToSubmit);
            removeTicket(ticket, source);
        } else {
            doneTicketsTemp.add(ticket);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(doneTicketsTemp);
            doneTickets.setValue(listToSubmit);
            doneTicketsFullList.setValue(listToSubmit);
            removeTicket(ticket, source);
        }
    }

    public void updateTicket(Ticket ticket){
        System.out.println(ticket);
        if(ticket.getStatus() == TicketStatus.TODO){
            Ticket toEdit = todoTicketsTemp.stream().filter(oldTicket -> oldTicket.getId().equals(ticket.getId())).findFirst().orElse(null);
            if(toEdit != null) {
                toEdit.setLoggedTime(ticket.getLoggedTime());
                toEdit.setTitle(ticket.getTitle());
                toEdit.setDescription(ticket.getDescription());
                toEdit.setEstimatedTime(ticket.getEstimatedTime());
                toEdit.setTicketType(ticket.getTicketType());
                toEdit.setPriority(ticket.getPriority());
            }
            ArrayList<Ticket> listToSubmit = new ArrayList<>(todoTicketsTemp);
            todoTickets.setValue(listToSubmit);
            todoTicketsFullList.setValue(listToSubmit);
        } else if(ticket.getStatus() == TicketStatus.IN_PROGRESS){
            Ticket toEdit = inProgressTicketsTemp.stream().filter(oldTicket -> oldTicket.getId().equals(ticket.getId())).findFirst().orElse(null);
            if(toEdit != null) {
                toEdit.setLoggedTime(ticket.getLoggedTime());
                toEdit.setTitle(ticket.getTitle());
                toEdit.setDescription(ticket.getDescription());
                toEdit.setEstimatedTime(ticket.getEstimatedTime());
                toEdit.setTicketType(ticket.getTicketType());
                toEdit.setPriority(ticket.getPriority());
            }
            ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgressTicketsTemp);
            inProgressTickets.setValue(listToSubmit);
            inProgressTicketsFullList.setValue(listToSubmit);
        } else {
            Ticket toEdit = doneTicketsTemp.stream().filter(oldTicket -> oldTicket.getId().equals(ticket.getId())).findFirst().orElse(null);
            if(toEdit != null) {
                toEdit.setLoggedTime(ticket.getLoggedTime());
                toEdit.setTitle(ticket.getTitle());
                toEdit.setDescription(ticket.getDescription());
                toEdit.setEstimatedTime(ticket.getEstimatedTime());
                toEdit.setTicketType(ticket.getTicketType());
                toEdit.setPriority(ticket.getPriority());
            }
            ArrayList<Ticket> listToSubmit = new ArrayList<>(doneTicketsTemp);
            doneTickets.setValue(listToSubmit);
            doneTicketsFullList.setValue(listToSubmit);
        }
    }

    public LiveData<List<Ticket>> getDoneTickets() {
        return doneTickets;
    }

    public LiveData<List<Ticket>> getTodoTickets() {
        return todoTickets;
    }

    public LiveData<List<Ticket>> getInProgressTickets() {
        return inProgressTickets;
    }


    public LiveData<List<Ticket>> getInProgressTicketsFullList() {
        return inProgressTicketsFullList;
    }

    public LiveData<List<Ticket>> getDoneTicketsFullList() {
        return doneTicketsFullList;
    }

    public LiveData<List<Ticket>> getTodoTicketsFullList() {
        return todoTicketsFullList;
    }




    public int addTicket(Ticket ticket) {
        todoTicketsTemp.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(todoTicketsTemp);
        todoTickets.setValue(listToSubmit);
        todoTicketsFullList.setValue(listToSubmit);
        return ticket.getId();
    }

    public void removeTicket(Ticket toRemove, TicketStatus oldStatus) {
        if(oldStatus == TicketStatus.TODO) {
            todoTicketsTemp.remove(toRemove);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(todoTicketsTemp);
            todoTickets.setValue(listToSubmit);
            todoTicketsFullList.setValue(listToSubmit);
        } else if(oldStatus == TicketStatus.IN_PROGRESS){
            inProgressTicketsTemp.remove(toRemove);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgressTicketsTemp);
            inProgressTickets.setValue(listToSubmit);
            inProgressTicketsFullList.setValue(listToSubmit);
        } else {
            doneTicketsTemp.remove(toRemove);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(doneTicketsTemp);
            doneTickets.setValue(listToSubmit);
            doneTicketsFullList.setValue(listToSubmit);
        }
    }

}

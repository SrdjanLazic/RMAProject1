package com.example.rmaproject1.model;

public class Ticket {

    private Integer id;
    private String title;
    private String description;
    private Integer estimatedTime;
    private String priority;
    private String ticketType;
    private Integer loggedTime;
    private String status;

    public Ticket(Integer id, String title, String description, Integer estimatedTime, String priority, String ticketType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.ticketType = ticketType;
        this.loggedTime = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(Integer loggedTime) {
        this.loggedTime = loggedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", priority='" + priority + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", loggedTime=" + loggedTime +
                '}';
    }
}

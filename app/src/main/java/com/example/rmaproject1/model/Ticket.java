package com.example.rmaproject1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ticket implements Parcelable {

    private Integer id;
    private String title;
    private String description;
    private Integer estimatedTime;
    private String priority;
    private String ticketType;
    private Integer loggedTime;
    private TicketStatus status;

    public Ticket(Integer id, String title, String description, Integer estimatedTime, String priority, String ticketType, TicketStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.ticketType = ticketType;
        this.loggedTime = 0;
        this.status = status;
    }

    protected Ticket(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            estimatedTime = null;
        } else {
            estimatedTime = in.readInt();
        }
        priority = in.readString();
        ticketType = in.readString();
        if (in.readByte() == 0) {
            loggedTime = null;
        } else {
            loggedTime = in.readInt();
        }
        status = TicketStatus.values()[in.readInt()];
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

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

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
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
                ", status=" + status +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(title);
        parcel.writeString(description);
        if (estimatedTime == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(estimatedTime);
        }
        parcel.writeString(priority);
        parcel.writeString(ticketType);
        if (loggedTime == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(loggedTime);
        }
        parcel.writeInt(status.ordinal());
    }

    public static void updateTicket(Ticket original, Ticket update){

    }
}

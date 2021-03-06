package com.umbala.cuongbv.todo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * lớp Task này sử dụng Builder Pattern, một trong số các Design Pattern của GoF.
 * nếu chưa biết gì về pattern này, có thể tiếp cận qua cuốn sách
 * Headfirst Design Pattern của nhà xuất bản O'Reilly
 *
 */
public class Task implements Comparable<Task>,Parcelable {

    private String taskName;
    private String taskContent;
    private int taskPriority;
    private double taskEstimateTime;
    private long taskReminder;
    private int taskDoneState;
    private String taskID;

    private Task(Builder builder) {
        this.taskName = builder.taskName;
        this.taskContent = builder.taskContent;
        this.taskPriority = builder.taskPriority;
        this.taskEstimateTime = builder.taskEstimateTime;
        this.taskReminder = builder.taskReminder;
        this.taskDoneState = builder.taskDoneState;
        this.taskID = UUID.randomUUID().toString();
    }

    protected Task(Parcel in) {
        taskName = in.readString();
        taskContent = in.readString();
        taskPriority = in.readInt();
        taskEstimateTime = in.readDouble();
        taskReminder = in.readLong();
        taskDoneState = in.readInt();
        taskID = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    /**
     * Lóp task có implement interface {@link Comparable}.
     * interface này được viết ra để nhằm mục đích giúp một đối tượng có thể so sánh được
     * với một đối tượng khác, mục đích của việc này để sắp xếp một danh sách các đối tượng
     * dựa trên thuộc tính nào đó của đối tượng bằng thuật toán Merged sort build-in cùng với
     * {@link java.util.Collections} framework của Java.
     *
     * @param task
     * @return
     */
    @Override
    public int compareTo(@NonNull Task task) {
        return Integer.compare(this.taskDoneState, task.taskDoneState);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(taskName);
        parcel.writeString(taskContent);
        parcel.writeInt(taskPriority);
        parcel.writeDouble(taskEstimateTime);
        parcel.writeLong(taskReminder);
        parcel.writeInt(taskDoneState);
        parcel.writeString(taskID);
    }

    public static class Builder {

        private String taskName = "";
        private String taskContent = "";
        private int taskPriority = 3;
        private double taskEstimateTime = 0;
        private long taskReminder = 0; // ms
        private int taskDoneState = 0;

        public Builder setTaskName(String name) {
            taskName = name;
            return this;
        }

        public Builder setTaskContent(String content) {
            taskContent = content;
            return this;
        }

        public Builder setTaskPriority(int priority) {
            taskPriority = priority;
            return this;
        }

        public Builder setTaskEstimateTime (double estimateTime) {
            taskEstimateTime = estimateTime;
            return this;
        }

        public Builder setTaskReminder(long reminder) {
            taskReminder = reminder;
            return this;
        }

        public Builder setTaskDoneState(int doneState) {
            taskDoneState = doneState;
            return this;
        }

        public Task builder(){
            return new Task(this);
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public double getTaskEstimateTime() {
        return taskEstimateTime;
    }

    public long getTaskReminder() {
        return taskReminder;
    }

    public int getTaskDoneState() {
        return taskDoneState;
    }

    public void setTaskDoneState(int taskDoneState) {
        this.taskDoneState = taskDoneState;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getEstimateTime() {
        return taskEstimateTime/(60 * 60 * 1000) + "";
    }

    public String getRemindDate() {
       return getStringPattern("dd/MM/yyyy");
    }

    public String getRemindTime() {
        return getStringPattern("HH/mm/ss");
    }

    private String getStringPattern(String string) {
        Date date = new Date(taskReminder);
        DateFormat dateFormat = new SimpleDateFormat(string, Locale.US);
        return dateFormat.format(date);
    }
}

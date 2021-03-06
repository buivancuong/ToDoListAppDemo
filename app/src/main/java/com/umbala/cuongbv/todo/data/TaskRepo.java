package com.umbala.cuongbv.todo.data;

import android.util.Log;

import com.umbala.cuongbv.todo.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * lớp này tạo ra giữ liệu giả để test các khối khác khi chưa triển khai được Database hoặc API
 * lớp này cũng là một ví dụ cụ thể cho việc liên kết lỏng lẻo giữa tầng data
 * và tầng ui (tham khảo thêm {@link TaskRepository})
 *
 */
public class TaskRepo implements TaskRepository {

    private static TaskRepo instance;

    ArrayList<Task> tasks;

    public static TaskRepo getInstance(){ // Singleton Pattern
        if (instance == null){
            instance = new TaskRepo();
        }
        return instance;
    }

    private TaskRepo() {
        tasks = new ArrayList<>();
        tasks.add(new Task.Builder().setTaskName("Task1").setTaskContent("This is Task 1").builder());
        tasks.add(new Task.Builder().setTaskName("Task2").setTaskContent("This is Task 2").builder());
        tasks.add(new Task.Builder().setTaskName("Task3").setTaskContent("This is Task 3").builder());
        tasks.add(new Task.Builder().setTaskName("Task4").setTaskContent("This is Task 4").builder());
        tasks.add(new Task.Builder().setTaskName("Task5").setTaskContent("This is Task 5").builder());
        tasks.add(new Task.Builder().setTaskName("Task6").setTaskContent("This is Task 6").builder());
    }

    @Override
    public List<Task> getAllTask() {
        Collections.sort(tasks);
        return tasks;
    }

    @Override
    public Task getTask(String id) {
        return tasks.get(Integer.parseInt(id));
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        Log.i("Task", tasks.size() + "");
    }

    @Override
    public void delTask(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskID().equals(id)) {
                tasks.remove(i);
                break;
            }
        }
    }

    @Override
    public void updateTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskID().equals(task.getTaskID())) {
                tasks.set(i, task);
                Log.i("Test", task.getTaskID() + " updated");
            }
        }
    }

    @Override
    public List<Task> updateTaskList(List<Task> tasks, Task task) {
        return null;
    }

    @Override
    public List<Task> getSortTaskList() {
        return null;
    }
}

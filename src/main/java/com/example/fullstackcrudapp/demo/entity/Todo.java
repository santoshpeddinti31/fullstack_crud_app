package com.example.fullstackcrudapp.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private  int id;
    @Column(name = "task_name")
    private  String taskName;

    @Column(name = "task_desc")
    private String taskDesc;

    public Todo()
    {

    }

    public Todo(String taskName,String taskDesc)
    {
        this.taskName=taskName;
        this.taskDesc=taskDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                '}';
    }
}

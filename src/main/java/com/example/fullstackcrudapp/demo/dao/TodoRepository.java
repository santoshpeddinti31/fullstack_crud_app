package com.example.fullstackcrudapp.demo.dao;

import com.example.fullstackcrudapp.demo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
}

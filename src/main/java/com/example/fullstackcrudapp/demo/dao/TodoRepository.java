package com.example.fullstackcrudapp.demo.dao;

import com.example.fullstackcrudapp.demo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
}

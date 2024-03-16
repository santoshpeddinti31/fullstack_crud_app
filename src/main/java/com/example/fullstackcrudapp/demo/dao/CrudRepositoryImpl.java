package com.example.fullstackcrudapp.demo.dao;

import com.example.fullstackcrudapp.demo.entity.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CrudRepositoryImpl implements CrudRepository {

    private EntityManager entityManager;

    @Autowired
    public CrudRepositoryImpl(EntityManager theEntityManager)
    {
        this.entityManager=theEntityManager;
    }

    @Override
    public List<Todo> findAll() {

        //create query
        TypedQuery<Todo> todoAll=entityManager.createQuery("from Todo",Todo.class);

        //execute query and get result list
        List<Todo> todosList=todoAll.getResultList();

        //return the results
        return todosList;
    }

    @Override
    public Todo findById(int theId) {
         Todo todoQuery= entityManager.find(Todo.class,theId);
         return todoQuery;
    }

    @Override
    public Todo save(Todo theTodo) {
        Todo dbTodo=entityManager.merge(theTodo);
        return dbTodo;
    }

    @Override
    public void deleteById(int theId) {
      Todo deleteTodo =  entityManager.find(Todo.class,theId);
      entityManager.remove(deleteTodo);
    }
}

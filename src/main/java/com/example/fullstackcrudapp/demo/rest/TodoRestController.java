package com.example.fullstackcrudapp.demo.rest;


import com.example.fullstackcrudapp.demo.entity.Todo;
import com.example.fullstackcrudapp.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoRestController {

    private TodoService todoService;


    @Autowired
    public TodoRestController(TodoService theTodoService)
    {
        this.todoService=theTodoService;
    }

    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello springboot ";
    }

    @GetMapping("/todos")
    public List<Todo> findAll()
    {
        return todoService.findAll();
    }

    @GetMapping("/todos/{id}")
    public Todo findById(@PathVariable int id)
    {
        Todo theTodo=todoService.findById(id);

        if(theTodo == null)
        {
            throw  new RuntimeException("Employee id not found - "+id);
        }
        return theTodo;
    }

    @PostMapping("/todos")
    public Todo save(@RequestBody Todo theTodo)
    {
        return todoService.save(theTodo);
    }

    @PutMapping("/todos")
    public Todo update(@RequestBody Todo theTodo)
    {
        return todoService.save(theTodo);
    }

    @DeleteMapping("/todos/{id}")
    public String deleteById(@PathVariable int id)
    {
       Todo deleteTodo= todoService.findById(id);

       if(deleteTodo == null){
           throw new RuntimeException("Employee id not found - "+id);
       }

       todoService.deleteById(id);
        return "Deleted employee id -: "+id;
    }
}

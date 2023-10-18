package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    @Autowired
    List<Tod> todoList;

    @PostMapping("addtodo")
    public String addTodo(@RequestBody Tod e){
        todoList.add(e);
        return "Added And Currently We have "+todoList.size()+" Todos";
    }
@GetMapping("getTodos")
    public List<Tod> getTodoList() {
        return todoList;
    }
    //update todo send id and update status...
    @PutMapping("getStatus/{id}")
    public String updateStatus(@PathVariable Integer id, @RequestParam Boolean flag){
        for (Tod todo:todoList) {
            if(todo.getTodoId().equals(id)) {
                todo.setTodoStatus(flag);
                return "Status Updated!";
            }
        }
        return "Invalid Data";
    }
    @DeleteMapping("todo/{id}")
    public String deleteTodo(@PathVariable Integer id){
        for (Tod todo:todoList) {
            if(id.equals(todo.getTodoId())){
                todoList.remove(todo);
                return "Todo Deleted";
            }
        }
        return "Invalid url";
    }
    // add multiple todo at a time

    @PostMapping("todos")
    public String addTodo(@RequestBody List<Tod> mylist){
//        for(Tod todo:mylist){
//            todoList.add(todo);
//            return "Added And Currently We have "+todoList.size()+" Todos";
//        }
        todoList.addAll(mylist);
        return mylist.size()+" Were Added in The List";
    }

    @GetMapping("undonetodos")
    public List<Tod> getUnDone(){

        List<Tod> mylist=new ArrayList<>();
        for(Tod todo:todoList){
            if (!todo.getTodoStatus()){
                mylist.add(todo);
            }
        }
        return mylist;
//        return todoList.stream().filter(todo ->!todo.getTodoStatus()).collect(Collectors.toList()) ;
    }
    //delete todos by id
    @DeleteMapping("todos/id")
    public List<Tod> deleteTodos(@RequestBody List<Integer> user){
        int count=0;
        for(Integer eleId:user) {
            for (int i = 0; i < todoList.size(); i++) {
                if (eleId.equals(todoList.get(i).getTodoId())) {
                    todoList.remove(todoList.get(i));
                    count++;
                    break;
                }
            }
        }
        System.out.println(count+" Were Deleted !!");
        return todoList;
    }

}

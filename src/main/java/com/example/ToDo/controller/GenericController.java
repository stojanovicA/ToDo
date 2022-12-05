package com.example.ToDo.controller;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public abstract class GenericController<D, I, S extends CrudService>{

    @Autowired
    private S s;


    @GetMapping
    public ResponseEntity<List<D>> findAll(){
        List<D> data = s.findAll();
        return new ResponseEntity<List<D>>(data, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<D> findOne(I id){

       return new ResponseEntity<D>((D) s.findById(id),HttpStatus.OK);


    }

    @PostMapping
    public ResponseEntity<D> save(D d){
        return new ResponseEntity<D>((D) s.add(d),HttpStatus.OK);
    }

    @DeleteMapping
    public void delete(I id){
        s.deleteById(id);
    }



    //DOMACI
    // generalizuj metode za dodavanje, brisanje, prikaz jednog


}

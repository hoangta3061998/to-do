package com.codegym.todo.controller;


import com.codegym.todo.model.Work;
import com.codegym.todo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WorkRestController {
    @Autowired
    private WorkService workService;

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createWork(@RequestBody Work work) {
        try {
            this.workService.save(work);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Work>> getListWorks(Pageable pageable) {
        Page<Work> listWorks = this.workService.findAll(pageable);
        if (listWorks.getTotalElements() != 0) {
            return new ResponseEntity<Page<Work>>(listWorks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteWork(@RequestParam("id") Long id) {
        Optional<Work> work = this.workService.findById(id);
        if (work.isPresent()) {
            this.workService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<Void> updateWork(@RequestBody Work work, @RequestParam("id") Long id) {
        boolean result = this.workService.setFields(work, id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/startingDateAsc")
    public ResponseEntity<List<Work>> startingDateAsc() {
        List<Work> workList = this.workService.findByOrderByStartingDateAsc();
        if(workList.size() != 0) {
            return new ResponseEntity<>(workList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/startingDateDesc")
    public ResponseEntity<List<Work>> sartingDateDesc() {
        List<Work> workList = this.workService.findByOrderByStartingDateDesc();
        if(workList.size() != 0) {
            return new ResponseEntity<>(workList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/endingDateAsc")
    public ResponseEntity<List<Work>> endingDateAsc() {
        List<Work> workList = this.workService.findByOrderByEndingDateAsc();
        if(workList.size() != 0) {
            return new ResponseEntity<>(workList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/endingDateDesc")
    public ResponseEntity<List<Work>> endingDateDesc() {
        List<Work> workList = this.workService.findByOrderByEndingDateDesc();
        if(workList.size() != 0) {
            return new ResponseEntity<>(workList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/nameAsc")
    public ResponseEntity<List<Work>> nameAsc() {
        List<Work> workList = this.workService.findByOrderByNameAsc();
        if(workList.size() != 0) {
            return new ResponseEntity<>(workList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/nameDesc")
    public ResponseEntity<List<Work>> nameDesc() {
        List<Work> workList = this.workService.findByOrderByNameDesc();
        if(workList.size() != 0) {
            return new ResponseEntity<>(workList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

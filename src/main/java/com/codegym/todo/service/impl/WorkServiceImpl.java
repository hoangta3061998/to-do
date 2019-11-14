package com.codegym.todo.service.impl;

import com.codegym.todo.model.Work;
import com.codegym.todo.repository.WorkRepository;
import com.codegym.todo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkRepository workRepository;

    @Override
    public Optional<Work> findById(Long id) {
        return workRepository.findById(id);
    }

    @Override
    public Page<Work> findAll(Pageable pageable) {
        return workRepository.findAll(pageable);
    }

    @Override
    public List<Work> findByOrderByNameAsc() {
        return workRepository.findByOrderByNameAsc();
    }

    @Override
    public List<Work> findByOrderByStartingDateAsc() {
        return workRepository.findByOrderByStartingDateAsc();
    }

    @Override
    public List<Work> findByOrderByEndingDateAsc() {
        return workRepository.findByOrderByEndingDateAsc();
    }

    @Override
    public List<Work> findByOrderByNameDesc() {
        return workRepository.findByOrderByNameDesc();
    }

    @Override
    public List<Work> findByOrderByStartingDateDesc() {
        return workRepository.findByOrderByStartingDateDesc();
    }

    @Override
    public List<Work> findByOrderByEndingDateDesc() {
        return workRepository.findByOrderByEndingDateDesc();
    }

    @Override
    public void save(Work work) {
        workRepository.save(work);
    }

    @Override
    public void deleteById(Long id) {
        workRepository.deleteById(id);
    }

    @Override
    public boolean setFields(Work work, Long id) {
       Optional<Work>  oldInfo = this.findById(id);
       Work newInfo = work;
       if(oldInfo.isPresent()) {
           oldInfo.get().setName(newInfo.getName());
           oldInfo.get().setStartingDate(newInfo.getStartingDate());
           oldInfo.get().setEndingDate(newInfo.getEndingDate());
           oldInfo.get().setStatus(newInfo.getStatus());
           this.save(oldInfo.get());
           return true;
       }
        return false;
    }
}

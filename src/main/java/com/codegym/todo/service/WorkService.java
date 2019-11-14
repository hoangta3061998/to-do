package com.codegym.todo.service;

import com.codegym.todo.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WorkService {
    Optional<Work> findById(Long id);
    Page<Work> findAll(Pageable pageable);
    List<Work> findByOrderByNameAsc();
    List<Work> findByOrderByStartingDateAsc();
    List<Work> findByOrderByEndingDateAsc();
    List<Work> findByOrderByNameDesc();
    List<Work> findByOrderByStartingDateDesc();
    List<Work> findByOrderByEndingDateDesc();
    void save(Work work);
    void deleteById(Long id);
    boolean setFields(Work work, Long id);
}

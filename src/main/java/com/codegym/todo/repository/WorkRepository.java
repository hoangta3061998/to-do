package com.codegym.todo.repository;

import com.codegym.todo.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends PagingAndSortingRepository<Work, Long> {
    Optional<Work> findById(Long id);
    Page<Work> findAll(Pageable pageable);
    List<Work> findByOrderByNameAsc();
    List<Work> findByOrderByStartingDateAsc();
    List<Work> findByOrderByEndingDateAsc();
    List<Work> findByOrderByNameDesc();
    List<Work> findByOrderByStartingDateDesc();
    List<Work> findByOrderByEndingDateDesc();

}

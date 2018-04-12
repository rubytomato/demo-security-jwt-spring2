package com.example.demo.service;

import com.example.demo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemoService {
    Optional<Memo> findById(Long id);
    Page<Memo> findAll(Pageable page);
    void store(Memo memo);
    void removeById(Long id);
}
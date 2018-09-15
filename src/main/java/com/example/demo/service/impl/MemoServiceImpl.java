package com.example.demo.service.impl;

import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import com.example.demo.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class MemoServiceImpl implements MemoService {

  private final MemoRepository repository;

  public MemoServiceImpl(MemoRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Memo> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Memo> findAll(Pageable page) {
    return repository.findAll(page);
  }

  @Transactional(timeout = 10)
  @Override
  public void store(Memo memo) {
    repository.save(memo);
  }

  @Transactional(timeout = 10)
  @Override
  public void updateById(Long id, Memo memo) {
    repository.findById(id).ifPresent(targetMemo -> targetMemo.merge(memo));
  }

  @Transactional(timeout = 10)
  @Override
  public void removeById(Long id) {
    repository.deleteById(id);
  }

}

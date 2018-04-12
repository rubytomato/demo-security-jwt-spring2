package com.example.demo.controller;

import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "memo")
@Slf4j
public class MemoController {

    private final MemoService service;

    public MemoController(MemoService service) {
        this.service = service;
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Memo> id(@PathVariable(value = "id") Long id) {
        Optional<Memo> memo = service.findById(id);
        return memo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Memo>> list(Pageable page) {
        Page<Memo> memos = service.findAll(page);
        return ResponseEntity.ok(memos.getContent());
    }

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String store(@RequestBody Memo memo) {
        service.store(memo);
        return "success";
    }

    @DeleteMapping(path = "{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String remove(@PathVariable(value = "id") Long id) {
        service.removeById(id);
        return "success";
    }

}

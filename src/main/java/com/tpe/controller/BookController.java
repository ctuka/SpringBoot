package com.tpe.controller;

import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/fetchBooks")
    public ResponseEntity<String> fetchbook (@RequestParam String query)
    {
        bookService.fetchAndSaveBooks(query);
        return ResponseEntity.ok(" Book are saved successfully in DB");
    }
}

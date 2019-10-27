package org.example.sbela.controller;

import org.example.sbela.bean.Book;
import org.example.sbela.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book/{id}")
    public Book getBookById(@PathVariable String id) {
        Optional<Book> opt =bookService.findById(id);
        Book book=opt.get();
        System.out.println(book);
        return book;
    }

    @RequestMapping("/save")
    @ResponseBody
    public void Save(){
        Book book=new Book("1","ES入门教程","程裕强","2018-10-01");
        System.out.println(book);
        bookService.save(book);
    }

}

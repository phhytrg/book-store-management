package com.example.bookstoremanagement.controller;

import com.example.bookstoremanagement.domain.Book;
import com.example.bookstoremanagement.domain.BookDeliveryNote;
import com.example.bookstoremanagement.dto.BookDTO;
import com.example.bookstoremanagement.dto.BookDeliveryNoteDTO;
import com.example.bookstoremanagement.mapping.BookDeliveryNoteMapper;
import com.example.bookstoremanagement.response.Response;
import com.example.bookstoremanagement.response.ResponseAPI;
import com.example.bookstoremanagement.service.BookDeliveryNoteService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "deliveries", consumes = "application/json;charset=UTF-8")
public class BookDeliveryNoteController {
    private static final String DELIVERY_NOTE_DETAILS_MISSING_MSG = "Book delivery's details must be specified";
    private final BookDeliveryNoteService service;
    private final BookDeliveryNoteMapper mapper;
    @PostMapping("add")
    public Response addBookDeliveryNote(@RequestBody BookDeliveryNoteDTO bookDeliveryNoteDTO){
        Preconditions.checkState(Objects.nonNull(bookDeliveryNoteDTO), DELIVERY_NOTE_DETAILS_MISSING_MSG);
        BookDeliveryNote bookDeliveryNote = mapper.toEntity(bookDeliveryNoteDTO);
        return ResponseAPI.positiveResponse(service.addBookDeliveryNote(bookDeliveryNote));
    }
//    @PostMapping("update")
//    public Response updateBookDeliveryNote(@RequestParam Long id,
//                                           @RequestBody BookDeliveryNoteDTO bookDeliveryNoteDTO){
//
//    }
}
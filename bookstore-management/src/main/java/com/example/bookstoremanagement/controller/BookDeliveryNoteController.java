package com.example.bookstoremanagement.controller;

import com.example.bookstoremanagement.domain.Book;
import com.example.bookstoremanagement.domain.BookDeliveryNote;
import com.example.bookstoremanagement.domain.BookDeliveryNoteBook;
import com.example.bookstoremanagement.domain.Invoice;
import com.example.bookstoremanagement.dto.BookDTO;
import com.example.bookstoremanagement.dto.BookDeliveryNoteBookDTO;
import com.example.bookstoremanagement.dto.BookDeliveryNoteDTO;
import com.example.bookstoremanagement.dto.InvoiceDTO;
import com.example.bookstoremanagement.mapping.BookDeliveryNoteMapper;
import com.example.bookstoremanagement.response.Response;
import com.example.bookstoremanagement.response.ResponseAPI;
import com.example.bookstoremanagement.service.BookDeliveryNoteService;
import com.example.bookstoremanagement.service.RegulationService;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(value = "deliveries")
public class BookDeliveryNoteController{
    private static final String DELIVERY_NOTE_DETAILS_MISSING_MSG = "Book delivery's details must be specified";
    private static final String DELIVERY_NOTE_ID_MISSING_MSG = "Book delivery note's id must be specified";
    private static final String DELIVERY_QUANTITY_LESS_THAN_REGULATION = "Delivery's quantity of book less than regulation!";
    private static final String CURRENT_QUANTITY_MORE_THAN_REGULATION = "Current book's quantity more than regulation!";
    private final BookDeliveryNoteService service;
    private final BookDeliveryNoteMapper mapper;

    @GetMapping("list")
    public Page<BookDeliveryNoteDTO> getDeliveryNotesByPage(@RequestParam("page") int page,
                                              @RequestParam("size") int size){
        Page<BookDeliveryNote> deliveryNotes = service.fetchNotesByPage(page, size);
        return new PageImpl<>(mapper.toDtoList(deliveryNotes.getContent()), deliveryNotes.getPageable(), deliveryNotes.getTotalElements());
    }
    @PostMapping("add")
    public Response addBookDeliveryNote(@RequestBody BookDeliveryNoteDTO bookDeliveryNoteDTO){
        Preconditions.checkState(Objects.nonNull(bookDeliveryNoteDTO), DELIVERY_NOTE_DETAILS_MISSING_MSG);
//        for(BookDeliveryNoteBookDTO book: bookDeliveryNoteDTO.getDeliveryNoteBooks()){
//            Preconditions.checkState(isDeliveryQuantityLargerThanRegulation(book.getQuantity()),
//                    book.getBook().getTitle() + ": "+ DELIVERY_QUANTITY_LESS_THAN_REGULATION);
//            Preconditions.checkState(isCurrentQuantityLessThanRegulation(book.getQuantity()),
//                    book.getBook().getTitle() + ": "+ CURRENT_QUANTITY_MORE_THAN_REGULATION);
//        }
        BookDeliveryNote bookDeliveryNote = mapper.toEntity(bookDeliveryNoteDTO);
        return ResponseAPI.positiveResponse(service.addBookDeliveryNote(bookDeliveryNote));
    }
    @PostMapping("update")
    public Response updateBookDeliveryNote(@RequestParam(value = "id") Long id,
                                           @RequestBody BookDeliveryNoteDTO bookDeliveryNoteDTO){
        Preconditions.checkState(Objects.nonNull(id), DELIVERY_NOTE_ID_MISSING_MSG);
        Preconditions.checkState(Objects.nonNull(bookDeliveryNoteDTO), DELIVERY_NOTE_DETAILS_MISSING_MSG);
        BookDeliveryNote bookDeliveryNote = mapper.toEntity(bookDeliveryNoteDTO);
        return ResponseAPI.positiveResponse(service.updateBookDeliveryNote(id, bookDeliveryNote));
    }
    @PostMapping("remove/{id}")
    public Response removeBookDeliveryNote(@PathVariable("id") Long id){
        Preconditions.checkState(Objects.nonNull(id), DELIVERY_NOTE_ID_MISSING_MSG);
        service.deleteBookDeliveryNote(id);
        return ResponseAPI.emptyPositiveResponse();
    }

}

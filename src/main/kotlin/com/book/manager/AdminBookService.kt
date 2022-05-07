package com.book.manager

import com.book.manager.infrastructure.database.repository.Book
import com.book.manager.infrastructure.database.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminBookService (
    private val bookRepository: BookRepository
) {
    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id)?.let { throw IllegalAccessException("すでに存在する書籍ID：${book.id}")}
        bookRepository.register(book)
    }
}

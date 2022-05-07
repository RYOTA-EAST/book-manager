package com.book.manager

import com.book.manager.infrastructure.database.repository.Book
import com.book.manager.infrastructure.database.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class AdminBookService (
    private val bookRepository: BookRepository
) {
    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id)?.let { throw IllegalAccessException("すでに存在する書籍ID：${book.id}")}
        bookRepository.register(book)
    }

    @Transactional
    fun update(bookId: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookRepository.findWithRental(bookId) ?: throw IllegalAccessException("存在しない書籍ID:${bookId}")
        bookRepository.update(bookId, title, author, releaseDate)
    }
}

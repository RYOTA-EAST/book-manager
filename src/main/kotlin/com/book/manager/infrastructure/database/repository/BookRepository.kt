package com.book.manager.infrastructure.database.repository

import com.book.manager.infrastructure.database.mapper.custom.BookWithRentalMapper

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
    fun findWithRental(id: Long): BookWithRental?
    fun register(book: Book)
}
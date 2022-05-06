package com.book.manager.infrastructure.database.repository

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
}
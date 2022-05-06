package com.book.manager.infrastructure.database.repository

data class BookWithRental(
    val book: Book,
    val rental: Rental?,
) {
    val isRental: Boolean
        get() = rental != null
}

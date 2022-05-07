package com.book.manager.manager.presentation.form

import com.book.manager.infrastructure.database.repository.BookWithRental
import com.book.manager.infrastructure.database.repository.Rental
import java.time.LocalDate
import java.time.LocalDateTime
import javax.sound.sampled.DataLine

data class GetBookListResponse(val bookList: List<BookInfo>)
data class BookInfo(
    val id: Long,
    val title: String,
    val author: String,
    val isRental: Boolean,
) {
    constructor(model: BookWithRental) : this(model.book.id, model.book.title, model.book.author, model.isRental)
}
data class GetBookDetailResponse(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate,
    val rentalInfo: RentalInfo?
){
    constructor(model: BookWithRental): this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.book.releaseDate,
        model.rental?.let { RentalInfo(model.rental) })
}
data class RentalInfo(
    val userId: Long,
    val rentalDateTime: LocalDateTime,
    val returnDeadline: LocalDateTime,
){
    constructor(rental: Rental) : this(rental.userId, rental.rentalDatetime, rental.returnDeadline)
}

data class RegisterBookRequest(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate,
)

data class UpdateBookRequest(
    val id: Long,
    val title: String?,
    val author: String?,
    val releaseDate: LocalDate?,
)
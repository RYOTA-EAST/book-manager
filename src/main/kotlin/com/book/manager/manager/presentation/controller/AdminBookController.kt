package com.book.manager.manager.presentation.controller

import com.book.manager.AdminBookService
import com.book.manager.infrastructure.database.repository.Book
import com.book.manager.manager.presentation.form.RegisterBookRequest
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
@RequestMapping("admin/book")
class AdminBookController (
    private val adminBookService: AdminBookService
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterBookRequest){
        adminBookService.register(
            Book(
                request.id,
                request.title,
                request.author,
                request.releaseDate,
            )
        )
    }
}

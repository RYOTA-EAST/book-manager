package com.book.manager.manager.presentation.controller

import com.book.manager.manager.presentation.form.BookInfo
import com.book.manager.manager.presentation.form.GetBookListResponse
import com.book.manager.service.BookService
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@RestController
@CrossOrigin
@RequestMapping("book")
class BookController(
    private val bookService: BookService
) {
    @GetMapping("/list")
    fun getList(): GetBookListResponse {
        val bookList = bookService.getList().map {
            BookInfo(it)
        }
        return GetBookListResponse(bookList)
    }
}

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8081")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("Authorization")
            .allowCredentials(true);
    }
}


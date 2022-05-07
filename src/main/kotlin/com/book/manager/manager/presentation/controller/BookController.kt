package com.book.manager.manager.presentation.controller

import com.book.manager.manager.presentation.form.BookInfo
import com.book.manager.manager.presentation.form.GetBookDetailResponse
import com.book.manager.manager.presentation.form.GetBookListResponse
import com.book.manager.service.BookService
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@RestController
@CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
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

    @GetMapping("/detail/{book_id}")
    fun getDetail(@PathVariable("book_id") bookId: Long): GetBookDetailResponse {
        val book = bookService.getDetail(bookId)
        return GetBookDetailResponse(book)
    }
}

//@Configuration
//class WebMvcConfig : WebMvcConfigurer {
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/**")
//            .allowedOrigins("http://localhost:8081")
//            .allowedMethods("GET", "POST", "PUT", "DELETE")
//            .allowedHeaders("Authorization")
//            .allowCredentials(true);
//    }
//}


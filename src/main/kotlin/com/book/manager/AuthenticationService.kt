package com.book.manager

import com.book.manager.infrastructure.database.repository.User
import com.book.manager.infrastructure.database.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {
    fun findUser(email: String): User? {
        return userRepository.find(email)
    }
}
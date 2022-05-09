package com.book.manager.infrastructure.database.repository

interface UserRepository {
    fun find(email: String): User?
}
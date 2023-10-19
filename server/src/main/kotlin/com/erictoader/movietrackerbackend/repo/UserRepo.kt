package com.erictoader.movietrackerbackend.repo

import com.erictoader.movietrackerbackend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<User, Int> {

    fun findByUsername(username: String): User?

    fun findByEmail(email: String): User?

    fun findByUsernameAndPassword(username: String, password: String): User?
}
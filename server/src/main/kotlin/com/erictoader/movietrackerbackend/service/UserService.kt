package com.erictoader.movietrackerbackend.service

import com.erictoader.movietrackerbackend.entity.User
import com.erictoader.movietrackerbackend.repo.UserRepo
import com.erictoader.movietrackerbackend.request.UserRequest
import com.erictoader.movietrackerbackend.response.Response
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_EMAIL_EXISTS
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_EMAIL_INVALID
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_INCORRECT_CREDENTIALS
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_PASSWORD_INVALID
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_PASSWORD_TOO_SHORT
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_USERNAME_EXISTS
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_USERNAME_TOO_SHORT
import com.erictoader.movietrackerbackend.response.ResponseStatus.ERROR_USER_NOT_FOUND
import com.erictoader.movietrackerbackend.response.ResponseStatus.SUCCESS
import com.erictoader.movietrackerbackend.response.model.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepo: UserRepo
) {

    fun getAll(): Response<List<UserModel>> {
        val users = userRepo.findAll()
        return Response(
            SUCCESS,
            users.map { it.mapToModel() }
        )
    }

    fun register(request: UserRequest.Register): Response<UserModel> {
        if (request.username.length < MINIMUM_USERNAME_LENGTH) return Response(ERROR_USERNAME_TOO_SHORT)
        if (request.password.length < MINIMUM_PASSWORD_LENGTH) return Response(ERROR_PASSWORD_TOO_SHORT)
        if (!request.email.matches(EMAIL_REGEX)) return Response(ERROR_EMAIL_INVALID)
        if (!request.password.matches(PASSWORD_REGEX)) return Response(ERROR_PASSWORD_INVALID)

        if (userRepo.findByEmail(request.email) != null) return Response(ERROR_EMAIL_EXISTS)
        if (userRepo.findByUsername(request.username) != null) return Response(ERROR_USERNAME_EXISTS)

        return Response(
            SUCCESS,
            userRepo.save(
                User(
                    email = request.email,
                    username = request.username,
                    password = request.password
                )
            ).mapToModel()
        )
    }

    fun login(username: String, password: String): Response<UserModel> {
        val user = userRepo.findByUsernameAndPassword(username, password)
            ?: return Response(ERROR_INCORRECT_CREDENTIALS)
        return Response(SUCCESS, user.mapToModel())
    }

    fun update(request: UserRequest.Update): Response<UserModel> {
        if (request.password.length < MINIMUM_PASSWORD_LENGTH) return Response(ERROR_PASSWORD_TOO_SHORT)
        if (!request.password.matches(PASSWORD_REGEX)) return Response(ERROR_PASSWORD_INVALID)
        if (!request.email.matches(EMAIL_REGEX)) return Response(ERROR_EMAIL_INVALID)

        val existing = userRepo.findById(request.id).orElse(null) ?: return Response(ERROR_USER_NOT_FOUND)
        if (existing.email != request.email) {
            val userByEmail = userRepo.findByEmail(request.email)
            if (userByEmail != null) return Response(ERROR_EMAIL_EXISTS)
        }

        return Response(
            SUCCESS,
            userRepo.save(
                existing.copy(
                    firstName = request.firstName,
                    lastName = request.lastName,
                    email = request.email,
                    password = request.password
                )
            ).mapToModel()
        )
    }

    companion object {
        private const val MINIMUM_USERNAME_LENGTH = 6
        private const val MINIMUM_PASSWORD_LENGTH = 8
        private val EMAIL_REGEX = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)".toRegex()
        private val PASSWORD_REGEX = ".*\\d.*".toRegex()
    }
}
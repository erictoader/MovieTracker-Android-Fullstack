package com.erictoader.movietrackerbackend.controller

import com.erictoader.movietrackerbackend.request.UserRequest
import com.erictoader.movietrackerbackend.response.Response
import com.erictoader.movietrackerbackend.response.model.UserModel
import com.erictoader.movietrackerbackend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RequestMapping("user")
@RestController
class UserController @Autowired constructor(
    private val userService: UserService
) {

    @GetMapping("/get")
    fun getAll(): Response<List<UserModel>> =
        userService.getAll()

    @GetMapping("/login")
    fun login(
        @RequestParam("username") username: String,
        @RequestParam("password") password: String
    ): Response<UserModel> =
        userService.login(username, password)

    @PostMapping("/register")
    fun register(@RequestBody request: UserRequest.Register): Response<UserModel> =
        userService.register(request)

    @PutMapping("/update")
    fun update(@RequestBody request: UserRequest.Update): Response<UserModel> =
        userService.update(request)

}
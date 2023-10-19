package com.erictoader.movietrackerbackend.response

data class Response<T>(
    val code: Int,
    val message: String,
    val data: T?
) {
    constructor(status: Pair<Int, String>, data: T? = null) : this(status.first, status.second, data)
}


package com.erictoader.movietrackerbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

const val BASE_PACKAGE = "com.erictoader.movietrackerbackend"

@SpringBootApplication
@EntityScan("$BASE_PACKAGE.entity")
open class MovieTrackerBackEndApplication

fun main(args: Array<String>) {
    runApplication<MovieTrackerBackEndApplication>(*args)
}

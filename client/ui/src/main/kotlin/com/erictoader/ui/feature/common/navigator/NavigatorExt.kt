package com.erictoader.ui.feature.common.navigator

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.reflect.full.superclasses

abstract class Destination(open val name: String, private vararg val argumentPaths: String) {

    val route: String
        get() = name + argumentPaths.joinToString("/")

    init {
        if (argumentPaths != null) {
            val duplicates = argumentPaths
                .groupingBy { it }
                .eachCount()
                .filter { it.value > 1 }

            if (duplicates.isNotEmpty()) {
                throw IllegalStateException(
                    "Only one argument type allowed. Duplicate type: ${duplicates.keys.first()}"
                )
            }
        }
    }
}

inline fun <reified Arg : NavigationArgument> argumentOf(): String = "{${Arg::class.simpleName}}"

inline fun <reified Arg : NavigationArgument> NavController.navigate(
    destination: ScreenDestination,
    vararg args: Arg
) {
    var route = destination.route

    for (arg in args) {
        val argRoute = String(route.toCharArray())
        val serializedArg = arg.serialize()

        val argSuperclasses = arg::class.superclasses
        if (argSuperclasses.isEmpty()) continue
        var argSuperclassesIndex = 0

        while (route == argRoute) {
            route = route.replace(
                oldValue = "{${argSuperclasses[argSuperclassesIndex].simpleName}}",
                newValue = serializedArg
            )
            argSuperclassesIndex++
            if (argSuperclasses.size == argSuperclassesIndex) break
        }
    }

    navigate(route = route)
}

fun NavController.navigate(destination: ScreenDestination) = navigate(destination.route)

inline fun <reified Arg : NavigationArgument> NavBackStackEntry.getArgument(): Arg? =
    arguments?.getString(Arg::class.simpleName)?.deserialize()

inline fun <reified Arg : NavigationArgument> jsonAdapterOf(): JsonAdapter<Arg> =
    Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(Arg::class.java)

inline fun <reified Arg : NavigationArgument> Arg.serialize(): String =
    jsonAdapterOf<Arg>().toJson(this).urlEncode()

inline fun <reified Arg : NavigationArgument> String.deserialize(): Arg? =
    jsonAdapterOf<Arg>().fromJson(this.urlDecode())

fun String.urlEncode(): String = URLEncoder.encode(this, "utf-8")

fun String.urlDecode(): String = URLDecoder.decode(this, "utf-8")

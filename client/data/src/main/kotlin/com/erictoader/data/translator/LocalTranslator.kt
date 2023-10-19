package com.erictoader.data.translator

import com.erictoader.domain.translator.TRANSLATION_AIRING_TODAY
import com.erictoader.domain.translator.TRANSLATION_CONTINUE_WATCHING
import com.erictoader.domain.translator.TRANSLATION_LATEST
import com.erictoader.domain.translator.TRANSLATION_MODULE_CAST
import com.erictoader.domain.translator.TRANSLATION_MODULE_REVIEWS
import com.erictoader.domain.translator.TRANSLATION_NOW_PLAYING
import com.erictoader.domain.translator.TRANSLATION_ON_THE_AIR
import com.erictoader.domain.translator.TRANSLATION_POPULAR
import com.erictoader.domain.translator.TRANSLATION_RECOMMENDED
import com.erictoader.domain.translator.TRANSLATION_TOP_RATED
import com.erictoader.domain.translator.TRANSLATION_UPCOMING
import com.erictoader.domain.translator.TRANSLATION_WATCHLIST
import com.erictoader.domain.translator.Translator

class LocalTranslator: Translator {

    private val translations: HashMap<String, String> = HashMap<String, String>().apply {
        this[TRANSLATION_CONTINUE_WATCHING] = LOCAL_TRANSLATION_CONTINUE_WATCHING
        this[TRANSLATION_NOW_PLAYING] = LOCAL_TRANSLATION_NOW_PLAYING
        this[TRANSLATION_ON_THE_AIR] = LOCAL_TRANSLATION_ON_THE_AIR
        this[TRANSLATION_AIRING_TODAY] = LOCAL_TRANSLATION_AIRING_TODAY
        this[TRANSLATION_POPULAR] = LOCAL_TRANSLATION_POPULAR
        this[TRANSLATION_UPCOMING] = LOCAL_TRANSLATION_UPCOMING
        this[TRANSLATION_LATEST] = LOCAL_TRANSLATION_LATEST
        this[TRANSLATION_TOP_RATED] = LOCAL_TRANSLATION_TOP_RATED
        this[TRANSLATION_MODULE_CAST] = LOCAL_TRANSLATION_MODULE_CAST
        this[TRANSLATION_MODULE_REVIEWS] = LOCAL_TRANSLATION_MODULE_REVIEWS
        this[TRANSLATION_RECOMMENDED] = LOCAL_TRANSLATION_RECOMMENDED
        this[TRANSLATION_WATCHLIST] = LOCAL_TRANSLATION_WATCHLIST
    }

    override fun fetchTranslation(keyword: String): String {
        translations[keyword]?.let { return it }
        throw LocalTranslationNotFoundException(keyword)
    }

    companion object {
        const val LOCAL_TRANSLATION_CONTINUE_WATCHING = "Continue Watching"
        const val LOCAL_TRANSLATION_NOW_PLAYING = "Now Playing"
        const val LOCAL_TRANSLATION_ON_THE_AIR = "On The Air"
        const val LOCAL_TRANSLATION_POPULAR = "Popular Results"
        const val LOCAL_TRANSLATION_UPCOMING = "Upcoming Movies"
        const val LOCAL_TRANSLATION_AIRING_TODAY = "Airing Today"
        const val LOCAL_TRANSLATION_LATEST = "Latest Releases"
        const val LOCAL_TRANSLATION_TOP_RATED = "Top Rated"
        const val LOCAL_TRANSLATION_MODULE_CAST = "Cast"
        const val LOCAL_TRANSLATION_MODULE_REVIEWS = "Reviews"
        const val LOCAL_TRANSLATION_RECOMMENDED = "Recommended"
        const val LOCAL_TRANSLATION_WATCHLIST = "Added to Watchlist"
    }
}

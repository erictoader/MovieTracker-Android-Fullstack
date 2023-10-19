package com.erictoader.data.cache.abstraction

import com.erictoader.domain.feature.auth.model.UserDomain

interface UserCacheProxy {

    suspend fun cacheUser(user: UserDomain)

    suspend fun retrieveCachedUser(): UserDomain?

    suspend fun clearCachedUser()
}

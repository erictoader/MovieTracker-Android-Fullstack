package com.erictoader.data.cache.concrete

import com.erictoader.data.cache.abstraction.UserCacheProxy
import com.erictoader.data.cache.datastore.abstraction.DataStoreRepo
import com.erictoader.domain.feature.auth.model.UserDomain

class UserCacheProxyImpl(
    private val dataStoreRepo: DataStoreRepo
) : UserCacheProxy {

    override suspend fun cacheUser(user: UserDomain) =
        dataStoreRepo.storeUserDetails(user)

    override suspend fun retrieveCachedUser(): UserDomain? =
        dataStoreRepo.retrieveUserDetails()

    override suspend fun clearCachedUser() =
        dataStoreRepo.clearUserDetails()
}

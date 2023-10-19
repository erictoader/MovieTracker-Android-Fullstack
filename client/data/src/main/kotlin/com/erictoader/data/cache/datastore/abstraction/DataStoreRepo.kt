package com.erictoader.data.cache.datastore.abstraction

import com.erictoader.domain.feature.auth.model.UserDomain

interface DataStoreRepo {

    suspend fun storeUserDetails(user: UserDomain)

    suspend fun retrieveUserDetails(): UserDomain?

    suspend fun clearUserDetails()
}

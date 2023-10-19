package com.erictoader.data.cache.datastore.concrete

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.erictoader.data.cache.datastore.abstraction.DataStoreRepo
import com.erictoader.data.cache.datastore.util.DATASTORE_USER_EMAIL_KEY
import com.erictoader.data.cache.datastore.util.DATASTORE_USER_FIRST_NAME_KEY
import com.erictoader.data.cache.datastore.util.DATASTORE_USER_ID_KEY
import com.erictoader.data.cache.datastore.util.DATASTORE_USER_LAST_NAME_KEY
import com.erictoader.data.cache.datastore.util.DATASTORE_USER_PASSWORD_KEY
import com.erictoader.data.cache.datastore.util.DATASTORE_USER_USERNAME_KEY
import com.erictoader.domain.feature.auth.model.UserDomain
import kotlinx.coroutines.flow.first

class DataStoreRepoImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepo {

    private val userIdKey = intPreferencesKey(DATASTORE_USER_ID_KEY)
    private val userEmailKey = stringPreferencesKey(DATASTORE_USER_EMAIL_KEY)
    private val userUsernameKey = stringPreferencesKey(DATASTORE_USER_USERNAME_KEY)
    private val userPasswordKey = stringPreferencesKey(DATASTORE_USER_PASSWORD_KEY)
    private val userFirstNameKey = stringPreferencesKey(DATASTORE_USER_FIRST_NAME_KEY)
    private val userLastNameKey = stringPreferencesKey(DATASTORE_USER_LAST_NAME_KEY)

    override suspend fun storeUserDetails(user: UserDomain) {
        dataStore.edit { preferences ->
            preferences[userIdKey] = user.id
            preferences[userEmailKey] = user.email
            preferences[userUsernameKey] = user.username
            preferences[userPasswordKey] = user.password
            preferences[userFirstNameKey] = user.firstName
            preferences[userLastNameKey] = user.lastName
        }
    }

    override suspend fun retrieveUserDetails(): UserDomain? {
        val preferences = dataStore.data.first()
        val userId = preferences[userIdKey]
        val email = preferences[userEmailKey]
        val username = preferences[userUsernameKey]
        val password = preferences[userPasswordKey]
        val firstName = preferences[userFirstNameKey]
        val lastName = preferences[userLastNameKey]

        return if (
            userId != null &&
            email != null &&
            username != null &&
            password != null &&
            firstName != null &&
            lastName != null
        ) {
            UserDomain(
                id = userId,
                email = email,
                username = username,
                password = password,
                firstName = firstName,
                lastName = lastName
            )
        } else null
    }

    override suspend fun clearUserDetails() {
        dataStore.edit { it.clear() }
    }
}

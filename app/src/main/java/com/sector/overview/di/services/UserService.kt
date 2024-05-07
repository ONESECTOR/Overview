package com.sector.overview.di.services

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserService {

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    fun login(email: String?, password: String?, nickname: String?) {
        _authState.update { state ->
            state.copy(
                email = email,
                password = password,
                nickname = nickname
            )
        }
    }

    fun signOut() {
        _authState.update { AuthState() }
    }
}

data class AuthState(
    val email: String? = null,
    val password: String? = null,
    val nickname: String? = null
)
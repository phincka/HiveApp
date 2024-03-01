package com.example.hiveapp.data.repository

import com.example.hiveapp.data.model.UserModel
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single


@Single
class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    override suspend fun getCurrentUser(): UserModel? = firebaseAuth.currentUser?.run {
        UserModel(
            userId = uid,
            email = email,
        )
    }

    override suspend fun firebaseEmailSignIn(
        email: String,
        password: String
    ): AuthState {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthState.Success(true)
        } catch (error: Exception) {
            AuthState.Error(error)
        }
    }

    override suspend fun firebaseSignOut() = try {
        firebaseAuth.signOut()
        AuthState.Success(true)
    } catch (error: Exception) {
        AuthState.Error(error)
    }
}
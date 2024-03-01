package com.example.hiveapp.ui.theme.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.usecase.signin.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignInViewModel(
    private val signInUseCase: SignInUseCase,
//    private val getAuthStateUseCase: GetAuthStateUseCase,
): ViewModel() {
    private val _state: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Success(false))
    val signInState: StateFlow<AuthState> = _state



//    var signInResponse by mutableStateOf(SignInState.Success(false))
//    var signOutResponse by mutableStateOf<SignOutResponse>(AuthResponse.Success(false))

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            _state.value = signInUseCase(email, password)
        }
    }

//    fun signOut() = viewModelScope.launch {
//        signOutResponse = AuthResponse.Loading
//        signOutResponse = signOutUseCase()
//    }

//    fun signIn(
//        email: String,
//        password: String,
//    ) {
//        CoroutineScope(viewModelScope.coroutineContext).launch {
//            signInUseCase(email, password)
//        }
//    }
//    fun onSignInResult(result: SignInResult) {
//        _state.update { it.copy(
//            isSignInSuccessful = result.data != null,
//            signInError = result.errorMessage
//        ) }
//    }
//
//    fun resetState() {
//        _state.update { SignInState() }
//    }
//    fun signIn(email: String, password: String) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnSuccessListener { authRes ->
//                if (authRes != null) {
//                    println("--------------")
//                    println(authRes.user?.email)
//                    println("--------------")
//
////                    navigator.navigate(HomeScreenDestination)
//                } else {
//                    println("--------------")
//                    println("--------------")
//                    Log.d("LOG", "Coś poszło nie tak")
//                    println("--------------")
//                    println("--------------")
//                }
//
//            }
//            .addOnFailureListener { err ->
//                println("--------------")
//                println("--------------")
//                Log.d("LOG", err.message.toString())
//                println("--------------")
//                println("--------------")
//            }
//    }

}
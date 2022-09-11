package Isoul.Safe.workoutlog.viewmodel

import Isoul.Safe.workoutlog.models.LoginRequest
import Isoul.Safe.workoutlog.models.LoginResponse
import Isoul.Safe.workoutlog.models.RegisterRequest
import Isoul.Safe.workoutlog.models.RegisterResponse
import Isoul.Safe.workoutlog.repository.UserRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository = UserRepository()
    var loginResponseLiveData =
        MutableLiveData<LoginResponse>() //live data is an observable live data
    val loginErrorLiveData = MutableLiveData<String?>()
    var signUpLiveData=MutableLiveData<RegisterResponse>()
    val signUpErrorLiveData=MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest) {
        //launch function is what creates the coroutine
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful) {
                loginResponseLiveData.postValue(response.body())
            } else {
                val error = response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }
    }
    fun signUpUser(signUpRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.signUpUser(signUpRequest)
            if (response.isSuccessful){
                signUpLiveData.postValue(response.body())
            }else{
                val error=response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }
    }

}
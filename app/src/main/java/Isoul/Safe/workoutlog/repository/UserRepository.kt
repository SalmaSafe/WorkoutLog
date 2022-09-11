package Isoul.Safe.workoutlog.repository

import Isoul.Safe.workoutlog.api.ApiClient
import Isoul.Safe.workoutlog.api.ApiInterface
import Isoul.Safe.workoutlog.models.LoginRequest
import Isoul.Safe.workoutlog.models.RegisterRequest
import Isoul.Safe.workoutlog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient= ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest)= withContext(Dispatchers.IO){
        val response=apiClient.login(loginRequest)
        return@withContext response
    }
    // repository is a data source
    suspend fun signUpUser(signUpRequest:RegisterRequest):Response<RegisterResponse>
    = withContext(Dispatchers.IO){
        val response=apiClient.registerUser(signUpRequest)
        return@withContext response
    }
}
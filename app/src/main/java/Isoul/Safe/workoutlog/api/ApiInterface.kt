package Isoul.Safe.workoutlog.api

import Isoul.Safe.workoutlog.models.LoginRequest
import Isoul.Safe.workoutlog.models.LoginResponse
import Isoul.Safe.workoutlog.models.RegisterRequest
import Isoul.Safe.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>

}
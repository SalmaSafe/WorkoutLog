package Isoul.Safe.workoutlog.retrofit

import Isoul.Safe.workoutlog.models.LoginRequest
import Isoul.Safe.workoutlog.models.LoginResponse
import Isoul.Safe.workoutlog.models.RegisterRequest
import Isoul.Safe.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest):Call<LoginResponse>

}
package com.clghks.androidapptest.common.network.api

import com.clghks.androidapptest.common.network.model.GithubUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubService {
    @GET("/search/users")
    fun getUsers(@Query("q") q: String, @Query("page") page: Int = 1): Observable<GithubUser>
}
package com.chat.vaibhav.slambook;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by vaibhav on 18-03-2018.
 */

interface RegisterAPI {
    @FormUrlEncoded
    @POST("/http://php")
    public void insertUser(
            @Field("name") String name,
            @Field("username") String username,
            @Field("hobbies") String password,
            @Field("bf") String email,
            @Field("contact") String contact,
            @Field("crush") String crush,
            Callback<Response> callback);

}

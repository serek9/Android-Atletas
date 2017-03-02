package com.taniafontcuberta.basketball.controller.services;

import com.taniafontcuberta.basketball.model.Atleta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface AtletaService {

    @GET("/api/atletas")
    Call<List<Atleta>> getAllPlayer(
            @Header("Authorization") String Authorization
    );

    @POST("api/atletas") // Se tiene que cambiar en un interfaz propia
    Call<Atleta> createPlayer(
            @Header("Authorization") String Authorization,
            @Body Atleta atleta);


    @PUT("api/atletas")
    Call<Atleta> updatePlayer(
            @Header("Authorization") String Authorization,
            @Body Atleta atleta);

    @DELETE("api/atletass/{id}")
    Call<Void> deletePlayer(
            @Header("Authorization") String Authorization,
            @Path("id") Long id);


    /* FILTERS */

    @GET("api/players/byName/{name}")
    Call<List<Atleta>> getPlayerByName(

            @Header("Authorization") String Authorization,
            @Path("name") String name);

    @GET("api/players/topBaskets/{baskets}")
    Call<List<Atleta>> getPlayersByBaskets(

            @Header("Authorization") String Authorization,
            @Path("baskets") Integer baskets);

    @GET("api/players/topBirthdate/{birthdate}")
    Call<List<Atleta>> getPlayersByBirthdate(

            @Header("Authorization") String Authorization,
            @Path("birthdate") String fechaNacimiento);


    @GET("api/players/topBirthdateBetween/{birthdate}/{birthdate2}")
    Call<List<Atleta>> getPlayersByBirthdateBetween(

            @Header("Authorization") String Authorization,
            @Path("birthdate") String birthdate,
            @Path("birthdate2") String birthdate2);
}
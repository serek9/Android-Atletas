package com.taniafontcuberta.basketball.controller.managers;

import android.util.Log;

import com.taniafontcuberta.basketball.controller.services.AtletaService;
import com.taniafontcuberta.basketball.model.Atleta;
import com.taniafontcuberta.basketball.util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtletaManager {
    private static AtletaManager ourInstance;
    private List<Atleta> atletas;
    private Retrofit retrofit;
    private AtletaService atletaService;

    private AtletaManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        atletaService = retrofit.create(AtletaService.class);
    }

    public static AtletaManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new AtletaManager();
        }

        return ourInstance;
    }

    /* GET - GET ALL PLAYER */

    public synchronized void getAllPlayers(final AtletaCallback atletaCallback) {
        Call<List<Atleta>> call = atletaService.getAllPlayer(UserLoginManager.getInstance().getBearerToken());

        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    atletaCallback.onSuccess(atletas);
                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getAllPlayers()->ERROR: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    public Atleta getPlayer(String id) {
        for (Atleta atleta : atletas) {
            if (atleta.getId().toString().equals(id)) {
                return atleta;
            }
        }

        return null;
    }

    /* POST - CREATE PLAYER */

    public synchronized void createPlayer(final AtletaCallback atletaCallback, Atleta atleta) {
        Call<Atleta> call = atletaService.createPlayer(UserLoginManager.getInstance().getBearerToken(), atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    //atletaCallback.onSuccess1(apuestas1x2);
                    Log.e("Atleta->", "createPlayer: OK" + 100);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("AtletaManager->", "createPlayer: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* PUT - UPDATE PLAYER */
    public synchronized void updatePlayer(final AtletaCallback atletaCallback, Atleta atleta) {
        Call <Atleta> call = atletaService.updatePlayer(UserLoginManager.getInstance().getBearerToken() , atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Atleta->", "updatePlayer: OOK" + 100);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("AtletaManager->", "updatePlayer: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* DELETE - DELETE PLAYER */
    public synchronized void deletePlayer(final AtletaCallback atletaCallback, Long id) {
        Call <Void> call = atletaService.deletePlayer(UserLoginManager.getInstance().getBearerToken() ,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Atleta->", "Deleted: OK");

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("AtletaManager->", "deletePlayer: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* GET - TOP PLAYERS BY NAME */

    public synchronized void getPlayerByName(final AtletaCallback atletaCallback, String name) {
        // Call<List<Apuesta>> call = atletaService.getAllPlayer(UserLoginManager.getInstance(context).getBearerToken());
        Call<List<Atleta>> call = atletaService.getPlayerByName(UserLoginManager.getInstance().getBearerToken(), name);
        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    atletaCallback.onSuccess(atletas);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getPlayerByName: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* GET - TOP PLAYERS BY X BASKETS */

    public synchronized void getPlayersByBaskets(final AtletaCallback atletaCallback, Integer baskets) {
        Call<List<Atleta>> call = atletaService.getPlayersByBaskets(UserLoginManager.getInstance().getBearerToken(), baskets);
        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    atletaCallback.onSuccess(atletas);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getPlayersByBaskets : " + t);

                atletaCallback.onFailure(t);
            }
        });
    }


    /* GET - TOP PLAYERS BY X DATEBIRTH */

    public synchronized void getPlayersByBirthdate(final AtletaCallback atletaCallback, String birthdate) {
        Call<List<Atleta>> call = atletaService.getPlayersByBirthdate(UserLoginManager.getInstance().getBearerToken(), birthdate);
        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    atletaCallback.onSuccess(atletas);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getPlayersByBirthdate: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }

    /* GET - TOP PLAYERS BY DATEBIRTH BETWEEN X AND Y */

    public synchronized void getPlayersByBirthdateBetween(final AtletaCallback atletaCallback, String birthdate, String birthdate2) {
        Call<List<Atleta>> call = atletaService.getPlayersByBirthdateBetween(UserLoginManager.getInstance().getBearerToken(), birthdate, birthdate2);
        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (code == 200 || code == 201) {
                    atletaCallback.onSuccess(atletas);

                } else {
                    atletaCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AtletaManager->", "getPlayersByBirthdateBetween: " + t);

                atletaCallback.onFailure(t);
            }
        });
    }


}

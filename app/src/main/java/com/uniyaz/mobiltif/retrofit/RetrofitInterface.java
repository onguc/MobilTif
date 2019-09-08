package com.uniyaz.mobiltif.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.LoginInfo;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.dto.AmbarDto;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.data.dto.TifIslemResponseDto;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by ONGUC on 18.03.2019.
 */

public interface RetrofitInterface {

    boolean isUriFlex = false;
    String uriFlex = "http://flextest2.uni-yaz.com:8070/FlexCityUi/rest/json/";
    String uriBesiktasTest = "https://demoybs.besiktas.bel.tr/FlexCityUi/rest/json/";

    @POST("../auth/login")
    Call<LoginInfo> loginAndGetToken(@Body RequestBody loginInfo);


    @POST("vys/FindVysTasinirDemirbasByEtiketNo")
//    Call<ResponseInfo<Envanter>> getEnvanterByQrCode(@Header("Authorization") String authTicket, @Body RequestBody bodyQrCode);
    Call<ResponseInfo<Envanter>> getEnvanterByQrCode(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyQrCode);


    @POST("vys/FindVysVarlikLokasyonDtoByOdaEtiketNo")
//    Call<ResponseInfo<Room>> getRoomAndEnvanterListByQrCodeRoom(@Header("Authorization") String authTicket, @Body RequestBody bodyQrCodeRoom);
    Call<ResponseInfo<Room>> getRoomAndEnvanterListByQrCodeRoom(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyQrCodeRoom);


    @POST("vys/FindAllVysTasinirAmbar")
    Call<ResponseInfo<List<AmbarDto>>> getAllAmbarDtoList(@Header("AuthorizationTicket") String authTicket);

    @POST("vys/FindAllPbsPersonelKayitYetkilisiByVysTasinirAmbarId")
//    Call<ResponseInfo<List<PersonelDto>>> getAllPersonelDtoListByAmbarId(@Header("Authorization") String authTicket, @Body RequestBody bodyIdAmbar);
    Call<ResponseInfo<List<PersonelDto>>> getAllPersonelDtoListByAmbarId(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyIdAmbar);


    @POST("vys/FindAllVysTasinirDemirbasImagesByUrl")
//    Call<ResponseInfo<byte[]>> loadImage(@Header("Authorization") String authTicket, @Body RequestBody bodyQrCode);
    Call<ResponseInfo<byte[]>> loadImage(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyQrCode);

    @POST("vys/MobilTifIslem") //SaveVysTasinirTransferIslem
//    Call<ResponseInfo<TifIslemResponseDto>> saveVysTasinirTransferIslem(@Header("Authorization") String authTicket, @Body RequestBody bodyDto);
    Call<ResponseInfo<TifIslemResponseDto>> saveVysTasinirTransferIslem(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyDto);

    @POST("sbs/FindAllSbsMuhatap")
//    Call<ResponseInfo<List<MuhatapDto>>> findAllSbsMuhatap(@Header("Authorization") String authTicket, @Body RequestBody bodyDto);
    Call<ResponseInfo<List<MuhatapDto>>> findAllSbsMuhatap(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyDto);


    @POST("pbs/FindAllPbsPersonelBilgileriDtoByKullaniciAdi")
//    Call<ResponseInfo<List<PersonelDto>>> findAllPbsPersonelBilgileriDtoByKullaniciAdi(@Header("Authorization") String authTicket, @Body RequestBody bodyDto);
    Call<ResponseInfo<List<PersonelDto>>> findAllPbsPersonelBilgileriDtoByKullaniciAdi(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyDto);


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl(isUriFlex ? uriFlex : uriBesiktasTest)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(RetrofitInterface.class);

}

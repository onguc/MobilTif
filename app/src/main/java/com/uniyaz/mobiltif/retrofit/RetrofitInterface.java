package com.uniyaz.mobiltif.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uniyaz.mobiltif.data.domain.Department;
import com.uniyaz.mobiltif.data.domain.Envanter;
import com.uniyaz.mobiltif.data.domain.LoginInfo;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.domain.Room;
import com.uniyaz.mobiltif.data.domain.Tasinir;

import java.util.List;

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
    String uriFlex = "http://flextest2.uni-yaz.com:8070/FlexCityUi/rest/json/vys/";
    String uri = "http://tarihisozluk.site/TarihZumresi/rest/tifislem/";   //test

    @POST("../../auth/login")
    Call<LoginInfo> loginAndGetToken(@Body RequestBody loginInfo);

    //    @POST("json/vys/FindAllDemirbasListByQrCodeRoom")
    @POST("getEnvanterListByQrCodeRoom.php")
    Call<ResponseInfo<List<Envanter>>> getEnvanterListByQrCodeRoom(@Header("Authorization") String authTicket, @Body RequestBody bodyQrCodeRoom);
//    Call<ResponseInfo<List<Envanter>>> getEnvanterListByQrCodeRoom(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyQrCodeRoom);


//    @POST("getEnvanterByQrCode.php")
    @POST("FindVysTasinirDemirbasByEtiketNo")
    Call<ResponseInfo<Envanter>> getEnvanterByQrCode(@Header("Authorization") String authTicket, @Body RequestBody bodyQrCode);
//    Call<ResponseInfo<Envanter>> getEnvanterByQrCode(@Header("AuthorizationTicket") String authTicket, @Body RequestBody bodyQrCode);


    //    @Headers({"Content-Type: application/json", "Authorization:applicationkey=FLX_EBELEDIYE,requestdate=2014-10-01T2:32:50+02:00,md5hashcode=61411bbfbd3675953aa1e3738ce8a5c0"})
    @POST("json/kbs/FindAllKbsServisDto")
    Call<ResponseInfo<List<Department>>> getDepartmList(@Header("Authorization") String authTicket, @Body RequestBody servisTuru);
//    Call<ResponseInfo<List<Department>>> getDepartmList(@Header("AuthorizationTicket") String authTicket, @Body RequestBody servisTuru);

    //    @Headers({"Content-Type: application/json", "Authorization:applicationkey=FLX_EBELEDIYE,requestdate=2014-10-01T2:32:50+02:00,md5hashcode=61411bbfbd3675953aa1e3738ce8a5c0"})
    @POST("json/vys/FindAllVysSayimTasinirKod")
    Call<ResponseInfo<List<Tasinir>>> getTasinirList(@Header("AuthorizationTicket") String authTicket);


    @POST("json/vys/FindAllVysSayimOda")
    Call<ResponseInfo<List<Room>>> getRoomList(@Header("AuthorizationTicket") String authTicket);

    @POST("json/vys/SaveVysSayimOdaList")
    Call<ResponseInfo<Boolean>> saveRoomList(@Header("AuthorizationTicket") String authTicket, @Body RequestBody roomDtoList);


    @POST("FindAllVysTasinirDemirbasImagesByUrl")
    Call<ResponseInfo<byte[]>> loadImage(@Header("Authorization") String authTicket, @Body RequestBody bodyQrCode);


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();

    RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl(isUriFlex?uriFlex:uri)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RetrofitInterface.class);

}

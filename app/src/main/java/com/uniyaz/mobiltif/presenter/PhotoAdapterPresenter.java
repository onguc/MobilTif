package com.uniyaz.mobiltif.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationTicket;

public class PhotoAdapterPresenter {

    public void loadImage(String imageUrl, ImageView imageViev) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "demirbasUrl=" + imageUrl);
        Call<ResponseInfo<byte[]>> imageCall = RetrofitInterface.retrofitInterface.loadImage(getAuthorizationTicket(), requestBody);
        imageCall.enqueue(new Callback<ResponseInfo<byte[]>>() {
            @Override
            public void onResponse(Call<ResponseInfo<byte[]>> call, Response<ResponseInfo<byte[]>> response) {
                if (response != null) {
                    ResponseInfo<byte[]> responseInfo = response.body();
                    if (responseInfo == null) {
                        String errorInfo = response.errorBody().source().buffer().readUtf8();
                        Log.i("PhotoAdapterPresenter", "image byte is null : errorInfo = " + errorInfo);
                    } else if (!responseInfo.getSuccess()) {
                        Log.i("PhotoAdapterPresenter", "loadImage not success!");
                    } else {
                        byte[] bytesImage = responseInfo.getResponse();
                        loadImageInImageView(bytesImage, imageViev);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseInfo<byte[]>> call, Throwable t) {
                Log.e("PhotoAdapterPresenter", "onFailure --> Message = " + t.getMessage());
            }
        });

    }

    private void loadImageInImageView(byte[] bytesImage, ImageView imageView) {
        if (bytesImage != null && bytesImage.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
            imageView.setImageBitmap(bitmap);
        }
    }
}

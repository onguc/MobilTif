package com.uniyaz.mobiltif.ui.components;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;
import com.uniyaz.mobiltif.pattern.PopupBuilder;
import com.uniyaz.mobiltif.presenter.ResponseResult;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.ui.adapters.MuhatapAdapter;
import com.uniyaz.mobiltif.utils.StaticUtils;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationTicket;

/**
 * Created by İrfan Öngüç on 31.07.2019
 */

public class MuhatapComponent extends CoordinatorLayout {
    private TextInputLayout tilMuhatapField;
    private TextInputEditText tietMuhatapField;
    private ListView lvMuhatap;
    private PopupBuilder popupBuilder;
    private MuhatapDto selectedMuhatapDto = new MuhatapDto();

    public MuhatapComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.muhatap_field, this);
        tietMuhatapField = findViewById(R.id.tietMuhatapField);
        tilMuhatapField = findViewById(R.id.tilMuhatapField);
        tietMuhatapField.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    openComponent();
                }
                return true;
            }
        });
        int[] sets = {R.attr.hint};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence text = typedArray.getText(0);

        //tilMuhatapField.setHint(text);
        setHint(text);
        //tilMuhatapField.setError(textForError);
        //tilMuhatapField.setErrorEnabled(false);
    }

    public void setHint(CharSequence text) {
        tietMuhatapField.setHint(text);
    }

    public void setError(CharSequence error) {
        tietMuhatapField.setError(error);
        if (error != null) {
            requestFocus(tietMuhatapField);
        }
    }

    public CharSequence getHint() {
        return tietMuhatapField.getHint();
    }

    public CharSequence getError() {
        return tietMuhatapField.getError();
    }

    public void openComponent() {
        View dialog = LayoutInflater.from(getContext()).inflate(R.layout.popup_call_muhatap, null);
        TextView etAdi = dialog.findViewById(R.id.etAdi);
        Button btnCall = dialog.findViewById(R.id.btnCallMuhatap);

        lvMuhatap = dialog.findViewById(R.id.lvMuhatapList);
        lvMuhatap.setOnItemClickListener((parent, view, position, id) -> {
            MuhatapDto dto = (MuhatapDto) lvMuhatap.getAdapter().getItem(position);
            selectedMuhatapDto.setSbsMuhatapId(dto.getSbsMuhatapId());
            selectedMuhatapDto.setIsim(dto.getIsim());
            hidePopup();
            tietMuhatapField.setText(selectedMuhatapDto.getIsim());
            setError(null);
        });
        btnCall.setOnClickListener(view -> {
            String adi = etAdi.getText().toString();
            if (adi == null || "".equals(adi))
                return;
            StaticUtils.iMain.showProgressBar();
            fillAllMuhatapDto(adi);
        });

        popupBuilder = new PopupBuilder(dialog, getRootView());
        popupBuilder.show();
    }

    public void hidePopup() {
        popupBuilder.hide();
        StaticUtils.iMain.hideProgressBar();
    }

    public void fillAllMuhatapDto(String adi) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "adi=" + adi);
        Call<ResponseInfo<List<MuhatapDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.findAllSbsMuhatap(getAuthorizationTicket(), requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<MuhatapDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<MuhatapDto>>> call, Response<ResponseInfo<List<MuhatapDto>>> response) {
                ResponseResult<List<MuhatapDto>> responseResult = responseDto -> {
                    MuhatapAdapter adapter = new MuhatapAdapter(responseDto, getContext());
                    lvMuhatap.setAdapter(adapter);
                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<MuhatapDto>>> call, Throwable t) {
                Log.e("TifPresenter", "findAllSbsMuhatap-->" + t.getMessage());
                StaticUtils.iMain.showWarningDialog("Error", "findAllSbsMuhatap-->" + t.getMessage());
            }
        });
    }

    public MuhatapDto getSelectedMuhatapDto() {
        return selectedMuhatapDto;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            Activity activity = (Activity) getContext();
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}

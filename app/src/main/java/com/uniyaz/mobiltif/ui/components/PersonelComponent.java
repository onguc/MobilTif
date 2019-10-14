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

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.PersonelDto;
import com.uniyaz.mobiltif.pattern.PopupBuilder;
import com.uniyaz.mobiltif.presenter.ResponseResult;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.ui.adapters.PersonelAdapter;
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

public class PersonelComponent extends CoordinatorLayout {

    private TextInputEditText tietPersonelField;
    private ListView lvPersonel;
    private PopupBuilder popupBuilder;
    private PersonelDto selectedPersonelDto = new PersonelDto();
    private static List<PersonelDto> personelDtoList;

    public PersonelComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.personel_field, this);
        View view = defineComponent();
        tietPersonelField = findViewById(R.id.tietPersonelField);
        TextInputLayout tilPersonelField = findViewById(R.id.tilPersonelField);
        tietPersonelField.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    openComponent(view);
                }
                return true;
            }
        });
        int[] sets = {R.attr.hint};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence text = typedArray.getText(0);

        tilPersonelField.setHint(text);
        fillPersonelDtoList();
    }

    public void setHint(CharSequence text) {
        tietPersonelField.setHint(text);
    }

    public CharSequence getHint() {
        return tietPersonelField.getHint();
    }

    public void openComponent(View dialog) {
        popupBuilder = new PopupBuilder(dialog, getRootView());
        popupBuilder.show();
    }

    public View defineComponent() {
        View dialog = LayoutInflater.from(getContext()).inflate(R.layout.popup_call_personel, null);
        TextView etAdi = dialog.findViewById(R.id.etAdi);
        Button btnCall = dialog.findViewById(R.id.btnCallPersonel);

        lvPersonel = dialog.findViewById(R.id.lvPersonelList);
        lvPersonel.setOnItemClickListener((parent, view, position, id) -> {
            PersonelDto dto = (PersonelDto) lvPersonel.getAdapter().getItem(position);
            selectedPersonelDto.setId(dto.getId());
            selectedPersonelDto.setIsim(dto.getIsim());
            hidePopup();
            tietPersonelField.setText(selectedPersonelDto.getIsim());
            setError(null);
        });
        btnCall.setOnClickListener(view -> {
            String adi = etAdi.getText().toString();
            fillListViewByIsim(adi);
        });
        fillListViewByIsim("");
        return dialog;
    }

    public void hidePopup() {
        popupBuilder.hide();
        StaticUtils.iMain.hideProgressBar();
    }

    public void fillListViewByIsim(String isim) {
        if (personelDtoList == null || personelDtoList.size() == 0) {
            fillPersonelDtoList();
        } else {
            if (isim == null || "".equals(isim)) {
                PersonelAdapter adapter = new PersonelAdapter(personelDtoList, getContext());
                lvPersonel.setAdapter(adapter);
            } else {
                List<PersonelDto> collect = Stream.of(personelDtoList).filter(p -> p.getIsim() != null && p.getIsim().toLowerCase().contains(isim.toLowerCase())).collect(Collectors.toList());
                PersonelAdapter adapter = new PersonelAdapter(collect, getContext());
                lvPersonel.setAdapter(adapter);
            }
        }
    }

    public void fillPersonelDtoList() {
        if (personelDtoList != null && personelDtoList.size() > 0)
            return;

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "kullaniciAdi=" + StaticUtils.kullaniciAdi);
        Call<ResponseInfo<List<PersonelDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.findAllPbsPersonelBilgileriDtoByKullaniciAdi(getAuthorizationTicket(), requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<PersonelDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<PersonelDto>>> call, Response<ResponseInfo<List<PersonelDto>>> response) {
                ResponseResult<List<PersonelDto>> responseResult = personelDtos -> {
                    personelDtoList = personelDtos;
                    PersonelAdapter adapter = new PersonelAdapter(personelDtoList, getContext());
                    lvPersonel.setAdapter(adapter);

                };
                responseResult.onReult(response);
            }

            @Override
            public void onFailure(Call<ResponseInfo<List<PersonelDto>>> call, Throwable t) {
                Log.e("TifPresenter", "findAllSbsMuhatap-->" + t.getMessage());
                StaticUtils.iMain.showWarningDialog("Error", "findAllSbsMuhatap-->" + t.getMessage());
            }
        });
    }

    public PersonelDto getSelectedPersonelDto() {
        return selectedPersonelDto;
    }

    public void setError(CharSequence error) {
        tietPersonelField.setError(error);
        if (error != null) {
            requestFocus(tietPersonelField);
        }
    }

    public CharSequence getError() {
        return tietPersonelField.getError();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            Activity activity = (Activity) getContext();
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}

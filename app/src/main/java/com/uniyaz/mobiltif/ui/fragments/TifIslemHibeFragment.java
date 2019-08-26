package com.uniyaz.mobiltif.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;
import com.uniyaz.mobiltif.R;
import com.uniyaz.mobiltif.data.domain.ResponseInfo;
import com.uniyaz.mobiltif.data.dto.MuhatapDto;
import com.uniyaz.mobiltif.data.dto.TifIslemHibeDto;
import com.uniyaz.mobiltif.databinding.FragmentTifHibeBinding;
import com.uniyaz.mobiltif.iface.ITifIslem;
import com.uniyaz.mobiltif.pattern.PopupBuilder;
import com.uniyaz.mobiltif.presenter.ResponseResult;
import com.uniyaz.mobiltif.retrofit.RetrofitInterface;
import com.uniyaz.mobiltif.ui.adapters.MuhatapAdapter;
import com.uniyaz.mobiltif.utils.StaticUtils;
import com.uniyaz.mobiltif.viewmodel.TifIslemHibeViewModel;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.uniyaz.mobiltif.utils.StaticUtils.getAuthorizationForTest;
import static com.uniyaz.mobiltif.utils.StaticUtils.muhatapDtoList;

public class TifIslemHibeFragment extends Fragment implements ITifIslem<TifIslemHibeDto> {
    TifIslemHibeViewModel islemHibeViewModel;
    ListView lvMuhatap;
    SwipeDismissDialog dismissDialog;
    PopupBuilder popupBuilder;

    public TifIslemHibeFragment() {
        // Required empty public constructor
    }


    public static TifIslemHibeFragment newInstance() {
        TifIslemHibeFragment fragment = new TifIslemHibeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        islemHibeViewModel = new TifIslemHibeViewModel();
        MuhatapDto muhatapDto = new MuhatapDto();
        muhatapDto.setIsim("İRFAN HACI BILGIN OZKORKMAZ");
        muhatapDto.setSbsMuhatapId(30023l);
        islemHibeViewModel.setMuhatap(muhatapDto);
        FragmentTifHibeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tif_hibe, container, false);
        binding.setViewModel(islemHibeViewModel);
        binding.setFragment(this);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public TifIslemHibeDto getIslemDto() {
        TifIslemHibeDto dto = new TifIslemHibeDto();
        dto.setIslemTarihi(islemHibeViewModel.getIslemTarihi());
        dto.setIdMuhatap(islemHibeViewModel.getMuhatap().getSbsMuhatapId());
        dto.setDayanakBelgeTarihi(islemHibeViewModel.getDayanakBelgeTarihi());
        dto.setAciklama(islemHibeViewModel.getAciklama());
        return dto;
    }
    View dialog;
    public void onClicEtMuhatap() {

        View dialog = LayoutInflater.from(getActivity()).inflate(R.layout.popup_call_muhatap, null);
        TextView etAdi = dialog.findViewById(R.id.etAdi);
        Button btnCall = dialog.findViewById(R.id.btnCallMuhatap);
        lvMuhatap = dialog.findViewById(R.id.lvMuhatapList);
        lvMuhatap.setOnItemClickListener((parent, view, position, id) -> {
            MuhatapDto item = (MuhatapDto) lvMuhatap.getAdapter().getItem(position);
            islemHibeViewModel.setMuhatap(item);
            popupBuilder.hide();
            //dismissDialog.dismiss();

        });
        btnCall.setOnClickListener(view -> {
            String adi = etAdi.getText().toString();
            if (adi == null || "".equals(adi))
                return;
            fillAllMuhatapDto(adi);
        });

        popupBuilder = new PopupBuilder(dialog,getView());
        popupBuilder.show();

       // dismissDialog = new SwipeDismissDialog.Builder(getActivity())
         //       .setView(dialog)
           //     .build()
             //   .show();

        StaticUtils.iMain.showProgressBar();

    }

    public void fillAllMuhatapDto(String adi) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), "adi=" + adi);
        Call<ResponseInfo<List<MuhatapDto>>> responseInfoCall = RetrofitInterface.retrofitInterface.findAllSbsMuhatap(getAuthorizationForTest(), requestBody);
        responseInfoCall.enqueue(new Callback<ResponseInfo<List<MuhatapDto>>>() {
            @Override
            public void onResponse(Call<ResponseInfo<List<MuhatapDto>>> call, Response<ResponseInfo<List<MuhatapDto>>> response) {
                ResponseResult<List<MuhatapDto>> responseResult = responseDto -> {
                    StaticUtils.muhatapDtoList = responseDto;

                    MuhatapAdapter adapter = new MuhatapAdapter(muhatapDtoList, getActivity());
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
}

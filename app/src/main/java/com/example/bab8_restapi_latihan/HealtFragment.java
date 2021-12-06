package com.example.bab8_restapi_latihan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup;
import androidx.annotation.NonNull; import androidx.annotation.Nullable; import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager; import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import retrofit2.Call; import retrofit2.Callback; import retrofit2.Response;

class HealthFragment extends Fragment {
    String api_key = "e244549dd4bf43f99947773a13288406";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="id";
    private RecyclerView recyclerViewshealth;
    private String category="health";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.healtfragment,null);

        recyclerViewshealth=v.findViewById(R.id.recyclerhealth);
        modelClassArrayList = new ArrayList<>();
        recyclerViewshealth.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewshealth.setAdapter(adapter);
        findNews();
        return v;
    }
    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api_key).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());                     adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}


package com.jeffrey.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jeffrey.myapplication.databinding.ImgurCellBinding;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<BoundViewHolder<ImgurCellBinding>> {

    private ImgurService imgur;
    private int page = 0;
    private String query = "cat";
    private List<SearchItem> values = new ArrayList<>();

    public SearchResultAdapter() {
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor())
            .addInterceptor(chain -> {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                                            .addHeader("Authorization", "Client-ID 497f6a3cb01e50f")
                                            .build();
                return chain.proceed(newRequest);
            })
            .build();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        imgur = retrofit.create(ImgurService.class);
    }

    public void setQuery(String query) {
        this.query = query;
        this.page = 0;
        this.values.clear();
        loadNextPage();
    }

    public void loadNextPage() {
        Call<SearchResult> results = imgur.search(page, query);
        results.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                SearchResult body = response.body();
                if (body != null) {
                    values.addAll(body.data);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });
        page++;
    }

    @Override
    public BoundViewHolder<ImgurCellBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.imgur_cell, parent, false);
        return new BoundViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(BoundViewHolder<ImgurCellBinding> holder, int position) {
        SearchItem item = values.get(position);
        holder.binding.setData(item);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}

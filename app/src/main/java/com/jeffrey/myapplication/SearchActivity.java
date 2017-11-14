package com.jeffrey.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jeffrey.myapplication.databinding.ActivitySearchBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;

import java.util.concurrent.TimeUnit;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();
    private static final int SPAN_COUNT = 2;

    ActivitySearchBinding binding;
    SearchResultAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new SearchResultAdapter();

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        binding.recyclerView.setAdapter(adapter);

        binding.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });

        RxSearchView.queryTextChanges(binding.searchView)
                    .debounce(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<CharSequence>() {
                        @Override
                        public void onNext(CharSequence charSequence) {
                            Log.d(TAG, "search query changed: " + charSequence);
                            adapter.setQuery(charSequence.toString());
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "search query completed");
                            KeyboardUtil.closeSoftKeyboard(SearchActivity.this);
                        }
                    });

        adapter.loadNextPage();
    }
}

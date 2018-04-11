package com.beesightsoft.training6.domain;

/**
 * Created by Dy Dy on 09/04/18.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.beesightsoft.training5.R;
import com.beesightsoft.training6.factory.RetrofitClient;
import com.beesightsoft.training6.service.model.Post;
import com.beesightsoft.training6.service.model.RestPostService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OthersActivity extends AppCompatActivity {

    private TextView responseTextView;

    private RestPostService restPostService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        final EditText etTitle = (EditText) findViewById(R.id.activity_others_et_title);
        final EditText etBody = (EditText) findViewById(R.id.activity_others_et_body);
        Button btnPost = (Button) findViewById(R.id.activity_others_btn_post);
        Button btnPut = (Button) findViewById(R.id.activity_others_btn_put);
        Button btnDelete = (Button) findViewById(R.id.activity_others_btn_delete);
        responseTextView = (TextView) findViewById(R.id.activity_others_tv_response);

        restPostService = RetrofitClient.getAPIService(RetrofitClient.provideRetrofit());

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String body = etBody.getText().toString().trim();
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    sendPost(title, body);
                }
            }
        });
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String body = etBody.getText().toString().trim();
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    updatePost(title, body);
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost();
            }
        });
    }

    public void deletePost() {
        restPostService.deletePost(1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                int n = response.code();
                Toast.makeText(OthersActivity.this, "delete object", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("tag", "delete submitted to API." + response.body().toString());
                    Toast.makeText(OthersActivity.this, "delete object", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("tag", "Unable to submit delete to API.");
            }
        });
    }

    public void updatePost(String title, String body) {
        restPostService.updatePost(1, title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("tag", "post submitted to API." + response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("tag", "Unable to submit post to API.");
            }
        });
    }

    public void sendPost(String title, String body) {
        restPostService.savePost(title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("tag", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("tag", "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {
        if (responseTextView.getVisibility() == View.GONE) {
            responseTextView.setVisibility(View.VISIBLE);
        }
        responseTextView.setText(response);
    }

}

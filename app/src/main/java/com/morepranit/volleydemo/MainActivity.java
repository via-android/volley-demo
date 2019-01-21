package com.morepranit.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    TextView tvData;
    NetworkImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tv_data);
        imageView = findViewById(R.id.imageView);

        ImageLoader loader = AppController.getInstance().getImageLoader();

        imageView.setImageUrl("https://i.pinimg.com/originals/0c/48/76/0c4876e490e1e4dc925cc09be057a5a5.jpg", loader);

        getData();
    }

    private void getData() {
        String url = "https://www.googleapis.com/books/v1/volumes?q=inspirational";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvData.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse: ", error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(request, "req_tag");
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppController.getInstance().cancelPendingRequests("req_tag");
    }
}

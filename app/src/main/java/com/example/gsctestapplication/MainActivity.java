package com.example.gsctestapplication;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsctestapplication.adaptors.ProductAdaptor;
import com.example.gsctestapplication.models.ProductItems;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ProductItems> mDataItems = new ArrayList<>();

    private RecyclerView recyclerView;
    private TextView toolbar_title;
    private Button btn_product;
    private CoordinatorLayout rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolBar();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        } else {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        btn_product = (Button) findViewById(R.id.btn_product);
        btn_product.setText(getResources().getString(R.string.add_product));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProductAdaptor(this, genData()));

    }

    private void setToolBar() {
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("Products");
    }

    private List<ProductItems> getData() {
        List<ProductItems> data = new ArrayList<>();
        String[] title = getResources().getStringArray(R.array.product_list);

        for (String s : title) {
            ProductItems currentItem = new ProductItems();
            currentItem.setProduct_name(s);
            data.add(currentItem);
        }
        return data;
    }

    private List<ProductItems> genData(){

        String[] product_list = getResources().getStringArray(R.array.product_list);
        for (String p : product_list) {
            ProductItems product = new ProductItems();
            product.setProduct_name(p);
            Log.d("IlaTestApp", "Product " + p);
            mDataItems.add(product);
        }
        return mDataItems;
    }
}

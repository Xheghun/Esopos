package com.xheghun.esopos.fragments;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xheghun.esopos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        //setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        toolbar.showOverflowMenu();
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.new_slide_btn:
                    Toast.makeText(this, "yo", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.export_to_excel_btn:
                    Toast.makeText(this, "yi", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mkt_plc_btn:
                    Toast.makeText(this, "yal", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.offline_sales_btn:
                    Toast.makeText(this, "yom", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
    }
}

package com.example.ddangnmarket.src.location;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.location.interfaces.LocationActivityView;
import com.example.ddangnmarket.src.location.models.RequestLocation;
import com.example.ddangnmarket.src.location.models.Result;
import com.example.ddangnmarket.src.login.LoginActivity;
import com.example.ddangnmarket.src.main.MainActivity;
import com.example.ddangnmarket.src.nickname.NicknameActivity;

import java.util.ArrayList;

import static com.example.ddangnmarket.src.ApplicationClass.sSharedPreferences;

public class LocationActivity extends BaseActivity implements LocationActivityView {

    ListView mLvLocationList;
    EditText mEtLocation;
    Button mBtnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getLocaton("");

        mEtLocation = findViewById(R.id.location_et_search);
        mEtLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                getLocaton(s.toString());
            }
        });

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mBtnLocation = findViewById(R.id.location_btn_search);
        mBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            0);
                } else {

                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            1000,
                            1,
                            gpsLocationListener);
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            1000,
                            1,
                            gpsLocationListener);
                }
            }
        });
    }

    public void getLocaton(String location) {
        final LocationService locationService = new LocationService(this);
        locationService.getLocation(location);
    }

    public void locationChange(int locationNo) {
        LocationService locationService = new LocationService(this);
        RequestLocation requestLocation = new RequestLocation();
        requestLocation.setLocationNo(locationNo);
        locationService.postLocation(requestLocation);
    }

    @Override
    public void validateLocationSuccess(boolean isSuccess, int code, String message, ArrayList<Result> results) {
        hideProgressDialog();
        if (isSuccess) {
            if (code == 100) {
                showCustomToast(message);
                LocationAdapter locationAdapter = new LocationAdapter(results, this);
                mLvLocationList = findViewById(R.id.location_lv);
                mLvLocationList.setAdapter(locationAdapter);
            } else if (code == 101) {
                LocationAdapter locationAdapter = new LocationAdapter(results, this);
                mLvLocationList = findViewById(R.id.location_lv);
                mLvLocationList.setAdapter(locationAdapter);
            }
        } else {
        }
    }

    @Override
    public void validateLocationFailure(String message) {
        showCustomToast(message);
    }

    @Override
    public void validateLocationChangeSuccess(boolean isSuccess, int code, String message) {
        showCustomToast(message);
    }

    @Override
    public void validateLocationChangeFailure(String message) {
        showCustomToast(message);
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            System.out.println("위치정보 : " + provider + "\n" +
                    "위도 : " + longitude + "\n" +
                    "경도 : " + latitude + "\n" +
                    "고도  : " + altitude);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
}

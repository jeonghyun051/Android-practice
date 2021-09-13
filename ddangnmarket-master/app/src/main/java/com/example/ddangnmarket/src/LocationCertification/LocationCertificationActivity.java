package com.example.ddangnmarket.src.LocationCertification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ddangnmarket.R;
import com.example.ddangnmarket.src.BaseActivity;
import com.example.ddangnmarket.src.LocationCertification.interfaces.LocationActivityView;
import com.example.ddangnmarket.src.LocationCertification.models.RequestLocation;
import com.example.ddangnmarket.src.location.LocationActivity;
import com.example.ddangnmarket.src.login.LoginService;
import com.example.ddangnmarket.src.login.models.RequestMessage;

public class LocationCertificationActivity extends BaseActivity implements LocationActivityView {
    Button mBtnCert, mBtnSubmit;
    TextView mTvGetLocation;
    double x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_certification);

        mBtnCert = findViewById(R.id.location_btn_cert);
        mTvGetLocation = findViewById(R.id.location_tv_get_location);
        mBtnSubmit = findViewById(R.id.location_btn_submit);
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mBtnCert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LocationCertificationActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
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
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CertLocation();
            }
        });
    }

    public void CertLocation() {
        LocationService locationService = new LocationService(this);
        RequestLocation requestLocation = new RequestLocation();
        System.out.println(x + "" + y);
        requestLocation.setX_axis(x);
        requestLocation.setY_axis(y);
        locationService.postLocation(requestLocation);
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            mTvGetLocation.setText("위도 : " + latitude + ", 경도 : " + longitude);
            x = latitude;
            y = longitude;
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

    @Override
    public void validateLocationSuccess(boolean isSuccess, int code, String message) {
        showCustomToast(message);
    }

    @Override
    public void validateLocationFailure(String message) {
        showCustomToast(message);
    }
}

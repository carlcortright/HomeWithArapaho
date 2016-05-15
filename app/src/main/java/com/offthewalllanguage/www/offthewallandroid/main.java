package com.offthewalllanguage.www.offthewallandroid;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.offthewalllanguage.www.offthewallandroid.Preview.CameraSource;
import com.offthewalllanguage.www.offthewallandroid.Preview.CameraSourcePreview;

import java.io.IOException;

public class main extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    * Fragment that stores and starts the scanner.
    */
    public static class ScannerFragment extends Fragment{

        public static final String TAG = ScannerFragment.class.getSimpleName();

        CameraSourcePreview mPreview;
        CameraSource mCameraSource;

        private boolean autoFocus = true;

        public ScannerFragment(){}

        public static ScannerFragment newInstance() {
            ScannerFragment fragment = new ScannerFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.scanner, container, false);
            //For some reason, this isnt
            Activity activity = getActivity();
            mPreview = (CameraSourcePreview) activity.findViewById(R.id.preview);

            // Check for the camera permission before accessing the camera.  If the
            // permission is not granted yet, request permission.
            int rc = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
            if (rc == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Creating the camera source...");
                createCameraSource();
            } else {
                Log.v(TAG, "Requesting camera permission...");
                requestCameraPermission();
            }
            startCameraSource();
            return rootView;
        }

        private void requestCameraPermission(){
            final int RC_HANDLE_CAMERA_PERM = 2; // Permission request code
            Log.w(TAG, "Camera permission is not granted. Requesting permission");

            final String[] permissions = new String[]{Manifest.permission.CAMERA};

            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(getActivity(), permissions, RC_HANDLE_CAMERA_PERM);
                return;
            }

            final Activity thisActivity = getActivity();

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(thisActivity, permissions,
                            RC_HANDLE_CAMERA_PERM);
                }
            };

            Snackbar.make(mPreview, R.string.permission_camera_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, listener)
                    .show();
        }

        private void createCameraSource(){
            BarcodeDetector qrDetector = new BarcodeDetector.Builder(getActivity()).setBarcodeFormats(Barcode.QR_CODE).build(); //Create the QR code detector

            //Creates and starts the camera
            CameraSource.Builder builder = new CameraSource.Builder(getActivity(), qrDetector)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1600, 1024)
                    .setRequestedFps(17.0f);

            // make sure that auto focus is an available option
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                builder = builder.setFocusMode(
                        autoFocus ? Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE : null);
            }

            mCameraSource = builder.setFlashMode(null).build();
        }

        private void startCameraSource() throws SecurityException {
            final int RC_HANDLE_GMS = 9001;
            // check that the device has play services available.
            int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
            if (code != ConnectionResult.SUCCESS) {
                Dialog dlg =
                        GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), code, RC_HANDLE_GMS);
                dlg.show();
            }

            if (mCameraSource != null) {
                try {
                    Log.v(TAG, "mCameraSource: " + mCameraSource.toString());
                    Log.v(TAG, "mPreview: " + mPreview.toString());
                    mPreview.start(mCameraSource);
                } catch (IOException e) {
                    Log.e(TAG, "Unable to start camera source.", e);
                    mCameraSource.release();
                    mCameraSource = null;
                }
            }
        }

    }
    /*
    * Fragment that holds all of the store items.
    */
    public static class StoreFragment extends Fragment {

        public StoreFragment(){}

        public static StoreFragment newInstance() {
            StoreFragment fragment = new StoreFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.store, container, false);
            Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.store_toolbar);
            toolbar.setTitle("Language Learning Store");
            toolbar.setTitleTextColor(0xFFFFFFFF);
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(toolbar);
            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return ScannerFragment.newInstance();
                case 1:
                    return StoreFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

    }
}

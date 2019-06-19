package co.acidlabs.lanala;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import co.acidlabs.lanala.adapters.SectionsPagerAdapter;
import co.acidlabs.lanala.helpers.Utils;
import co.acidlabs.lanala.models.User;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage, name, code;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    BottomNavigationView navigation;
    private int lastItem = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    lastItem = 0;
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    lastItem = 1;
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    lastItem = 2;
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;

                case R.id.navigation_contact:
                    //mViewPager.setCurrentItem(3);

                    if (Utils.selectedContract.getNumTelephoneConseille().length() != 0) {

                        showAlert(true);

                    } else {

                        showAlert(false);

                    }


                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    public void showAlert(boolean status) {

        if (status) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeActivity.this);

            builder1.setTitle(Utils.selectedContract.getNomConseille());

            if (Utils.selectedContract.getNomConseille().length() == 0) {
                builder1.setTitle("Nom indisponible");
            }

            builder1.setMessage(Utils.selectedContract.getNumTelephoneConseille());
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Appeler",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            //mViewPager.setCurrentItem(0);
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + Utils.selectedContract.getNumTelephoneConseille()));
                            if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(callIntent);

                            switch (lastItem) {
                                case 0:
                                    navigation.setSelectedItemId(R.id.navigation_home);
                                    break;

                                case 1:
                                    navigation.setSelectedItemId(R.id.navigation_dashboard);
                                    break;

                                case 2:
                                    navigation.setSelectedItemId(R.id.navigation_notifications);
                                    break;

                                case 3:
                                    navigation.setSelectedItemId(R.id.navigation_contact);
                                    break;

                            }


                        }
                    });

            builder1.setNegativeButton(
                    "Annuler",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                            switch (lastItem) {
                                case 0:
                                    navigation.setSelectedItemId(R.id.navigation_home);
                                    break;

                                case 1:
                                    navigation.setSelectedItemId(R.id.navigation_dashboard);
                                    break;

                                case 2:
                                    navigation.setSelectedItemId(R.id.navigation_notifications);
                                    break;

                                case 3:
                                    navigation.setSelectedItemId(R.id.navigation_contact);
                                    break;

                            }
                            //mViewPager.setCurrentItem(0);
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }

        else {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeActivity.this);
            builder1.setTitle("Erreur");
            builder1.setMessage("Vous n'avez aucun conseiller ");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();


                            switch (lastItem) {
                                case 0:
                                    navigation.setSelectedItemId(R.id.navigation_home);
                                    break;

                                case 1:
                                    navigation.setSelectedItemId(R.id.navigation_dashboard);
                                    break;

                                case 2:
                                    navigation.setSelectedItemId(R.id.navigation_notifications);
                                    break;

                                case 3:
                                    navigation.setSelectedItemId(R.id.navigation_contact);
                                    break;

                            }


                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();

        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mTextMessage = (TextView) findViewById(R.id.message);
        name = findViewById(R.id.name);
        code = findViewById(R.id.code);



        name.setText(Utils.selectedContract.getNomProduit());
        code.setText(Utils.selectedContract.getNomAssuree()+"        "+Utils.selectedContract.getDateNaissanceAssure());
        navigation  = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.content);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        lastItem = position;
                        break;

                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        lastItem = position;
                        break;

                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                        lastItem = position;
                        break;

                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_contact);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }


    @Override
    public void onBackPressed () {

        super.onBackPressed();
        Utils.selectedBox.clear();

    }



    public void onBackClicked (View v) {

        Utils.selectedBox.clear();

        finish();
    }


    public void showMenu(View view) {
        openOptionsMenu();
    }
}

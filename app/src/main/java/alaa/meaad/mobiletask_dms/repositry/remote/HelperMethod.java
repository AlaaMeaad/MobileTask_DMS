package alaa.meaad.mobiletask_dms.repositry.remote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.service.autofill.UserData;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class HelperMethod {

    private static ProgressDialog checkDialog;
    public static AlertDialog alertDialog;
    public static Snackbar snackbar;
    private static SharedPreferences sharedPreferences = null;
    private static String USER_DATA = "USER_DATA";

    //post retfit
    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {
        if (pathImageFile != null) {
            File file = new File(pathImageFile);

            RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);

            MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);

            return Imagebody;
        } else {
            return null;
        }
    }


    //RequestBody
    public static RequestBody convertToRequestBody(String part) {
        try {
            if (!part.equals("")) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), part);
                return requestBody;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //img url
    public static void onLoadImageFromUrl(ImageView imageView, String URl, Context context) {
        Glide.with(context)
                .load(URl)
                .into(imageView);
    }





    public static void showProgressDialog(Activity activity, String title) {
        try {
            checkDialog = new ProgressDialog(activity);
            checkDialog.setMessage(title);
            checkDialog.setIndeterminate(false);
            checkDialog.setCancelable(false);
            checkDialog.show();

        } catch (Exception e) {

        }
    }


    //display dialog
    public static void dismissProgressDialog() {
        try {
            if (checkDialog != null && checkDialog.isShowing()) {
                checkDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }


    //call fragment
    public static void ReplaceFragment(Fragment fragment, FragmentManager supportFragmentManager, int container_id
            , TextView toolbarTitle, String title) {

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        transaction.replace(container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }

    }
   /* public static void getDonation(Activity activity, ApiServers apiServices, String donationRequestId, String ApiToken, boolean fromDonation) {
        showProgressDialog(activity, "برجاء الانتظار ...");
        apiServices.getDonationDetails(ApiToken, donationRequestId).enqueue(new Callback<PostsData>() {
            @Override
            public void onResponse(Call<DonationDetails> call, Response<DonationDetails> response) {
                try {
                    dismissProgressDialog();
                    if (response.body().getStatus() == 1) {

                        HomeActivity navigationActivity = (HomeActivity) activity;
                        navigationActivity.setCount();
                        navigationActivity.setTitle(activity.getString(R.string.donation) + response.body().getHotpointData().getPatientName());

                        DonationDetailsFragment donationDetails = new DonationDetailsFragment();
                        donationDetails.donationData = response.body().getHotpointData();
                        donationDetails.fromDonation = fromDonation;
                        HelperMethod.replaceFragment(navigationActivity.getSupportFragmentManager(), R.id.Content_Frame_Replace, donationDetails);
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<DonationDetails> call, Throwable t) {
                dismissProgressDialog();
            }
        });
    }*/

    // a5fy elkeypord
    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }





}

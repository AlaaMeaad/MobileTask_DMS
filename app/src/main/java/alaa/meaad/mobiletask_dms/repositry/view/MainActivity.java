package alaa.meaad.mobiletask_dms.repositry.view;



import android.os.Bundle;
;

import alaa.meaad.mobiletask_dms.R;

import alaa.meaad.mobiletask_dms.repositry.remote.HelperMethod;
import alaa.meaad.mobiletask_dms.repositry.view.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperMethod.ReplaceFragment(new HomeFragment() , getSupportFragmentManager() , R.id.activity_main, null ,null);


    }



}
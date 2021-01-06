package alaa.meaad.mobiletask_dms.repositry.view.base;

import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superonBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}

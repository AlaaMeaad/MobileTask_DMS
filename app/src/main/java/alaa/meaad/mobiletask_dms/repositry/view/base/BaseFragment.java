package alaa.meaad.mobiletask_dms.repositry.view.base;

import androidx.fragment.app.Fragment;

import alaa.meaad.mobiletask_dms.repositry.view.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    public BaseActivity baseActivity;
    public MainActivity homeActivity;

    public void initFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }


    public void setUpHomeActivity() {
        homeActivity = (MainActivity) getActivity();
    }
    public void onBack() {
        baseActivity.superonBackPressed();
    }

}

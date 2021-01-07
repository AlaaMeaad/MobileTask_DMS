package alaa.meaad.mobiletask_dms.repositry.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import alaa.meaad.mobiletask_dms.R;
import alaa.meaad.mobiletask_dms.repositry.remote.HelperMethod;
import alaa.meaad.mobiletask_dms.repositry.view.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPicFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imageView;
    public String url ;
    View mRootView;

    public ViewPicFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ViewPicFragment newInstance(String param1, String param2) {
        ViewPicFragment fragment = new ViewPicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mRootView =  inflater.inflate(R.layout.fragment_view_pic, container, false);
        if (getArguments() != null) {
            url = getArguments().getString("url");
        }
//        Toast.makeText(getContext(), url , Toast.LENGTH_LONG).show();
        initFragment();
        imageView = mRootView.findViewById(R.id.image_post);
        Toast.makeText(getContext(), "جارى فتح الصورة ..." , Toast.LENGTH_SHORT).show();
        HelperMethod.onLoadImageFromUrl(imageView, url, getContext());

        return mRootView ;

    }

    @Override
    public void onBack() {
//        HelperMethod.ReplaceFragment(new HomeFragment() , getFragmentManager() , R.id.activity_main, null ,null);
    super.onBack();
    }
}

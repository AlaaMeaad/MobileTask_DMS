package alaa.meaad.mobiletask_dms.repositry.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alaa.meaad.mobiletask_dms.R;
import alaa.meaad.mobiletask_dms.repositry.model.home.DataHome;
import alaa.meaad.mobiletask_dms.repositry.model.home.Home;
import alaa.meaad.mobiletask_dms.repositry.model.profile.Profile;
import alaa.meaad.mobiletask_dms.repositry.remote.ApiService;
import alaa.meaad.mobiletask_dms.repositry.remote.DataManager;
import alaa.meaad.mobiletask_dms.repositry.remote.DataManagerImpl;
import alaa.meaad.mobiletask_dms.repositry.remote.HelperMethod;
import alaa.meaad.mobiletask_dms.repositry.remote.RetrofitCallback;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static alaa.meaad.mobiletask_dms.repositry.remote.HelperMethod.dismissProgressDialog;
import static com.google.android.gms.location.ActivityRecognition.getClient;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView postsFragmentRvPosts;

    // TODO: Rename and change types of parameters

    private ApiService apiServers;
    private LinearLayoutManager linearLayoutManager;
    private List<DataHome> postHome = new ArrayList<>();
    private PostsAdapter postAdapter;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private int currentPage = 1;
    private DataManagerImpl dataManager;
    CircleImageView circleImageView;
    View mRootView;
    TextView tv_name, tv_city , tv_bio , tv_post , tv_folowes , tv_following;



    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        postsFragmentRvPosts = mRootView.findViewById(R.id.posts_fragment_rv_posts);
        circleImageView = mRootView.findViewById(R.id.iv_image);
        tv_city = mRootView.findViewById(R.id.city);
        tv_bio = mRootView.findViewById(R.id.bio);
        tv_name = mRootView.findViewById(R.id.tv_name);
        tv_post = mRootView.findViewById(R.id.tv_post);
        tv_following = mRootView.findViewById(R.id.tv_folowing);
        tv_folowes = mRootView.findViewById(R.id.tv_followers);
        dataManager = new DataManagerImpl();
        getprofile();

initRecyclerView();
        return mRootView;

    }
    private void initRecyclerView() {
//        linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity() , 3);
        postsFragmentRvPosts.setLayoutManager(gridLayoutManager);
        postAdapter = new PostsAdapter(getActivity(), getActivity(), postHome);
        postsFragmentRvPosts.setAdapter(postAdapter);
        getAllProperty();

    }


    private void getprofile() {
//        HelperMethod.showProgressDialog(getActivity(), "جارى التحميل");
        dataManager.getProfile(new RetrofitCallback() {
            @Override
            public void onSuccess(Object response) {
                Profile profile = (Profile) response;
                tv_name.setText(profile.getData().getFullName());
                HelperMethod.onLoadImageFromUrl(circleImageView, profile.getData().getProfilePicture(), getContext());
                tv_bio.setText(profile.getData().getBio());
                tv_city.setText(profile.getData().getLocation());
                tv_post.setText(profile.getData().getCounts().getPosts().toString());
                tv_folowes.setText(profile.getData().getCounts().getFollowers().toString());
                tv_following.setText(profile.getData().getCounts().getFollowing().toString());


        }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onErrorCode(Response<Object> response) {

            } });

    }


            private void getAllProperty() {
                HelperMethod.showProgressDialog(getActivity(), "جارى التحميل");
                dataManager.getHome(new RetrofitCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        HelperMethod.dismissProgressDialog();
                        Home properties = (Home) response;
                        if (properties.getData() != null) {
                            if (properties.getData().size() > 0) {
                                if (currentPage == 1) {
                                    if (properties != null && properties.getData().size() > 0) {
                                        postHome.clear();
                                        postHome.addAll(properties.getData());
                                        postAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        HelperMethod.dismissProgressDialog();
                        Log.w("error", throwable.getMessage());
                    }

                    @Override
                    public void onErrorCode(Response<Object> response) {
                        HelperMethod.dismissProgressDialog();
                        Log.w("error", "here**");
                    }
                });
            }





//    private void getProfile() {
////        HelperMethod.showProgressDialog(getActivity(), "جارى التحميل");
//        dataManager.getProfile(new RetrofitCallback() {
//            @Override
//            public void onSuccess(Object response) {
//                HelperMethod.dismissProgressDialog();
//                Profile profile = (Profile) response;
//
//                    Toast.makeText(getContext() , "dfdfdf" , Toast.LENGTH_LONG).show();
//                    tv_name.setText(profile.getData().getFullName());
////                    HelperMethod.onLoadImageFromUrl(circleImageView, profile.getData().getProfilePicture(), getContext());
//
//
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                HelperMethod.dismissProgressDialog();
//                Log.w("error", throwable.getMessage());
//                Toast.makeText(getContext() , "2225" , Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onErrorCode(Response<Object> response) {
//                HelperMethod.dismissProgressDialog();
//                Toast.makeText(getContext() , "6666666" , Toast.LENGTH_LONG).show();
//
//                Log.w("error", "here**");
//            }
//        });
//    }



}
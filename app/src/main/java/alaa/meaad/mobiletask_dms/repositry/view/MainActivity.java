package alaa.meaad.mobiletask_dms.repositry.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alaa.meaad.mobiletask_dms.R;
import alaa.meaad.mobiletask_dms.repositry.model.home.DataHome;
import alaa.meaad.mobiletask_dms.repositry.model.home.Home;
import alaa.meaad.mobiletask_dms.repositry.model.profile.Profile;
import alaa.meaad.mobiletask_dms.repositry.remote.DataManagerImpl;
import alaa.meaad.mobiletask_dms.repositry.remote.HelperMethod;
import alaa.meaad.mobiletask_dms.repositry.remote.RetrofitCallback;
import alaa.meaad.mobiletask_dms.repositry.view.base.BaseActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

import static alaa.meaad.mobiletask_dms.repositry.remote.HelperMethod.ReplaceFragment;

public class MainActivity extends BaseActivity  implements PostsAdapter.PostsAdapterListener , View.OnClickListener {

    private List<DataHome> postHome = new ArrayList<>();
    private PostsAdapter postAdapter;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private int currentPage = 1;
    private DataManagerImpl dataManager;
    CircleImageView circleImageView;
    View mRootView;
    TextView tv_name, tv_city , tv_bio , tv_post , tv_folowes , tv_following;
    private RecyclerView postsFragmentRvPosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        HelperMethod.ReplaceFragment(new HomeFragment() , getSupportFragmentManager() , R.id.activity_main, null ,null);

        postsFragmentRvPosts = findViewById(R.id.posts_fragment_rv_posts);
        circleImageView = findViewById(R.id.iv_image);
        tv_city = findViewById(R.id.city);
        tv_bio = findViewById(R.id.bio);
        tv_name = findViewById(R.id.tv_name);
        tv_post = findViewById(R.id.tv_post);
        tv_following = findViewById(R.id.tv_folowing);
        tv_folowes = findViewById(R.id.tv_followers);
        dataManager = new DataManagerImpl();
//        toolbar = mRootView.findViewById(R.id.toolbar);
        getprofile();

        initRecyclerView();


    }
    private void initRecyclerView() {
//        linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this , 3);
        postsFragmentRvPosts.setLayoutManager(gridLayoutManager);
        postAdapter = new PostsAdapter(this, this, postHome , this);
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
                HelperMethod.onLoadImageFromUrl(circleImageView, profile.getData().getProfilePicture(), getApplication());
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
        HelperMethod.showProgressDialog(this, "جارى التحميل");
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

    @Override
    public void onClickItem(int position, String id) {
//        ReplaceFragment(new ViewPicFragment() , getFragmentManager() , R.id.activity_main, null ,null);
//        Toast.makeText(getContext() , "kkkkkk" + id , Toast.LENGTH_LONG).show();
//        url = id;
        ViewPicFragment actavite =
                new ViewPicFragment();
        Bundle b = new Bundle();
        b.putString("url", id);
        actavite.setArguments(b);
        ReplaceFragment(actavite, this.getSupportFragmentManager(), R.id.activity_main, null, null);
    }

    @Override
    public void onClick(View v) {

    }
}
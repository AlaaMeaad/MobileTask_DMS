package alaa.meaad.mobiletask_dms.repositry.view;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import alaa.meaad.mobiletask_dms.R;
import alaa.meaad.mobiletask_dms.repositry.model.home.DataHome;
import alaa.meaad.mobiletask_dms.repositry.remote.HelperMethod;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {



    private Activity activity;
    private Context context;
    private PostsAdapterListener mPostsAdapterListener;

    private List<DataHome> postDataList = new ArrayList<>();

    public PostsAdapter(Activity activity, Context context, List<DataHome> postDataList ,PostsAdapterListener mPostsAdapterListener ) {
        this.activity = activity;
        this.context = context;
        this.postDataList = postDataList;
        this.mPostsAdapterListener = mPostsAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        // setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

//        holder.articlesAdapterTvTitle.setText(postDataList.get(position).getTitle());
        HelperMethod.onLoadImageFromUrl(holder.articlesAdapterIvPostImage, postDataList.get(position).getImage(), activity);

    }



    @Override
    public int getItemCount() {
        return postDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;

//        @BindView(R.id.articles_adapter_tv_title)
//        TextView articlesAdapterTvTitle;
        @BindView(R.id.articles_adapter_iv_post_image)
        ImageView articlesAdapterIvPostImage;




        public ViewHolder(View itemview) {
            super(itemview);
            view = itemview;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPostsAdapterListener.onClickItem(getAdapterPosition(), postDataList.get(getAdapterPosition()).getImage());
                }
            });
            ButterKnife.bind(this, view);
        }
    }
    public interface PostsAdapterListener{
        void onClickItem( int position , String id);
    }
}
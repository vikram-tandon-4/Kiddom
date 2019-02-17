package kiddomchallenge.vikramtandonapps.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kiddomchallenge.vikramtandonapps.com.R;
import kiddomchallenge.vikramtandonapps.com.model.ItemListElementModel;
import kiddomchallenge.vikramtandonapps.com.model.Result;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder> {

    private ArrayList<ItemListElementModel> searchData;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvDescription, tvArticle, tvWiki;
        CardView cvContainer;
        ImageView ivImage;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvWiki = (TextView) view.findViewById(R.id.tvWiki);
            cvContainer = (CardView) view.findViewById(R.id.cvContainer);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            tvArticle = (TextView) view.findViewById(R.id.tvArticle);
            ivImage = (ImageView) view.findViewById(R.id.ivImage);
        }

        public void clearAnimation() {
            cvContainer.clearAnimation();
        }
    }

    public SearchResultAdapter(ArrayList<ItemListElementModel> searchData, Context mContext) {
        this.searchData = searchData;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Result result = searchData.get(position).getResult();
        holder.tvName.setText(result.getName());

        if (TextUtils.isEmpty(result.getDescription())) {
            holder.tvDescription.setVisibility(View.GONE);
        } else {
            holder.tvDescription.setVisibility(View.VISIBLE);
            holder.tvDescription.setText(result.getDescription());
        }

        if (result.getDetailedDescription() == null || TextUtils.isEmpty(result.getDetailedDescription().getArticleBody())) {
            holder.tvArticle.setVisibility(View.GONE);
        } else {
            holder.tvArticle.setVisibility(View.VISIBLE);
            holder.tvArticle.setText(result.getDetailedDescription().getArticleBody());
        }


        if (result.getDetailedDescription() == null || TextUtils.isEmpty(result.getDetailedDescription().getUrl())) {
            holder.tvWiki.setVisibility(View.GONE);
        } else {
            holder.tvWiki.setVisibility(View.VISIBLE);
        }

        holder.tvWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(result.getDetailedDescription().getUrl()));

                mContext.startActivity(intent);
            }
        });

        if (result.getImage() == null || TextUtils.isEmpty(result.getImage().getContentUrl())) {
            holder.ivImage.setVisibility(View.GONE);
        } else {
            Picasso.get()
                    .load(result.getImage().getContentUrl())
                    .into(holder.ivImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.ivImage.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            holder.ivImage.setVisibility(View.GONE);
                        }
                    });
        }
        setAnimation(holder.cvContainer, position);
    }

    @Override
    public int getItemCount() {
        return searchData.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        (holder).clearAnimation();
    }
}

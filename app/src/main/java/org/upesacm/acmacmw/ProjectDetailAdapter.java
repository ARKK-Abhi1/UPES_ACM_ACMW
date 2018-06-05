package org.upesacm.acmacmw;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProjectDetailAdapter extends RecyclerView.Adapter<ProjectDetailAdapter.DetailViewHolder> {
    private Context mCtx;
    private List<ProjectDetail> detailList;

    public ProjectDetailAdapter(Context mCtx, List<ProjectDetail> detailList)
    {
        this.mCtx = mCtx;
        this.detailList = detailList;
    }
    @Override
    public DetailViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_projects_layout, null);
        return new DetailViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position){

        ProjectDetail projectDetail = detailList.get(position);
        holder.textView6.setText(projectDetail.getName());
        holder.textView7.setText(projectDetail.getDescription());
        holder.img.setImageDrawable(mCtx.getResources().getDrawable(projectDetail.getImage()));

    }
    @Override
    public int getItemCount() {
        return detailList.size();
    }
    class DetailViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView textView6;
        TextView textView7;

        public DetailViewHolder(View itemView) {
            super(itemView);

            img= itemView.findViewById(R.id.img);
            textView6= itemView.findViewById(R.id.textView6);
            textView7= itemView.findViewById(R.id.textView7);
        }
    }

};

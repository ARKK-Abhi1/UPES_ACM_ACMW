package org.upesacm.acmacmw;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProjectDetailAdapter extends RecyclerView.Adapter<ProjectDetailAdapter.DetailViewHolder>
                              implements View.OnClickListener{
    private Context mCtx;
    private List<ProjectDetail> detailList;
    FragmentManager fragmentManager;
    public ProjectDetailAdapter(Context mCtx, List<ProjectDetail> detailList,FragmentManager fm)
    {
        this.mCtx = mCtx;
        this.detailList = detailList;
        fragmentManager=fm;
    }
    @Override
    public DetailViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_projects_layout,parent,false);
        return new DetailViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position){

        ProjectDetail projectDetail = detailList.get(position);
        holder.textView6.setText(projectDetail.getName());
        holder.textView7.setText(projectDetail.getDescription());
        holder.img.setImageDrawable(mCtx.getResources().getDrawable(projectDetail.getImage()));

        holder.root.setOnClickListener(this);
        holder.textView6.setOnClickListener(this);
        holder.textView7.setOnClickListener(this);
        holder.img.setOnClickListener(this);
    }
    @Override
    public int getItemCount() {
        return detailList.size();
    }

    @Override
    public void onClick(View view) {
        System.out.println("on click inside recycler view called");
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.addToBackStack("ongoingprojectfragment");
        ft.add(R.id.frameLayout,ProjectDetailsFragment.newInstance(),"projectdetailsfragment");
        ft.commit();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder
    {
        View root;
        ImageView img;
        TextView textView6;
        TextView textView7;

        public DetailViewHolder(View itemView) {
            super(itemView);
            root=itemView;
            img= itemView.findViewById(R.id.img);
            textView6= itemView.findViewById(R.id.textView6);
            textView7= itemView.findViewById(R.id.textView7);
        }
    }

};

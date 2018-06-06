package org.upesacm.acmacmw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OngoingProjects extends AppCompatActivity {

    RecyclerView recyclerView;
    ProjectDetailAdapter adapter;
    List<ProjectDetail> detailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_projects);

        detailList= new ArrayList<>();
        recyclerView= (RecyclerView) findViewById(R.id.projects_recycler_view);

        detailList.add(new ProjectDetail(R.drawable.parking,"E-Parky","Parking automation app"));
        detailList.add(new ProjectDetail(R.drawable.scenery,"ABC","XYZ"));
        adapter = new ProjectDetailAdapter(this,detailList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}

package org.upesacm.acmacmw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    }
}

package org.upesacm.acmacmw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Alumni extends AppCompatActivity {

    RecyclerView recyclerView;
    AlumniDetailAdapter adapter;
    List<AlumniDetail> detailList;
    private DatabaseReference AlumniDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);

        final ImageView contactim = (ImageView) findViewById(R.id.contactim);
        ImageView linkedinim = (ImageView) findViewById(R.id.linkedinim);
        AlumniDatabase = FirebaseDatabase.getInstance().getReference().child("Alumni");
        AlumniDatabase.keepSynced(true);

        detailList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //detailList.add(new AlumniDetail(R.drawable.a1, "ABC", "App Head", "2016-2017"));
        //detailList.add(new AlumniDetail(R.drawable.a2, "ABCD", "Webmaster", "2016-2017"));

        //adapter= new AlumniDetailAdapter(this, detailList);
        //recyclerView.setAdapter(adapter);


    }


    @Override
    public void onStart() {
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference().child("Alumni");
        FirebaseRecyclerOptions<AlumniDetail> options = new FirebaseRecyclerOptions.Builder<AlumniDetail>().setQuery(query, AlumniDetail.class).build();
        FirebaseRecyclerAdapter<AlumniDetail, AlumniViewHolder>
                firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AlumniDetail, AlumniViewHolder>
                (options) {


            @Override
            protected void onBindViewHolder(@NonNull final AlumniViewHolder holder, final int position, @NonNull final AlumniDetail model) {
                holder.setName(model.getName());
                holder.setPosition(model.getPosition());
                holder.setSession(model.getSession());
                holder.setImage(getApplicationContext(), model.getImage());

                holder.contactim.setOnClickListener( new View.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(View v) {
                        System.out.println("Write on click");
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        String temp="tel:"+model.getContact();
                        callIntent.setData(Uri.parse(temp));
                        getApplicationContext().startActivity(callIntent);
                    }
                });
                }

            @NonNull
            @Override
            public AlumniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.alumni_card_layout, parent, false);

                return new AlumniViewHolder(view);
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

        }
    }


    class AlumniViewHolder extends RecyclerView.ViewHolder {

            ImageView contactim;
            public AlumniViewHolder(View itemView) {
            super(itemView);
            contactim= itemView.findViewById(R.id.contactim);
        }

        public void setName(String Name) {
            TextView post_name = (TextView) itemView.findViewById(R.id.textViewName);
            post_name.setText(Name);
        }

        public void setPosition(String Position) {
            TextView post_position = (TextView) itemView.findViewById(R.id.textViewDesignation);
            post_position.setText(Position);
        }

        public void setSession(String Session) {
            TextView post_session = (TextView) itemView.findViewById(R.id.textViewSession);
            post_session.setText(Session);
        }

        public void setImage(Context ctx, String Image) {
            ImageView post_image = (ImageView) itemView.findViewById(R.id.imageView);
            Picasso.get().load(Image).into(post_image);
        }
    }




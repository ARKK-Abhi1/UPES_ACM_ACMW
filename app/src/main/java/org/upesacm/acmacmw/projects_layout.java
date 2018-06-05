package org.upesacm.acmacmw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class projects_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_layout);
        ImageView img = (ImageView) findViewById(R.id.img);
        TextView tv1 = (TextView) findViewById(R.id.textView6);
        TextView tv2 = (TextView) findViewById(R.id.textView7);
    }
}

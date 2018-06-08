package org.upesacm.acmacmw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.upesacm.acmacmw.model.NewMember;

public class EventRegistration extends AppCompatActivity {
    EditText ParticipantName,ParticipantSAPID,ParticipantBranch,Year,
            ParticipantEmail,ParticipantPhone,ParticipantWhatsapp,C_Name,
            C_Roll_No;
    Spinner Events;
    TextView University;
    RadioButton Upes,Other;
    Button Register,Cancel;

    FirebaseDatabase firebase;

    DatabaseReference databaseReference;

// Root Database Name for Firebase Database.




    public static final String Database_Path = "Student_Details_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);


        Firebase.setAndroidContext(EventRegistration.this);

        firebase = new Firebase(Firebase_Server_URL);

        databaseReference = FirebaseDatabase.getInstance().getReference("Event_registration");



        ParticipantName = (EditText)findViewById(R.id.ParticipantName);
        ParticipantSAPID = (EditText)findViewById(R.id.ParticipantSAPID);
        ParticipantBranch = (EditText)findViewById(R.id.ParticipantBranch);
        Year = (EditText)findViewById(R.id.Year);
        ParticipantEmail = (EditText)findViewById(R.id.ParticipantEmail);
        ParticipantPhone  = (EditText)findViewById(R.id.ParticipantPhone);
        ParticipantWhatsapp = (EditText)findViewById(R.id.ParticipantWhatsapp);
        Register = (Button)findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserData();
            }
        });


    }
    public void getUserData(){

        String fullName = ParticipantName.getText().toString().trim();
        String phoneNo = ParticipantPhone.getText().toString().trim();
        String whatsappNo = ParticipantWhatsapp.getText().toString().trim();
        String branch = ParticipantBranch.getText().toString().trim();
        String email = ParticipantEmail.getText().toString().trim();
        String sapId = ParticipantSAPID.getText().toString().trim();
        Integer year = Year.getText().toString().trim();

        NewMember a = new NewMember();

        a.setBranch(branch);
        a.setEmail(email);
        a.setFullName(fullName);
        a.setSapId(sapId);
        a.setPhoneNo(phoneNo);
        a.setYear(year);
        a.setWhatsappNo(whatsappNo);

        FirebaseDatabase.setValue(a);
    }

    public void saveUserData(){

    }
}

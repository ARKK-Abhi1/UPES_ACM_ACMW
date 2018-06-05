package org.upesacm.acmacmw;

import android.widget.Button;
import android.widget.EditText;

public class RegisterUser {
    String ParticipantName,ParticipantBranch,
            ParticipantEmail,ParticipantPhone,ParticipantWhatsapp,ParticipantSAPID,C_Name,
            C_Roll_No;
    int Year;

    public RegisterUser() {
        // This is default constructor.
    }

    public String getParticipantName() {
        return ParticipantName;
    }

    public void setParticipantName(String ParticipantName) {
        this.ParticipantName = ParticipantName;
    }

    public String getParticipantBranch() {
        return ParticipantBranch;
    }

    public void setParticipantBranch(String ParticipantBranch) {
        this.ParticipantBranch = ParticipantBranch;
    }
    public String getParticipantEmail() {
        return ParticipantEmail;
    }

    public void setParticipantEmail(String ParticipantEmail ) {
        this.ParticipantEmail = ParticipantEmail;
    }

    public String getParticipantSAPID() {
        return ParticipantSAPID;
    }

    public void setParticipantSAPID(String ParticipantSAPID) {
        this.ParticipantSAPID = ParticipantSAPID;
    }

    public String getParticipantPhone() {
        return ParticipantPhone;
    }

    public void setParticipantPhone(String ParticipantPhone) {
        this.ParticipantPhone = ParticipantPhone;
    }


    public String getParticipantWhatsapp() {
        return ParticipantWhatsapp;
    }

    public void setParticipantWhatsapp(String ParticipantWhatsapp) {
        this.ParticipantWhatsapp = ParticipantWhatsapp;
    }

    public String C_Name() {
        return C_Name;
    }

    public void setC_Name(String C_Name) {
        this.C_Name = C_Name;
    }
    public String getC_Roll_No() {
        return C_Roll_No;
    }

    public void setC_Roll_No(String C_Roll_No) {
        this.C_Roll_No = C_Roll_No;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer Year) {
        this.Year = Year;
    }
}





        SubmitButton = (Button)findViewById(R.id.submit);

        NameEditText = (EditText)findViewById(R.id.name);

        PhoneNumberEditText = (EditText)findViewById(R.id.phone_number);

public void GetDataFromEditText(){

        NameHolder = NameEditText.getText().toString().trim();

        NumberHolder = PhoneNumberEditText.getText().toString().trim();

        }
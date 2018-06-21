package org.upesacm.acmacmw.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.upesacm.acmacmw.R;
import org.upesacm.acmacmw.model.Member;
import org.upesacm.acmacmw.model.NewMember;
import org.upesacm.acmacmw.retrofit.MembershipClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerificationFragment extends Fragment implements
        View.OnClickListener,
        Callback<Member>{
    static final int MAX_TRIES=10;
    TextView textViewOTPRecpientDetails;
    EditText editTextOTP;
    EditText editTextSap;
    Button buttonVerify;
    Button buttonNewSap;
    ProgressBar progressBar;
    String otp;
    String sap;
    MembershipClient membershipClient;
    NewMember newMember;

    boolean verifyNewSap;
    private int failureCount=0;
    private OTPVerificationResultListener resultListener;
    public OTPVerificationFragment() {
        // Required empty public constructor
    }

    public static OTPVerificationFragment newInstance(MembershipClient membershipClient,String sap) {
        OTPVerificationFragment fragment = new OTPVerificationFragment();
        fragment.membershipClient=membershipClient;
        fragment.sap=sap;
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        if(context instanceof OTPVerificationResultListener) {
            super.onAttach(context);
            resultListener=(OTPVerificationResultListener)context;
        }
        else
            throw new IllegalStateException(context.toString()+" must implement " +
                    "OnVerificationResult Listener");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("inside oncreate view of otpverification fragment");
        View view=inflater.inflate(R.layout.fragment_otpverification, container, false);
        editTextOTP=view.findViewById(R.id.editText_otp);
        editTextSap = view.findViewById(R.id.editText_sap_verify);
        buttonVerify=view.findViewById(R.id.button_verify);
        buttonNewSap = view.findViewById(R.id.button_newsap);
        textViewOTPRecpientDetails = view.findViewById(R.id.text_view_otp_recepient_details);
        progressBar = view.findViewById(R.id.progress_bar_otp);

        textViewOTPRecpientDetails.setText("Loading recipient details...");
        Bundle args=getArguments();

        if(args!=null) {
            newMember = getArguments().getParcelable(getString(R.string.new_member_key));
            showProgress(true);
            membershipClient.getMember(newMember.getRecipientSap())
                    .enqueue(this);
        }
        else {
            System.out.println("fetching new data");
            fetchNewMemberData(sap);
        }

        buttonNewSap.setOnClickListener(this);
        buttonVerify.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        /* when verify button is pressed */
        if(view.getId() == R.id.button_verify) {
            otp = editTextOTP.getText().toString().trim();
            System.out.println("OTP Entered by user : " + otp);
            if(editTextSap.getVisibility() == View.VISIBLE) {
                String newsap= editTextSap.getText().toString().trim();
                fetchNewMemberData(newsap);
            }
            else {
                if (newMember == null) {
                    fetchNewMemberData(sap);
                } else {
                    verify();
                }
            }
        }
        else if(view.getId() == R.id.button_newsap) {
            editTextSap.setVisibility(View.VISIBLE);
            verifyNewSap = true;
        }
    }

    public NewMember getVerifiedNewMember() {
        return newMember;
    }






    @Override
    public void onResponse(Call<Member> call, Response<Member> response) {
        Member recepient = response.body();
        if(recepient!=null) {
            textViewOTPRecpientDetails.setText("Name    : "+recepient.getName()+
                    "Contact : "+recepient.getContact()+"\n" +
                    "Email   : "+recepient.getEmail());

        }
        else {
            textViewOTPRecpientDetails.setText("Name     : Abhishek Bisht\n" +
                    "Contact : 8979588935\n" +
                    "Email   : arkk.abhi1@gmail.com");
        }
        showProgress(false);
        if(verifyNewSap) {
            verify();
        }

    }

    @Override
    public void onFailure(Call<Member> call, Throwable t) {
        t.printStackTrace();
        Toast.makeText(getContext(),"Failed to fetch recipient",Toast.LENGTH_SHORT).show();
    }

    void verify() {
        String msg;
        boolean verified=otp.equals(newMember.getOtp());
        if(verified) {
            msg="Successfully verified";
            resultListener.onSuccessfulVerification(this);
        }
        else {
            failureCount++;
            if(failureCount==MAX_TRIES) {
                msg="Maximum Tries exceeded Please Contact ACM Membership Team";
                resultListener.onMaxTriesExceed(this);
            }
            else
                msg="Failed to verify "+(MAX_TRIES-failureCount)+" tries left";
        }
        System.out.println(msg);
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }



    void showProgress(boolean show) {
        progressBar.setVisibility(show?View.VISIBLE:View.INVISIBLE);
        editTextOTP.setVisibility(show?View.INVISIBLE:View.VISIBLE);
        buttonVerify.setVisibility(show?View.INVISIBLE:View.VISIBLE);
        buttonNewSap.setVisibility(show?View.INVISIBLE:View.VISIBLE);
    }

    void fetchNewMemberData(final String sap) {
        showProgress(true);
        membershipClient.getNewMemberData(sap)
                .enqueue( new Callback<NewMember>(){
                    @Override
                    public void onResponse (Call < NewMember > call, Response < NewMember > response){
                        System.out.println("Successfully fetched unconfirmed member data");
                        newMember = response.body();
                        if (newMember == null) {
                            Toast.makeText(getContext(), "No data availabe for " + sap, Toast.LENGTH_LONG).show();
                        }
                        else {
                            membershipClient.getMember(newMember.getRecipientSap())
                                    .enqueue(OTPVerificationFragment.this);
                        }

                    }

                    @Override
                    public void onFailure (Call < NewMember > call, Throwable t){
                        System.out.println("failed to fetch unconfirmed member data");
                        t.printStackTrace();
                    }
                });
    }

    public interface OTPVerificationResultListener {
        void onSuccessfulVerification(OTPVerificationFragment otpVerificationFragment);

        void onMaxTriesExceed(OTPVerificationFragment otpVerificationFragment);
    }
}

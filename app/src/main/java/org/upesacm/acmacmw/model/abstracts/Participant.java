package org.upesacm.acmacmw.model.abstracts;

import android.os.Parcelable;

import java.util.List;

public interface Participant extends Parcelable {
    String PARCEL_KEY = "Participant Parcel Key";
    String PARTICIPANT_SAP_KEY = "Participant SAP Key";

    String getBranch();

    String getYear();

    String getEmail();

    String getContact();

    String getSap();

    String getName();

    String getWhatsappNo();

    String getDob();

    String getCurrentAdd();

    String getRecepientSap();

    boolean isACMMember();

    List<String> getEventsList();
}
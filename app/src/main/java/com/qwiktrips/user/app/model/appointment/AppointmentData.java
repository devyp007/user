package com.qwiktrips.user.app.model.appointment;

import androidx.databinding.BaseObservable;

public class AppointmentData extends BaseObservable {

    private int appointmentType = 1;

    public int getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(int appointmentType) {
        this.appointmentType = appointmentType;
        notifyChange();
    }
}

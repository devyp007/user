package com.qwiktrips.user.app.ui.appointment;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.qwiktrips.user.app.model.appointment.AppointmentData;
import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class MyAppointmentViewModel extends BaseViewModel {

    public MyAppointmentObserver observer;

    @Inject
    public MyAppointmentViewModel(Context context) {
        this.context = context;
    }

    public MyAppointmentObserver getObserver() {
        if (observer == null) {
            observer = new MyAppointmentObserver();
        }
        return observer;
    }

    public static class MyAppointmentObserver extends BaseObservable {
        /**
         * 1 =   Active
         * 2 =   Completed
         * 3 =   Cancelled
         */
        private int appointmentStatus;

        public int getAppointmentStatus() {
            return appointmentStatus;
        }

        public void setAppointmentStatus(int appointmentStatus) {
            this.appointmentStatus = appointmentStatus;
            notifyChange();
        }


        private AppointmentData appointmentData;

        public AppointmentData getAppointmentData() {
            if (appointmentData == null) {
                appointmentData = new AppointmentData();
            }
            return appointmentData;
        }

        /**
         * 0 = default
         * 1 = appointment complete message screen
         * 2 = appointment confirmed screen
         * 3 = confirmation page
         * 4 = payment page
         */

        private int screenHandler;

        public int getScreenHandler() {
            return screenHandler;
        }

        public void setScreenHandler(int screenHandler) {
            this.screenHandler = screenHandler;
            notifyChange();
        }
    }
}

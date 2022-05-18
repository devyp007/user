package com.qwiktrips.user.app.dagger.module;

import androidx.lifecycle.ViewModelProvider;

import com.qwiktrips.user.app.dagger.factory.BaseViewModelFactory;
import com.qwiktrips.user.app.ui.appointment.MyAppointmentActivity;
import com.qwiktrips.user.app.ui.booked.BookedAndFavouritesActivity;
import com.qwiktrips.user.app.ui.cart.CartActivity;
import com.qwiktrips.user.app.ui.feedback.ActivityFeedback;
import com.qwiktrips.user.app.ui.hairstylist.HairStylistActivity;
import com.qwiktrips.user.app.ui.hairstylist.find.FindHairStylist;
import com.qwiktrips.user.app.ui.hairstylist.find.SearchedStylistActivity;
import com.qwiktrips.user.app.ui.hairstylist.select.SelectHairStylistActivity;
import com.qwiktrips.user.app.ui.home.HomeActivity;
import com.qwiktrips.user.app.ui.login.LoginActivity;
import com.qwiktrips.user.app.ui.main.MainActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.ui.notification.NotificationActivity;
import com.qwiktrips.user.app.ui.otp.OtpVerificationActivity;
import com.qwiktrips.user.app.ui.payment.PaymentActivity;
import com.qwiktrips.user.app.ui.payment.PaymentMethodActivity;
import com.qwiktrips.user.app.ui.profile.ProfileActivity;
import com.qwiktrips.user.app.ui.profile.userprofile.UserProfileActivity;
import com.qwiktrips.user.app.ui.providers.PreferredProvidersActivity;
import com.qwiktrips.user.app.ui.signup.SignupActivity;
import com.qwiktrips.user.app.ui.splash.SplashActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(
        includes = {
                ViewModelModule.class
        }
)
public abstract class UiModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BaseViewModelFactory viewModelFactory);

    @ContributesAndroidInjector()
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector()
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector()
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector()
    abstract HairStylistActivity hairStylistActivity();

    @ContributesAndroidInjector()
    abstract ProfileActivity profileActivity();

    @ContributesAndroidInjector()
    abstract PaymentMethodActivity paymentMethodActivity();

    @ContributesAndroidInjector()
    abstract FindHairStylist findHairStylist();

    @ContributesAndroidInjector()
    abstract MapActivity mapActivity();

    @ContributesAndroidInjector()
    abstract UserProfileActivity userProfileActivity();

    @ContributesAndroidInjector()
    abstract SelectHairStylistActivity selectHairStylistActivity();

    @ContributesAndroidInjector()
    abstract SearchedStylistActivity searchedStylistActivity();

    @ContributesAndroidInjector()
    abstract PaymentActivity paymentActivity();

    @ContributesAndroidInjector()
    abstract MyAppointmentActivity myAppointmentActivity();

    @ContributesAndroidInjector()
    abstract NotificationActivity notificationActivity();

    @ContributesAndroidInjector()
    abstract CartActivity cartActivity();

    @ContributesAndroidInjector(modules = {HomeActivityModule.class})
    abstract HomeActivity homeActivity();

    @ContributesAndroidInjector()
    abstract OtpVerificationActivity otpVerificationActivity();

    @ContributesAndroidInjector()
    abstract SignupActivity signupActivity();

    @ContributesAndroidInjector()
    abstract ActivityFeedback activityFeedback();

    @ContributesAndroidInjector()
    abstract PreferredProvidersActivity preferredProvidersActivity();

    @ContributesAndroidInjector()
    abstract BookedAndFavouritesActivity bookedAndFavouritesActivity();

}

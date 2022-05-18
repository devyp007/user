package com.qwiktrips.user.app.dagger.module;

import androidx.lifecycle.ViewModel;

import com.qwiktrips.user.app.dagger.factory.BaseViewModelKey;
import com.qwiktrips.user.app.ui.appointment.MyAppointmentViewModel;
import com.qwiktrips.user.app.ui.cart.CartViewModel;
import com.qwiktrips.user.app.ui.feedback.FeedbackViewModel;
import com.qwiktrips.user.app.ui.hairstylist.HairStylistViewModel;
import com.qwiktrips.user.app.ui.hairstylist.find.FindHairStylistViewModel;
import com.qwiktrips.user.app.ui.hairstylist.select.SelectHairStyleViewModel;
import com.qwiktrips.user.app.ui.home.HomeViewModel;
import com.qwiktrips.user.app.ui.login.LoginViewModel;
import com.qwiktrips.user.app.ui.main.MainActivityViewModel;
import com.qwiktrips.user.app.ui.main.MainViewModel;
import com.qwiktrips.user.app.ui.map.MapViewModel;
import com.qwiktrips.user.app.ui.notification.NotificationActivity;
import com.qwiktrips.user.app.ui.notification.NotificationViewModel;
import com.qwiktrips.user.app.ui.payment.PaymentViewModel;
import com.qwiktrips.user.app.ui.profile.ProfileViewModel;
import com.qwiktrips.user.app.ui.profile.userprofile.UserProfileViewModel;
import com.qwiktrips.user.app.ui.providers.PreferredProvidersViewModel;
import com.qwiktrips.user.app.ui.signup.SignupViewModel;
import com.qwiktrips.user.app.ui.splash.SplashViewModel;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @BaseViewModelKey(SplashViewModel.class)
    public abstract ViewModel splashViewModel(SplashViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(LoginViewModel.class)
    public abstract ViewModel loginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel mainViewModel(MainActivityViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(HairStylistViewModel.class)
    public abstract ViewModel hairStylistViewModel(HairStylistViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(ProfileViewModel.class)
    public abstract ViewModel profileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(PaymentViewModel.class)
    public abstract ViewModel paymentViewModel(PaymentViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(FindHairStylistViewModel.class)
    public abstract ViewModel findHairStylistViewModel(FindHairStylistViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(MapViewModel.class)
    public abstract ViewModel mapViewModel(MapViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(UserProfileViewModel.class)
    public abstract ViewModel userProfileViewModel(UserProfileViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(SelectHairStyleViewModel.class)
    public abstract ViewModel selectHairStyleViewModel(SelectHairStyleViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(MyAppointmentViewModel.class)
    public abstract ViewModel myAppointmentViewModel(MyAppointmentViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(NotificationViewModel.class)
    public abstract ViewModel notificationViewModel(NotificationViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(CartViewModel.class)
    public abstract ViewModel cartViewModel(CartViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(MainViewModel.class)
    public abstract ViewModel mainFragmentViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(HomeViewModel.class)
    public abstract ViewModel homeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(SignupViewModel.class)
    public abstract ViewModel signupViewModel(SignupViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(FeedbackViewModel.class)
    public abstract ViewModel feedbackViewModel(FeedbackViewModel viewModel);

    @Binds
    @IntoMap
    @BaseViewModelKey(PreferredProvidersViewModel.class)
    public abstract ViewModel preferredViewModel(PreferredProvidersViewModel viewModel);
}

package com.example.github

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.github.di.module.component.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class GithubApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    AndroidInjection.inject(activity)
                }

                override fun onActivityStarted(activity: Activity) {

                }

                override fun onActivityResumed(activity: Activity) {

                }

                override fun onActivityPaused(activity: Activity) {

                }

                override fun onActivityStopped(activity: Activity) {

                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

                }

                override fun onActivityDestroyed(activity: Activity) {

                }
            })
    }

    override fun activityInjector() = dispatchingAndroidInjector
}

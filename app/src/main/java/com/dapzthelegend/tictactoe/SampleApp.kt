package com.dapzthelegend.tictactoe

import com.dapzthelegend.tictactoe.di.AppModule
import com.dapzthelegend.tictactoe.di.DaggerAppComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication
import timber.log.Timber

/**
 * Base class for maintaining global application state.
 *
 * @see SplitCompatApplication
 */
class SampleApp: SplitCompatApplication() {

    /**
     * Called when the application is starting before any activity, service or receiver objects
     * (excluding content providers) have been provided.
     *
     * @see SplitCompatApplication.onCreate
     */
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initAppDependencyInjection()
    }

    // =============================================================================================
    // Private init methods
    // =============================================================================================

    /**
     * Initialize application's dependency graph.
     */
    private fun initAppDependencyInjection() {
       DaggerAppComponent
           .builder()
           .build()
           .inject(this)
    }

    /**
     * Initialize log library timber on debug build.
     */
    private fun initTimber() {
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}

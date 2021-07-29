package com.dapzthelegend.tictactoe.di

import com.dapzthelegend.core.di.scopes.AppScope
import com.dapzthelegend.tictactoe.SampleApp
import dagger.Component

/**
 * App component that application's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param The sample application.
     */
    fun inject(application: SampleApp)
}
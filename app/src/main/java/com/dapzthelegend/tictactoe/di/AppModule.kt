package com.dapzthelegend.tictactoe.di

import com.dapzthelegend.tictactoe.SampleApp
import dagger.Module
import dagger.Provides

/**
 * Class that contribute to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    /**
     * Create provider menthod binding for [Context].
     *
     * @see application The sample application.
     * @return The application context.
     * @see Provides
     */
    @Provides
    fun provideContext(application: SampleApp) = application.applicationContext
}
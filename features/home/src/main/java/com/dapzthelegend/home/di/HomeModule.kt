package com.dapzthelegend.home.di

import com.dapzthelegend.core.di.scopes.FeatureScope
import com.dapzthelegend.home.HomeFragment
import com.dapzthelegend.home.HomeViewModel
import com.dapzthelegend.ui.extensions.viewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [HomeComponent]
 *
 * @see Module
 */
@Module
class HomeModule(
    private val fragment: HomeFragment
) {

    /**
     * Create provider method binding for [HomeViewModel]
     *
     * @return The view model
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideHomeViewModel() = fragment.viewModel {
        HomeViewModel()
    }
}

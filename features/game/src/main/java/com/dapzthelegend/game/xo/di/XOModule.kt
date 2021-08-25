package com.dapzthelegend.multiplayer.xo.di

import com.dapzthelegend.core.di.scopes.FeatureScope
import com.dapzthelegend.multiplayer.xo.XOFragment
import com.dapzthelegend.multiplayer.xo.XOViewModel
import com.dapzthelegend.ui.extensions.viewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes dependencies to the object graoh [XOComponent].
 *
 * @see Module
 */
@Module
class XOModule(
    private val fragment: XOFragment
) {

    /**
     * Create provider method binding for [XOViewModel].
     *
     * @return The xo view model.
     */
    @Provides
    @FeatureScope
    fun provideXOViewModel() = fragment.viewModel {
        XOViewModel()
    }
}

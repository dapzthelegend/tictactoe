package com.dapzthelegend.multiplayer.register.di

import com.dapzthelegend.core.di.scopes.FeatureScope
import com.dapzthelegend.multiplayer.register.RegisterFragment
import com.dapzthelegend.multiplayer.register.RegisterViewModel
import com.dapzthelegend.ui.extensions.viewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributed to the object graph [RegisterComponent].
 *
 * @see Module
 */
@Module
class RegisterModule(
    val fragment: RegisterFragment
) {
    /**
     * Create provider method binding for [RegisterViewModel].
     *
     * @return The register view model.
     */
    @Provides
    @FeatureScope
    fun provideRegisterViewModel() = fragment.viewModel {
        RegisterViewModel()
    }
}

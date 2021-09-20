package com.dapzthelegend.game.register.di

import com.dapzthelegend.core.di.scopes.FeatureScope
import com.dapzthelegend.game.register.RegisterFragment
import dagger.Component

/**
 * Class from which fully-formed, dependency injected implementation is to be
 * formed from [RegisterModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [RegisterModule::class]
)
interface RegisterComponent {

    /**
     * Inject dependencies on component.
     *
     * @param registerFragment The register fragment.
     */
    fun inject(registerFragment: RegisterFragment)
}

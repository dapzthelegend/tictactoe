package com.dapzthelegend.home.di

import com.dapzthelegend.core.di.scopes.FeatureScope
import com.dapzthelegend.home.HomeFragment
import dagger.Component

/**
 * Class from which fully formed, dependency injected implementation is to
 * be formed from [HomeModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [HomeModule::class]
)
interface HomeComponent {

    /**
     * Inject dependencies on component.
     *
     * @param homeFragment The home fragment
     */
    fun inject(homeFragment: HomeFragment)
}

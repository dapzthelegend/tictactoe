package com.dapzthelegend.game.xo.di

import com.dapzthelegend.core.di.scopes.FeatureScope
import com.dapzthelegend.game.xo.XOFragment
import dagger.Component

/**
 * Class for which fully-formed, dependency injected implementation is to be formed
 * from [XOModule]
 *
 * @see Component
 */
@FeatureScope
@Component(modules = [XOModule::class])
interface XOComponent {

    /**
     * Inject dependencies in component
     *
     * @param fragment The XO fragment.
     */
    fun inject(fragment: XOFragment)
}

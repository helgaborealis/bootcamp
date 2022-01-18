package co.alty.feature.auth.di

import androidx.fragment.app.Fragment
import com.coroutineshub.di.PresentationModuleDependencies
import co.alty.feature.auth.ui.AuthFragment
import dagger.BindsInstance
import dagger.Component

@Component(
  dependencies = [PresentationModuleDependencies::class],
  modules = [AuthModule::class]
)
interface AuthComponent {
  fun inject(fragment: AuthFragment)

  @Component.Factory
  interface Factory {
    fun create(
      dependentModule: PresentationModuleDependencies,
      @BindsInstance fragment: Fragment
    ): AuthComponent
  }
}
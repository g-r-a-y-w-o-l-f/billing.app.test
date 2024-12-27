package info.sph.billing.android.di

import info.sph.billing.android.data.RepositoryImpl
import info.sph.billing.android.domain.repository.IRepository
import info.sph.billing.android.viewModels.ViewModelAddFunds
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Serhii Polishchuk on 23.12.24
 */

val ViewModelModule = module {

    viewModel { ViewModelAddFunds(get()) }

    single<IRepository> { RepositoryImpl(get()) }
}
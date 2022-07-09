package com.muchbeer.di

import com.muchbeer.db.DatabaseFactory
import com.muchbeer.repository.Repository
import com.muchbeer.repository.RepositoryImpl
import com.muchbeer.service.UserService
import com.muchbeer.service.UserServiceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.rmi.Naming.bind

val koinModule = module {
    single { DatabaseFactory.init() }

    single<Repository> { RepositoryImpl(get()) }

    single<UserService> { UserServiceImpl(get()) }
}
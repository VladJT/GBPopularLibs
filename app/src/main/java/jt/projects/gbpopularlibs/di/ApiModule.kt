//package jt.projects.gbpopularlibs.di
//
//import dagger.Module
//import dagger.Provides
//import jt.projects.gbpopularlibs.data.retrofit.GithubAPI
//import jt.projects.gbpopularlibs.data.retrofit.RetrofitDataSourceImpl
//import javax.inject.Singleton
//
//
//@Module
//class ApiModule {
//    @Singleton
//    @Provides
//    fun api(): GithubAPI = RetrofitDataSourceImpl().getApi()
//}
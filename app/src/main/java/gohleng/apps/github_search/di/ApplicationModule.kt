package gohleng.apps.github_search.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import gohleng.apps.github_search.data.local.AppDatabase
import gohleng.apps.github_search.data.local.RepositoryDao
import gohleng.apps.github_search.data.remote.RepositoryService
import gohleng.apps.github_search.data.repository.RepoRepository
import gohleng.apps.itunes_appetiser_apps.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {
    @Provides
    internal fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    internal fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(httpClient)
            .build()

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Provides
    fun provideRepositoryService(retrofit: Retrofit): RepositoryService = retrofit.create(
        RepositoryService::class.java
    )

    @Provides
    fun provideRepositoryDao(db: AppDatabase) = db.repositoryDao()

    @Provides
    fun provideRepoRepository(remote: RepositoryService, local: RepositoryDao) = RepoRepository(remote, local)
}
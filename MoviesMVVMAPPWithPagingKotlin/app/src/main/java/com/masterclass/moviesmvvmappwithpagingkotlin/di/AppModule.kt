package com.masterclass.moviesmvvmappwithpagingkotlin.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.masterclass.moviesmvvmappwithpagingkotlin.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
            .applyDefaultRequestOptions(
                RequestOptions()
                .error(R.drawable.ic_image)
                .placeholder(R.drawable.ic_image))
    }
}
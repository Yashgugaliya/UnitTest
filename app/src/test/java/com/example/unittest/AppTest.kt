package com.example.unittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.unittest.di.AppModule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.mockito.MockitoAnnotations

open class AppTest: KoinTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(listOf(
            AppModule().getAppModule(),
            AppModule().getNetworkModule(BuildConfig.BASE_URL))) }
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        //RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        //RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        //RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        //RxJavaPlugins.setInitSingleSchedulerHandler { Schedulers.trampoline() }
        //RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }
    @After
    fun after() {
        //RxAndroidPlugins.reset()
        //RxJavaPlugins.reset()
        stopKoin()
    }
}
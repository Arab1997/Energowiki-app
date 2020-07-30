package com.reactive.energowiki.ui.activities

import android.view.KeyEvent
import com.reactive.energowiki.ui.screens.splash.SplashScreen
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseActivity
import com.reactive.energowiki.base.BaseViewModel
import com.reactive.energowiki.base.initialFragment
import com.reactive.energowiki.ui.screens.BottomNavScreen
import com.reactive.energowiki.utils.extensions.showGone
import com.reactive.energowiki.utils.preferences.SharedManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel by viewModel<BaseViewModel>()
    val sharedManager: SharedManager by inject()

    override fun onActivityCreated() {
        viewModel.apply {
            parentLayoutId = R.id.fragmentContainer
            navLayoutId = R.id.navContainer

            fetchData()
        }

        debug()
        startFragment()
    }

    private fun debug() = initialFragment(BottomNavScreen())

    private fun startFragment() {
        initialFragment(
            if (sharedManager.token.isEmpty()) SplashScreen()
            else BottomNavScreen(), true
        )
    }

    fun showProgress(show: Boolean) {
        progressBar.showGone(show)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> return false
                KeyEvent.KEYCODE_VOLUME_DOWN -> return false
            }
        }
        return super.dispatchKeyEvent(event)
    }
}

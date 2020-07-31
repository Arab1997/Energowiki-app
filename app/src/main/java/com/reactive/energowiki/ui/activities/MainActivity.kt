package com.reactive.energowiki.ui.activities

import android.view.KeyEvent
import com.reactive.energowiki.ui.screens.SplashScreen
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseActivity
import com.reactive.energowiki.base.BaseViewModel
import com.reactive.energowiki.base.initialFragment
import com.reactive.energowiki.base.parentLayoutId
import com.reactive.energowiki.ui.screens.BasicsScreen
import com.reactive.energowiki.ui.screens.HomeScreen
import com.reactive.energowiki.utils.extensions.inDevelopment
import com.reactive.energowiki.utils.extensions.showGone
import com.reactive.energowiki.utils.preferences.SharedManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_toolbar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel by viewModel<BaseViewModel>()
    val sharedManager: SharedManager by inject()

    override fun onActivityCreated() {
        viewModel.apply {
            parentLayoutId = R.id.container
            fragmentLayoutId = R.id.fragmentContainer

            fetchData()
        }

        setClicks()

        debug()
        startFragment()
    }

    private fun setClicks() {
        menu.setOnClickListener { inDevelopment(this) }
        notifications.setOnClickListener { inDevelopment(this) }
    }

    private fun debug() = initialFragment(BasicsScreen(), viewModel.fragmentLayoutId)

    private fun startFragment() {
        val splash = SplashScreen().apply {
            setListener { replaceFragment(HomeScreen(), id = parentLayoutId()) }
        }
        initialFragment(splash, viewModel.parentLayoutId, true)
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

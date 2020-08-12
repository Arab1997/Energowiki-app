package com.reactive.energowiki.ui.activities

import android.content.Intent
import android.view.KeyEvent
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.reactive.energowiki.BuildConfig
import com.reactive.energowiki.R
import com.reactive.energowiki.base.*
import com.reactive.energowiki.ui.adapters.NavAdapter
import com.reactive.energowiki.ui.adapters.NotificationsAdapter
import com.reactive.energowiki.ui.dialogs.AvariyaDialog
import com.reactive.energowiki.ui.dialogs.RequestDialog
import com.reactive.energowiki.ui.screens.*
import com.reactive.energowiki.ui.screens.payment.PaymentScreen
import com.reactive.energowiki.utils.UpdateManager
import com.reactive.energowiki.utils.extensions.inDevelopment
import com.reactive.energowiki.utils.extensions.showGone
import com.reactive.energowiki.utils.preferences.SharedManager
import com.reactive.energowiki.utils.livedata.ConnectionLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_navigation.*
import kotlinx.android.synthetic.main.content_toolbar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel by viewModel<BaseViewModel>()
    val sharedManager: SharedManager by inject()

    companion object {
        var isCurrentLangUz: Boolean = true
    }

    private var updateManager: UpdateManager? = null
    override fun onActivityCreated() {
        viewModel.apply {
            parentLayoutId = R.id.container
            fragmentLayoutId = R.id.fragmentContainer
        }

        initConnection()

        initToolbar()

        initDrawer()

//        debug()
        startFragment()

        if (!BuildConfig.DEBUG) updateManager = UpdateManager(this).apply {
            checkUpdate()
        }
    }

    private fun initConnection() {
        ConnectionLiveData(this).observe(this, Observer {
            val hasNetwork = it?.isConnected ?: false
            if (hasNetwork) viewModel.fetchData()
        })
    }

    private fun debug() = initialFragment(GlossariesScreen(), viewModel.fragmentLayoutId)

    private fun startFragment() {
        val splash = SplashScreen().apply {
            setListener { replaceFragment(HomeScreen(), id = parentLayoutId()) }
        }

        initialFragment(
            if (BuildConfig.DEBUG) HomeScreen() else splash,
            viewModel.fragmentLayoutId, true
        )
    }

    private fun initToolbar() {
        menu.setOnClickListener { toggleDrawer() }
        notifications.setOnClickListener { expandableLayout.toggle() }

        recycler.adapter = NotificationsAdapter().apply {
            setData(arrayListOf("Изменение цены", "Ваша заявка принято на рассмотрение")) // todo
        }
        recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(leftDrawerMenu)) {
            drawerLayout.closeDrawer(leftDrawerMenu)
        } else {
            drawerLayout.openDrawer(leftDrawerMenu)
        }
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


    private var data = arrayListOf<HomeData>()
    private fun initDrawer() {
        avariya.setOnClickListener {
            addFragment(AvariyaDialog(), viewModel.parentLayoutId)
            toggleDrawer()
        }
        zayavka.setOnClickListener {
            addFragment(RequestDialog(), viewModel.parentLayoutId)
            toggleDrawer()
        }

        data = arrayListOf(
            HomeData(0, "Главная", HomeMenus.MAIN),
            HomeData(0, "Основы", HomeMenus.BASICS),
            HomeData(0, "Емкость", HomeMenus.CAPACITY),
            HomeData(0, "Проводник", HomeMenus.CONDUCTOR),
            HomeData(0, "ПУЭ", HomeMenus.PUE),
            HomeData(0, "Двигатель", HomeMenus.ENGINE),
            HomeData(0, "Кабель", HomeMenus.CABLE),
            HomeData(0, "Перекур", HomeMenus.PEREKUR),
            HomeData(0, "Оплата за электроэнергия и за газ", HomeMenus.PAYMENT), //
            HomeData(0, "Новости", HomeMenus.NEWS),
            HomeData(0, "Нормативные документы", HomeMenus.DOCUMENTS),
            HomeData(0, "Частые вопросы", HomeMenus.FAQ),
            HomeData(0, "Организации", HomeMenus.ORGANIZATIONS),
            HomeData(0, "Глоссарии", HomeMenus.GLOSSARY),
            HomeData(0, "Цена электроэнергии", HomeMenus.PRICE),
            HomeData(0, "Калькулятор", HomeMenus.CALCULATOR),
            HomeData(0, "Полезные ссылки", HomeMenus.LINKS),
            HomeData(0, "Справка", HomeMenus.REFERENCE),
            HomeData(0, "О программе", HomeMenus.ABOUT)

        )
        val adapter = NavAdapter {
            toggleDrawer()
            when (it.type) {
                HomeMenus.MAIN -> {
                }
                HomeMenus.BASICS -> {
                    add(BasicsScreen())
                }
                HomeMenus.CAPACITY -> {
                    inDevelopment(this)
                }
                HomeMenus.CONDUCTOR -> {
                    inDevelopment(this)
                }
                HomeMenus.PUE -> {
                    inDevelopment(this)
                }
                HomeMenus.ENGINE -> {
                    inDevelopment(this)
                }
                HomeMenus.CABLE -> {
                    inDevelopment(this)
                }
                HomeMenus.PEREKUR -> {
                    inDevelopment(this)
                }
                HomeMenus.PAYMENT -> {
                    add(PaymentScreen())
                }
                HomeMenus.NEWS -> {
                    add(NewsScreen())
                }
                HomeMenus.DOCUMENTS -> {
                    add(DocumentsScreen())
                }
                HomeMenus.FAQ -> {
                    add(FAQScreen())
                }
                HomeMenus.ORGANIZATIONS -> {
                    add(OrganizationsScreen())
                }
                HomeMenus.GLOSSARY -> {
                    add(GlossariesScreen())
                }
                HomeMenus.PRICE -> {
                    inDevelopment(this)
                }
                HomeMenus.CALCULATOR -> {
                    add(CalculatorScreen())
                }
                HomeMenus.LINKS -> {
                    add(LinksScreen())
                }
                HomeMenus.REFERENCE -> {
                    add(ReferenceScreen())
                }
                HomeMenus.ABOUT -> {
                    inDevelopment(this)
                }

                else -> {
                }
            }
        }.apply { setData(data) }

        recyclerNavigation.adapter = adapter
    }

    private fun add(fragment: BaseFragment) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        addFragment(fragment, viewModel.fragmentLayoutId)
    }

    override fun onResume() {
        super.onResume()
        updateManager?.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateManager?.onActivityResult(requestCode, resultCode)
    }


    fun openLink(link: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(if (!link.startsWith("http")) "https://$link" else link)
            )
        )
    }

}

fun TextView.setLangText(first: String?, second: String?) {
    this.text = if (MainActivity.isCurrentLangUz) first else second
}

fun getLangText(first: String?, second: String?): String? {
    return if (MainActivity.isCurrentLangUz) first else second
}

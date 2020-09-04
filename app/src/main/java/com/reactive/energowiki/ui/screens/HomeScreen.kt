package com.reactive.energowiki.ui.screens

import androidx.annotation.DrawableRes
import androidx.lifecycle.Observer
import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseFragment
import com.reactive.energowiki.ui.adapters.FAQAdapter
import com.reactive.energowiki.ui.adapters.LinksAdapter
import com.reactive.energowiki.ui.adapters.MenuAdapter
import com.reactive.energowiki.ui.adapters.NewsAdapter
import com.reactive.energowiki.ui.bottomsheets.DetailBottomSheet
import com.reactive.energowiki.ui.bottomsheets.NewsBottomSheet
import com.reactive.energowiki.ui.dialogs.AvariyaDialog
import com.reactive.energowiki.ui.dialogs.RequestDialog
import com.reactive.energowiki.ui.screens.calculator.CalculatorData
import com.reactive.energowiki.ui.screens.calculator.CalculatorFragment
import com.reactive.energowiki.ui.screens.calculator.CalculatorMenus
import com.reactive.energowiki.ui.screens.payment.PaymentScreen
import com.reactive.energowiki.utils.extensions.inDevelopment
import kotlinx.android.synthetic.main.screen_home.*

class HomeScreen : BaseFragment(R.layout.screen_home) {

    private lateinit var mainAdapter: MenuAdapter
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var faqAdapter: FAQAdapter
    private lateinit var linksAdapter: LinksAdapter

    override fun initialize() {

        initClicks()

        initMainData()

        initMenuData()

        initRecyclers()
    }

    private fun initClicks() {

        newsAll.setOnClickListener { addFragment(NewsScreen()) }

        faqAll.setOnClickListener { addFragment(FAQScreen()) }

        linksAll.setOnClickListener { addFragment(LinksScreen()) }

        payment.setOnClickListener { addFragment(PaymentScreen()) }

        avariya.setOnClickListener { addFragment(AvariyaDialog(), id = viewModel.parentLayoutId) }

        zayavka.setOnClickListener { addFragment(RequestDialog(), id = viewModel.parentLayoutId) }
    }

    private var homeData = arrayListOf<HomeData>()
    private fun initMainData() {
        homeData = arrayListOf(
            HomeData(R.drawable.documents, "Документы", HomeMenus.DOCUMENTS),
            HomeData(R.drawable.organizations, "Организации", HomeMenus.ORGANIZATIONS),
            HomeData(R.drawable.pue, "ПУЭ", HomeMenus.PUE),
            HomeData(R.drawable.calculator, "Калькулятор", HomeMenus.CALCULATOR),
            HomeData(R.drawable.glossary, "Глосарии", HomeMenus.GLOSSARY),
            HomeData(R.drawable.perekur, "Перекур", HomeMenus.PEREKUR)
        )

        mainAdapter = MenuAdapter {
            when (it.type) {
                HomeMenus.DOCUMENTS -> addFragment(DocumentsScreen())
                HomeMenus.ORGANIZATIONS -> addFragment(OrganizationsScreen())
                HomeMenus.PUE -> addFragment(PueFragment.newInstance(PueData("Основы", PueMenus.BASICS)))
                HomeMenus.CALCULATOR -> inDevelopment(requireContext())// todo
                HomeMenus.GLOSSARY -> addFragment(GlossariesScreen())
                HomeMenus.PEREKUR -> inDevelopment(requireContext())// todo
                else -> {
                }
            }
        }.apply { setData(homeData) }
        recycler.adapter = mainAdapter
    }

    private var menuData = arrayListOf<HomeData>()
    private fun initMenuData() {
        menuData = arrayListOf(
            HomeData(R.drawable.reference, "Справка", HomeMenus.REFERENCE),
            HomeData(R.drawable.basics, "Основы", HomeMenus.BASICS),
            HomeData(R.drawable.capacity, "Емкость", HomeMenus.CAPACITY),
            HomeData(R.drawable.conductor, "Проводник", HomeMenus.CONDUCTOR),
            HomeData(R.drawable.engine, "Двигатель", HomeMenus.ENGINE),
            HomeData(R.drawable.cable, "Кабель", HomeMenus.CABLE)
        )

        menuAdapter = MenuAdapter {
            when (it.type) {
                HomeMenus.REFERENCE -> addFragment(ReferenceScreen())
                HomeMenus.BASICS -> addFragment(CalculatorFragment.newInstance(CalculatorData("Основы", CalculatorMenus.BASICS)))
                HomeMenus.CAPACITY -> addFragment(CalculatorFragment.newInstance(CalculatorData("Емкость", CalculatorMenus.CAPACITY)))
                HomeMenus.CONDUCTOR -> addFragment(CalculatorFragment.newInstance(CalculatorData("Проводник", CalculatorMenus.CONDUCTOR)))
                HomeMenus.ENGINE -> addFragment(CalculatorFragment.newInstance(CalculatorData("Двигатель", CalculatorMenus.ENGINE)))
                HomeMenus.CABLE -> addFragment(CalculatorFragment.newInstance(CalculatorData("Кабель", CalculatorMenus.CABEL)))
                else -> addFragment(ReferenceScreen())
            }
        }.apply { setData(menuData) }
        recyclerMenu.adapter = menuAdapter
    }

    private fun initRecyclers() {

        newsAdapter = NewsAdapter {
            removePreviousCallback({
                val bottomSheet = NewsBottomSheet.newInstance(it)
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recyclerNews.adapter = newsAdapter

        faqAdapter = FAQAdapter {
            removePreviousCallback({
                val bottomSheet = DetailBottomSheet.newInstance(it)
                bottomSheet.show(childFragmentManager, "")
            })
        }
        recyclerFAQ.adapter = faqAdapter

        linksAdapter = LinksAdapter {
            removePreviousCallback({ mainActivity.openLink(it.url) })
        }
        recyclerLinks.adapter = linksAdapter
    }

    override fun observe() {
        viewModel.apply {
            news.observe(viewLifecycleOwner, Observer {
                newsAdapter.setData(it.setLimitedItems(3))
            })

            faqs.observe(viewLifecycleOwner, Observer {
                faqAdapter.setData(it.setLimitedItems(2))
            })

            links.observe(viewLifecycleOwner, Observer {
                linksAdapter.setData(it.setLimitedItems(2))
            })

        }
    }

    private fun <T> List<T>.setLimitedItems(limit: Int): ArrayList<T> {
        return if (this.size > limit) ArrayList(this.subList(0, limit))
        else ArrayList(this)
    }
}

data class HomeData(@DrawableRes val icon: Int, val name: String, val type: HomeMenus)
enum class HomeMenus {
    DOCUMENTS, ORGANIZATIONS, PUE, CALCULATOR, GLOSSARY, PEREKUR,
    REFERENCE, BASICS, CAPACITY, CONDUCTOR, ENGINE, CABLE,
    ELECTRICITY, GAS,
    MAIN, PAYMENT, NEWS, LINKS, PRICE, FAQ, ABOUT
}

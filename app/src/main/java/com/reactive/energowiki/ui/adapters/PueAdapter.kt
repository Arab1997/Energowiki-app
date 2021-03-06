package com.reactive.energowiki.ui.adapters

import com.reactive.energowiki.R
import com.reactive.energowiki.base.BaseAdapter
import com.reactive.energowiki.ui.screens.PueData
import com.reactive.energowiki.ui.screens.PueMenus
import com.reactive.energowiki.ui.screens.calculator.CalculatorData
import com.reactive.energowiki.ui.screens.calculator.CalculatorMenus
import com.reactive.energowiki.utils.common.ViewHolder
import kotlinx.android.synthetic.main.item_calculator.view.*

class PueAdapter(private val type: PueData, private val listener: (Any) -> Unit) :
    BaseAdapter<Any>(R.layout.item_pue) {
    override fun bindViewHolder(holder: ViewHolder, data: Any) {
        when (type.type) {
            PueMenus.BASICS -> when (data) {
                1 -> holder.itemView.name.text = "Допустимый длительный ток"
                2 -> holder.itemView.name.text = "Сечение проводника по нагреву и потерям"
                3 -> holder.itemView.name.text = "Сечение и автоматзащиты кабеля электродвигателя"
                4 -> holder.itemView.name.text = "Максимальная длина цепи"
                5 -> holder.itemView.name.text = "Максимальная нагрузка цепи"
            }
            PueMenus.CAPACITY -> when (data) {
                1 -> holder.itemView.name.text = "Соединение конденсаторов"
                2 -> holder.itemView.name.text = "Сопротивление емкости переменному току"
                3 -> holder.itemView.name.text =
                    "Конденсатор для пуска трехфазного двигателя в однофазной сети"
                4 -> holder.itemView.name.text = "Емкостной делитель напряжение"
                5 -> holder.itemView.name.text = "Заряд и энергия корденсатора"
                6 -> holder.itemView.name.text = "Заряд и энергия конденсатора"
                7 -> holder.itemView.name.text = "Декодировать в буквенно-цифровую маркировку"
                8 -> holder.itemView.name.text = "Кодировать в буквенно-цифровую маркировку"
                9 -> holder.itemView.name.text = "Гасящий конденсатор"
            }
            PueMenus.CONDUCTOR -> when (data) {
                1 -> holder.itemView.name.text = "Сопротивление проводника"
                2 -> holder.itemView.name.text = "Длина проводника"
                3 -> holder.itemView.name.text = "Сечение проводника"
                4 -> holder.itemView.name.text = "Расчёт потерь напряжения"
                5 -> holder.itemView.name.text = "Удельное сопротивление и проводимость"
                6 -> holder.itemView.name.text = "Максимальная длина проводника"
            }
            PueMenus.ENGINE -> when (data) {
                1 -> holder.itemView.name.text = "Ток электродвигателя"
                2 -> holder.itemView.name.text = "Активная мощность"
                3 -> holder.itemView.name.text = "Полная мощность ( 3 фазы)"
                4 -> holder.itemView.name.text = "Коеффициент мощности (3 фазы)"
                5 -> holder.itemView.name.text = "Кпд электродвигателя"
                6 -> holder.itemView.name.text = "Скольжение электродвигателя"
                7 -> holder.itemView.name.text = "Крутящий  момент"
                8 -> holder.itemView.name.text =
                    "Единичная компенсация мощноности трехнофазного электродвигателя"
                9 -> holder.itemView.name.text = "Мощность элекродвигателя для центробежного насоса"
                10 -> holder.itemView.name.text =
                    "Мощность электродвигателя для поршневого компрессора"
                11 -> holder.itemView.name.text = "Мощность электродвигателя для вентилятро"
                12 -> holder.itemView.name.text =
                    "Асимметрия напряжений трехфазного элеткродвигателя"
                13 -> holder.itemView.name.text = "Ассиметрия токов трехфазного электродвигателя"
            }
            PueMenus.CABEL -> when (data) {
                1 -> holder.itemView.name.text = "Количество кабелей, которые помещаются в трубу"
                2 -> holder.itemView.name.text = "Емкость кабельного барабана"
                3 -> holder.itemView.name.text = "Остаток кабеля на барабане"
                4 -> holder.itemView.name.text = "Индуктивное сопротивление кабеля"
                5 -> holder.itemView.name.text = "Все металла в кабеле"
                6 -> holder.itemView.name.text = "Все кабеля по формулам"
                7 -> holder.itemView.name.text = "Цветовая кодировка проводов"
                8 -> holder.itemView.name.text = "Длина кабеля с учетом провиса"
                9 -> holder.itemView.name.text = "Потери мощности в кабеле"
                10 -> holder.itemView.name.text = "Минимальный размер защитного проводника"
            }
        }

        holder.itemView.setOnClickListener {
            listener.invoke(data)
        }
    }
}
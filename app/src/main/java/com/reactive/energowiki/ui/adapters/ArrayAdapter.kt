package com.reactive.energowiki.ui.adapters

import android.content.Context
import android.os.Build
import android.widget.BaseAdapter
import android.widget.Filterable
import android.widget.ThemedSpinnerAdapter
import androidx.annotation.RequiresApi


 class ArrayAdapter(
     resource: Int,
     textViewResourceId: Int,
     objects: ArrayList<String>)
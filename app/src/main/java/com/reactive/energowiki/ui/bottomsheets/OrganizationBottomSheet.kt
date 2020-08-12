package com.reactive.energowiki.ui.bottomsheets

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reactive.energowiki.R
import com.reactive.energowiki.network.Documents
import com.reactive.energowiki.ui.activities.getLangText
import com.reactive.energowiki.ui.activities.setLangText
import com.reactive.energowiki.utils.bottomsheet.BottomSheetRoundedFragment
import kotlinx.android.synthetic.main.bottomsheet_organization.*

class OrganizationBottomSheet : BottomSheetRoundedFragment(R.layout.bottomsheet_organization),
    OnMapReadyCallback {

    companion object {
        private lateinit var data: Documents
        fun newInstance(it: Documents): OrganizationBottomSheet {
            data = it
            return OrganizationBottomSheet()
        }
    }

    private var mMap: GoogleMap? = null

    override fun initialize() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        close.setOnClickListener { dismiss() }
        header.setLangText(data.name_ru, data.name_uz)
        address.setLangText(data.address_ru, data.address_uz)
        time.setLangText(data.work_time_ru, data.work_time_uz)
        phone.text = data.phone
        mail.text = data.email
        web.text = data.ws

        move()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        move()
    }

    private fun move() {
        mMap?.let {
            val marker = LatLng(data.longitude.toDouble(), data.latitude.toDouble())
            it.addMarker(
                MarkerOptions().position(marker).title(getLangText(data.name_ru, data.name_uz))
            )
            it.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 16.0f))
        }
    }
}
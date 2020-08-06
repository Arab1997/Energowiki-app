package com.reactive.energowiki.ui.bottomsheets

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reactive.energowiki.R
import com.reactive.energowiki.network.Documents
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

    private lateinit var mMap: GoogleMap

    override fun initialize() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        close.setOnClickListener { dismiss() }
        header.setLangText(data.title_uz, data.title_uz)
        address.setLangText(data.address_uz, data.address_ru)
        time.setLangText(data.work_time_uz, data.work_time_ru)
        phone.text = data.phone
        mail.text = data.email
        web.text = data.ws
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
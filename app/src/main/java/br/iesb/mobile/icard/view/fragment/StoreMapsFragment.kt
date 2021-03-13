package br.iesb.mobile.icard.view.fragment

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentStoreMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates

class StoreMapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentStoreMapsBinding

    private val MAP_REQUEST_TICKET = 9999

    private lateinit var googleMap: GoogleMap
    private lateinit var locationManager: LocationManager

    lateinit var imageUrl: String
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    var idStore by Delegates.notNull<Int>()
    lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = FragmentStoreMapsBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idStore = requireArguments().getInt("idStore")
        imageUrl = requireArguments().getString("imageUrl").toString()
        latitude = requireArguments().getDouble("latitude")
        longitude = requireArguments().getDouble("longitude")

        bundle = bundleOf(
            "idStore" to idStore,
            "imageUrl" to imageUrl,
            "latitude" to latitude,
            "longitude" to longitude
        )

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val mapFragment = childFragmentManager.findFragmentById((R.id.map)) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val positionStore = LatLng(
            requireArguments().getDouble("latitude"),
            requireArguments().getDouble("longitude")
        )

        val pinStore = MarkerOptions().position(positionStore)

        googleMap.addMarker(pinStore)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(positionStore, 18f))
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }


    private fun checkPermission() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            getActivity()?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    MAP_REQUEST_TICKET
                )
            }
        } else {
            setupLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MAP_REQUEST_TICKET) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupLocation()
            } else {
                showSnackbar("A permissão de localização é obrigatória!") {
                    checkPermission()
                }
            }
        }
    }

    fun setupLocation() {

        if (
            context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            3000,
            1.0f,
            locationListener
        )
    }

    private fun showSnackbar(msg: String, callback: () -> Unit) {
        val rootView = getView()?.findViewById<View>(android.R.id.content)?.rootView
        if (rootView != null) {
            Snackbar
                .make(rootView, msg, Snackbar.LENGTH_LONG)
                .setAction("Aceito") {
                    callback()
                }
                .show()
        }
    }

    private val locationListener = LocationListener { localizacao ->
        Log.d("MAPLOCATION", "Latitude: ${localizacao.latitude} Longitude ${localizacao.longitude}")
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home ->{
                screenDetailStore()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun screenDetailStore() {
        findNavController().navigate(R.id.action_storeMapsFragment_to_storeDetailFragment, bundle)
    }


}
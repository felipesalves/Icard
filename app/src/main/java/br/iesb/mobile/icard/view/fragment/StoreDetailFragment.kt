package br.iesb.mobile.icard.view.fragment

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentStoreDetailBinding
import br.iesb.mobile.icard.view.viewModel.StoreListViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_store_detail.*
import kotlin.properties.Delegates


@AndroidEntryPoint
@WithFragmentBindings
class StoreDetailFragment : Fragment() {


    private lateinit var binding: FragmentStoreDetailBinding
    private val viewModel: StoreListViewModel by lazy {
        ViewModelProvider(this).get(StoreListViewModel::class.java)
    }


    lateinit var front_animation_card: AnimatorSet
    lateinit var back_animation_card: AnimatorSet
    var isFront = true

    lateinit var bundleDetail: Bundle

    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    var idStore by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = ""

        val scale: Float = context?.resources?.displayMetrics!!.density
        frontCard.cameraDistance = 8000 * scale
        backCard.cameraDistance = 8000 * scale

        front_animation_card =
            AnimatorInflater.loadAnimator(context, R.animator.card_front_animation) as AnimatorSet
        back_animation_card =
            AnimatorInflater.loadAnimator(context, R.animator.card_back_animation) as AnimatorSet

        frontCard.setOnClickListener { showCard() }
        backCard.setOnClickListener { showCard() }
        location.setOnClickListener { showMaps() }
        promotions.setOnClickListener { showProducts() }
        storeDetail()
    }

    fun showCard() {
        if (isFront) {
            front_animation_card.setTarget(frontCard)
            back_animation_card.setTarget(backCard)
            front_animation_card.start()
            back_animation_card.start()
            isFront = false
        } else {
            front_animation_card.setTarget(backCard)
            back_animation_card.setTarget(frontCard)
            back_animation_card.start()
            front_animation_card.start()
            isFront = true
        }
    }

    fun showMaps() {
        findNavController().navigate(
            R.id.action_storeDetailFragment_to_storeMapsFragment,
            bundleDetail
        )
    }

    fun showProducts() {
        findNavController().navigate(
            R.id.action_storeDetailFragment_to_productsListFragment,
            bundleDetail
        )
    }

    @SuppressLint("ResourceType")
    private fun storeDetail() {

        idStore = requireArguments().getInt("idStore")
        val imageUrl = requireArguments().getString("imageUrl")
        latitude = requireArguments().getDouble("latitude")
        longitude = requireArguments().getDouble("longitude")

        bundleDetail = bundleOf(
            "idStore" to idStore,
            "imageUrl" to imageUrl,
            "latitude" to latitude,
            "longitude" to longitude
        )

        Picasso.get().load(imageUrl).into(binding.frontCardImage)

        viewModel.storeDetail.observe(viewLifecycleOwner) {
            (activity as AppCompatActivity).supportActionBar?.title = it.name
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            backCard.setCardBackgroundColor(Color.parseColor(it.primaryColor))
            cardCheckbar.setBackgroundColor(Color.parseColor(it.secondColor))
            it.loyaltyPoints?.toInt()?.let { points -> checkCardPoint(points) }
        }

        viewModel.storeDetail(idStore)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                screenListStore()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun screenListStore() {
        findNavController().navigate(R.id.action_storeDetailFragment_to_mainTabsFragment)
    }

    @SuppressLint("ResourceAsColor")
    fun checkCardPoint(points: Int) {
        when (points) {
            1 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
            }
            2 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
            }
            3 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
            }
            4 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
                cardCheck4.setCardBackgroundColor(R.color.ligth_green)
            }
            5 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
                cardCheck4.setCardBackgroundColor(R.color.ligth_green)
                cardCheck5.setCardBackgroundColor(R.color.ligth_green)
            }
            6 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
                cardCheck4.setCardBackgroundColor(R.color.ligth_green)
                cardCheck5.setCardBackgroundColor(R.color.ligth_green)
                cardCheck6.setCardBackgroundColor(R.color.ligth_green)
            }
            7 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
                cardCheck4.setCardBackgroundColor(R.color.ligth_green)
                cardCheck5.setCardBackgroundColor(R.color.ligth_green)
                cardCheck6.setCardBackgroundColor(R.color.ligth_green)
                cardCheck7.setCardBackgroundColor(R.color.ligth_green)
            }
            8 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
                cardCheck4.setCardBackgroundColor(R.color.ligth_green)
                cardCheck5.setCardBackgroundColor(R.color.ligth_green)
                cardCheck6.setCardBackgroundColor(R.color.ligth_green)
                cardCheck7.setCardBackgroundColor(R.color.ligth_green)
                cardCheck8.setCardBackgroundColor(R.color.ligth_green)
            }
            9 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
                cardCheck4.setCardBackgroundColor(R.color.ligth_green)
                cardCheck5.setCardBackgroundColor(R.color.ligth_green)
                cardCheck6.setCardBackgroundColor(R.color.ligth_green)
                cardCheck7.setCardBackgroundColor(R.color.ligth_green)
                cardCheck8.setCardBackgroundColor(R.color.ligth_green)
                cardCheck9.setCardBackgroundColor(R.color.ligth_green)
            }
            10 -> {
                cardCheck1.setCardBackgroundColor(R.color.ligth_green)
                cardCheck2.setCardBackgroundColor(R.color.ligth_green)
                cardCheck3.setCardBackgroundColor(R.color.ligth_green)
                cardCheck4.setCardBackgroundColor(R.color.ligth_green)
                cardCheck5.setCardBackgroundColor(R.color.ligth_green)
                cardCheck6.setCardBackgroundColor(R.color.ligth_green)
                cardCheck7.setCardBackgroundColor(R.color.ligth_green)
                cardCheck8.setCardBackgroundColor(R.color.ligth_green)
                cardCheck9.setCardBackgroundColor(R.color.ligth_green)
                cardCheck10.setCardBackgroundColor(R.color.ligth_green)
            }
        }
    }


}
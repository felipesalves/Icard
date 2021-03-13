package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import br.iesb.mobile.icard.databinding.FragmentReadQrCodeBinding
import kotlinx.android.synthetic.main.fragment_read_qr_code.*
import kotlinx.android.synthetic.main.fragment_read_qr_code.view.*


class ReadQrCodeFragment : Fragment() {

    lateinit var cardView: CardView
    private lateinit var binding: FragmentReadQrCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReadQrCodeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val description = tvQrCodeDescription

        cardView = view.cvQrCode

        cardView.setAlpha(0F)
        description.setAlpha(0F)


        description.translationY = 300F
        cardView.translationY = 300F

        description.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(500)
            .start()
        cardView.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(600).start()
    }


}
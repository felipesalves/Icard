package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentDialogFlowChatBinding
import br.iesb.mobile.icard.domain.dialogChat.MessageChat
import br.iesb.mobile.icard.view.ReceiveMessageItem
import br.iesb.mobile.icard.view.SendMessageItem
import br.iesb.mobile.icard.view.adapter.ProductsAdapter
import br.iesb.mobile.icard.view.viewModel.DialogFlowChatViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_dialog_flow_chat.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
@WithFragmentBindings
class DialogFlowChatFragment : Fragment() {

    private lateinit var binding: FragmentDialogFlowChatBinding
    private val viewModel: DialogFlowChatViewModel by viewModels()

    private val messageAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogFlowChatBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewChat.adapter = messageAdapter

        button.setOnClickListener {

            if(!editText.text.toString().isEmpty()){
            val message = MessageChat(text = editText.text.toString(), sendBy = "me")
            val sendMessageItem = SendMessageItem(message)

            messageAdapter.add(sendMessageItem)

                sendMensage(editText.text.toString())

                editText.text.clear()
            }

        }
    }

    fun sendMensage(mensagem: String) {

        recyclerViewChat.smoothScrollToPosition(messageAdapter.itemCount)

        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            val mensagemDialog = viewModel.responseDialog.value
                val receive = mensagemDialog?.let { MessageChat(text = it, sendBy = "chat") }
                val receiveItem =
                    receive?.let { ReceiveMessageItem(it) }
            if (receiveItem != null) {
                messageAdapter.add(receiveItem)
            }

            recyclerViewChat.smoothScrollToPosition(messageAdapter.itemCount)

        }

        viewModel.sendMensage(mensagem)
    }

}
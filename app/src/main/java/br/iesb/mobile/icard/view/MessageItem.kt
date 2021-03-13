package br.iesb.mobile.icard.view

import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.ItemMessageReceiveBinding
import br.iesb.mobile.icard.databinding.ItemMessageSendBinding
import br.iesb.mobile.icard.domain.dialogChat.MessageChat
import com.xwray.groupie.databinding.BindableItem

class SendMessageItem(private val message: MessageChat) : BindableItem<ItemMessageSendBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_message_send
    }

    override fun bind(viewBinding: ItemMessageSendBinding, position: Int) {
        viewBinding.messageChat = message
    }
}

class ReceiveMessageItem(private val message: MessageChat) : BindableItem<ItemMessageReceiveBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_message_receive
    }

    override fun bind(viewBinding: ItemMessageReceiveBinding, position: Int) {
        viewBinding.messageChatReceive = message
    }
}

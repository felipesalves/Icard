package br.iesb.mobile.icard.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.iesb.mobile.icard.view.fragment.*

class MainTabsAdapter (
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    private val getTitle =
        arrayOf("Lojas", "Qr Code", "Faq")

    override fun getPageTitle(position: Int): CharSequence? {
        return getTitle[position]
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return StoreListFragment()
            }
            1 -> {
                return ReadQrCodeFragment()
            }
            2 -> {
                return DialogFlowChatFragment()
            }
            else -> return LoginFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}
@file:Suppress("DEPRECATION")

package br.iesb.mobile.icard.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.iesb.mobile.icard.view.fragment.CreateCountFragment
import br.iesb.mobile.icard.view.fragment.LoginFragment

class LoginAdapter (
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    private val getTitle =
        arrayOf("Login", "Criar Conta")

    override fun getPageTitle(position: Int): CharSequence? {
        return getTitle[position]
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return LoginFragment()
            }
            1 -> {
                return CreateCountFragment()
            }
            else -> return LoginFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }


}
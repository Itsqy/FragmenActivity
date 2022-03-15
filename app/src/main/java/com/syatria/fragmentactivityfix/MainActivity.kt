package com.syatria.fragmentactivityfix

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val fragHome: Fragment = HomeFragment()
    val fragInfo: Fragment = InfoFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNavBottom()
    }

    private fun setUpNavBottom() {
        fm.beginTransaction().add(R.id.nav_content, fragHome).show(fragHome).commit()
        fm.beginTransaction().add(R.id.nav_content, fragInfo).hide(fragInfo).commit()

        menu = btn_nav_view.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        btn_nav_view.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_beranda -> {
                    callFrag(0, fragHome)
                }
                R.id.navigation_info -> {
                    callFrag(1, fragInfo)
                }
            }
            false


        }
    }

    private fun callFrag(i: Int, fragment: Fragment) {

        menuItem = menu.getItem(i)
        menuItem.isChecked = true


        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment

    }
}
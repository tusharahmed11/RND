package com.example.demoprojectforrnd.activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demoprojectforrnd.R
import com.example.demoprojectforrnd.customviews.lottinavview.*
import com.example.demoprojectforrnd.fragment.FirstFragment
import com.example.demoprojectforrnd.fragment.FourthFragment
import com.example.demoprojectforrnd.fragment.SecondFragment
import com.example.demoprojectforrnd.fragment.ThirdFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ILottieBottomNavCallback {

  /*  private var bottomNav = LottieBottomNav
    private var list :ArrayList<MenuItem> = ArrayList<MenuItem>(4)
    private var mFragments: ArrayList<Fragment> = ArrayList()*/

    private var  bottomNav: LottieBottomNav? = null
    private var list: ArrayList<MenuItem>? = null

    private var mFragments: ArrayList<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = FirstFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }

        bottomNav = findViewById<LottieBottomNav>(R.id.bottomNavigationView)

        mFragments = ArrayList(4)

        mFragments!!.add(FirstFragment())
        mFragments!!.add(SecondFragment())

       /* val mailFragment = MailFragment()
        mailFragment.setClickHandler(this)*/
        mFragments!!.add(ThirdFragment())

        mFragments!!.add(FourthFragment())

      //  findViewById<BottomNavigationView>(R.id.bottomNavigationView).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var fontItem: FontItem = FontBuilder.create("Dashboard")
            .selectedTextColor(Color.BLACK)
            .unSelectedTextColor(Color.GRAY)
            .selectedTextSize(16) //SP
            .unSelectedTextSize(12) //SP
            .setTypeface(Typeface.createFromAsset(assets, "coffeesugar.ttf"))
            .build()

        val item1: MenuItem =
            MenuItemBuilder.create("home_active.json", MenuItem.Source.Assets, fontItem, "dash")
                .pausedProgress(1f)
                .loop(true)
                .build()

        val spannableString = SpannableString("Gifts")
        spannableString.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        fontItem = FontBuilder.create(fontItem).setTitle(spannableString).build()
        val item2: MenuItem = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("message_active.json")
            .unSelectedLottieName("message_active.json")
            .loop(true)
            .build()

        fontItem = FontBuilder.create(fontItem).setTitle("Mail").build()
        val item3: MenuItem = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("message_active.json")
            .unSelectedLottieName("message_active.json")
            .loop(true)
            .pausedProgress(0.75f)
            .build()

        fontItem = FontBuilder.create(fontItem).setTitle("Settings").build()
        val item4: MenuItem = MenuItemBuilder.createFrom(item1, fontItem)
            .selectedLottieName("message_active.json")
            .unSelectedLottieName("message_active.json")
            .loop(true)
            .build()

        list = ArrayList(4)
        list!!.add(item1)
        list!!.add(item2)
        list!!.add(item3)
        list!!.add(item4)

        bottomNav!!.setCallback(this)
        bottomNav!!.setMenuItemList(list!!)
        bottomNav!!.selectedIndex = 0

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_alarm -> {
                val fragment = FirstFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_clock -> {
                val fragment = SecondFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_timer -> {
                val fragment = ThirdFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_stopwatch -> {
                val fragment = FourthFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onAnimationEnd(index: Int, menuItem: MenuItem?) {
        if (index == 2 && menuItem!!.tag != null && "cupid".equals(
                menuItem.tag.toString(), ignoreCase = true
            )
        ) {
            restoreMenuForMessage(menuItem)
        }
    }

    override fun onAnimationCancel(index: Int, menuItem: MenuItem?) {
        if (index == 2 && menuItem!!.tag != null && "cupid".equals(
                menuItem.tag.toString(), ignoreCase = true
            )
        ) {
            restoreMenuForMessage(menuItem)
        }
    }

    override fun onAnimationStart(index: Int, menuItem: MenuItem?) {
        //TODO("Not yet implemented")
    }

    override fun onMenuSelected(oldIndex: Int, newIndex: Int, menuItem: MenuItem?) {
        val fragment = FirstFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, mFragments!!.get(newIndex), fragment.javaClass.getSimpleName())
            .commit()
    }

    private fun restoreMenuForMessage(menuItem: MenuItem) {
        val item = MenuItemBuilder.createFrom(menuItem)
            .selectedLottieName("message.json")
            .build()
        bottomNav!!.updateMenuItemFor(2, item)
    }

}
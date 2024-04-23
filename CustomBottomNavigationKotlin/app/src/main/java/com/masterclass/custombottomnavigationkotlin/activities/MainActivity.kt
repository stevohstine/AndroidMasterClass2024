package com.masterclass.custombottomnavigationkotlin.activities

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.masterclass.custombottomnavigationkotlin.R
import com.masterclass.custombottomnavigationkotlin.fragments.AccountFragment
import com.masterclass.custombottomnavigationkotlin.fragments.HomeFragment
import com.masterclass.custombottomnavigationkotlin.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private val colorTransitionDuration = 300
    private val clickScale = 0.9f // Scale factor when clicked
    private val clickDuration = 100L // Duration of the click animation in milliseconds

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val accountButton: RelativeLayout = findViewById(R.id.account)
        val accountButtonImageView: ImageView = findViewById(R.id.accountImageView)
        val accountButtonTextView: TextView = findViewById(R.id.accountTextView)
        val homeButton: ImageView = findViewById(R.id.home)
        val settingsButton: RelativeLayout = findViewById(R.id.settings)
        val settingsButtonImageView: ImageView = findViewById(R.id.settingsImageView)
        val settingsButtonTextView: TextView = findViewById(R.id.settingsTextView)

        accountButton.setOnClickListener{
            loadFragment(AccountFragment())

            animateClick(accountButtonImageView)
            animateClick(accountButtonTextView)

            animateColorTransition(accountButtonImageView, accountButtonTextView, R.color.grey, R.color.masterColor)
            animateColorTransition(settingsButtonImageView, settingsButtonTextView, R.color.masterColor, R.color.grey)
        }

        homeButton.setOnClickListener{
            loadFragment(HomeFragment())

            animateClick(homeButton)

            animateColorTransition(accountButtonImageView, accountButtonTextView, R.color.grey, R.color.grey)
            animateColorTransition(settingsButtonImageView, settingsButtonTextView, R.color.grey, R.color.grey)
        }

        settingsButton.setOnClickListener{
            loadFragment(SettingsFragment())

            animateClick(settingsButtonImageView)
            animateClick(settingsButtonTextView)

            animateColorTransition(accountButtonImageView, accountButtonTextView, R.color.masterColor, R.color.grey)
            animateColorTransition(settingsButtonImageView, settingsButtonTextView, R.color.grey, R.color.masterColor)
        }

        loadFragment(HomeFragment())
    }

    private fun animateClick(view: View) {
        val clickAnimation = ScaleAnimation(1f, clickScale, 1f, clickScale,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        clickAnimation.duration = clickDuration
        clickAnimation.fillAfter = true
        view.startAnimation(clickAnimation)
    }

    private fun animateColorTransition(imageView: ImageView, textView: TextView, startColorResId: Int, endColorResId: Int) {
        val startColor = ContextCompat.getColor(this, startColorResId)
        val endColor = ContextCompat.getColor(this, endColorResId)

        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        colorAnimator.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN)
            textView.setTextColor(color)
        }
        colorAnimator.duration = colorTransitionDuration.toLong()
        colorAnimator.start()
    }

    private fun loadFragment(selectedFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, selectedFragment)
        transaction.addToBackStack(selectedFragment.javaClass.simpleName)
        transaction.commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()

        loadFragment(HomeFragment())

        closeApp()

//        val fm = supportFragmentManager
//
//        val f = supportFragmentManager.findFragmentById(R.id.fragment_container)
//        if (f is HomeFragment || fm.backStackEntryCount - 1 >= 0) {
//            closeApp()
//        } else {
//            val lastFragEntry = fm.backStackEntryCount - 1
//            Log.e("LAST FRAG", lastFragEntry.toString())
//            try {
//                if (1 == lastFragEntry) {
//                    closeApp()
//                } else {
//                    fm.popBackStack()
//                }
//            } catch (e: Exception) {
//                closeApp()
//            }
//        }
    }

    private fun closeApp() {
        if (doubleBackToExitPressedOnce) {
            finish()
            Handler().postDelayed({  onBackPressed() }, 300)
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
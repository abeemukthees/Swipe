package msa.swipe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val swipeCardListController by lazy { SwipeCardListController() }
    private val cardStackLayoutManager by lazy {
        CardStackLayoutManager(this, object : CardStackListener {

            override fun onCardDisappeared(view: View?, position: Int) {
                Log.d(TAG, "onCardDisappeared $position")


            }

            override fun onCardDragging(direction: Direction?, ratio: Float) {
                Log.d(TAG, "onCardDragging")
            }

            override fun onCardSwiped(direction: Direction?) {
                Log.d(TAG, "onCardSwiped $direction")
            }

            override fun onCardCanceled() {
                Log.d(TAG, "onCardCanceled")
            }

            override fun onCardAppeared(view: View?, position: Int) {
                Log.d(TAG, "onCardAppeared $position")
                progressBar.progress = position + 1
                if (position == 10) Toast.makeText(
                    this@MainActivity,
                    "Hey, You reached the end.",
                    Toast.LENGTH_SHORT
                ).show()

            }

            override fun onCardRewound() {

            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        cardStackView.adapter = swipeCardListController.adapter
        cardStackLayoutManager.setStackFrom(StackFrom.None)
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL)
        cardStackLayoutManager.setCanScrollVertical(false)

        val rewindSetting = RewindAnimationSetting.Builder()
            .setDirection(Direction.Bottom)
            .setDuration(100)
            .build()

        cardStackLayoutManager.setRewindAnimationSetting(rewindSetting)

        cardStackView.layoutManager = cardStackLayoutManager
        fab_back.setOnClickListener { cardStackView.rewind() }
        fab_next.setOnClickListener { cardStackView.swipe() }
        fab_restart.setOnClickListener {
            cardStackView.scrollToPosition(0)
            progressBar.progress = 1
        }

        progressBar.max = 11

        setupViews()

    }

    private fun setupViews() {

        val lessonTitles = resources.getStringArray(R.array.lesson_titles)
        val icons = resources.obtainTypedArray(R.array.lesson_icons)
        val lessons = arrayListOf<Lesson>()
        for (i in 0..9) {
            lessons.add(Lesson(i, lessonTitles[i], icons.getResourceId(i, -1)))
        }
        swipeCardListController.setLessons(lessons)
        icons.recycle()
    }
}


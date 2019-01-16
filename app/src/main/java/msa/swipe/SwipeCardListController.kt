package msa.swipe

import com.airbnb.epoxy.TypedEpoxyController

/**
 * Created by Abhi Muktheeswarar.
 */

class SwipeCardListController : TypedEpoxyController<List<Lesson>>() {

    fun setLessons(lessons: List<Lesson>) {
        setData(lessons)
    }

    override fun buildModels(data: List<Lesson>) {

        data.forEach { lesson ->

            swipeCardItem {
                id(lesson.id)
                lessonId(lesson.id)
                title(lesson.title)
                iconResId(lesson.iconResId)
            }
        }
    }
}
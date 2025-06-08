package ru.fefu.activitiesfefu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.fefu.activitiesfefu.data.ActivityType
import java.util.Date

class HistoryFragment : BaseActivityListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val dummyActivities = mutableListOf<ActivityListItem>()
        val now = Date()
        val yesterday = Date(now.time - 24 * 60 * 60 * 1000)

        dummyActivities.add(ActivityListItem.Section("Вчера"))

        // Серфинг
        dummyActivities.add(
            ActivityListItem.Activity(
                id = 1,
                type = ActivityType.SURFING,
                startDate = yesterday,
                endDate = Date(yesterday.time + 2 * 60 * 60 * 1000 + 46 * 60 * 1000), // 2 часа 46 минут
                distance = "14.32 км",
                duration = "2 часа 46 минут"
            )
        )

        // Качели
        dummyActivities.add(
            ActivityListItem.Activity(
                id = 2,
                type = ActivityType.SWING,
                startDate = yesterday,
                endDate = Date(yesterday.time + 14 * 60 * 60 * 1000 + 48 * 60 * 1000), // 14 часов 48 минут
                distance = "228 м",
                duration = "14 часов 48 минут"
            )
        )

        // Езда на кадиллаке
        dummyActivities.add(
            ActivityListItem.Activity(
                id = 3,
                type = ActivityType.CAR,
                startDate = yesterday,
                endDate = Date(yesterday.time + 1 * 60 * 60 * 1000 + 10 * 60 * 1000), // 1 час 10 минут
                distance = "10 км",
                duration = "1 час 10 минут"
            )
        )

        updateActivityList(dummyActivities)
    }

    override fun updateActivityList(items: List<ActivityListItem>) {
        // Это может быть изменено позже для отображения данных пользователей
        activityListAdapter?.submitList(items)
    }
}
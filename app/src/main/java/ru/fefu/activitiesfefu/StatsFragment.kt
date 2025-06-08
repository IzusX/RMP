package ru.fefu.activitiesfefu

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.fefu.activitiesfefu.data.ActivityDao
import ru.fefu.activitiesfefu.data.ActivityEntity
import ru.fefu.activitiesfefu.data.ActivityType
import java.util.Date
import kotlin.random.Random

class StatsFragment : BaseActivityListFragment() {

    private lateinit var activityDao: ActivityDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDao = (requireActivity().application as ActivityApplication).database.activityDao()
    }

    override fun onResume() {
        super.onResume()
        activityDao.getAllActivities().observe(viewLifecycleOwner, Observer {
            activities ->
            Log.d("StatsFragment", "Received activities: ${activities.size}")

            if (activities.isEmpty()) {
                // Insert dummy data if the database is empty
                lifecycleScope.launch(Dispatchers.IO) {
                    val dummyActivities = generateDummyActivities()
                    dummyActivities.forEach { activityDao.insertActivity(it) }
                }
            } else {
                val activityListItems = activities.map { activityEntity ->
                    ActivityListItem.Activity(
                        id = activityEntity.id,
                        type = activityEntity.type,
                        startDate = activityEntity.startDate,
                        endDate = activityEntity.endDate,
                        distance = String.format("%.2f км", activityEntity.distance), // Format as needed
                        duration = formatDuration(activityEntity.durationMillis) // Format as needed
                    )
                }
                updateActivityList(activityListItems)
            }
        })
    }

    override fun updateActivityList(newActivities: List<ActivityListItem>) {
        val items = mutableListOf<ActivityListItem>()
        items.add(ActivityListItem.Section("Все активности"))
        items.addAll(newActivities.filterIsInstance<ActivityListItem.Activity>().sortedByDescending { it.startDate })

        activityListAdapter?.submitList(items)
    }

    private fun generateDummyActivities(): List<ActivityEntity> {
        val dummyList = mutableListOf<ActivityEntity>()
        val random = Random(System.currentTimeMillis())

        val now = Date()
        val twentyFourHoursAgo = Date(now.time - 24 * 60 * 60 * 1000)
        val twoDaysAgo = Date(now.time - 2 * 24 * 60 * 60 * 1000)

        dummyList.add(ActivityEntity(
            id = 0,
            type = ActivityType.SURFING,
            startDate = twoDaysAgo,
            endDate = Date(twoDaysAgo.time + random.nextLong(3600000, 10800000)), // 1-3 hours
            distance = random.nextFloat() * 20,
            durationMillis = random.nextLong(3600000, 10800000), // 1-3 hours
            coordinates = emptyList()
        ))
        dummyList.add(ActivityEntity(
            id = 0,
            type = ActivityType.BIKE,
            startDate = twentyFourHoursAgo,
            endDate = Date(twentyFourHoursAgo.time + random.nextLong(1800000, 7200000)), // 30 mins - 2 hours
            distance = random.nextFloat() * 50,
            durationMillis = random.nextLong(1800000, 7200000), // 30 mins - 2 hours
            coordinates = emptyList()
        ))
        dummyList.add(ActivityEntity(
            id = 0,
            type = ActivityType.RUN,
            startDate = now,
            endDate = Date(now.time + random.nextLong(600000, 3600000)), // 10 mins - 1 hour
            distance = random.nextFloat() * 10,
            durationMillis = random.nextLong(600000, 3600000), // 10 mins - 1 hour
            coordinates = emptyList()
        ))
        return dummyList
    }

    private fun formatDuration(millis: Long): String {
        val hours = millis / (1000 * 60 * 60)
        val minutes = (millis % (1000 * 60 * 60)) / (1000 * 60)
        return when {
            hours > 0 -> String.format("%d ч %d мин", hours, minutes)
            else -> String.format("%d мин", minutes)
        }
    }
}
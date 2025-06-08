package ru.fefu.activitiesfefu

sealed class ActivityListItem {
    data class Section(val title: String) : ActivityListItem()
    data class Activity(
        val distance: String,
        val duration: String,
        val type: String,
        val user: String? = null, // null для "Моя", ник для "Пользователей"
        val timeAgo: String
    ) : ActivityListItem()
} 
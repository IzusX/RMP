package ru.fefu.activitiesfefu

import android.os.Bundle

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsFragment : BaseActivityListFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun getActivityList(): List<ActivityListItem> = listOf(
        ActivityListItem.Section("Вчера"),
        ActivityListItem.Activity(
            distance = "14.32 км",
            duration = "2 часа 46 минут",
            type = "Серфинг 🏄‍♂️",
            timeAgo = "14 часов назад"
        ),
        ActivityListItem.Section("Май 2022 года"),
        ActivityListItem.Activity(
            distance = "1 000 м",
            duration = "60 минут",
            type = "Велосипед 🚴‍♂️",
            timeAgo = "29.05.2022"
        )
    )

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
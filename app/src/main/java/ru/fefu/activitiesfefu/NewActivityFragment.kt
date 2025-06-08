package ru.fefu.activitiesfefu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitiesfefu.databinding.FragmentNewActivityBinding

class NewActivityFragment : Fragment() {
    private var _binding: FragmentNewActivityBinding? = null
    private val binding get() = _binding!!

    private lateinit var activityTypeAdapter: ActivityTypeAdapter
    private var selectedActivityType: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityTypes = listOf("Велосипед 🚴‍♂️", "Бег 🏃‍♂️", "Шаг 🚶‍♂️")
        activityTypeAdapter = ActivityTypeAdapter(activityTypes) { selectedType ->
            selectedActivityType = selectedType
        }

        binding.activityTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.activityTypeRecyclerView.adapter = activityTypeAdapter

        // Обработка клика по кнопке "Начать"
        binding.startButton.setOnClickListener {
            selectedActivityType?.let { type ->
                val fragment = ActiveActivityFragment()
                fragment.arguments = Bundle().apply {
                    putString("activityType", type)
                }
                parentFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .hide(this)
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            } ?: run {
                // TODO: Показать сообщение, что нужно выбрать тип активности
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
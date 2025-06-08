package ru.fefu.activitiesfefu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitiesfefu.databinding.FragmentActiveActivityBinding

class ActiveActivityFragment : Fragment() {
    private var _binding: FragmentActiveActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActiveActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityType = arguments?.getString("activityType")
        binding.activityType.text = activityType
        // TODO: Реализовать логику для кнопок Пауза и Финиш

        binding.pauseButton.setOnClickListener {
            // TODO: Реализовать логику паузы
        }

        binding.finishButton.setOnClickListener {
            // TODO: Реализовать логику финиша
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
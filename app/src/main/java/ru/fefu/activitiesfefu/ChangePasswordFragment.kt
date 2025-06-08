package ru.fefu.activitiesfefu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitiesfefu.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : Fragment() {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.applyButton.setOnClickListener {
            // TODO: Реализовать логику смены пароля
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
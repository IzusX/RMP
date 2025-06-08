package ru.fefu.activitiesfefu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.fefu.activitiesfefu.databinding.FragmentProfileEditBinding

class ProfileEditFragment : Fragment() {
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val CHANGE_PASSWORD_TAG = "CHANGE_PASSWORD_TAG"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.findViewById<TextView>(R.id.save_button).setOnClickListener {
            // TODO: Реализовать сохранение данных профиля
        }

        binding.changePasswordButton.setOnClickListener {
            val changePasswordFragment = parentFragmentManager.findFragmentByTag(CHANGE_PASSWORD_TAG)
            parentFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                parentFragmentManager.fragments.forEach { hide(it) }
                if (changePasswordFragment == null) {
                    add(R.id.fragment_container, ChangePasswordFragment(), CHANGE_PASSWORD_TAG)
                } else {
                    show(changePasswordFragment)
                }
                addToBackStack(null)
                commit()
            }
        }

        binding.logoutButton.setOnClickListener {
            // TODO: Реализовать выход из аккаунта
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
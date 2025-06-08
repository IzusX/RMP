package ru.fefu.activitiesfefu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitiesfefu.databinding.FragmentActivityDetailsBinding

class ActivityDetailsFragment : Fragment() {
    private var _binding: FragmentActivityDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        arguments?.let { args ->
            binding.detailsDistance.text = args.getString("distance", "")
            binding.detailsDuration.text = args.getString("duration", "")
            binding.toolbar.title = args.getString("type", "")
            binding.detailsTimeAgo.text = args.getString("timeAgo", "")
            args.getString("user")?.let { user ->
                binding.userText.text = user
                binding.userText.visibility = View.VISIBLE
            } ?: run {
                binding.userText.visibility = View.GONE
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
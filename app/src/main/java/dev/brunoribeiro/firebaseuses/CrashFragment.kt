package dev.brunoribeiro.firebaseuses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import dev.brunoribeiro.firebaseuses.databinding.FragmentCrashBinding


class CrashFragment : Fragment() {

    private var _binding: FragmentCrashBinding? = null
    private val binding get() = _binding!!
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrashBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnEvent.setOnClickListener {
            Toast.makeText(view.context, "Nada ainda", Toast.LENGTH_SHORT).show()
        }

        binding.btnCrash.setOnClickListener {
            Toast.makeText(view.context, "Nada ainda", Toast.LENGTH_SHORT).show()
        }


        return view
    }


}
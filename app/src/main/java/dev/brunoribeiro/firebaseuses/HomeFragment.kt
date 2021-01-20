package dev.brunoribeiro.firebaseuses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import dev.brunoribeiro.firebaseuses.databinding.ActivityMainBinding
import dev.brunoribeiro.firebaseuses.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var firebaseAnalytics: FirebaseAnalytics

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val nameUser = activity?.intent?.getStringExtra("name")
        binding.tvHome.text = "Olá, " + nameUser


        binding.btnCrud.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_crudFragment)
        }

        binding.btnTeste.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_crashFragment)
        }

        binding.btnStorage.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_storageFragment)
        }


        firebaseAnalytics = FirebaseAnalytics.getInstance(view.context)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM){
            param(FirebaseAnalytics.Param.ITEM_NAME, "Caneta")
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
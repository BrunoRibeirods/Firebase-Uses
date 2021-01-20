package dev.brunoribeiro.firebaseuses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.brunoribeiro.firebaseuses.databinding.FragmentHomeBinding
import dev.brunoribeiro.firebaseuses.databinding.FragmentStorageBinding


class StorageFragment : Fragment() {

    private var _binding: FragmentStorageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentStorageBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnStorageUpload.setOnClickListener {

        }



        return view
    }

}
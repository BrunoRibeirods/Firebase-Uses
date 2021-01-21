package dev.brunoribeiro.firebaseuses

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dev.brunoribeiro.firebaseuses.databinding.FragmentHomeBinding
import dev.brunoribeiro.firebaseuses.databinding.FragmentStorageBinding
import dmax.dialog.SpotsDialog


class StorageFragment : Fragment() {

    private var _binding: FragmentStorageBinding? = null
    private val binding get() = _binding!!
    lateinit var storageReference: StorageReference
    lateinit var alertDialog: AlertDialog
    private val CODE_IMG = 1000


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentStorageBinding.inflate(inflater, container, false)
        val view = binding.root
        config(view.context)

        binding.btnStorageUpload.setOnClickListener {
            setIntent()
        }



        return view
    }

    fun config(context: Context){
        alertDialog = SpotsDialog.Builder().setContext(context).build()
        storageReference = FirebaseStorage.getInstance().getReference("prod_img")
    }

    fun setIntent(){
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Captura IMG"), CODE_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODE_IMG){
            val uploadTask = storageReference.putFile(data?.data!!)
            uploadTask.continueWithTask {task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Chegando", Toast.LENGTH_SHORT).show()
                }
                storageReference.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val url = downloadUri!!.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                    Log.i("Link Direto ", url)

                    context?.let {
                        Glide.with(it)
                            .load(url)
                            .into(binding.ivStorage)
                    }
                }
            }
        }
    }

}
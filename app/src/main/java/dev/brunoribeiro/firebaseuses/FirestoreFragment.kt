package dev.brunoribeiro.firebaseuses

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dev.brunoribeiro.firebaseuses.databinding.FragmentFirestoreBinding


class FirestoreFragment : Fragment() {

    private var _binding: FragmentFirestoreBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var cr: CollectionReference
    private val TAG = "Firestore"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirestoreBinding.inflate(inflater, container, false)
        val view = binding.root
        config()
        readProds()



        binding.btnSave.setOnClickListener {
            val prod = getData()
            sendProd(prod)
        }


        binding.btnUpdate.setOnClickListener {
            val prod = getData()
            updateProd(prod)
        }

        binding.btnDelete.setOnClickListener {
            val prod = getData()
            deleteprod()
        }

       return view
    }

    fun config(){
        db = FirebaseFirestore.getInstance()
        cr = db.collection("products")
    }

    fun getData(): MutableMap<String, Any>{
        val prod: MutableMap<String, Any> = HashMap()


        prod["nome"] = binding.etNomeProd.text.toString()
        prod["qtd"] = binding.etQtdProd.text.toString()
        prod["preco"] = binding.etPrecoProd.text.toString()


        return prod
    }

    fun sendProd(prod: MutableMap<String, Any>){
        val nome = prod["nome"].toString()
       // val nome = binding.etNomeProd.text.toString()

        cr.document(nome).set(prod).addOnCompleteListener {
            Log.i(TAG, it.toString())
        }.addOnFailureListener {
            Log.i(TAG, it.toString())
        }
    }

    fun updateProd(prod: MutableMap<String, Any>){
        cr.document("Carro").update(prod).addOnSuccessListener{
            Log.i(TAG, "Produto Alterado!")
        }.addOnFailureListener {
            Log.e(TAG, it.toString())
        }
    }

    fun deleteprod(){
        cr.document("Carro").delete().addOnSuccessListener{
            Log.i(TAG, "Produto deletado!")
        }.addOnFailureListener {
            Log.e(TAG, it.toString())
        }
    }

    fun readProds(){
        cr.get()
            .addOnCompleteListener {task ->
                if (task.isSuccessful()) {
                    for (document in task.getResult()!!) {
                        Log.d(TAG, document.id + " => " + document.data)
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException())
                }}
    }


}


package com.example.beautybox.mainScreens.mainScreen.diary

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.beautybox.MainActivity
import com.example.beautybox.R
import com.example.beautybox.databinding.FragmentDiaryBinding
import com.example.beautybox.mainScreens.firebaseUtils.DatabaseUtils
import java.time.LocalDate
import java.util.Date

class DiaryFragment : Fragment() {

    private lateinit var binding: FragmentDiaryBinding
    private lateinit var diaryEntry: DiaryEntry
    private lateinit var dbFirebase: DatabaseUtils
    private val openDocumentLauncher = registerForActivityResult(
        object : ActivityResultContracts.OpenDocument(){
            override fun createIntent(context: Context, input: Array<String>): Intent {
                val intent = super.createIntent(context, input)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                return intent
            }
        }){uri ->
            uri?.let {
                onImagesSelected(uri)
            }

        }

    private fun onImagesSelected(uri: Uri) {
        binding.imageReal.setImageURI(uri)
        binding.addPhoto.isVisible = false
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiaryBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavView.isGone = true
        dbFirebase = DatabaseUtils().getInstance()
        diaryEntry = DiaryEntry(LocalDate.now().toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSkinCondition.adapter = SkinConditionAdapter(diaryEntry)
        binding.rvSkinCareMorning.adapter = CareMorningAdapter(diaryEntry)
        binding.rvSkinCareEvening.adapter = CareEveningAdapter(diaryEntry)

        binding.btnSave.setOnClickListener {
            diaryEntry.note = binding.etNote.text.toString()
            dbFirebase.sendDiaryEntryToFirebase(diaryEntry, (requireActivity() as MainActivity))
            Toast.makeText(requireContext(), "Успешно!", Toast.LENGTH_SHORT).show()
            binding.root.findNavController().navigate(R.id.action_diaryFragment_to_main_screen_item)
        }
        binding.addPhoto.setOnClickListener {
            openDocumentLauncher.launch(arrayOf("image/*"))
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

}
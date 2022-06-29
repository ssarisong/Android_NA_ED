package com.example.lab7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lab7.databinding.ItemDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemDialog(private val clickedItemPos: Int = -1) : BottomSheetDialogFragment() {
    private lateinit var binding: ItemDialogLayoutBinding

    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(clickedItemPos >= 0) {
            binding.editTextName.setText(viewModel.getItem(clickedItemPos).name)
            binding.editTextAddress.setText(viewModel.getItem(clickedItemPos).address)
        }

        binding.buttonOK.setOnClickListener {
            val item = Item(binding.editTextName.text.toString(), binding.editTextAddress.text.toString())
            if(clickedItemPos < 0)
                viewModel.addItem(item)
            else
                viewModel.updateItem(item, clickedItemPos)
            dismiss()
        }
    }
}
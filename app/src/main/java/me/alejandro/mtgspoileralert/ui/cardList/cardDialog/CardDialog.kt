package me.alejandro.mtgspoileralert.ui.cardList.cardDialog

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import me.alejandro.mtgspoileralert.R
import me.alejandro.mtgspoileralert.databinding.CardDialogBinding

class CardDialog(activity: FragmentActivity) : Dialog(activity) {

    private lateinit var binding: CardDialogBinding
    private lateinit var viewModel: CardDialogViewModel

    init {
        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_dialog, null, false)
        setContentView(binding.root)
    }


    fun setViewModel(viewModel: CardDialogViewModel) {
        this.viewModel = viewModel
        binding.viewModel = this.viewModel
    }

    fun loadCardImage(url: String) {
        viewModel.loadImage(url)
    }


}
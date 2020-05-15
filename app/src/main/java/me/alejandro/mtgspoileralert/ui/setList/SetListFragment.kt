package me.alejandro.mtgspoileralert.ui.setList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import me.alejandro.mtgspoileralert.R

import me.alejandro.mtgspoileralert.databinding.FragmentSetListBinding
import me.alejandro.mtgspoileralert.injection.viewModelFactory

class SetListFragment : Fragment() {
    private lateinit var binding: FragmentSetListBinding
    private lateinit var viewModel: SetListAndroidViewModel
    private lateinit var progressbar: ProgressBar

    private var errorSnackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_list, container, false)
        binding.lifecycleOwner = this

        val root = binding.root
        binding.setList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory { SetListAndroidViewModel(requireActivity().application) }).get(
            SetListAndroidViewModel::class.java
        )
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewModel = viewModel
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressbar = view.findViewById(R.id.set_progress_bar)
    }

    private fun showError(@StringRes errorMessage: Int){
        errorSnackBar =
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE).apply {
                setAction(R.string.retry, viewModel.errorClickListener)
                show()
            }
    }

    private fun hideError(){
        errorSnackBar?.dismiss()
    }
}
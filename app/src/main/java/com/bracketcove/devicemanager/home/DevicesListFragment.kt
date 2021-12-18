package com.bracketcove.devicemanager.home

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bracketcove.devicemanager.databinding.FragmentDevicesListBinding
import com.bracketcove.devicemanager.di.buildDeviceListPresenter
import com.bracketcove.devicemanager.domain.Device

/**
 * A fragment representing a list of Items.
 */
class DevicesListFragment : Fragment(), IDeviceListContract.View {
    private var _binding: FragmentDevicesListBinding? = null
    private val binding get() = _binding!!

    private val vm: DeviceListViewModel by viewModels()
    private lateinit var presenter: IDeviceListContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDevicesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.subShowPrompt = { binding.messageText.text = it }
        vm.subShowLoading = { binding.loadingScreen.rootLoadingScreen
            .visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        binding.textInputSearch.apply {
            //cover both hard and soft keyboards
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.onEvent(DeviceListEvent.OnSearchTextInput(text.toString()))
                    true
                } else false
            }

            setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    presenter.onEvent(DeviceListEvent.OnSearchTextInput(text.toString()))
                    true
                } else false
            }
        }

    }

    override fun showMessage(message: String)
            = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

    override fun onStart() {
        super.onStart()
        presenter = buildDeviceListPresenter(
            requireContext(),
            this,
            vm
        )

        val adapter = DeviceAdapter(
            presenter::onEvent
        )

        binding.devList.adapter = adapter

        vm.subDeviceList = {
            adapter.submitList(it)
        }

        presenter.onEvent(DeviceListEvent.OnStart)
    }

    override fun onStop() {
        super.onStop()
        presenter.onEvent(DeviceListEvent.OnStop)
    }

    override fun navigateToDetailFragment(device: Device) = findNavController().navigate(
        DevicesListFragmentDirections
            .actionDeviceDetailFragmentToDevicesListFragment(device)
    )

    //avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        binding.devList.adapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DevicesListFragment()
    }

}
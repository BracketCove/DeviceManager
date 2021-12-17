package com.bracketcove.devicemanager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bracketcove.devicemanager.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide

class DeviceDetailFragment : Fragment(), IDetailContract.View {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val vm: DetailViewModel by viewModels()
    private lateinit var presenter: IDetailContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        //TODO() don't forget to instantiate the presenter

        presenter.onEvent(
            DetailViewEvent.OnStart(
                DeviceDetailFragmentArgs.fromBundle(
                    requireArguments()
                ).device
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.subShowLoading = { binding.loadingScreen.rootLoadingScreen
            .visibility = if (it) View.VISIBLE else View.INVISIBLE
        }
        vm.subName = { binding.textDeviceName.text = it }
        vm.subStatus = { binding.textDeviceStatus.text = it }
        vm.subType = { binding.textDeviceType.text = it }
        vm.subDescription = { binding.textDescription.text = it }
        vm.subImageUrl = {
            Glide.with(this)
                .load(it)
                .centerCrop()
                .into(binding.imageDevice)
        }

        binding.imbDetailBack.setOnClickListener { presenter.onEvent(DetailViewEvent.OnBackPressed) }
    }

    override fun navigateToDeviceList() = findNavController().navigate(
            DeviceDetailFragmentDirections.actionDeviceDetailFragmentToDevicesListFragment()
        )

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = DeviceDetailFragment()
    }
}
package com.bracketcove.devicemanager.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bracketcove.devicemanager.R
import com.bracketcove.devicemanager.databinding.FragmentDetailBinding
import com.bracketcove.devicemanager.di.buildDetailPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

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

        presenter = buildDetailPresenter(
            requireContext(),
            this,
            vm
        )

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
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)

                .placeholder(R.drawable.ic_laptop)
                .into(binding.imageDevice)

        }

        vm.subIconIsFavourite = {
            binding.imbFavouriteIcon.setImageResource(
                if (it) R.drawable.ic_star
                else R.drawable.ic_star_outline
            )
        }

        binding.imbDetailBack.setOnClickListener { presenter.onEvent(DetailViewEvent.OnUpPressed) }
        binding.imbFavouriteIcon.setOnClickListener { presenter.onEvent(DetailViewEvent.OnFavouriteSelected) }

    }

    override fun navigateToDeviceList() = findNavController().navigate(
            DeviceDetailFragmentDirections.actionDeviceDetailFragmentToDevicesListFragment()
        )

    override fun showMessage(message: String)
    = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = DeviceDetailFragment()
    }
}
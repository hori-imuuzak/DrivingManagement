package app.imuuzak.driving_management.ui.organizer.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivityCreateOrganizerBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.domain.repository.ResourceState
import app.imuuzak.driving_management.ui.organizer.viewmodel.CreateOrganizerViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CreateOrganizerActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: CreateOrganizerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as DrivingManagementApp).getComponent().inject(this)

        val binding = DataBindingUtil.setContentView<ActivityCreateOrganizerBinding>(
            this,
            R.layout.activity_create_organizer
        )

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CreateOrganizerViewModel::class.java)

        bind(binding)

        observe()
    }

    private fun bind(binding: ActivityCreateOrganizerBinding) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.uiEvent = object : UIEvent {
            override fun onClickCreate() {
                viewModel.createOrganizer()
            }
        }
    }

    private fun observe() {
        viewModel.createdOrganizerResource.observe(this, Observer {
            when (it.status) {
                ResourceState.Status.SUCCESS -> {
                    showSnackbar(getString(R.string.success_create))
                    finish()
                }
                ResourceState.Status.LOADING -> {
                }
                ResourceState.Status.ERROR -> {
                    print(it.message)
                    showSnackbar(getString(R.string.error_create))
                }
            }
        })
    }

    private fun showSnackbar(str: String) {
        Snackbar.make(findViewById<View>(android.R.id.content), str, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        interface UIEvent {
            fun onClickCreate()
        }
    }
}

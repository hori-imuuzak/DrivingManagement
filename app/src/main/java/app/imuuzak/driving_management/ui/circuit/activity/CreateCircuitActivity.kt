package app.imuuzak.driving_management.ui.circuit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivityCreateCircuitBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.domain.repository.ResourceState
import app.imuuzak.driving_management.ui.circuit.viewmodel.CreateCircuitViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class CreateCircuitActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: CreateCircuitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCreateCircuitBinding>(
            this,
            R.layout.activity_create_circuit
        )

        (application as DrivingManagementApp).getComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CreateCircuitViewModel::class.java)

        bind(binding)
        observe()
    }

    private fun bind(binding: ActivityCreateCircuitBinding) {
        binding.uiEvent = object : UIEvent {
            override fun onClickCreate() {
                viewModel.createCircuit()
            }
        }

        binding.viewModel = viewModel
    }

    private fun observe() {
        viewModel.circuitResource.observe(this, Observer {
            when (it.status) {
                ResourceState.Status.SUCCESS -> {
                    showSnackbar(getString(R.string.success_create))
                    finish()
                }
                ResourceState.Status.LOADING -> {
                }
                ResourceState.Status.ERROR -> {
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

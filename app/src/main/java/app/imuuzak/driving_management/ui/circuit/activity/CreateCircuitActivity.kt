package app.imuuzak.driving_management.ui.circuit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivityCreateCircuitBinding
import app.imuuzak.driving_management.ui.circuit.viewmodel.CreateCircuitViewModel

class CreateCircuitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCreateCircuitBinding>(
            this,
            R.layout.activity_create_circuit
        )

        bind(binding)
    }

    private fun bind(binding: ActivityCreateCircuitBinding) {
        val viewModel = CreateCircuitViewModel()

        binding.uiEvent = object: UIEvent {
            override fun onClickCreate() {
                // TODO viewModel save()
                finish()
            }
        }

        binding.viewModel = viewModel
    }

    companion object {
        interface UIEvent {
            fun onClickCreate()
        }
    }
}

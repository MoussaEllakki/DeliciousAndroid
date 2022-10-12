package moussa.ellakki

import AllClasses.ViewModelID
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import moussa.ellakki.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTakeOrder.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_tablesFragment)
        }

        binding.goToKitchenButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_kitchenFragment)
        }

        binding.buttonGetPaid.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_getPaidFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
            }
        })

        binding.logOutButton.setOnClickListener {

            view.findNavController().navigate((R.id.action_mainFragment_to_signInFragment2))
        }


    }





}
package moussa.ellakki

import AllClasses.IsRightRestaurantID
import AllClasses.Message
import AllClasses.Table
import AllClasses.ViewModelID
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

import moussa.ellakki.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding
    val model: ViewModelID by activityViewModels()
    var message = Message()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.isThereREstaurantID.value = IsRightRestaurantID.maybe

        val isThereRestaurantIDObserver = Observer<IsRightRestaurantID> {

            if (it == IsRightRestaurantID.yes) {
                model.getAllt(model.restaurantID)
                binding.restaurantIDEditText.text.clear()
                view.findNavController().navigate(R.id.action_signInFragment_to_mainFragment)

            } else if (it == IsRightRestaurantID.no) {
                message.sendMsg("Wrong ID! Restaurant ID is: 0000", requireActivity())
            }
        }
        model.isThereREstaurantID.observe(viewLifecycleOwner, isThereRestaurantIDObserver)
        binding.buttonEnter.setOnClickListener {
            val restaurangId = binding.restaurantIDEditText.text.toString()

            if (restaurangId.isEmpty()) {
                message.sendMsg("Type restaurant ID first! Restaurant ID is: 0000", requireActivity())
                return@setOnClickListener
            }
            model.restaurantID = restaurangId
            model.controllid(restaurangId)
        }
    }
}
package moussa.ellakki

import AllClasses.AdapterInfoToGetPaid
import AllClasses.SendToFirebase
import AllClasses.Table
import AllClasses.ViewModelID
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import moussa.ellakki.databinding.FragmentGetPaidBinding
import moussa.ellakki.databinding.FragmentInfoToGetPaidBinding


class InfoFragmentToGetPaid : Fragment() {


    lateinit var binding: FragmentInfoToGetPaidBinding
    var sendToFirebase = SendToFirebase()
    val model: ViewModelID by activityViewModels()
    var adapterInfoToGetPaid = AdapterInfoToGetPaid()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInfoToGetPaidBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerViewGetPaidInfo = binding.recycklerviewGetPaidInfo
        recyclerViewGetPaidInfo.layoutManager = LinearLayoutManager(requireActivity())
        recyclerViewGetPaidInfo.adapter = adapterInfoToGetPaid

        var table = requireArguments().getParcelable<Table>("table")!!

        binding.tablePriceTextview.text =
            "Table " + table.tableNumber + " Sum " + table.wholesum.toString()
        adapterInfoToGetPaid.guests = table.guests

        binding.buttonGetPaid.setOnClickListener {

            var tablenumberToInt = table.tableNumber.toInt()
            var tableNumberToString = (tablenumberToInt - 1).toString()

            sendToFirebase.removeBooking(tableNumberToString, model.restaurantID)
            view.findNavController().popBackStack(R.id.mainFragment, false)
        }
    }
}
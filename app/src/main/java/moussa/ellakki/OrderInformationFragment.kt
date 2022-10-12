package moussa.ellakki

import AllClasses.*
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class OrderInformationFragment : Fragment() {

    val model: ViewModelID by activityViewModels()
    var kitchenAdapter = KitchenAdapter()
    lateinit var table: Table
    var sendToFirebase = SendToFirebase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerViewOrderFragment = view.findViewById<RecyclerView>(R.id.RV_i_orderinformationFragment)
        recyclerViewOrderFragment.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewOrderFragment.adapter = kitchenAdapter

        table = requireArguments().getParcelable<Table>("table")!!
        kitchenAdapter.table = table

        view.findViewById<Button>(R.id.button_order_finished).setOnClickListener {
            var orderdone = true

            for(done_guest in table.guests)
            {
                for(done_order in done_guest.orders)
                {
                    if(done_order.finished == false)
                    {
                        orderdone = false
                    }
                }
            }

            if(orderdone)
            {
                sendToFirebase.finishOrder(table.tableNumber, model.restaurantID)
                view.findNavController().popBackStack()

            } else {

             sendMsg(requireActivity(), view)
            }
        }
    }

    fun sendMsg( ctx: Context, view : View) {

        val builder = AlertDialog.Builder(ctx)
        builder.setTitle("Message")
        builder.setMessage("Are you sure you want to complete the order?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->

            sendToFirebase.finishOrder(table.tableNumber, model.restaurantID)
            view.findNavController().popBackStack()

        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->

        }
        builder.show()
    }
}
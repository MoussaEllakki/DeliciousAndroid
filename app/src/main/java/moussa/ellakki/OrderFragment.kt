package moussa.ellakki

import AllClasses.*
import android.content.Context
import android.content.DialogInterface


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import moussa.ellakki.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    var tableNumber = ""
    var guest = Guest()
    var table = Table()
    var message = Message()
    var drinkAdapter = DrinkAdapter()
    var extraAdapter = ExtraAdapter()
    var dishesAdapter = DishesAdapter()
    var sendToFirebase = SendToFirebase()
    lateinit var binding: FragmentOrderBinding
    lateinit var orderRecyclerView: RecyclerView
    var guestOrdersAdapter = GuestOrdersAdapter()
    val model: ViewModelID by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tableNumber = requireArguments().getString("tableNumber").toString()
        showMenue()
        showPrices()

        binding.buttonNext.setOnClickListener {
            if (guest.sum == 0.0) {
                message.sendMsg(
                    "Guest " + (table.guests.size).toString() + " hasn't ordered",
                    requireActivity()
                )
            } else {
                guest.filWholeOrder()
                var removeGuest = Guest()
                guest.guestnumber = (table.guests.size).toString()
                guest = removeGuest
                table.guests.add(guest)
                showPrices()
            }
        }
        binding.buttonSendOrder.setOnClickListener {

            if (table.wholesum == 0.0) {
                message.sendMsg("There is no order to send", requireActivity())
            }
            else {

                if (guest.sum == 0.0){
                    sendMsg2("Guest " + (table.guests.size).toString() + " hasn't ordered. Do you want to send order anyway?", view)
                }
                else {
                    sendMsg2(message.sendOrderConfimation,view)
                }
            }
        }
    }


    fun showMenue() {
        table.guests.add(guest)

        var menuRecyclerView = binding.menuRecyclerView
        menuRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        menuRecyclerView.adapter = dishesAdapter
        dishesAdapter.dishes = model.restaurant.dishes
        dishesAdapter.orderFragment = this

        var drinkRecyclerView = binding.drinkRecyclerView
        drinkRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        drinkRecyclerView.adapter = drinkAdapter
        drinkAdapter.drinks = model.restaurant.drinks
        drinkAdapter.orderFragment = this

        var extraRecyclerView = binding.extraRecyclerView
        extraRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        extraRecyclerView.adapter = extraAdapter
        extraAdapter.extras = model.restaurant.extras
        extraAdapter.orderFragment = this

        orderRecyclerView = binding.ordersRecyclerView
        orderRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        orderRecyclerView.adapter = guestOrdersAdapter
        guestOrdersAdapter.orderFragment = this
    }


    fun countPrices() {
        guest.filOleOrder()
        table.countTableSum()
        showPrices()
    }

    fun showPrices() {

        var guestNumber = table.guests.size
        var tableSum = table.wholesum.toString()
        var guestSum = guest.sum.toString()
        guestOrdersAdapter.orders = guest.orders
        guestOrdersAdapter.notifyDataSetChanged()
        binding.priceTableTextview.text  = "Total : " + tableSum
        binding.guestNumberTextview.text = "Guest " + guestNumber + " Sum : " + guestSum
    }

    fun sendMsg2(msg: String, view: View) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Message")
        builder.setMessage(msg)
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->

            if(guest.sum == 0.0){
                table.guests.remove(guest)
            }

            guest.filWholeOrder()
            table.filWholeOrder()
            guest.guestnumber = (table.guests.size).toString()
            var removeGuest = Guest()
            guest = removeGuest
             table.available = false
             table.haveOrder = true
             table.tableNumber = tableNumber
            sendToFirebase.sendOrder(table , tableNumber, model.restaurantID)
            view.findNavController().popBackStack(R.id.mainFragment, false)
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
        }
        builder.show()
    }

}

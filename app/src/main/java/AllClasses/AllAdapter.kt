package AllClasses

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import moussa.ellakki.OrderFragment
import moussa.ellakki.R

class DishesAdapter : RecyclerView.Adapter<DishesViewHolder>() {

    var dishes = mutableListOf<Dish>()
    lateinit var orderFragment: OrderFragment

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        holder.foodType.text = dishes[position].name + " " + dishes[position].pris.toString()

        var order = Order()
        order.name = dishes[position].name!!
        order.pris = dishes[position].pris!!

        holder.foodType.setOnClickListener {
            orderFragment.guest.orders.add(order)
            orderFragment.countPrices()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {

        val viewHolder = DishesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_for_menu, parent, false)
        )
        return viewHolder
    }
}


class DishesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var foodType = view.findViewById<Button>(R.id.food_button)
}


class DrinkAdapter : RecyclerView.Adapter<DrinksViewHolder>() {
    lateinit var orderFragment: OrderFragment
    var drinks = mutableListOf<Drink>()
    override fun getItemCount(): Int {
        return drinks.size
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        holder.foodType.text = drinks[position].name + " " + drinks[position].pris.toString()

        var order = Order()
        order.name = drinks[position].name!!
        order.pris = drinks[position].pris!!

        holder.foodType.setOnClickListener {
            orderFragment.guest.orders.add(order)
            orderFragment.countPrices()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        val viewHolder = DrinksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_for_menu, parent, false)
        )
        return viewHolder
    }
}

class DrinksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var foodType = view.findViewById<Button>(R.id.food_button)
}

class ExtraAdapter : RecyclerView.Adapter<ExtraViewHolder>() {
    var extras = mutableListOf<Extra>()
    lateinit var orderFragment: OrderFragment

    override fun getItemCount(): Int {
        return extras.size
    }

    override fun onBindViewHolder(holder: ExtraViewHolder, position: Int) {
        holder.foodType.text = extras[position].name + " " + extras[position].pris.toString()

        var order = Order()
        order.name = extras[position].name!!
        order.pris = extras[position].pris!!

        holder.foodType.setOnClickListener {
            orderFragment.guest.orders.add(order)
            orderFragment.countPrices()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraViewHolder {
        val viewHolder = ExtraViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_for_menu, parent, false)
        )
        return viewHolder
    }
}

class ExtraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var foodType = view.findViewById<Button>(R.id.food_button)
}

class GuestOrdersAdapter : RecyclerView.Adapter<GuestOrderViewHolder>() {
    var orders = mutableListOf<Order>()
    lateinit var orderFragment: OrderFragment

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: GuestOrderViewHolder, position: Int) {
        holder.orderText.text = orders[position].name
        var order = orders[position]

        holder.deleteOrder.setOnClickListener {
            orderFragment.guest.orders.remove(order)
            orderFragment.countPrices()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestOrderViewHolder {
        val viewHolder = GuestOrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_for_orders_in_kitchen, parent, false)
        )
        return viewHolder
    }
}

class GuestOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var orderText = view.findViewById<TextView>(R.id.order_textview)
    var deleteOrder = view.findViewById<TextView>(R.id.deleteOrder_textview)

}




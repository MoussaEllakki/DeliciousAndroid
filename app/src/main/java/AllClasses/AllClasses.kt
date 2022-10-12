package AllClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


open class Order() {
    var name = ""
    var pris = 0.0
    var finished = false
}

class Dish() : Order() {}

class Drink : Order() {}

class Extra() : Order() {}

class Guest {
    var sum = 0.0
    var orders = mutableListOf<Order>()
    var guestnumber = ""
    var wholeOrder = ""

    fun filWholeOrder() {
        for (order in this.orders) {
            this.wholeOrder += order.name + " - "
        }
    }

    fun filOleOrder() {
        this.sum = 0.0
        for (order in this.orders) {
            this.sum += order.pris
        }
    }
}

@Parcelize
class Table : Parcelable {
    var available = true
    var wholesum = 0.0
    var tableNumber = ""
    var orderFinished = false
    var haveOrder = false
    var guests = mutableListOf<Guest>()
    var tableorders = mutableListOf<Order>()

 fun countTableSum() {
        this.wholesum = 0.0
        for (guest in guests) {
            this.wholesum += guest.sum
        }
 }

    fun filWholeOrder() {
        for (guest in this.guests) {

            for (guestOrder in guest.orders) {
                this.tableorders.add(guestOrder)
            }
        }
    }
}

class Restaurant {
    var tables = mutableListOf<Table>()
    var dishes = mutableListOf<Dish>()
    var drinks = mutableListOf<Drink>()
    var extras = mutableListOf<Extra>()
    var restaurantID = ""
}





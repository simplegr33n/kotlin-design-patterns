package structural_patterns._09_proxy

interface IShipment {
    // Parcel represented by a pair of content (String) and  cartesian location (Pair<Double,Double>)
    fun dispatchParcel(parcel: Pair<String, Pair<Double, Double>>)
}

class SortingFacility(val name: String, var isWarehouseFull: Boolean, var location: Pair<Double, Double>) : IShipment {

    override fun dispatchParcel(parcel: Pair<String, Pair<Double, Double>>) {
        println(
            "$name Sorting Facility...\n" +
                    "  [] handling ${parcel.first} to ${parcel.second}"
        )
    }
}

class ParcelDispatcher : IShipment {

    // Create list of facilities
    private var facilities = listOf(
        SortingFacility("North", true, 0.toDouble() to 10.toDouble()),
        SortingFacility("North West", false, (-10).toDouble() to 10.toDouble()),
        SortingFacility("North East", false, 10.toDouble() to 10.toDouble()),
        SortingFacility("South", false, 0.toDouble() to (-10).toDouble()),
        SortingFacility("South West", true, (-10).toDouble() to (-10).toDouble()),
        SortingFacility("South East", false, 10.toDouble() to (-10).toDouble()),
        SortingFacility("West", true, (-10).toDouble() to 0.toDouble()),
        SortingFacility("East", false, 10.toDouble() to 0.toDouble())
    )

    override fun dispatchParcel(parcel: Pair<String, Pair<Double, Double>>) {
        // Initialize nearest facility and low distance as first facility in list
        var nearestDeliveryFacility: SortingFacility? = null
        var lowDistance: Double? = null

        // Find nearest facility to parcel destination which is not full
        for (facility in facilities) {
            if (!facility.isWarehouseFull) {
                val dist: Double = distanceCalculator(
                    facility.location.first, facility.location.second,
                    parcel.second.first, parcel.second.second
                )

                if (lowDistance == null || dist < lowDistance) {
                    nearestDeliveryFacility = facility
                    lowDistance = dist

                }
            }
        }
        nearestDeliveryFacility?.dispatchParcel(parcel)
    }

}

fun distanceCalculator(parcelX: Double, parcelY: Double, facilityX: Double, facilityY: Double): Double {
    return Math.sqrt((facilityY - parcelY) * (facilityY - parcelY) + (facilityX - parcelX) * (facilityX - parcelX))
}


fun main() {
    // CREATE DISPATCHER
    val parcelDispatcher = ParcelDispatcher()

    // CREATE PARCEL
    val parcel01: Pair<String, Pair<Double, Double>> = "SmartPhone" to (1.toDouble() to (-6).toDouble())
    val parcel02: Pair<String, Pair<Double, Double>> = "Flashlight" to ((-10).toDouble() to 1.toDouble())
    val parcel03: Pair<String, Pair<Double, Double>> = "Umbrella" to (6.toDouble() to (-7).toDouble())

    // SEND PARCEL
    parcelDispatcher.dispatchParcel(parcel01)
    parcelDispatcher.dispatchParcel(parcel02)
    parcelDispatcher.dispatchParcel(parcel03)
}
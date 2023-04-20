package com.msla_mac.lamrecordscrud

class RecordsItem (var recordID: String, var name: String, var description: String,
                   var price: Int, var rating: Int, var dateModified: String, var dateCreated : String  ) {

    fun toCSV(): String {
        return "$recordID, $name, $description, $price, $rating, $dateModified, $dateCreated"
    }

    override fun toString(): String {
        return "$name : $description"
    }


}
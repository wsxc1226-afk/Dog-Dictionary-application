package edu.skku.cs.project

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dog")
data class dogdata (
    @PrimaryKey(autoGenerate = true)
                  val id: Int,
                  val weight: String?=null,
                  val height: String?=null,
                  val name: String,
                  val breed_group: String?=null,
                  val life_span: String?=null,
                  val temperament: String?=null,
                  val imageurl: String?=null
                    ){
    var checked =false

}
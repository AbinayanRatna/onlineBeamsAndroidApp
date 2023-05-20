package com.example.onlinebeamsandroidapp

data class CategoryClassOB(
    val category_Name:String?,
    val category_Image:Int?
)

data class ItemClass(
    val item_Id:String?="",
    val item_Name:String?="",
    val item_Image:String?="",
    val item_Warrenty:String?="",
    val item_Descrip:String?="",
    val item_Price:String?=""
)
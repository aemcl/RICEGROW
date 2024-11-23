package com.example.ricegrow

import androidx.annotation.DrawableRes

data class Information(
     var name: String,
    @DrawableRes var image: Int,
     var description: String)


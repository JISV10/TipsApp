package com.example.tipsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Agrocards(

    @StringRes val tipNumber: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int

)

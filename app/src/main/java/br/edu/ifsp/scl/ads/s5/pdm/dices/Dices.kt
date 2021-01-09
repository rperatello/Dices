package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dices (
        var dice1Image: String,
        var dice2Image: String
): Parcelable
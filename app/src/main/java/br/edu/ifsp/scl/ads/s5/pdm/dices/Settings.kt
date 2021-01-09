package br.edu.ifsp.scl.ads.s5.pdm.dices

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Settings (var doubleDices: Boolean, var numFaces: Int): Parcelable
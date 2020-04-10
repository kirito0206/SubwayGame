package com.example.subwaygame.data

import org.litepal.crud.LitePalSupport

class Flower(
    var name : String = "",
    var waterNumber : Int = 0,
    var cutFunction: Int = 0,
    var fertilizerFunction : Int = 0
) : LitePalSupport(){}
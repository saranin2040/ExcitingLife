package com.example.excitinglife.model

import com.example.excitinglife.model.modelitems.Stopwatch
import com.example.excitinglife.multicontainer.container.AbstractMulticontainer

class Model : AbstractMulticontainer() {
    companion object {
        fun CreateStopwatch() : Stopwatch {
            return Stopwatch()
        }
    }

    fun visit(stopwatch : Stopwatch) {
        stopwatchList.add(stopwatch)
    }

    private var stopwatchList : MutableList<Stopwatch> = ArrayList<Stopwatch>()
}
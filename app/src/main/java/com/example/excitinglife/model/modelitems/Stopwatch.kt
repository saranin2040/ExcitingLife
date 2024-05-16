package com.example.excitinglife.model.modelitems

import com.example.excitinglife.multicontainer.container.IMulticontainer
import com.example.excitinglife.multicontainer.item.IMulticontainerItem

class Stopwatch : IMulticontainerItem {
    final override fun visit(container: IMulticontainer) {
        container.visit(this)
    }

    private var msec : Short = 0
    private var sec : Short = 0
    private var min : Short = 0
    private var hrs : Short = 0
    private var days : Short = 0
}
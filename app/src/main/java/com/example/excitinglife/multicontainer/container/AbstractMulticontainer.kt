package com.example.excitinglife.multicontainer.container

import com.example.excitinglife.multicontainer.item.IMulticontainerItem
import com.example.excitinglife.multicontainer.item.IMulticontainerItem as ContainerItem

abstract class AbstractMulticontainer : IMulticontainer {
    final override fun visit(item: IMulticontainerItem) {
        item.visit(this)
    }
}
package com.example.excitinglife.multicontainer.container

import com.example.excitinglife.multicontainer.item.IMulticontainerItem

interface IMulticontainer {
    abstract fun visit(item : IMulticontainerItem)
}
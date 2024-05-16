package com.example.excitinglife.multicontainer.item

import com.example.excitinglife.multicontainer.container.IMulticontainer

interface IMulticontainerItem {
    abstract fun visit(container : IMulticontainer)
}
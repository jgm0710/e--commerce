package com.example.ecommerce.application.port.delivery.out

import com.example.ecommerce.domain.delivery.ShippingAddress

interface SaveShippingAddressPort {

    fun save(shippingAddress: ShippingAddress) : ShippingAddress
}

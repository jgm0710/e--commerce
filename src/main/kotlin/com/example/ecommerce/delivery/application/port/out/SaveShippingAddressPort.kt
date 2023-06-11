package com.example.ecommerce.delivery.application.port.out

import com.example.ecommerce.delivery.domain.ShippingAddress

interface SaveShippingAddressPort {

    fun save(shippingAddress: ShippingAddress) : ShippingAddress
}

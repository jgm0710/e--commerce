package com.example.ecommerce.domain.delivery

import com.example.ecommerce.global.domain.Aggregate

class ShippingAddress(
    memberId: Long,
    deliveryAddress: DeliveryAddress,
) : Aggregate() {

    lateinit var id: ShippingAddressId
}

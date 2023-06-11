package com.example.ecommerce.delivery.domain

import com.example.ecommerce.global.domain.AbstractAggregate

class ShippingAddress(
    memberId: Long,
    deliveryAddress: DeliveryAddress
) : AbstractAggregate<ShippingAddressId>()

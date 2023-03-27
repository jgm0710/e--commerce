package com.example.ecommerce.domain.delivery

import com.example.ecommerce.global.domain.AbstractAggregate

class ShippingAddress(
    memberId: Long,
    deliveryAddress: DeliveryAddress
) : AbstractAggregate<ShippingAddressId>()
